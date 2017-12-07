package dk.binfo.services;

import dk.binfo.models.Seniority;
import dk.binfo.models.User;

import java.util.List;

public interface UserService {
	User findUserByEmail(String email);
	User updateUserSettings(User user);
	User deleteUser(String email);
	User update(User user);
	List<User> findAll();
	void register(User user, String role);
	void adminRegisterUser(User user);

	Seniority setUserSeniority();
}
