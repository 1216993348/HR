package cn.xhy.util.validate;

public class ValidateUtil {
    /**
     * 验证数据是否为空
     * @param data 用于验证的字符串
     * @return 如果字符串为null或"" 返回true,否则返回false
     */
    public static boolean ValidateEmpty(String data){
        if(data == null || "".equals(data)){
            return false;
        }
        return true;
    }

    /**
     * 对数据进行正则验证
     * @param data 用于正则验证的字符串
     * @param regex 用于验证的正则表达式
     * @return 如果字符串满足正则验证返回 true， 否则返回false
     */
    public static boolean ValidateRegex(String data,String regex){
        if(ValidateEmpty(data) && ValidateEmpty(regex)){
            return data.matches(regex);
        }
        return false;
    }

    /**
     * 验证两个字符串是否相等
     * @param data0 用于验证的第一个字符串
     * @param data1 用于验证的第二个字符串
     * @return 如果两个字符串相等返回true,否则犯规false
     */
    public static boolean ValidateSame(String data0,String data1){
        if(data0 !=null && data1 != null){
            return data0.equalsIgnoreCase(data1);
        }
        return false;
    }
}
