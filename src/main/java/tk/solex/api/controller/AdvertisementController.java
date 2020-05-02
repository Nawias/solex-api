package tk.solex.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdvertisementController {
    @RequestMapping("/nowe-ogloszenie")
    public String newAd() {
        return "<html><h1>New Ad Page</h1></html>";
    }

    @RequestMapping("/edytuj-ogloszenie")
    public String editAd() {
        return "<html><h1>Edit Ad Page</h1></html>";
    }
}
