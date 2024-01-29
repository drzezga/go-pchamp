package tp.feature.game;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import tp.feature.client.Client;
import tp.feature.client.ClientRepository;
import tp.feature.lobby.Lobby;
import tp.model.Board;
import tp.model.Move;
import tp.model.Piece;
import tp.model.Position;
import tp.model.messages.response.ResponseMessage;
import tp.model.messages.shared.GameSettings;

import java.util.*;
import java.util.stream.Collectors;

@Log
@Controller
public class GameController {
    private HashMap<Client, Game> clientToGameMap = new HashMap<>();
    private final ClientRepository clientRepository;

    @Autowired
    public GameController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Game startGameFromLobby(Lobby lobby, GameSettings settings) {
        boolean isPlayingAgainstBot = settings.getBotOpponent();
        if(isPlayingAgainstBot) {
            return startGameAgainstBot(lobby, settings);
        } else {
            return startGameAgainstHuman(lobby, settings);
        }
    }

    public Game getGameByClient(Client client) {
        Game game = clientToGameMap.get(client);

        if(game == null) {
            throw new ClientNotInGameException(client);
        }

        return game;
    }

    public void destroyGame(Game game) {
        Client blackPlayer = clientRepository.getClientByName(game.getBlackPlayerName()).get();
        Client whitePlayer = clientRepository.getClientByName(game.getWhitePlayerName()).get();

        clientToGameMap.remove(blackPlayer);
        clientToGameMap.remove(whitePlayer);
    }

    public List<Position> makeMove(Client player, Position position) {
        Game game = getGameByClient(player);
        Piece playerPiece = getPlayerPiece(game, player);
        Move move = new Move(position, playerPiece);

        game.makeMove(move);

        System.out.println(game.getCurrentBoardState());

        Set<Move> capturedPieces = createMoveSetFromBoard(game.getCurrentBoardState());
        capturedPieces.removeAll(createMoveSetFromBoard(game.getPreviousBoardState()));
        capturedPieces.remove(move);
        return capturedPieces.stream().map(Move::position).collect(Collectors.toList());
    }

    private Set<Move> createMoveSetFromBoard(Board board) {
        var moveSet = new HashSet<Move>();

        for(int x = 0; x < board.getSize(); x++) {
            for(int y = 0; y < board.getSize(); y++) {
                if(board.getPiece(x, y) == null) {
                    continue;
                }

                moveSet.add(new Move(
                        new Position(x, y),
                        board.getPiece(x, y)
                ));
            }
        }

        return moveSet;
    }


    public void broadcastMessageToPlayers(Game game, ResponseMessage<?> message) {
        Client blackClient = clientRepository.getClientByName(game.getBlackPlayerName())
                .orElseThrow(() -> new ClientAbsentFromGameException(game.getBlackPlayerName()));
        Client whiteClient = clientRepository.getClientByName(game.getWhitePlayerName())
                .orElseThrow(() -> new ClientAbsentFromGameException(game.getWhitePlayerName()));

        blackClient.getMessageChannel().sendResponse(message);
        whiteClient.getMessageChannel().sendResponse(message);
    }

    private Game startGameAgainstHuman(Lobby lobby, GameSettings settings) {
        var game = new Game(settings);

        Client host = clientRepository.getClientByName(lobby.getHost().get()).get();
        Client guest = clientRepository.getClientByName(lobby.getGuest().get()).get();

        String hostName = host.getName();
        String guestName = guest.getName();

        if(settings.getStartingPlayer().equals(hostName)) {
            game.setBlackPlayerName(guestName);
            game.setWhitePlayerName(hostName);
        } else {
            game.setWhitePlayerName(hostName);
            game.setBlackPlayerName(guestName);
        }

        clientToGameMap.put(host, game);
        clientToGameMap.put(guest, game);

        return game;
    }

    private Game startGameAgainstBot(Lobby lobby, GameSettings settings) {
        // TODO: Implement playing against a bot
        throw new UnsupportedOperationException("Playing against a bot hasnt yet been implemented");
    }

    private void updateCurrentPlayerTurn(Game game) {
        throw new UnsupportedOperationException("FIX IMPLEMENTATION");

//        Piece nextTurnPiece = switch(game.getCurrentPlayer()) {
//            case WHITE -> Piece.BLACK;
//            case BLACK -> Piece.WHITE;
//        };
//
//        game.setCurrentPlayer(nextTurnPiece);
    }

    private Piece getPlayerPiece(Game game, Client player) {
        String playerName = player.getName();

        if(playerName.equals(game.getWhitePlayerName())) {
            return Piece.WHITE;
        }

        if(playerName.equals(game.getBlackPlayerName())) {
            return Piece.BLACK;
        }

        throw new IllegalArgumentException(
                String.format("Client '%s' is not currently in specified game", player.getName())
        );
    }

    public class ClientNotInGameException extends RuntimeException {
        public ClientNotInGameException(Client client) {
            super(String.format("Client '%s' is not currently in a game", client.getName()));
        }
    }

    public class ClientAbsentFromGameException extends RuntimeException {
        public ClientAbsentFromGameException(String clientName) {
            super(String.format("Client '%s' is not currently in the specified game", clientName));
        }
    }
}
