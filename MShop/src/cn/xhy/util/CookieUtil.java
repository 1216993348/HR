package cn.xhy.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class CookieUtil {
    /**
     * 负责保存Cookie的方法,可以设置Cookie内容,和保存时间
     * @param response 使用addCookie()方法将Cookie保存到客户端浏览器上
     * @param name Cookie的名字
     * @param value Cookie的内容
     * @param expiry Cookie的保存时间
     */
    public static void save(HttpServletResponse response, HttpServletRequest request, String name, String value, int expiry){
        Cookie cook = new Cookie(name,value);
        cook.setMaxAge(expiry);
        cook.setPath(request.getContextPath());
        response.addCookie(cook);
    }
    public static Map<String,String> load(HttpServletRequest request){
        Map map = new HashMap<String,String>();
        Cookie cookies[] = request.getCookies();
        if(cookies != null){
            for (int i = 0 ;i < cookies.length ;i++){
                if(!"JSESSIONID".equals(cookies[i].getName())){
                    map.put(cookies[i].getName(),cookies[i].getValue());
                }
            }
        }
        return map;
    }
    public static void clean(HttpServletRequest request,HttpServletResponse response){
        Cookie cookies[] = request.getCookies();
        if(cookies != null){
            for (int i = 0 ;i < cookies.length ;i++){
                cookies[i].setMaxAge(0);
                cookies[i].setPath(request.getContextPath());
                response.addCookie(cookies[i]);
            }
        }
    }

}
