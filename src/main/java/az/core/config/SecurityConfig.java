package az.core.config;


import az.core.security.common.JwtAuthFilterConfigurerAdapter;
import az.core.security.common.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.List;

@Configuration
@EnableWebSecurity
@Slf4j
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private  final List<AuthService> authServices;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.apply(new JwtAuthFilterConfigurerAdapter(authServices));

        http
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/blogs/categories","/blogs/categories/{id}","/blogs/image/**")
                .permitAll()
                .antMatchers(HttpMethod.GET,"/blogs","/blogs/{id}","/blogs/urls/{url}")
                .permitAll()
                .antMatchers(HttpMethod.GET,"/contacts","/contacts/{id}","/contacts/urls/{url}")
                .permitAll()
                .antMatchers(HttpMethod.GET,"/trainings/categories","/trainings/categories/{id}")
                .permitAll()
                .antMatchers(HttpMethod.GET,"/trainings","/trainings/{id}","/trainings/image/**","/trainings/urls/{url}")
                .permitAll()
                .antMatchers(HttpMethod.GET,"/events","/events/{id}","/events/image/**","/events/urls/{url}")
                .permitAll()
                .antMatchers(HttpMethod.POST,"/inquiries")
                .permitAll()
                .antMatchers(HttpMethod.GET,"/news","/news/{id}","/news/image/**","/news/urls/{url}")
                .permitAll()
                .antMatchers(HttpMethod.POST,"/auth/**").permitAll()
                .anyRequest().hasAnyRole("ADMIN");
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**");
    }
    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.withUsername("admin")
                .passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder()::encode)
                .password("admin").roles("ADMIN").build();

        return new InMemoryUserDetailsManager(admin);
    }
}
