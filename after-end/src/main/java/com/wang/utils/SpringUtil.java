package com.wang.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @author feige
 * @version v1.0
 * @date 2023-03-20-17:07
 * @description
 */

@Component
public class SpringUtil implements BeanFactoryPostProcessor {

    private static ConfigurableListableBeanFactory beanFactory;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        SpringUtil.beanFactory = beanFactory;
    }

    public static ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }

    public static <T> void setBean(String name, T t) {
        beanFactory.autowireBean(t);
        beanFactory.registerSingleton(name, t);
    }

    public static <T> T getBean(String name) {
        return (T) beanFactory.getBean(name);
    }

    public static <T> T getBean(Class<T> clazz) {
        return beanFactory.getBean(clazz);
    }
}
