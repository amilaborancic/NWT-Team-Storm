package user.usermicroservice.Servisi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import user.usermicroservice.DTO.UserRatingDTO;
import user.usermicroservice.Models.User;
import user.usermicroservice.exception.ApiRequestException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServisTest {

    @Autowired
    UserServis userServis;

    @Test
	public void findUserById() throws Exception{
    	//sve okej
		assertThat(userServis.findUserById((long) 1).get().getUserName().equals("Amila"));
		//ne postoji korisnik sa tim id-jem
		ApiRequestException nemaUsera = assertThrows(
				ApiRequestException.class,
				()->userServis.findUserById((long) 122),
				"Trebalo bi baciti"
		);
		assertThat(nemaUsera.getMessage().contains("ne postoji"));
	}
	@Test
	public void findUserByUserName() throws Exception{
    	//sve okej
		assertThat(userServis.findUserByUserName("Amila").getId().equals((long) 1));
		//user ne postoji
		ApiRequestException nemaUsera = assertThrows(
				ApiRequestException.class,
				()->userServis.findUserByUserName("kjaskd"),
				"Trebalo bi baciti"
		);
		assertThat(nemaUsera.getMessage().contains("ne postoji"));
	}

	@Test
	public void postojiUserName() throws Exception {
    	//ima
		assertThat(userServis.postojiUserName("Mahira")).isTrue();
		//nema
		assertThat(userServis.postojiUserName("alsdmk")).isFalse();
	}

    @Test
    public void postojiEmail() throws Exception{
    	//sve okej
        assertThat(userServis.postojiEmail("ahmo.arsenal@gmail.com")).isTrue();
        //email ne postoji
        assertThat(userServis.postojiEmail("nema@gmail.com")).isFalse();
    }
    
	@Test
	public void updateUser() throws Exception {
		Optional<User> user = userServis.findUserById(Long.valueOf(1));
		Integer br_losih_reviewa = user.get().getBroj_losih_reviewa();
		Integer ukupno_reviewa = user.get().getUkupno_reviewa();
		UserRatingDTO ur = new UserRatingDTO(user.get().getId(), ukupno_reviewa + 1, br_losih_reviewa + 1);
		userServis.updateUser(ur);
		assertThat(userServis.findUserById(1L).get().getBroj_losih_reviewa())
				.isEqualTo(ur.getBroj_losih_reviewa());
		assertThat(userServis.findUserById(1L).get().getUkupno_reviewa())
				.isEqualTo(ur.getUkupno_reviewa());

		ApiRequestException exception = assertThrows(
				ApiRequestException.class,
				() -> userServis.updateUser(new UserRatingDTO(9999L,0,0)));
		assertTrue(exception.getMessage().contains("ne postoji"));
	}
    
}
