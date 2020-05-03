package tk.solex.api.model;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="message")
public class Message {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long Id;

    private String body;

    private Date dateTime;

    @OneToOne
    private Conversation conversation;

    @OneToOne
    private User sender;


}