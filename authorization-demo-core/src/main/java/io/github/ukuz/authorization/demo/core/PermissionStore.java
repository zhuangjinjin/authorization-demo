package io.github.ukuz.authorization.demo.core;

import java.util.List;

/**
 * @author ukuz90
 */
public interface PermissionStore {

    List<Permission> findPermissionBySubjectId(Identity identity);

}
