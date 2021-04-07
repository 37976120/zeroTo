package ml.hfer.security.springsec_mvc.controller;

import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;
import ml.hfer.security.springsec_mvc.service.AuthenticationRequest;
import ml.hfer.security.springsec_mvc.service.AuthenticationService;
import ml.hfer.security.springsec_mvc.service.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
public class LoginController {
    @Autowired
    private AuthenticationService authenticationService;

    /**
     * 用户登录
     *
     * @param authenticationRequest 登录请求
     * @return
     */
    @PostMapping(value = "/login", produces = {"application/json;charset=UTF-8"})
    public String login(AuthenticationRequest authenticationRequest, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        Cookie cookie = new Cookie("hfer", "v11");
        cookie.setVersion(2);
        response.addCookie(cookie);

        UserDto userDetails = authenticationService.authentication(authenticationRequest);
        session.setAttribute(UserDto.SESSION_USER_KEY, userDetails);
        return userDetails.getFullName() + " 登录成功";
    }

    @GetMapping(value = "/logout", produces = {"application/json;charset=UTF-8"})
    public String logout(HttpSession session) {
        session.invalidate();
        return "退出成功";
    }

    @GetMapping(value = "/r/r1", produces = {"application/json;charset=UTF-8"})
    public String r1(HttpSession session) {
        String fullname = null;
        Object userObj = session.getAttribute(UserDto.SESSION_USER_KEY);
        if (userObj != null) {
            fullname = ((UserDto) userObj).getFullName();
        } else {
            fullname = "匿名";
        }
        return fullname + " 访问资源1";
    }


    //该资源需要授权
    @GetMapping(value = "/r/r2", produces = {"application/json;charset=UTF-8"})
    public String r2(HttpSession session) {
        String fullname = null;
        Object userObj = session.getAttribute(UserDto.SESSION_USER_KEY);
        if (userObj != null) {
            fullname = ((UserDto) userObj).getFullName();
        } else {
            fullname = "匿名";
        }
        return fullname + " 访问资源2";
    }


}
