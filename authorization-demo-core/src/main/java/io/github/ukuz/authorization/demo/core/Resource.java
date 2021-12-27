package io.github.ukuz.authorization.demo.core;

import lombok.AllArgsConstructor;

/**
 * @author ukuz90
 */
@AllArgsConstructor
public class Resource {

    private String path;

    @Override
    public String toString() {
        return path;
    }
}
