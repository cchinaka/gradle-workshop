package ng.com.workshop.model.user;

import java.util.HashSet;
import java.util.Set;

import ng.com.workshop.model.BaseModel;


public class User extends BaseModel {

    private static final long serialVersionUID = 1790583511052602895L;

    private String username;

    private String password;

    private Set<UserRole> roles = new HashSet<UserRole>();

    private boolean accountNonExpired = true;

    private boolean credentialsNonExpired = true;

    private boolean accountNonLocked = true;

    private boolean verified;

    private boolean enabled = true;

    private UserDetail details = new UserDetail();


    public User() {
    }


    public User(String code, String username, String description) {
        setCode(code);
        setUsername(username);
        setDescription(description);
    }


    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public Set<UserRole> getRoles() {
        return roles;
    }


    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }


    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }


    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }


    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }


    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }


    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }


    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }


    public boolean isVerified() {
        return verified;
    }


    public void setVerified(boolean verified) {
        this.verified = verified;
    }


    public UserDetail getDetails() {
        return details;
    }


    public void setDetails(UserDetail details) {
        this.details = details;
    }


    public boolean isEnabled() {
        return enabled;
    }


    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
