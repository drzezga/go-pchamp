package tp.feature.replay.requestHandlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tp.communication.MessageStatus;
import tp.communication.MessageType;
import tp.communication.RequestMessageHandler;
import tp.feature.client.Client;
import tp.feature.game.GameState;
import tp.feature.game.persistence.GameRecord;
import tp.feature.game.persistence.ReplayRepository;
import tp.model.Board;
import tp.model.Move;
import tp.model.Piece;
import tp.model.Position;
import tp.model.messages.request.RequestReplayGet;
import tp.model.messages.response.ResponseGameMove;
import tp.model.messages.response.ResponseReplayGet;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReplayGetHandler implements RequestMessageHandler<RequestReplayGet> {

    private final ReplayRepository replayRepository;

    @Autowired
    public ReplayGetHandler(ReplayRepository replayRepository) {
        this.replayRepository = replayRepository;
    }

    @Override
    public void onMessage(RequestReplayGet message, Client sender) {
        String id = message.getContent().getId();

        GameRecord record = replayRepository.findById(id).get();

        List<ResponseReplayGet.BoardState> boardStates = simulateGame(record);
        List<Position> movePositions = record.getMoves().stream().map(Move::getPosition).toList();

        ResponseReplayGet.Content content = new ResponseReplayGet.Content();

        content.setId(record.getId());
        content.setName(record.getName());
        content.setPlayers(record.getPlayers());
        content.setMoves(movePositions);
        content.setGameSettings(record.getGameSettings());
        content.setBoardStates(boardStates);

        sender.getMessageChannel().sendResponse(new ResponseReplayGet(content));
    }

    private List<ResponseReplayGet.BoardState> simulateGame(GameRecord record) {
        ArrayList<ResponseReplayGet.BoardState> boardStates = new ArrayList<>();

        GameState game = new GameState(record.getGameSettings());

        for(Move move : record.getMoves()) {
            boardStates.add(convertBoardToMessageFormat(game.getCurrentBoardState()));
            game.makeMove(move);
        }
        boardStates.add(convertBoardToMessageFormat(game.getCurrentBoardState()));

        return boardStates;
    }

    private ResponseReplayGet.BoardState convertBoardToMessageFormat(Board state) {
        ResponseReplayGet.BoardState boardState = new ResponseReplayGet.BoardState();

        boardState.setBlack(findAllPositionsByPiece(state, Piece.BLACK));
        boardState.setWhite(findAllPositionsByPiece(state, Piece.WHITE));

        return boardState;
    }

    private List<Position> findAllPositionsByPiece(Board board, Piece piece) {
        ArrayList<Position> positions = new ArrayList<>();

        for(int x = 0; x < board.getSize(); x++) {
            for(int y = 0; y < board.getSize(); y++) {
                if(board.getPiece(x, y) == piece) {
                    positions.add(new Position(x, y));
                }
            }
        }

        return positions;
    }


    @Override
    public MessageType getMessageType() {
        return MessageType.REPLAY_GET;
    }
}
