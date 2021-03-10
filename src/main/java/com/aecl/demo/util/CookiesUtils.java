package com.aecl.demo.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class CookiesUtils {

    /**
     * set cookies
     *
     * @param expired effective time(s)
     */
    public static void setCookies(HttpServletResponse response, String name, String value, int expired) throws UnsupportedEncodingException {
        value = URLEncoder.encode(value, StandardCharsets.UTF_8);
        Cookie cookies = new Cookie(name, value);
        cookies.setMaxAge(expired);
        cookies.setPath("/");
        cookies.setSecure(true);
        cookies.setHttpOnly(true);
        response.addCookie(cookies);
    }

    /**
     * Set cookies
     */
    public static void setCookies(HttpServletResponse response, String name, String value) {
        value = URLEncoder.encode(value, StandardCharsets.UTF_8);
        Cookie cookies = new Cookie(name, value);
        cookies.setPath("/");
        cookies.setSecure(true);
        cookies.setHttpOnly(true);
        response.addCookie(cookies);

    }

    /**
     * Get cookie
     */
    public static String getValue(HttpServletRequest request, String name) throws UnsupportedEncodingException {
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (Objects.equals(cookie.getName(), name)) {
                return URLDecoder.decode(cookie.getValue(), StandardCharsets.UTF_8);
            }
        }
        return null;
    }

}

