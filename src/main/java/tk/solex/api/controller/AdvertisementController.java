package tk.solex.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tk.solex.api.dao.AdvertisementDAO;
import tk.solex.api.dao.CategoryDAO;
import tk.solex.api.dao.UserDAO;
import tk.solex.api.model.Advertisement;
import tk.solex.api.model.Category;
import tk.solex.api.model.User;
import tk.solex.api.service.FileStorageService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Optional;

@RestController
public class AdvertisementController {

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private AdvertisementDAO advertisementDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private CategoryDAO categoryDAO;


    @PreAuthorize("hasAnyRole('USER,ADMIN')")
    @GetMapping("/nowe-ogloszenie")
    public String newAd() {
        return "<html><h1>New Ad Page</h1></html>";
    }


    @PreAuthorize("hasAnyRole('USER,ADMIN')")
    @GetMapping("/edytuj-ogloszenie")
    public String editAd(@RequestParam Long id) {
        return "<html><h1>Edit Ad Page</h1><body>"+
                advertisementDAO.findById(id).get().toString()
                +"</body></html>";
    }

    @PreAuthorize("hasAnyRole('USER,ADMIN')")
    @ResponseBody
    @RequestMapping(value = "/nowe-ogloszenie",method = RequestMethod.POST, consumes = {"multipart/form-data"})
    public String newAd(HttpServletRequest request, @RequestParam("model") String model, @RequestParam("files") MultipartFile[] files) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Advertisement advertisement = mapper.readValue(model, Advertisement.class);
        try {
            advertisement.setPhotos(uploadPhotos(files));
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "Failed to upload the file";
        }
        advertisement.setUser(getUser(request));
        advertisement.setCategory(getCategoryFromJson(model));

        advertisementDAO.save(advertisement);
        return "Uploaded";
    }

    private String uploadPhotos(MultipartFile[] files)throws IOException,NoSuchAlgorithmException{
        String photos = "[";
        for(MultipartFile file : files) {
            if(photos.equals("["))
                photos += fileStorageService.upload(file);
            else
                photos += "," + fileStorageService.upload(file);
        }
        photos += "]";
        return photos;
    }

    private User getUser(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        Optional<User> optionalUser = userDAO.findByUsername(principal.getName());
        return optionalUser.get();
    }

    private Category getCategoryFromJson(@RequestParam("model") String model) {
        JSONParser parser = new JSONParser(model);
        LinkedHashMap<String,Object> messageJson = null;
        try {
            messageJson = parser.parseObject();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return categoryDAO.getOne((Long)messageJson.get("categoryId"));
    }
}
