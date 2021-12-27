package io.github.ukuz.authorization.demo.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ukuz90
 */
public class SessionStore {

    private Map<Session, Subject> subjectMap = new ConcurrentHashMap<>();

    public void registerSession(Session session, Subject subject) {
        subjectMap.put(session, subject);
    }

    public Subject find(Session session) {
        return subjectMap.get(session);
    }

}
