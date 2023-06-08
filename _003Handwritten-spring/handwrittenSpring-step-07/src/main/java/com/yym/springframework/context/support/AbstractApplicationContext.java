package com.yym.springframework.context.support;

import com.yym.springframework.beans.BeansException;
import com.yym.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.yym.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.yym.springframework.beans.factory.config.BeanPostProcessor;
import com.yym.springframework.context.ConfigurableApplicationContext;
import com.yym.springframework.core.io.DefaultResourceLoader;

import java.util.Map;

/**
 * @Description: 应用上下文的抽象类, 提供一个基础的上下文实现类, 不同的上下文继承该抽象类即可, 封装通用逻辑和流程
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-04-30 22:51
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    @Override
    public void refresh() throws BeansException {
        // 1. 创建BeanFactory 并加载beanDefinition
        refreshBeanFactory();

        // 2. 获取beanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        // 3. 在bean实例化之前, 执行 BeanFactoryPostProcessor
        invokeBeanFactoryPostProcessor(beanFactory);

        // 4. BeanPostProcessor需要在其它Bean实例化之前执行注册操作
        registerBeanPostProcessors(beanFactory);

        // 5. 提前实例化单例Bean对象
        beanFactory.preInstantiateSingletons();
    }

    protected abstract void refreshBeanFactory() throws BeansException;

    protected abstract ConfigurableListableBeanFactory getBeanFactory();


    private void invokeBeanFactoryPostProcessor(ConfigurableListableBeanFactory beanFactory) {
        // 获取beanFactoryPost, 在bean实例化之前执行
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            // beanFactory中有beanDefinition的定义和状态, 此时beanDefinition尚未加载 无法直接传入
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public Object getBean(String beanName) throws BeansException {
        return getBeanFactory().getBean(beanName);
    }

    @Override
    public Object getBean(String beanName, Object... args) throws BeansException {
        return getBeanFactory().getBean(beanName, args);
    }

    @Override
    public <T> T getBean(String beanName, Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(beanName, requiredType);
    }

    @Override
    public void registerShutdownHook() {
        // 向JVM注册一个钩子, 钩子的方法是销毁单例bean, 防止容器关闭时, bean未及时内存未及时清理, 导致内存泄漏
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public void close() {
        getBeanFactory().destroySingletons();
    }
}
