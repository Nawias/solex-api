package tk.solex.api.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="category")
public class Category {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long Id;

    private String name;

    private long parentId;


}