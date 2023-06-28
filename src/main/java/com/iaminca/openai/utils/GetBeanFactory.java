package com.iaminca.openai.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

public class GetBeanFactory implements BeanFactoryAware {
    private static BeanFactory beanFactory;

    public static Object getSpringEntry(String beanId){
        return beanFactory.getBean(beanId);
    }
    public static <T>T getSpringEntry(String beanId,Class<T>clazz){
        return clazz.cast(getSpringEntry(beanId));
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        GetBeanFactory.beanFactory = beanFactory;

    }
}
