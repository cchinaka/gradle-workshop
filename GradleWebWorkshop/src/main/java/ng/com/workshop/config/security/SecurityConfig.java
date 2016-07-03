package ng.com.workshop.config.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;


    @Override
    public void configure(WebSecurity webConfig) throws Exception {
        super.configure(webConfig);
    }


    @Override
    public void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.inMemoryAuthentication().withUser("user").password("password").roles("USER").and().withUser("admin").password("password").roles("USER",
                "ADMIN");
        // String usersByUsernameQuery = "select username, password, enabled from AppUser where username = ?";
        // String authoritiesByUsernameQuery = "select a.username, r.roles_id from appuser a join appuser_roles r on
        // a.id=r.appuser_id where a.username = ?";
        // builder.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(usersByUsernameQuery).authoritiesByUsernameQuery(authoritiesByUsernameQuery)
        // .passwordEncoder(new StandardPasswordEncoder("53cr3t"));
    }


    @Override
    public void configure(HttpSecurity httpConfig) throws Exception {
        httpConfig.csrf().disable();
        httpConfig.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();
        // httpConfig.authorizeRequests().anyRequest().permitAll();
    }
}
