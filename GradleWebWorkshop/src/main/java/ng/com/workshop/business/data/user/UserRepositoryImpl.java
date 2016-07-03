package ng.com.workshop.business.data.user;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import ng.com.workshop.model.user.User;
import ng.com.workshop.model.user.UserRole;


@Repository
@Qualifier("userRepo")
public class UserRepositoryImpl implements UserRepository {

    @Override
    public User findUserByUsername(String username) {
        return new User("001", "admin", "password");
    }


    @Override
    public List<UserRole> findUserRoles(User user) {
        return Arrays.asList(new UserRole("ROLE_USER"), new UserRole("ROLE_ADMIN"));
    }
}
