package edu.note.mapstruct.dictmapping;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

import org.mapstruct.Qualifier;

/**
 * @author jackylee
 * @date 2025-12-08 14:23
 */
public class MappingUtil {

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public static @interface Host {
    }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public static @interface Port {
    }

    @Host
    public String host(Map<String, String> in) {
        return (String) in.get("host");
    }

    @Port
    public String port(Map<String, String> in) {
        return (String) in.get("port");
    }
}
