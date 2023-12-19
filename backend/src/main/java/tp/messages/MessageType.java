package tp.messages;

import lombok.Getter;

@Getter
public enum MessageType {
    REGISTER("REGISTER"),
    LOBBY_STATUS("LOBBY_STATUS"),
    GAME_START("GAME_START"),
    GAME_LEAVE("GAME_LEAVE"),
    GAME_TRY_MOVE("GAME_TRY_MOVE"),
    GAME_MOVE("GAME_MOVE"),
    GAME_FINISHED("GAME_FINISHED"),
    REPLAY_LIST("REPLAY_LIST"),
    REPLAY_GET("REPLAY_GET"),
    LOBBY_LIST("LOBBY_LIST");

    private final String msg;

    MessageType(String msg) {
        this.msg = msg;
    }
}
