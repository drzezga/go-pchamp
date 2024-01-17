package tp.feature.game;

import lombok.Data;
import tp.feature.game.core.rules.RuleValidator;
import tp.model.Board;
import tp.model.Move;
import tp.game.core.rules.*;
import tp.feature.player.Player;

import java.util.ArrayList;

@Data
public class Game {
    private Board board;
    private final RuleValidator rules = new RuleValidator();
    private ArrayList<Move> moves = new ArrayList<>();

    private Player whitePlayer;
    private Player blackPlayer;


//    private final Map<Piece, String> playerPieces;
//
//    public Game(Board board) {
//        this.board = board;
//        this.playerPieces = new HashMap<>();
//    }

//    public void addStandardRuleset() {
//        rules.addRule(new MustPlayWithinBounds());
//        rules.addRule(new CannotPlaceStoneOnOccupiedTile());
//        rules.addRule(new PlayingAddsAStone());
//    }

//    public void makeMove(Move move) throws RuleBrokenException {
//        this.board = rules.validate(board.clone(), move);
//        this.moves.add(move);
//        for (GameEventListener listener : listeners) {
//            listener.opponentPiecePlayed(move.position(), move.piece());
//        }
////        System.out.println(board.getPiece(move.position()));
//    }

//    public Optional<Piece> getPlayerPiece(String player) {
//        return this.playerPieces
//                .entrySet()
//                .stream()
//                .filter(e -> e.getValue().equals(player))
//                .map(Map.Entry::getKey)
//                .findFirst();
//    }

//    public Optional<String> getPiecePlayer(Piece piece) {
//        return Optional.ofNullable(this.playerPieces.get(piece));
//    }

//    public GameAPI addPlayer(Player player, Piece piece) {
//        playerPieces.put(piece, player.getName());
//        listeners.add(player);
//        return new GameAPI(piece, this);
//    }

//    public void startGame() {
//        for (GameEventListener listener : listeners) {
//            listener.gameStarted();
//        }
//    }
}
