package tk.solex.api.dao;

import org.springframework.data.repository.CrudRepository;
import tk.solex.api.model.User;


public interface UserDAO extends CrudRepository<User, Long> {

}
