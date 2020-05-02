package tk.solex.api.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="conversation")
public class Conversation {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long Id;

    @ManyToOne
    private Advertisement advertisement;

    @OneToOne
    private User client;

    @OneToMany
    private List<Message> messages;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public Advertisement getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(Advertisement advertisement) {
        this.advertisement = advertisement;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }
}
