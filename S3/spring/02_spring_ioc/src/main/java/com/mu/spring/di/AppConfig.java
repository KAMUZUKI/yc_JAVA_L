package com.mu.spring.di;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

/**
 * @author : MUZUKI
 * @date : 2023-02-25 15:16
 **/

@Configuration
@ComponentScan
@PropertySource("classpath:db.properties")
public class AppConfig {
    private static final Log logger = LogFactory.getLog(AppConfig.class.getName());

    @Value("${jdbc.driver}")
    private String driver;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    @Value("#{T(java.lang.Runtime).getRuntime().availableProcessors()*2}")
    private Integer cpuCount;

    @Bean(initMethod = "init")
    public DruidDataSource dataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(driver);
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);

        //设置连接池大小
        logger.info("Druid连接池大小为：" + cpuCount);
        druidDataSource.setMaxActive(cpuCount);
        return druidDataSource;
    }
}
