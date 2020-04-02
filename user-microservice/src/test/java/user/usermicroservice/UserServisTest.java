package user.usermicroservice;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
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
}
