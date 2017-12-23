package com.myself.library.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串校验类
 */

public class StringCheck {
    //密码
    public static boolean check(String str) {
        String pa = "\\w{6,18}";
        return str.matches(pa);
    }

    /**
     * 手机号验证
     *
     * @param str
     * @return 验证通过返回true
     */
    public static boolean checkMobile(String str) {
        String pa = "^1[34578]\\d{9}$";
        return str.matches(pa);
    }

    /**
     * 电话号码验证 验证带区号的,验证没有区号的
     *
     * @param str
     * @param hasZone 是否携带区号 true为携带
     * @return 验证通过返回true
     */
    public static boolean checkPhone(String str, boolean hasZone) {
        Pattern p1 = null;
        Pattern p2 = null;
        Matcher m = null;
        boolean b = false;
        if (hasZone) {
            p1 = Pattern.compile("^[0][0-9]{1,3}-[0-9]{5,10}$");  // 验证带区号的
            m = p1.matcher(str);
        } else {
            p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$");         // 验证没有区号的
            m = p2.matcher(str);
        }
        b = m.matches();
        return b;
    }

    /**
     *    检验邮箱
     * @param str
     * @return
     */

    public static boolean checkEmail(String str) {
        String email = str.trim();
        String pa = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        return email.matches(pa);
    }

    /**
     *   检验身份证号码
     * @param str
     * @return
     */

    public static boolean checkIdNum(String str) {
        String mTrim = str.trim();
        String pa = "/^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$/";
        ///^[1-9][0-9]{5}(19[0-9]{2}|200[0-9]|2010)(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])[0-9]{3}[0-9xX]$/
        ///^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/
        ///^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/
        //"\d{14}[[0-9],0-9xX]
        return mTrim.matches(pa);
    }
}
