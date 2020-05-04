package tk.solex.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tk.solex.api.dao.AdvertisementDAO;
import tk.solex.api.model.Advertisement;
import tk.solex.api.service.FileStorageService;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@RestController
public class AdvertisementController {

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private AdvertisementDAO advertisementDAO;


    @PreAuthorize("hasAnyRole('USER,ADMIN')")
    @GetMapping("/nowe-ogloszenie")
    public String newAd() {
        return "<html><h1>New Ad Page</h1></html>";
    }


    @PreAuthorize("hasAnyRole('USER,ADMIN')")
    @GetMapping("/edytuj-ogloszenie")
    public String editAd() {
        return "<html><h1>Edit Ad Page</h1></html>";
    }

    @PreAuthorize("hasAnyRole('USER,ADMIN')")
    @RequestMapping(value = "/nowe-ogloszenie",method = RequestMethod.POST, consumes = {"multipart/form-data"})
    public String newAd(@RequestParam("model") String model, @RequestParam("files") MultipartFile[] files) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Advertisement advertisement = mapper.readValue(model, Advertisement.class);

        String photos = "[";


        try {
            for(MultipartFile file : files) {
                if(photos.equals("["))
                    photos += fileStorageService.upload(file);
                else
                    photos += "," + fileStorageService.upload(file);
            }
            photos += "]";
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "Failed to upload the file";
        }
        advertisement.setPhotos(photos);
        advertisementDAO.save(advertisement);
        return "Uploaded";
    }
}
