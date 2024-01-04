package work.twgj.springsecuritydemo.utils;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * spring security 5.X版本，此版本需要提供一个PasswordEncorder的实例，否则后台汇报错误：
 * java.lang.IllegalArgumentException: There is no PasswordEncoder mapped for the id "null"
 * @author weijie.zhu
 * @date 2023/12/1 10:49
 */
public class LoginPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(charSequence.toString());
    }
}
