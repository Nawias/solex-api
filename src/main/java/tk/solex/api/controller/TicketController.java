package tk.solex.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketController {
    @RequestMapping("/nowe-zgloszenie")
    public String newTicketPage() {
        return "<html><h1>New Ticket Page</h1></html>";
    }

}
