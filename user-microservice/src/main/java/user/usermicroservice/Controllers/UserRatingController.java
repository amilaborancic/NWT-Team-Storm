package user.usermicroservice.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import user.usermicroservice.DTO.UserRatingDTO;
import user.usermicroservice.Servisi.UserServis;

@RestController
public class UserRatingController {
	
	@Autowired
	UserServis userServis;
	
	@PutMapping(value="/user/update-rating")
	public void updateUser(@RequestBody UserRatingDTO userRatingInfo) {
		userServis.updateUser(userRatingInfo);
	}
	
}
