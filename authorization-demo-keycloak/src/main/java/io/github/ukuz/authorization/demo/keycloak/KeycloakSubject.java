package io.github.ukuz.authorization.demo.keycloak;

import io.github.ukuz.authorization.demo.core.Action;
import io.github.ukuz.authorization.demo.core.Identity;
import io.github.ukuz.authorization.demo.core.Resource;
import io.github.ukuz.authorization.demo.core.Role;
import io.github.ukuz.authorization.demo.core.Subject;
import org.keycloak.KeycloakSecurityContext;

import java.util.List;

/**
 * @author ukuz90
 */
public class KeycloakSubject implements Subject {

    private final KeycloakSecurityContext securityContext;
    private final Identity identity;

    public KeycloakSubject(KeycloakSecurityContext securityContext, Identity identity) {
        this.securityContext = securityContext;
        this.identity = identity;
    }

    @Override
    public Identity getIdentity() {
        return identity;
    }

    @Override
    public List<Role> getRole() {
        return null;
    }

    @Override
    public Boolean access(Resource resource, Action action) {
        return securityContext.getAuthorizationContext().hasPermission(resource.toString(), action.name());
    }
}
