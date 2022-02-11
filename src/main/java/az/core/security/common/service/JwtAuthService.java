package az.core.security.common.service;

import az.core.security.common.SecurityProperties;
import az.core.security.common.util.HttpConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthService implements AuthService {

    private static final String  ROLE_CLAIM = "role";
    private final SecurityProperties securityProperties;

    private Key key;




    @Override
    public Optional<Authentication> getAuthentication(HttpServletRequest httpServletRequest) {
        return Optional.ofNullable(httpServletRequest.getHeader(HttpConstants.AUTH_HEADER))
                .filter(this::isBearerAuth)
                .flatMap(this::getAuthenticationBearer);
    }


    @PostConstruct
    protected void init() {
        byte[] keyBytes = Decoders.BASE64.decode(securityProperties.getJwtProperties().getSecret());
        key = Keys.hmacShaKeyFor(keyBytes);
    }


    private boolean isBearerAuth(String header) {
        return header.toLowerCase().startsWith(HttpConstants.BEARER_AUTH_HEADER.toLowerCase());
    }

    public String issueToken(Authentication authentication, Duration duration){
        final JwtBuilder jwtBuilder = Jwts.builder()
                .setSubject(authentication.getName())
                .setIssuedAt(new Date())
                .setExpiration(Date.from(Instant.now().plus(duration)))
                .signWith(key, SignatureAlgorithm.HS512);

        addClaimsSets(jwtBuilder,authentication);

        return jwtBuilder.compact();
    }

    private void addClaimsSets(JwtBuilder jwtBuilder, Authentication authentication) {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        List roles = authorities.stream().map(a ->a.getAuthority()).collect(Collectors.toList());
        jwtBuilder.addClaims(Map.of("role",roles));
    }

    private Optional<Authentication> getAuthenticationBearer(String header) {
        String token = header.substring(HttpConstants.BEARER_AUTH_HEADER.length()).trim();

        Claims claims = parseToken(token);


        return Optional.of(getAuthenticationBearer(claims));
    }

    private Authentication getAuthenticationBearer(Claims claims) {
        List<?> roles  = claims.get(ROLE_CLAIM,List.class);
        List<GrantedAuthority> authorities = roles
                .stream()
                .map(a -> new SimpleGrantedAuthority(a.toString()))
                .collect(Collectors.toList());
        return new UsernamePasswordAuthenticationToken(claims.getSubject(),"",authorities);
    }


    private Claims parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
