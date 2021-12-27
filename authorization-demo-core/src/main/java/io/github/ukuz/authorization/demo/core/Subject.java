package io.github.ukuz.authorization.demo.core;

import java.util.List;

/**
 * @author ukuz90
 */
public interface Subject {

    List<Permission> getPermission();

    Identity getIdentity();

    List<Role> getRole();

    Boolean access(Resource resource, Action action);

}
