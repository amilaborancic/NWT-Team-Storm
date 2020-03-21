package catalogue.microsservice.cataloguemicroservice.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="strip")
public class Strip {
    @GeneratedValue
    @Id
    private Long Id;

    @ManyToMany(mappedBy = "stripovi")
    private List<Katalog> katalozi;

    public Long getId() { return Id; }
    public List<Katalog> getKatalozi() { return katalozi; }

    public Strip(){}
}
