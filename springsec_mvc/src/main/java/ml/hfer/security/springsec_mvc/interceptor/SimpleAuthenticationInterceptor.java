package ml.hfer.security.springsec_mvc.interceptor;

import ml.hfer.security.springsec_mvc.service.UserDto;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@Component
@Order(1)
public class SimpleAuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断登录
        Object attribute = request.getSession().getAttribute(UserDto.SESSION_USER_KEY);
        if (attribute == null) {
            writeContent(response, "dengLu");
        }
        UserDto user = (UserDto) attribute;
        UserDto user2 = (UserDto) attribute;

        try {
            user = (UserDto) attribute;
        } catch (RuntimeException e) {

        } catch (Exception e) {

        }


        StringBuffer requestURL = request.getRequestURL();
        String uri = request.getRequestURI();

        //jianquan
        if (user.getAuthorities().contains("p1") && uri.contains("/r1")) {//  访问r1鉴权判断
            return true;
        }
        if (user.getAuthorities().contains("p2") && uri.contains("/r2")) {//   访问r2鉴权判断
            return true;
        }
        writeContent(response, "权限不足，拒绝访问");
        return false;
    }

    private void writeContent(HttpServletResponse response, String content) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.print(content);
        writer.close();
        response.resetBuffer();
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("this is postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("this is after");
    }
}
