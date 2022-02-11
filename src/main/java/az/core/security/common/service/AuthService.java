package az.core.security.common.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public interface AuthService {

    Optional<Authentication> getAuthentication(HttpServletRequest httpServletRequest);

}
