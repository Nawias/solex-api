package tk.solex.api.model;
import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long Id;

    @ManyToOne
    private Advertisement advertisement;

    @ManyToOne
    private User reporter;

    private String description;

    private String status;


}
