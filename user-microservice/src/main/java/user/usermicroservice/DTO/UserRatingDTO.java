package user.usermicroservice.DTO;

public class UserRatingDTO {
	
	private Long id;
	private int broj_losih_reviewa;
	private int ukupno_reviewa;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getBroj_losih_reviewa() {
		return broj_losih_reviewa;
	}
	public void setBroj_losih_reviewa(int broj_losih_reviewa) {
		this.broj_losih_reviewa = broj_losih_reviewa;
	}
	public int getUkupno_reviewa() {
		return ukupno_reviewa;
	}
	public void setUkupno_reviewa(int ukupno_reviewa) {
		this.ukupno_reviewa = ukupno_reviewa;
	}
	
}
