package user.usermicroservice.Models;

import org.hibernate.annotations.Proxy;
import user.usermicroservice.RoleName;

import javax.persistence.*;

@Entity
@Proxy(lazy = false)
@Table(name="role")
public class Role {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    private RoleName role;

    public Role(){}
    public Role(RoleName role){
        this.role=role;
    }

    public void setRoleName(RoleName role){
        this.role=role;
    }
    public RoleName getRoleName(){
        return role;
    }
    public void setId(Long id){
        this.id=id;
    }
    public Long getId(){
        return id;
    }

}
