package pi.vortex.rescuethestray.controllers;


import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pi.vortex.rescuethestray.entities.AdoptionApplication;
import pi.vortex.rescuethestray.entities.ERole;
import pi.vortex.rescuethestray.entities.Role;
import pi.vortex.rescuethestray.entities.User;
import pi.vortex.rescuethestray.payload.request.*;
import pi.vortex.rescuethestray.payload.response.ApiResponse;
import pi.vortex.rescuethestray.payload.response.MessageResponse;
import pi.vortex.rescuethestray.payload.response.UserInfoResponse;
import pi.vortex.rescuethestray.repositories.RoleRepository;
import pi.vortex.rescuethestray.repositories.UserRepository;
import pi.vortex.rescuethestray.security.jwt.JwtUtils;
import pi.vortex.rescuethestray.security.services.AuthService;
import pi.vortex.rescuethestray.security.services.UserDetailsImpl;
import pi.vortex.rescuethestray.security.services.UserDetailsServiceImpl;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Null;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600,allowCredentials="true")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;


	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
	@Autowired
	AuthService authService;


	//UserDetailsImpl userDetailsImpl;





	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
				.body(new UserInfoResponse(userDetails.getId(),
						userDetails.getUsername(),
						userDetails.getEmail(),
						roles));
	}





	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(),
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "mod":
					Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}


	@PostMapping("/signout")
	public ResponseEntity<?> logoutUser() {
		ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
				.body(new MessageResponse("You've been signed out!"));
	}





		@PostMapping("/forgot-password")
		public ResponseEntity<?> forgotPassword(@Valid @RequestBody forgotpasswordRequest forgotpasswordrequest) {
		authService.forgotPassword(forgotpasswordrequest.getEmail());
			return ResponseEntity.ok(new MessageResponse("sent"));
	}

	@PostMapping("/changePassword")
	public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest, HttpServletResponse response) {
		try {
			authService.changePassword(changePasswordRequest.getUsername(),
					changePasswordRequest.getOldPassword(),
					changePasswordRequest.getNewPassword());

			jwtUtils.addJwtCookie(response, changePasswordRequest.getUsername());
			return ResponseEntity.ok(new ApiResponse(true, "Password changed successfully"));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, "An error occurred while changing password"));
		}
	}



	@GetMapping("/getallusers")
	public List<User> getallusers()
	{

		return userRepository.findAll();
	}
	@GetMapping("/users/{id}/adoptionpercentage")

		public ResponseEntity<Double>getAdoptionPercentage(@PathVariable Long id){
			Optional<User> ouser =userRepository.findById(id);
			if(ouser.isPresent())
			{
				User user =ouser.get();
				int monthsinceregistration =user.getMonthsSinceRegistraion();
				double adoptpercentage =0.0;
				adoptpercentage= monthsinceregistration*12;
				return ResponseEntity.ok(adoptpercentage);
			}
			else {
				throw new UsernameNotFoundException("user :"+ouser.get().getUsername()+"NOT FOUND");
			}


	}

	@GetMapping("/statsusers")
	public Map<String,Integer> getusersstats(){
			Map<String,Integer> userpercents = new HashMap<>();
			List<User> listusers = userRepository.findAll();
			for(User user :listusers)
			{
				ResponseEntity<Double> responseentity =this.getAdoptionPercentage(user.getId());
				Double percentage =responseentity.getBody();
				userpercents.put(user.getUsername(),percentage.intValue());
			}
			return userpercents;
	}

	@DeleteMapping("/deleteuser/{id}")
	public void removeAdoptionPost(@PathVariable("id") Long id) {
		userRepository.deleteById(id);
	}


}













