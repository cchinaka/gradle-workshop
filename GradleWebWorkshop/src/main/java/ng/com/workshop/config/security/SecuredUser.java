package ng.com.workshop.config.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import ng.com.workshop.model.user.User;
import ng.com.workshop.model.user.UserRole;


public class SecuredUser extends org.springframework.security.core.userdetails.User {

    private static final long serialVersionUID = 7088714700700676922L;

    private User user;

    private List<UserRole> roles;


    public SecuredUser(User user, List<UserRole> roles) {
        this(user, roles, user.getUsername(), user.getPassword(), user.isEnabled(), user.isAccountNonExpired(), user.isCredentialsNonExpired(),
                user.isAccountNonLocked(), getGrantedAuthorities(roles));
    }


    private static Collection<GrantedAuthority> getGrantedAuthorities(Collection<UserRole> roles) {
        List<GrantedAuthority> listOfAuthorities = new ArrayList<GrantedAuthority>();
        for (UserRole role : roles) {
            listOfAuthorities.add(new SimpleGrantedAuthority(role.getCode()));
        }
        return listOfAuthorities;
    }


    public SecuredUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked,
            Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }


    public SecuredUser(User user, List<UserRole> roles, String username, String password, boolean enabled, boolean accountNonExpired,
            boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        setUser(user);
        setRoles(roles);
    }


    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }


    public List<UserRole> getRoles() {
        return roles;
    }


    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }
}
