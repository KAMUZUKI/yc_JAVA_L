package com.mu.spring.Test6_conditional;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

/**
 * @author : MUZUKI
 * @date : 2023-01-10 23:39
 **/

@Component
@Conditional({SystemCondition.class})
public class NetConnection {
}
