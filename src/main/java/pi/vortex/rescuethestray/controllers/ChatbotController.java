package pi.vortex.rescuethestray.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pi.vortex.rescuethestray.services.ChatbotService;

@RestController
public class ChatbotController {

    @Autowired
    private ChatbotService chatbotService;

    @PostMapping("/chatbot")
    public ResponseEntity<String> chatbot(@RequestBody String message) {
        String response = chatbotService.sendMessageToChatbot(message);
        return ResponseEntity.ok(response);
    }
}
