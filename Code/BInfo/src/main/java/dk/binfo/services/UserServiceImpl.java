package dk.binfo.services;

import java.util.*;

import javax.transaction.Transactional;

import dk.binfo.models.Role;
import dk.binfo.models.Seniority;
import dk.binfo.models.User;
import dk.binfo.models.user_ranking;
import dk.binfo.repositories.RoleRepository;
import dk.binfo.repositories.SeniorityRepository;
import dk.binfo.repositories.UserRankingRepository;
import dk.binfo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private SeniorityRepository seniorityRepository;

	@Autowired
	private UserRankingRepository userRankingRepository;

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
	public void register(User user, String role) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(true);
		user.setPhoneNumber(user.getPhoneNumber());
		Role userRole = roleRepository.findByRole(role);
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		user.setList(new HashSet<Role>(Arrays.asList(userRole)));
		Seniority seniority = setUserSeniority();
		user.setSeniority(new HashSet<Seniority>(Arrays.asList(seniority)));
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
	public Seniority setUserSeniority() {
		Seniority seniority = new Seniority();
		long id = seniorityRepository.count();
		if (id == 0) {
			//seniority.setseniority(id + 1);
			System.out.println("if"); //TODO FJERN
			seniorityRepository.save(seniority);
			return seniority;
		} else {
			//seniority.setseniority(id + 2);
			System.out.println("else"); //TODO fjern
			seniorityRepository.save(seniority);
			return seniority;
		}
	}


	@Override
	@Transactional
	public User update(User user){
		User updatedUser = userRepository.findByEmail("Vagabonden@outlook.com"); //TODO email

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
		System.out.println(Password);
		user.setPassword(bCryptPasswordEncoder.encode(Password));
		System.out.println(user.getPassword());
		user.setActive(user.isActive());
		user.setPhoneNumber(user.getPhoneNumber());
		Role userRole = roleRepository.findByRole("user");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}

	@Override
	@Transactional
	public User updateUserSettings(User user){
		User updateUser = userRepository.findByEmail("Vagabonden@outlook.com"); //TODO email
		System.out.println(user.getPassword());
		System.out.println(user.getPhoneNumber());
		if(user.getPassword().equalsIgnoreCase("") && user.getPhoneNumber() != null)
		{
			System.out.println(user.getPhoneNumber()); //TODO fjern
			updateUser.setPhoneNumber(user.getPhoneNumber());
		}
		if(user.getPhoneNumber() == null || user.getPhoneNumber().equalsIgnoreCase("") && user.getPassword() != null) {
			System.out.println(user.getPassword()); //TODO fjern
			updateUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		}
		if(user.getPhoneNumber() != null && user.getPassword() != null) {

			System.out.println(user.getPassword()); //TODO fjern
			System.out.println(user.getPhoneNumber()); //TODO fjern
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
		return new org.springframework.security.core.userdetails.User("Vagabonden@outlook.com", user.getPassword(), user.isActive(), true, true, true, authorities); //TODO email
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
