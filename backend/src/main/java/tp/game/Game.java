package tp.game;

import lombok.Getter;
import tp.game.core.Board;
import tp.game.core.Move;
import tp.game.core.Piece;
import tp.game.core.rules.*;
import tp.util.Observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Getter
public class Game extends Observer<GameEventListener> {
    private Board board;
    private final RuleValidator rules = new RuleValidator();
    private final ArrayList<Move> moves = new ArrayList<>();
    private final Map<Piece, String> playerPieces;

    public Game(Board board) {
        this.board = board;
        this.playerPieces = new HashMap<>();
    }

    public void addStandardRuleset() {
        rules.addRule(new MustPlayWithinBounds());
        rules.addRule(new CannotPlaceStoneOnOccupiedTile());
        rules.addRule(new PlayingAddsAStone());
    }

    public void makeMove(Move move) throws RuleBrokenException {
        this.board = rules.validate(board.clone(), move);
        this.moves.add(move);
        for (GameEventListener listener : listeners) {
            listener.piecePlayed(move.position(), move.piece());
        }
//        System.out.println(board.getPiece(move.position()));
    }

    public Optional<Piece> getPlayerPiece(String player) {
        return this.playerPieces
                .entrySet()
                .stream()
                .filter(e -> e.getValue().equals(player))
                .map(Map.Entry::getKey)
                .findFirst();
    }

    public Optional<String> getPiecePlayer(Piece piece) {
        return Optional.ofNullable(this.playerPieces.get(piece));
    }

    public GameAPI addPlayer(Player player, Piece piece) {
        playerPieces.put(piece, player.getName());
        listeners.add(player);
        return new GameAPI(piece, this);
    }

    public void startGame() {
        for (GameEventListener listener : listeners) {
            listener.gameStarted();
        }
    }
}
