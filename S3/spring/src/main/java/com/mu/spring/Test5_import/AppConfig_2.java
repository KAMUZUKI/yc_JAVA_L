package com.mu.spring.Test5_import;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author : MUZUKI
 * @date : 2023-01-10 18:15
 **/

@Configuration
@Import({Banana.class ,FruitImportSelector.class})
public class AppConfig_2 {
}
