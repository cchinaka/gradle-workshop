package ng.com.workshop.business.data.user;

import java.util.List;

import ng.com.workshop.model.user.User;
import ng.com.workshop.model.user.UserRole;


public interface UserRepository {

    User findUserByUsername(String username);


    List<UserRole> findUserRoles(User user);
}
