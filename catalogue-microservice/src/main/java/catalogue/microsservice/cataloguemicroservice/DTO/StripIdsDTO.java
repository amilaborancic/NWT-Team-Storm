package catalogue.microsservice.cataloguemicroservice.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class StripIdsDTO {
    List<Long> idStripova;

    public StripIdsDTO(@JsonProperty List<Long> idStripova) {
        this.idStripova = idStripova;
    }
    public StripIdsDTO(){}

    public void setIdStripova(List<Long> idStripova) {
        this.idStripova = idStripova;
    }

    public List<Long> getIdStripova() {
        return idStripova;
    }
}
