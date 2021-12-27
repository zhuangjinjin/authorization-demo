package io.github.ukuz.authorization.demo.jcasbin;

import io.github.ukuz.authorization.demo.core.Identity;
import io.github.ukuz.authorization.demo.core.Permission;
import io.github.ukuz.authorization.demo.core.PermissionStore;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ukuz90
 */
public class MemoryPermissionStore implements PermissionStore {
    @Override
    public List<Permission> findPermissionBySubjectId(Identity identity) {
        List<Permission> permissions = new ArrayList<>();
        permissions.add(new Permission());
        return permissions;
    }
}
