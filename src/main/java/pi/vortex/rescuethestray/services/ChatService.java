package pi.vortex.rescuethestray.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import io.github.flashvayne.chatgpt.service.ChatgptService;
import lombok.AllArgsConstructor;
import pi.vortex.rescuethestray.entities.BotResponse;
import pi.vortex.rescuethestray.entities.ChatPrompt;
import pi.vortex.rescuethestray.interfaces.IChatService;

@Service
@Slf4j
@AllArgsConstructor
public class ChatService implements IChatService {

    private final ChatgptService chatgptService;

    public ResponseEntity<BotResponse> askBot(ChatPrompt chatObj) {
        String answer = this.chatgptService.sendMessage(chatObj.getPrompt());
        log.debug(answer);
        return new ResponseEntity<>(new BotResponse(answer), HttpStatus.OK);
    }

}
