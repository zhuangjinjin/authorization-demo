package io.github.ukuz.authorization.demo.interceptor;

import io.github.ukuz.authorization.demo.core.RequiresPermissions;
import io.github.ukuz.authorization.demo.core.Resource;
import io.github.ukuz.authorization.demo.core.Session;
import io.github.ukuz.authorization.demo.core.SessionStore;
import io.github.ukuz.authorization.demo.core.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * @author ukuz90
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private SessionStore sessionStore;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        if (handler instanceof HandlerMethod) {
            RequiresPermissions requiresPermissions =
                ((HandlerMethod)handler).getMethodAnnotation(RequiresPermissions.class);
            if (requiresPermissions != null) {
                if (!isAccessAllowed(request, requiresPermissions)) {
                    response.setStatus(HttpStatus.FORBIDDEN.value());
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isAccessAllowed(HttpServletRequest request, RequiresPermissions requiresPermissions) {
        Cookie[] cookies = request.getCookies();
        String token = Stream.of(cookies).filter(cookie -> Objects.equals(cookie.getName(), "sid")).map(Cookie::getValue).findFirst().orElse(null);
        if (Objects.isNull(token)) {
            return false;
        }
        Subject subject = sessionStore.find(Session.from(token));
        return subject.access(new Resource(requiresPermissions.value()), requiresPermissions.action());
    }
}
