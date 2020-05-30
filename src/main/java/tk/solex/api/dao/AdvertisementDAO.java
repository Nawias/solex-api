package tk.solex.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import tk.solex.api.model.Advertisement;

import java.util.List;

public interface AdvertisementDAO extends JpaRepository <Advertisement,Long> {

    List<Advertisement> findByTitleContaining(String title);
}
