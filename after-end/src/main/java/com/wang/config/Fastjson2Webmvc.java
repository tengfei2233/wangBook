package com.wang.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: tengfei
 * @Create: 2023 04 24 1:09
 * @Description fastjson2替换webmvc的序列化与反序列化器
 **/

// @EnableWebMvc
@Configuration
public class Fastjson2Webmvc implements WebMvcConfigurer {

    // TODO 存在Long序列化精度丢失问题，目前在fastjson2中还不知道如何全局解决，以后有时间启用

    // @Override
    // public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    //     FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
    //     //自定义配置...
    //     FastJsonConfig config = new FastJsonConfig();
    //     config.setDateFormat("yyyy-MM-dd HH:mm:ss");
    //     config.setReaderFeatures(JSONReader.Feature.FieldBased, JSONReader.Feature.SupportArrayToBean);
    //     config.setWriterFeatures(JSONWriter.Feature.WriteMapNullValue, JSONWriter.Feature.PrettyFormat);
    //
    //     converter.setFastJsonConfig(config);
    //     converter.setDefaultCharset(StandardCharsets.UTF_8);
    //     converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
    //
    //     converters.add(0, converter);
    // }
    //
    // @Override
    // public void configureViewResolvers(ViewResolverRegistry registry) {
    //     FastJsonJsonView fastJsonJsonView = new FastJsonJsonView();
    //     //自定义配置...
    //     FastJsonConfig config = new FastJsonConfig();
    //     config.setDateFormat("yyyy-MM-dd HH:mm:ss");
    //     config.setReaderFeatures(JSONReader.Feature.FieldBased, JSONReader.Feature.SupportArrayToBean);
    //     config.setWriterFeatures(JSONWriter.Feature.WriteMapNullValue, JSONWriter.Feature.PrettyFormat);
    //     fastJsonJsonView.setFastJsonConfig(config);
    //     registry.enableContentNegotiation(fastJsonJsonView);
    // }

}
