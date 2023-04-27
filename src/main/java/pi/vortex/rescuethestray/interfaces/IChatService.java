package pi.vortex.rescuethestray.interfaces;

import org.springframework.http.ResponseEntity;
import pi.vortex.rescuethestray.entities.BotResponse;
import pi.vortex.rescuethestray.entities.ChatPrompt;

public interface IChatService {
    ResponseEntity<BotResponse> askBot(ChatPrompt chatObj);

}
