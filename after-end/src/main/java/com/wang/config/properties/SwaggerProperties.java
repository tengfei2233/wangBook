package com.wang.config.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author feige
 * @version v1.0
 * @date 2023-03-10-21:20
 * @description
 */


@Data
@Component
@ConfigurationProperties(prefix = "swagger")
public class SwaggerProperties {

    private Boolean enable;
    private String title;
    private String description;
    private String pathMapping;
    private String version;
    private Contact contact;
    private List<Groups> groups;

    @Data
    @NoArgsConstructor
    public static class Contact {
        private String contact;
        private String name;
        private String email;
        private String url;
        private String groups;
    }

    @Data
    @NoArgsConstructor
    public static class Groups {
        private String name;
        private String basePackage;
    }

}
