package dk.binfo.services;

import dk.binfo.models.User;

import java.util.List;
/**
 *  @author Patrick Klæbel
 *  @author Jens Bäckvall
 *  @author Steen Petersen
 *  @author Morten Olsen
 *
 */
public interface UserService {
	User findUserByEmail(String email);
	User updateUserSettings(User user);
	User deleteUser(String email);
	User update(User user);
	List<User> findAll();
	void register(User user);
	void adminRegisterUser(User user);
}
