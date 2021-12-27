package io.github.ukuz.authorization.demo.core;

import java.util.Objects;

/**
 * @author ukuz90
 */
public class Session {

    private String token;
    private Long expireTimeMillis;

    public Session(String token) {
        this.token = token;
    }

    public Session(String token, Long expireTimeMillis) {
        this.token = token;
        this.expireTimeMillis = expireTimeMillis;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getExpireTimeMillis() {
        return expireTimeMillis;
    }

    public void setExpireTimeMillis(Long expireTimeMillis) {
        this.expireTimeMillis = expireTimeMillis;
    }

    public static Session from(String token) {
        return new Session(token);
    }

    @Override
    public String toString() {
        return "Session{" + "token='" + token + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Session session = (Session)o;
        return Objects.equals(token, session.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token);
    }
}
