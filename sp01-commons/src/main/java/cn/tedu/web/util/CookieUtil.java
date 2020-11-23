package cn.tedu.web.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
    /**
     *  添加Cookie
     * @param response
     * @param name      Cookie名
     * @param value     Cookie值
     * @param domain    Cookie作用域
     * @param path      Cookie
     * @param maxAge    Cookie超时时间
     */
    public static void setCookie(
            HttpServletResponse response,
            String name,String value,
            String domain,String path,int maxAge){
        Cookie cookie=new Cookie(name, value);
        if (domain!=null){
            cookie.setDomain(domain);
        }
        cookie.setPath(path);
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    public static void setCookie(
            HttpServletResponse response,
            String name,String value,int maxAge){
        setCookie(response, name, value, null, "/", maxAge);

    }

    public static void setCookie(HttpServletResponse response, String name, String value) {
        setCookie(response, name, value, null, "/", 3600);
    }

    public static void setCookie(HttpServletResponse response, String name) {
        setCookie(response, name, "", null, "/", 3600);
    }
}
