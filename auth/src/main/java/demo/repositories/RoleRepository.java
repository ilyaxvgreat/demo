package demo.repositories;

import demo.model.Role;
import demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> getRolesByUsers(User user);
}
