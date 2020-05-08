package user.usermicroservice.Servisi;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import user.usermicroservice.DTO.UserRatingDTO;
import user.usermicroservice.Models.User;
import user.usermicroservice.Repository.UserRepository;
import user.usermicroservice.exception.ApiRequestException;
import user.usermicroservice.grpc.EventSubmission;
import user.usermicroservice.grpc.Events;
import user.usermicroservice.grpc.actionGrpc;

import java.util.List;
import java.util.Optional;

@Service
public class UserServis {

	@Autowired
	UserRepository userRepository;

	@Autowired
	EventSubmission eventSubmission;

	private Long idAdmin = 1000L;
	private Long idLogovanogKorisnika = 500L;

	public Optional<User> findUserById(Long id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isEmpty())
			throw new ApiRequestException("User sa id-jem " + id + " ne postoji!");
		eventSubmission.addEvent(Events.ActionType.GET, "Korisnik po ID-u:"+ id.toString());
		return user;
	}

	public void addNewUser(User user) {

		eventSubmission.addEvent(Events.ActionType.CREATE, "Dodaj novog korisnika.");
		userRepository.save(user);
	}

	public User findUserByUserName(String name) {
		User user = userRepository.findByUserName(name);
		if(user == null) throw new ApiRequestException("Korisnik sa username-om " + name + " ne postoji!");
		eventSubmission.addEvent(Events.ActionType.GET, "Korisnik po user-name.");
		return user;
	}

	public boolean postojiUserName(String userName){
		return userRepository.existsByUserName(userName);
	}

	public boolean postojiEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	//samo za testiranje kroz postman
	public List<User> svi() {
		return userRepository.findAll();
	}

	public void updateUser(UserRatingDTO userRatingInfo) {
		if(userRepository.findById(userRatingInfo.getId()).isPresent()) {
			User korisnik_iz_baze = userRepository.getOne(userRatingInfo.getId());
			korisnik_iz_baze.setBroj_losih_reviewa(userRatingInfo.getBroj_losih_reviewa());
			korisnik_iz_baze.setUkupno_reviewa(userRatingInfo.getUkupno_reviewa());

			String allRaitings = String.valueOf(userRatingInfo.getUkupno_reviewa());
			String negativeRaitings = String.valueOf(userRatingInfo.getBroj_losih_reviewa());

			eventSubmission.addEvent(Events.ActionType.UPDATE, "Korisnik ima ukupno reviewa: " +
					allRaitings +", a od toga je negativnih reviewa: " + negativeRaitings);
			userRepository.save(korisnik_iz_baze);
		}
		else throw new ApiRequestException("User sa id-jem " + userRatingInfo.getId().toString() + " ne postoji!");
	}
	public String getNazivRole(String username){
		return userRepository.findByUserName(username).getRole().getRoleName().toString();
	}
	public Long brojKorisnikaUBazi() {
		return userRepository.count();
	}

	public User singleUser(String username){return userRepository.findByUserName(username);}
}
