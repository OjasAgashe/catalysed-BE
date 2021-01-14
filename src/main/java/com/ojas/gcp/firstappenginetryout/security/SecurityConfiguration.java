package com.ojas.gcp.firstappenginetryout.security;

import com.ojas.gcp.firstappenginetryout.auth.AppUserDetailsService;
import com.ojas.gcp.firstappenginetryout.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//    @Autowired
//    DataSource dataSource;

    @Autowired
    AppUserDetailsService userDetailsService;

    @Autowired
    UserRepository userRepository;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //Set configuration on the auth object
//        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(authenticationProvider());


/*      In Memory Authentication
        auth.inMemoryAuthentication()
                .withUser("ojas")
                .password("pass")
                .roles("USER")
                .and()
                .withUser("admin")
                .password("admin")
                .roles("ADMIN");
 */
        //JDBC Auth
//        auth.jdbcAuthentication()
//                .dataSource(dataSource);
    }

    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(getPasswordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }



    //AuthManagerBuilder looks for a PaswordEncode bean, and we can return diff types of encoders
    @Bean
    public PasswordEncoder getPasswordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
        return new BCryptPasswordEncoder();
    }

    //Using HTTPSecurity for authorization (based on url paths)
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin").hasRole("ADMIN") //current and all nested paths
                .antMatchers("/user").hasAnyRole("USER", "ADMIN")
                .antMatchers("/", "static/css", "static/js", "/login", "/register", "/changePassword").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin();
//                .and()
//                .oauth2Login();

//        http
//                .csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
//                .addFilter(new JwtAuthorizationFilter(authenticationManager(), userRepository))
//                .authorizeRequests()
//                .antMatchers("/admin").hasRole("ADMIN") //current and all nested paths
//                .antMatchers("/user").hasAnyRole("USER", "ADMIN")
//                .antMatchers("/", "static/css", "static/js", "/login").permitAll()
//                .anyRequest().authenticated();
    }
}
