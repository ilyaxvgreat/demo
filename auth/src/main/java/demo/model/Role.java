package demo.model;

import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
@EntityListeners(AuditingEntityListener.class)
public class Role implements GrantedAuthority {

    @Id
    @Getter
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Column(name = "role_name", unique = true)
    private String authority;

    @ManyToMany(mappedBy = "authorities")
    private Set<User> users;

    @Override
    public String getAuthority() {
        return authority;
    }

}
