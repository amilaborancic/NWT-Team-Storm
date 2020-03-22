package catalogue.microsservice.cataloguemicroservice.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="strip")
public class Strip {
    @GeneratedValue
    @Id
    @Column(name="id")
    private Long idStrip;

    @ManyToMany(mappedBy = "stripovi")
    private List<Katalog> katalozi;
    public Long getIdStrip() { return idStrip; }
    public List<Katalog> getKatalozi() { return katalozi; }

    public Strip(){}
}
