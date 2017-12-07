package dk.binfo.models;

import java.util.List;
import java.util.Set;
import dk.binfo.models.Seniority;

import javax.persistence.*;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;

@Entity
@Table(name = "user")
public class User {

	@Id
	@Column(name = "email")
	@Email(message = "*Please provide a valid Email")
	@NotEmpty(message = "*Please provide an email")
	private String email;

	@Column(name = "password")
	@Length(min = 5, message = "*Your password must have at least 5 characters")
	@NotEmpty(message = "*Please provide your password")
	@Transient
	private String password;

	@Column(name = "name")
	@NotEmpty(message = "*Please provide your name")
	private String name;

	@Column(name = "last_name")
	@NotEmpty(message = "*Please provide your last name")
	private String lastName;

	@Column(name = "active")
	private boolean active;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_seniority", joinColumns = @JoinColumn(name = "email"), inverseJoinColumns = @JoinColumn(name = "seniority"))
	private Set<Seniority> seniority;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_ranking", joinColumns = @JoinColumn(name = "seniority"), inverseJoinColumns = @JoinColumn(name = "list"))
	private Set<Role> list;

	public Set<Role> getList() {
		return list;
	}

	public void setList(Set<Role> list) {
		this.list = list;
	}

	@Column(name ="phone_number")
	private String phoneNumber;

	@Column(name = "my_apartment")
	private String myApartment;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<Seniority> getSeniority() {
		return seniority;
	}

	public void setSeniority(Set<Seniority> seniority) {
		this.seniority = seniority;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMyApartment() {
		return myApartment;
	}

	public void setMyApartment(String myApartment) {
		this.myApartment = myApartment;
	}

}
