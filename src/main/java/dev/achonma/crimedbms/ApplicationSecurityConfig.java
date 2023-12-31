package dev.achonma.crimedbms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeRequests(requests -> requests
                        .antMatchers(
                                "/login",
                                "/resources/**",
                                "/css/**",
                                "/fonts/**",
                                "/img/**")
                        .permitAll()
                        .antMatchers(
                                "/register",
                                "/resources/**",
                                "/css/**",
                                "/fonts/**",
                                "/img/**")
                        .permitAll()
                        .antMatchers("/users/addNew").permitAll()
                        .antMatchers("/security/user/Edit/**", "/users", "/createnewrecord", "/records/**",
                                "/delete/**",
                                "/createnewprison", "/createnewstate", "/createnewcrime", "/prisons/update/**",
                                "/states/update/**", "/crimes/update/**", "/prisons/delete/**", "/states/delete/**",
                                "/crimes/delete/**")
                        .hasAuthority("ADMIN")
                        .anyRequest().authenticated())
                .formLogin(login -> login
                        .loginPage("/login").permitAll()
                        .defaultSuccessUrl("/index"))
                .exceptionHandling(handling -> handling
                        .accessDeniedPage("/accessDenied"))
                .logout(logout -> logout.invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login").permitAll());
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(bCryptPasswordEncoder());
        return provider;
    }
}