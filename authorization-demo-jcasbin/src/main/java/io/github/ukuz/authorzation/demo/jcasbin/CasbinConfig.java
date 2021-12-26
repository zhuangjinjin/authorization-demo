package io.github.ukuz.authorzation.demo.jcasbin;

import io.github.ukuz.authorzation.demo.core.PermissionStore;
import io.github.ukuz.authorzation.demo.utils.FileUtils;
import org.casbin.adapter.JDBCAdapter;
import org.casbin.jcasbin.main.Enforcer;
import org.casbin.jcasbin.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.InputStream;

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
