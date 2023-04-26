package pi.vortex.rescuethestray.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class ChatbotService {

    @Autowired
    private RestTemplate restTemplate;

    public String sendMessageToChatbot(String message) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<String> request = new HttpEntity<>(message, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:5005/webhooks/rest/webhook",
                HttpMethod.POST,
                request,
                String.class);

        return response.getBody();
    }
}
