package pi.vortex.rescuethestray.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(	name = "users", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "username"),
			@UniqueConstraint(columnNames = "email") 
		})
public class User {

	/*
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<AdoptionPost> adoptionPosts = new ArrayList<>();


	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<AdoptionApplication> adoptionApplications = new ArrayList<>();
	*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/*@OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
	List<Donation> donationList;*/
	@NotBlank
	@Size(max = 20)
	private String username;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	@NotBlank
	@Size(max = 120)
	private String password;

	@Size(max = 120)
	private String fullName;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();


	// INTEREST

	@ElementCollection(targetClass = TypeLRInterest.class)
	@Enumerated(EnumType.STRING)
	private List<TypeLRInterest> interests;

	// getters and setters for interests field
	public List<TypeLRInterest> getInterests() {
		return interests;
	}

	public void setInterests(List<TypeLRInterest> interests) {
		this.interests = interests;
	}


	public User() {
	}

	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public User(String username, String email, String password, String fullName) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.fullName = fullName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}
