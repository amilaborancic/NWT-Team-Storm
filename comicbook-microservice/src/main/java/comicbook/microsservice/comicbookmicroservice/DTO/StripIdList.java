package comicbook.microsservice.comicbookmicroservice.DTO;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class StripIdList {
    private List<Long> idStripova;

    public List<Long> getIdStripova() {
        return idStripova;
    }

    public void setIdStripova(List<Long> idStripova) {
        this.idStripova = idStripova;
    }

    public StripIdList(@JsonProperty List<Long> idStripova) {
        this.idStripova = idStripova;
    }
    public StripIdList(){}
}
