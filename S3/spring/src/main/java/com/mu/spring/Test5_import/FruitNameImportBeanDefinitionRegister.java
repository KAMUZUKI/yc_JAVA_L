package com.mu.spring.Test5_import;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author : MUZUKI
 * @date : 2023-01-10 18:16
 **/

/**
 * 需求：根据条件(是否已经加载好了 Pear，判断是否加载 Grape到容器，且指定beanid)
 */
public class FruitNameImportBeanDefinitionRegister implements ImportBeanDefinitionRegistrar {
    /**
     * @param annotationMetadata : 当前扫描的注解
     * @param registry : 已经注册好的bean的容器
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry registry) {
        //先判断是否已经有了Pear加载到容器
        boolean bean = registry.containsBeanDefinition("com.mu.spring.Test3_factoryBean.Pear");
        //如果有 再创建 Grape Bean加载到容器
        if (bean){
            RootBeanDefinition d = new RootBeanDefinition(Grape.class);
            //指定键为 grape
            registry.registerBeanDefinition("grape",d);
        }
    }
}
