package pi.vortex.rescuethestray.controllers;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pi.vortex.rescuethestray.entities.BotResponse;
import pi.vortex.rescuethestray.entities.ChatPrompt;
import pi.vortex.rescuethestray.interfaces.IChatService;


@RestController
@AllArgsConstructor
@RequestMapping("/testChat")
public class ChatController {
    private final IChatService testService;

    @PostMapping("/ask-bot")
    public ResponseEntity<BotResponse> talk(@RequestBody ChatPrompt obj) {

        return testService.askBot(obj);
    }
}
