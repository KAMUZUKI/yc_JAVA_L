package com.mu.spring.Test5_import;

import com.mu.spring.Test3_factoryBean.Pear;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.function.Predicate;

/**
 * @author : MUZUKI
 * @date : 2023-01-10 18:16
 **/

public class FruitImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{Pear.class.getName()};
    }
}
