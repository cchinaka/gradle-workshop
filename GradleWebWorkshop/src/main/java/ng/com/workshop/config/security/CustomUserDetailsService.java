package ng.com.workshop.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ng.com.workshop.business.data.user.UserRepository;
import ng.com.workshop.model.user.User;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    @Qualifier("userRepo")
    public UserRepository repo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findUserByUsername(username);
        if (user != null) {
            return new SecuredUser(user, repo.findUserRoles(user));
        } else {
            throw new UsernameNotFoundException("User not found for " + username);
        }
    }
}
