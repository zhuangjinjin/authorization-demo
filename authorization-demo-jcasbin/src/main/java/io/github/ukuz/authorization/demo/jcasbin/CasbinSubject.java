package io.github.ukuz.authorization.demo.jcasbin;

import io.github.ukuz.authorization.demo.core.Action;
import io.github.ukuz.authorization.demo.core.Identity;
import io.github.ukuz.authorization.demo.core.Permission;
import io.github.ukuz.authorization.demo.core.PermissionStore;
import io.github.ukuz.authorization.demo.core.Resource;
import io.github.ukuz.authorization.demo.core.Role;
import io.github.ukuz.authorization.demo.core.Subject;
import org.casbin.jcasbin.main.Enforcer;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhuangjj
 * @since 2021-12-25
 */
public class CasbinSubject implements Subject {

    private final Enforcer enforcer;
    private final Identity identity;

    public CasbinSubject(Enforcer enforcer, Identity identity) {
        this.enforcer = enforcer;
        this.identity = identity;
    }

    @Override
    public Identity getIdentity() {
        return identity;
    }

    @Override
    public List<Role> getRole() {
        List<String> roles = enforcer.getUsersForRole(this.getIdentity().getUsername());
        return roles.stream().map(Role::new).collect(Collectors.toList());
    }

    @Override
    public Boolean access(Resource resource, Action action) {
        return enforcer.enforce(this.identity, resource.toString(), action.name());
    }

}
