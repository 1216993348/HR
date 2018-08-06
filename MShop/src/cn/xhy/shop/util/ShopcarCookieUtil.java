package cn.xhy.shop.util;

import cn.xhy.util.CookieUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ShopcarCookieUtil {
    public static void addCar(HttpServletRequest request, HttpServletResponse response,Integer gid,Integer count){
        CookieUtil.save(response,request,"sc-" + String.valueOf(gid),String.valueOf(count),100000);
    }
    public static Map<Integer,Integer> load(HttpServletRequest request){
        Map<Integer,Integer> result = new HashMap<>();
        Map<String,String> map = CookieUtil.load(request);
        Iterator<Map.Entry<String,String>> iter = map.entrySet().iterator();
        while(iter.hasNext()){
            Map.Entry<String,String> entry= iter.next();
            if(entry.getKey().startsWith("sc-")){
                result.put(Integer.parseInt(entry.getKey().split("-")[1]),Integer.parseInt(entry.getValue()));
            }
        }
        return result;
    }
    public static void removeCar(Set<Integer> ids, HttpServletRequest request, HttpServletResponse response){
        Iterator<Integer> iter = ids.iterator();
        while(iter.hasNext()){
            CookieUtil.save(response,request,"sc-" + iter.next(),"",0);
        }
    }

}
