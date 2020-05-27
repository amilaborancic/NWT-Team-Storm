package catalogue.microsservice.cataloguemicroservice.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="strip")
public class Strip {
    @Id
    @Column(name="id")
    private Long id;

    @ManyToMany(mappedBy = "stripovi")
    private List<Katalog> katalozi;
    public Long getId() { return id;}
    public void setId(Long id) {this.id = id;}
    public List<Katalog> getKatalozi() { return katalozi; }

    public Strip(Long id){
        this.id = id;
    }
    public Strip(){}
}
