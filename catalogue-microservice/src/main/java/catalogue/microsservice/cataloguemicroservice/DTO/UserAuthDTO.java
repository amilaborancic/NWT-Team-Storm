package catalogue.microsservice.cataloguemicroservice.DTO;

public class UserAuthDTO {
    private Long id;
    private String username;
    private String password;
    private String rola;

    public String getRola() {
        return rola;
    }

    public void setRola(String rola) {
        this.rola = rola;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserAuthDTO(Long id, String username, String password, String rola) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.rola = rola;
    }
    public UserAuthDTO(){}
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
