package tk.solex.api.model;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

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


}
