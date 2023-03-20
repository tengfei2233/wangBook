package com.wang.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import com.wang.config.properties.SwaggerProperties;
import com.wang.utils.SpringUtil;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.plugins.WebFluxRequestHandlerProvider;
import springfox.documentation.spring.web.plugins.WebMvcRequestHandlerProvider;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author feige
 * @version v1.0
 * @date 2023-03-10-21:28
 * @description
 */

// 实现BeanFactoryPostProcessor获取beanContext上下文，用于待会手动注册bean
// TODO 实现BeanFactoryPostProcessor后@PostConstruct无法使用
@Configuration
@EnableKnife4j
public class SwaggerConfig {


    @Resource
    private SwaggerProperties properties;
    @Resource
    private OpenApiExtensionResolver openApiExtensionResolver;


    /**
     * 用于适配springboot 2.6
     */
    @Bean
    public BeanPostProcessor springfoxHandlerProviderBeanPostProcessor() {
        return new BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if (bean instanceof WebMvcRequestHandlerProvider || bean instanceof WebFluxRequestHandlerProvider) {
                    customizeSpringfoxHandlerMappings(getHandlerMappings(bean));
                }
                return bean;
            }

            private <T extends RequestMappingInfoHandlerMapping> void customizeSpringfoxHandlerMappings(List<T> mappings) {
                mappings.removeIf(mapping -> mapping.getPatternParser() != null);
            }

            private List<RequestMappingInfoHandlerMapping> getHandlerMappings(Object bean) {
                try {
                    Field field = ReflectionUtils.findField(bean.getClass(), "handlerMappings");
                    field.setAccessible(true);
                    return (List<RequestMappingInfoHandlerMapping>) field.get(bean);
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    throw new IllegalStateException(e);
                }
            }
        };
    }


    @PostConstruct
    public void createRestApi() {
        for (SwaggerProperties.Groups group : properties.getGroups()) {
            String basePackage = group.getBasePackage();
            // 文档类型
            Docket docket = new Docket(DocumentationType.OAS_30)
                    // 是否开启
                    .enable(properties.getEnable())
                    // API文档基本信息
                    .apiInfo(getApiInfo())
                    // 设置哪些接口暴露给Swagger
                    .select()
                    // 扫描指定包中的swagger注解
                    .apis(RequestHandlerSelectors.basePackage(basePackage))
                    // 扫描所有.apis()
                    .paths(PathSelectors.any())
                    .build()
                    .groupName(group.getName())
                    // TODO 设置安全模式，swagger可以设置访问token
                    .securitySchemes(securitySchemes())
                    .securityContexts(securityContexts())
                    .extensions(openApiExtensionResolver.buildExtensions(group.getName()))
                    .pathMapping(properties.getPathMapping());

            String beanName = StringUtils.substringAfterLast(basePackage, ".") + "Docket";
            // 手动注入bean对象
            SpringUtil.setBean(beanName, docket);
        }
    }

    /**
     * 安全模式，这里指定token通过Authorization头请求头传递
     */
    private List<SecurityScheme> securitySchemes() {
        List<SecurityScheme> apiKeyList = new ArrayList<>();
        // TODO Authorization指token名称
        apiKeyList.add(new ApiKey("Authorization", "Authorization", In.HEADER.toValue()));
        return apiKeyList;
    }

    /**
     * 安全上下文
     */
    private List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContexts = new ArrayList<>();
        securityContexts.add(
                SecurityContext.builder()
                        .securityReferences(defaultAuth())
                        .operationSelector(o -> o.requestMappingPattern().matches("/.*"))
                        .build());
        return securityContexts;
    }

    /**
     * 默认的安全上引用
     */
    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> securityReferences = new ArrayList<>();
        securityReferences.add(new SecurityReference("Authorization", authorizationScopes));
        return securityReferences;
    }


    private ApiInfo getApiInfo() {
        SwaggerProperties.Contact contact = properties.getContact();
        return new ApiInfoBuilder()
                .contact(new Contact(contact.getName(), contact.getUrl(), contact.getEmail()))
                .description(properties.getDescription())
                .title(properties.getTitle())
                .version(properties.getVersion())
                .build();
    }

}
