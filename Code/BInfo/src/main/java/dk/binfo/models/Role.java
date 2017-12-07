package dk.binfo.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@OneToMany(mappedBy="list", cascade = CascadeType.ALL)
	private Set<user_ranking> ranking;

	@Column(name = "role")
	private String role;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
