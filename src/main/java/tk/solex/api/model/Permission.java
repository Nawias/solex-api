package tk.solex.api.model;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="permission")
public class Permission {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long Id;

    private String name;

    @ManyToMany
    private Set<Role> roles;

}