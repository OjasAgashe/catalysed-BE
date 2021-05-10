package com.ojas.gcp.firstappenginetryout.auth;

import com.ojas.gcp.firstappenginetryout.entity.AppUser;
import com.ojas.gcp.firstappenginetryout.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;


//It should rather implement AuthenticationUserDetailsService interface
// This takes in the credentials/tokens from the login action, then does a authentication
// and if auth is successful returns a userObject (Auth Principle)

//The UserDetailsService expects a user object as return and it actually comparess the
// filled password with the getPassword from the fetched user
@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    AppUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<AppUser> user = userRepository.findByEmail(email);
        user.orElseThrow(() -> new UsernameNotFoundException("User " + email + "not found in system"));
        
        return new SessionUser(user.get());
    }
}
