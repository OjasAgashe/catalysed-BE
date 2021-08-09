package com.ojas.gcp.firstappenginetryout.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ojas.gcp.firstappenginetryout.auth.AppUserDetailsService;
import com.ojas.gcp.firstappenginetryout.repository.UserRepository;
import com.ojas.gcp.firstappenginetryout.rest.dto.ErrorResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//    @Autowired
//    DataSource dataSource;

    @Autowired
    AppUserDetailsService userDetailsService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private JwtRequestFilter jwtRequestFilter;
    @Autowired
    private ObjectMapper objectMapper;

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

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    //AuthManagerBuilder looks for a PaswordEncode bean, and we can return diff types of encoders
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/", "/sendMemeMail");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().antMatchers("/", "/_ah/start", "/app/**", "/static/**","/authenticate",
                "/admin", "/register/organization", "/register/student", "/register/mentor", "/invite/**",
                "/manifest.json", "/test/**", "/temp/orgUsers", "/favicon.ico").permitAll()

//        "/program", "/program/**", "/organization/programs", "/organization/program/**", "/test/**").permitAll()
                .anyRequest().authenticated().and().
                exceptionHandling().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        //Exception handling configuration
        http.exceptionHandling()
                .authenticationEntryPoint(
                (request, response, e) ->
                {
                    response.setContentType("application/json;charset=UTF-8");
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    response.getWriter().write(objectMapper.writeValueAsString(
                            new ErrorResponseDTO(LocalDateTime.now().toString(), "Access denied, Go to home page : https://level-abode-312509.el.r.appspot.com/")));
                });

        //TO-DO : Remove for prod build
        CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
        corsConfiguration.addAllowedMethod("PUT");
        http.cors().configurationSource(request -> corsConfiguration);

        // , "/static/**/**"  "/organizationDetail", "/organizer",
    }




//
//    //Using HTTPSecurity for authorization (based on url paths)
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/admin").hasRole("ADMIN") //current and all nested paths
//                .antMatchers("/user").hasAnyRole("USER", "ADMIN")
////                .antMatchers("/", "/static/*", "/login", "/organization/register", "/changePassword"
////                , "/organizer", "/student", "/mentor").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .sessionManagement();
////                .and()
////                .oauth2Login();
//
////        http
////                .csrf().disable()
////                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
////                .and()
////                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
////                .addFilter(new JwtAuthorizationFilter(authenticationManager(), userRepository))
////                .authorizeRequests()
////                .antMatchers("/admin").hasRole("ADMIN") //current and all nested paths
////                .antMatchers("/user").hasAnyRole("USER", "ADMIN")
////                .antMatchers("/", "static/css", "static/js", "/login").permitAll()
////                .anyRequest().authenticated();
//    }
}
