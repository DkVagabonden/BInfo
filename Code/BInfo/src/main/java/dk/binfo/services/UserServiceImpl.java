package dk.binfo.services;

import java.util.*;

import javax.transaction.Transactional;

import dk.binfo.models.Role;
import dk.binfo.models.User;
import dk.binfo.repositories.RoleRepository;
import dk.binfo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *  @author Patrick Klæbel
 *  @author Jens Bäckvall
 *  @author Steen Petersen
 *  @author Morten Olsen
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public User findUserByEmail(String email) {

		return userRepository.findByEmail(email);
	}


	@Override
	@Transactional
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public void register(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(true);
		user.setPhoneNumber(user.getPhoneNumber());
		Role userRole = roleRepository.findByRole("user");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}

	@Override
	@Transactional
	public User deleteUser(String email) {
		User deletedUser = userRepository.findByEmail(email);
		deletedUser.setRoles(null); // Deletes this user in user_role table
		userRepository.delete(deletedUser);
		return deletedUser;
	}

	@Override
	@Transactional
	public User update(User user){
		User updatedUser = userRepository.findByEmail(user.getEmail());
		updatedUser.setName(user.getName());
		updatedUser.setLastName(user.getLastName());
		updatedUser.setPhoneNumber(user.getPhoneNumber());
		updatedUser.setActive(user.isActive());

		return updatedUser;
	}

	@Override
	public void adminRegisterUser(User user) {
		Random ran = new Random();
		String Password = generateString(ran,"qwertyuiopasdfghjklzxcvbnm",8);
		user.setPassword(bCryptPasswordEncoder.encode(Password));
		user.setActive(user.isActive());
		user.setPhoneNumber(user.getPhoneNumber());
		Role userRole = roleRepository.findByRole("user");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}

	@Override
	@Transactional
	public User updateUserSettings(User user){
		User updateUser = userRepository.findByEmail(user.getEmail());
		if(user.getPassword().equalsIgnoreCase("") && user.getPhoneNumber() != null) {
			updateUser.setPhoneNumber(user.getPhoneNumber());
		}
		if(user.getPhoneNumber() == null || user.getPhoneNumber().equalsIgnoreCase("") && user.getPassword() != null) {
			updateUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		}
		if(user.getPhoneNumber() != null && user.getPassword() != null) {
			updateUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			updateUser.setPhoneNumber(user.getPhoneNumber());
		}
		return updateUser;
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(userName);
		List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
		return buildUserForAuthentication(user, authorities);
	}

	private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
		Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
		for (Role role : userRoles) {
			roles.add(new SimpleGrantedAuthority(role.getRole()));
		}

		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>(roles);
		return grantedAuthorities;
	}

	private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.isActive(), true, true, true, authorities);
	}


	public static String generateString(Random rng, String characters, int length)
	{
		char[] text = new char[length];
		for (int i = 0; i < length; i++)
		{
			text[i] = characters.charAt(rng.nextInt(characters.length()));
		}
		return new String(text);
	}
}
