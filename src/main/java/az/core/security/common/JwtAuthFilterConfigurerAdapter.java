package az.core.security.common;

import az.core.security.common.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilterConfigurerAdapter extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final List<AuthService> authServices;

    @Override
    public void configure(HttpSecurity http) {
        log.info("logging");
        http.addFilterBefore(new JwtAuthRequestFilter(authServices), UsernamePasswordAuthenticationFilter.class);
    }
}
