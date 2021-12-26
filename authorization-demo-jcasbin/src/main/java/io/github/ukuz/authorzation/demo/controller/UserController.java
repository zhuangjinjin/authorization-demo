package io.github.ukuz.authorzation.demo.controller;

import io.github.ukuz.authorzation.demo.core.Identity;
import io.github.ukuz.authorzation.demo.core.PermissionStore;
import io.github.ukuz.authorzation.demo.core.Subject;
import io.github.ukuz.authorzation.demo.jcasbin.CasbinSubject;
import org.casbin.jcasbin.main.Enforcer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
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

    private final Map<String, Subject> subjectMap = new HashMap<>();
    private final String INDEX_URL = "<a href=\"http://localhost:8080/index\">access.</a>";

    @Autowired
    private Enforcer enforcer;
    @Autowired
    private PermissionStore permissionStore;

    @GetMapping("/login")
    public ResponseEntity<String> login(String username, String password) {
        // TODO do authenticated

        Identity identity = new Identity(username);
        CasbinSubject subject = new CasbinSubject(permissionStore, enforcer, identity);
        String token = UUID.randomUUID().toString();

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.SET_COOKIE, "sid="+token);
        ResponseEntity<String> response = new ResponseEntity<>(INDEX_URL, headers, HttpStatus.OK);
        subjectMap.put(token, subject);

        return response;
    }

    @GetMapping("/index")
    public void index(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String token = cookies[0].getValue();
        Subject subject = subjectMap.get(token);
        System.out.println(subject);
    }

}
