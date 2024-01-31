package tp.feature.replay.requestHandlers;

import org.springframework.stereotype.Component;
import tp.communication.MessageType;
import tp.communication.RequestMessageHandler;
import tp.feature.client.Client;
import tp.feature.game.GameState;
import tp.feature.game.persistence.GameRecord;
import tp.feature.game.persistence.ReplayRepository;
import tp.model.messages.request.RequestReplayList;
import tp.model.messages.response.ResponseReplayList;

import java.util.List;

@Component
public class ReplayListHandler implements RequestMessageHandler<RequestReplayList> {
    private final ReplayRepository replayRepository;

    public ReplayListHandler(ReplayRepository replayRepository) {
        this.replayRepository = replayRepository;
    }

    @Override
    public void onMessage(RequestReplayList message, Client sender) {
        List<ResponseReplayList.ReplayMetadata> allReplays = replayRepository
                .findAll()
                .stream().map(this::convertGameRecordToMetadata).toList();

        sender.getMessageChannel().sendResponse(new ResponseReplayList(allReplays));
    }

    private ResponseReplayList.ReplayMetadata convertGameRecordToMetadata(GameRecord record) {
        ResponseReplayList.ReplayMetadata replayMetadata = new ResponseReplayList.ReplayMetadata();

        replayMetadata.setId(record.getId());
        replayMetadata.setName(record.getName());
        replayMetadata.setPlayers(record.getPlayers());
        replayMetadata.setStartingPlayer(record.getGameSettings().getStartingPlayer());

        return replayMetadata;
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.REPLAY_LIST;
    }
}
