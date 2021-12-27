package io.github.ukuz.authorization.demo.jcasbin;

import io.github.ukuz.authorization.demo.utils.FileUtils;
import io.github.ukuz.authorization.demo.core.PermissionStore;
import org.casbin.adapter.JDBCAdapter;
import org.casbin.jcasbin.main.Enforcer;
import org.casbin.jcasbin.model.Model;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author ukuz90
 */
@Configuration
public class CasbinConfig {

    @Bean
    public Enforcer enforcer(DataSource dataSource) throws Exception {
        JDBCAdapter adapter = new JDBCAdapter(dataSource);
        String content = FileUtils.getContent("/abac_model.conf");
        Model model = new Model();
        model.loadModelFromText(content);
        Enforcer enforcer = new Enforcer(model, adapter);
        return enforcer;
    }

    @Bean
    public PermissionStore permissionStore() {
        return new MemoryPermissionStore();
    }

}
