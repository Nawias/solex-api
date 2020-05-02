package tk.solex.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConversationController {
    @RequestMapping("/wiadomosci")
    public String conversationPage() {
        return "<html><h1>Conversation Page</h1></html>";
    }

}
