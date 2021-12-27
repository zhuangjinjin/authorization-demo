package io.github.ukuz.authorization.demo.controller;

import io.github.ukuz.authorization.demo.core.Action;
import io.github.ukuz.authorization.demo.core.RequiresPermissions;
import io.github.ukuz.authorization.demo.core.Resource;
import io.github.ukuz.authorization.demo.core.Session;
import io.github.ukuz.authorization.demo.core.SessionStore;
import io.github.ukuz.authorization.demo.jcasbin.CasbinSubject;
import io.github.ukuz.authorization.demo.core.Identity;
import io.github.ukuz.authorization.demo.core.PermissionStore;
import io.github.ukuz.authorization.demo.core.Subject;
import org.casbin.jcasbin.main.Enforcer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author ukuz90
 */
@RestController
public class UserController {

    private final String INDEX_URL = "<a href=\"http://localhost:8080/index\">access.</a>";

    @Autowired
    private Enforcer enforcer;
    @Autowired
    private SessionStore sessionStore;

    @GetMapping("/login")
    public ResponseEntity<String> login(String username, String password) {
        // TODO do authenticated

        Identity identity = new Identity(username, 17, "xiamen");
        CasbinSubject subject = new CasbinSubject(enforcer, identity);
        String token = UUID.randomUUID().toString();

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.SET_COOKIE, "sid="+token);
        ResponseEntity<String> response = new ResponseEntity<>(INDEX_URL, headers, HttpStatus.OK);
        sessionStore.registerSession(Session.from(token), subject);

        return response;
    }

    @GetMapping("/index")
    @RequiresPermissions("/index")
    public String index(HttpServletRequest request) {
        return "<h1><a>Hello World!</a></h1>";
    }

}
