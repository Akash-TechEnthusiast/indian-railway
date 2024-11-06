package com.india.railway.authservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
/*import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.crypto.password.PasswordEncoder; */
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // extends WebSecurityConfigurerAdapter
    // @Autowired
    // private JwtRequestFilter jwtRequestFilter;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(customizer -> customizer.disable());
        http.authorizeHttpRequests(request -> request.requestMatchers("/login", "/railway/authenticate").permitAll()
                .anyRequest().authenticated());
        http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        /*
         * httl
         * // Specify URL-based authorization using requestMatchers
         * .authorizeHttpRequests(authorize -> authorize
         * .requestMatchers("/login", "/test").permitAll() // Allow public access to
         * URLs under "/public"
         * // .requestMatchers("/admin/**").hasRole("ADMIN") // Restrict URLs under
         * // "/admin" to users with the
         * // ADMIN role
         * .anyRequest().authenticated() // Require authentication for any other request
         * );
         */
        // Enable form-based login and configure login page if needed

        // Disable CSRF for testing purposes (not recommended in production)

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customUserDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    // below code is for authentication purpose
    /*
     * @Override
     * protected void configure(AuthenticationManagerBuilder auth) throws Exception
     * {
     * auth.userDetailsService(customUserDetailsService).passwordEncoder(
     * passwordEncoder());
     * 
     * }
     * 
     * // below code is for authorization purpose
     * 
     * @Override
     * protected void configure(HttpSecurity http) throws Exception {
     * // for test environment
     * // http.csrf().disable().authorizeRequests().anyRequest().permitAll();
     * // for production environment
     * 
     * http.csrf().disable()
     * .authorizeRequests()
     * .antMatchers("/", "/welcome", "/authenticate", "/register",
     * "/products", "/products/name/{name}")
     * // .hasRole("admin").antMatchers("/")
     * .permitAll()
     * .anyRequest().authenticated()
     * // for any other request it should be validated
     * .and().sessionManagement()
     * .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
     * 
     * http.addFilterBefore(jwtRequestFilter,
     * UsernamePasswordAuthenticationFilter.class);
     * 
     * }
     * 
     * 
     * 
     * 
     * @Bean
     * 
     * @Override
     * public AuthenticationManager authenticationManager() throws Exception {
     * return super.authenticationManagerBean();
     * }
     * 
     * 
     * // @Bean
     * // public PasswordEncoder passwordEncoder() {
     * // return NoOpPasswordEncoder.getInstance();
     * // return new BCryptPasswordEncoder();
     * // }
     */

    // System.out.println("test");
    // "/products","/products/name/{name}"

}
