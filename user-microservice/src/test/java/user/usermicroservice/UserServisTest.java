package user.usermicroservice;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Optional;
import user.usermicroservice.Models.User;
import user.usermicroservice.DTO.UserRatingDTO;
import user.usermicroservice.Servisi.UserServis;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServisTest {

    @Autowired
    UserServis userServis;

    @Test
    public void postojiEmail() throws Exception{
        assertThat(userServis.postojiEmail("ahmo.arsenal@gmail.com")).isTrue();

        assertThat(userServis.postojiEmail("nema@gmail.com")).isFalse();


    }
    
	@Test
	public void updateUser() throws Exception {
		Optional<User> user = userServis.findUserById(Long.valueOf(1));
		Integer br_losih_reviewa = user.get().getBroj_losih_reviewa();
		Integer ukupno_reviewa = user.get().getUkupno_reviewa();
		UserRatingDTO ur = new UserRatingDTO(user.get().getId(), ukupno_reviewa + 1, br_losih_reviewa + 1);
		userServis.updateUser(ur);
		assertThat(userServis.findUserById(Long.valueOf(1)).get().getBroj_losih_reviewa())
				.isEqualTo(ur.getBroj_losih_reviewa());
		assertThat(userServis.findUserById(Long.valueOf(1)).get().getUkupno_reviewa())
				.isEqualTo(ur.getUkupno_reviewa());
	}
    
}
