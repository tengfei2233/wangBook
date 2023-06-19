package com.wang.config;

import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.support.config.FastJsonConfig;
import com.alibaba.fastjson2.support.spring.http.converter.FastJsonHttpMessageConverter;
import com.alibaba.fastjson2.support.spring.webservlet.view.FastJsonJsonView;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @Author: tengfei
 * @Create: 2023 04 24 1:09
 * @Description fastjson2替换webmvc的序列化与反序列化器
 **/

// @EnableWebMvc
@Configuration
public class Fastjson2Webmvc implements WebMvcConfigurer {

    // TODO 参看博客 https://tengfei2233.gitee.io/2023/6/15/Jackson%E8%A7%A3%E5%86%B3SpringMVC%E5%85%A8%E5%B1%80%E5%BA%8F%E5%88%97%E5%8C%96%E8%BF%94%E5%9B%9E%E4%B8%ADLong%E7%B1%BB%E5%9E%8B%E7%B2%BE%E5%BA%A6%E4%B8%A2%E5%A4%B1%E9%97%AE%E9%A2%98/
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig config = new FastJsonConfig();
        config.setCharset(StandardCharsets.UTF_8);
        config.setDateFormat("yyyy-MM-dd HH:mm:ss");
        config.setWriterFeatures(
                //long 转 string 丢失精度问题
                JSONWriter.Feature.WriteLongAsString,
                // 保留map空的字段
                JSONWriter.Feature.WriteMapNullValue,
                //将List类型的null转成[]
                JSONWriter.Feature.WriteNullListAsEmpty,
                //将String类型的null转成""
                JSONWriter.Feature.WriteNullStringAsEmpty,
                //将Boolean类型的null转成false
                JSONWriter.Feature.WriteNullBooleanAsFalse,
                //日期格式转换
                JSONWriter.Feature.PrettyFormat,
                //将空置输出为缺省值，Number类型的null都输出为0，String类型的null输出为""，数组和Collection类型的输出为[]
                JSONWriter.Feature.NullAsDefaultValue
        );
        fastJsonHttpMessageConverter.setFastJsonConfig(config);
        converters.add(0, fastJsonHttpMessageConverter);
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        FastJsonJsonView fastJsonJsonView = new FastJsonJsonView();
        //自定义配置...
        FastJsonConfig config = new FastJsonConfig();
        config.setCharset(StandardCharsets.UTF_8);
        config.setDateFormat("yyyy-MM-dd HH:mm:ss");
        config.setReaderFeatures(JSONReader.Feature.FieldBased, JSONReader.Feature.SupportArrayToBean);
        fastJsonJsonView.setFastJsonConfig(config);
        registry.enableContentNegotiation(fastJsonJsonView);
    }

}
