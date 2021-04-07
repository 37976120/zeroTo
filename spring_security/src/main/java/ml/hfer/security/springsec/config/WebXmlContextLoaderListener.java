package ml.hfer.security.springsec.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * 在config包下定义ApplicationConfig.java，它对应web.xml中ContextLoaderListener的配置
 * 在此配置除了Controller的其它bean，比如：数据库链接池、事务管理器、业务bean等。
 */
@Configuration
@ComponentScan(basePackages = "ml.hfer.security.springsec"
        , excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class)})
public class WebXmlContextLoaderListener {

}
