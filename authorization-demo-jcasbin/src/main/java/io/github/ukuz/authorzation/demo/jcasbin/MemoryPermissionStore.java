package io.github.ukuz.authorzation.demo.jcasbin;

import io.github.ukuz.authorzation.demo.core.Identity;
import io.github.ukuz.authorzation.demo.core.Permission;
import io.github.ukuz.authorzation.demo.core.PermissionStore;

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
