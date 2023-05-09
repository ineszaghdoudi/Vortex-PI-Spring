package pi.vortex.rescuethestray.security.services;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pi.vortex.rescuethestray.entities.User;
import pi.vortex.rescuethestray.payload.request.SignupRequest;
import pi.vortex.rescuethestray.repositories.RoleRepository;
import pi.vortex.rescuethestray.repositories.UserRepository;
import pi.vortex.rescuethestray.security.jwt.JwtUtils;

import java.util.List;
import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    PasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    AuthenticationManager authenticationManager;


    @Autowired
    RoleRepository roleRepository;


    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    public void forgotPassword(String email) {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if (user.getEmail().equals(email)) {


                String password = generateRandomPassword();
                user.setPassword(encoder.encode(password));
                userRepository.save(user);

                // sendPasswordByEmail(user, password);
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom("noreply@example.com");
                message.setTo(user.getEmail());
                message.setSubject("New Password");
                message.setText("Your new password is: " + password);
                javaMailSender.send(message);
                System.out.println("New Password sent to email : " + email);
            }
        }
    }

    private String generateRandomPassword() {
        // generate random password using a library like Apache Commons
        return RandomStringUtils.randomAlphanumeric(8);
    }

    public String changePassword(String username, String oldPassword, String newPassword) throws BadCredentialsException {

        // Validate old password
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, oldPassword));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }catch (Exception e)
        {
            System.out.println("error authentification");
            throw new BadCredentialsException("invalid username or passwoed");
        }

        // Update password in database
        try {
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
            user.setPassword(encoder.encode(newPassword));
            userRepository.save(user);
        }catch (Exception e)
        {
            System.out.println("error persisting password" +e.getMessage());
            throw e;
        }

        // Generate new JWT token
        try {
            UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);
            String token = jwtUtils.generateToken(userDetails);

            // Return new JWT token
            return token;
        } catch (Exception e) {
            System.out.println("error generation JWT token");
            throw e;
        }
    }


}



