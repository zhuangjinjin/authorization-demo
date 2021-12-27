package io.github.ukuz.authorization.demo.utils;


import java.io.IOException;
import java.io.InputStream;

/**
 * @author ukuz90
 */
public class FileUtils {

    public static String getContent(String fileName) {
        StringBuilder sb = new StringBuilder();
        try (InputStream is = FileUtils.class.getResourceAsStream(fileName)) {
            byte[] bytes = new byte[1024];
            int offset;
            while ((offset = is.read(bytes, 0, bytes.length)) != -1) {
                sb.append(new String(bytes, 0, offset));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

}
