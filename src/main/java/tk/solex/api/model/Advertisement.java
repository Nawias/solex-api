package tk.solex.api.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="advertisement")
public class Advertisement {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long Id;

    private String title;

    private String description;

    private String photos; //JSON

    private String phone;

    private String status;

    private Date DateTime;

    @ManyToOne
    private User user;

    @ManyToOne
    private Category category;


}