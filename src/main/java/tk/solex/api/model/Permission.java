package tk.solex.api.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="permission")
public class Permission {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long Id;

    private String name;

    @ManyToMany
    private Set<Role> roles;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}