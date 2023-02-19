package com.mu.spring.Test5_import;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author : MUZUKI
 * @date : 2023-01-10 18:15
 **/

@Configuration  //pear的托管  Grape的托管(只有Pear托管，才会托管Grape)
@Import({Banana.class,FruitImportSelector.class, FruitNameImportBeanDefinitionRegister.class})
public class AppConfig_3 {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig_3.class);
        String[] names = context.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }
    }
}
