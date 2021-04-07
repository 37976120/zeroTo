package ml.hfer.security.springsec_mvc.init;

import ml.hfer.security.springsec_mvc.config.WebXmlContextLoaderListener;
import ml.hfer.security.springsec_mvc.config.WebXmlDispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 *在init包下定义Spring容器初始化类SpringApplicationInitializer，此类实现WebApplicationInitializer接口，
 * Spring容器启动时加载WebApplicationInitializer接口的所有实现类。
 *SpringApplicationInitializer相当于web.xml，使用了servlet3.0开发则不需要再定义web.xml，
 * ApplicationConfig.class对应以下配置的application-context.xml，WebConfig.class对应以下配置的spring-mvc.xml，web.xml的内容参考：

 */
public class SpringApplicationInitializer extends
        AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { WebXmlContextLoaderListener.class };//指定rootContext的配置类
    }
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { WebXmlDispatcherServlet.class }; //指定servletContext的配置类
    }
    @Override
    protected String[] getServletMappings() {
        return new String [] {"/"};
    }
}

