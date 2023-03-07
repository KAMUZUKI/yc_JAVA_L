package org.muframework.context;

import lombok.Data;

/**
 * @author : MUZUKI
 * @date : 2023-02-28 20:31
 **/

@Data
public class BeanDefinition{
    private String beanId;
    private String beanClassName;
    private String classInfo;
    private String scope;
    private boolean lazy;
}
