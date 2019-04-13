package com.example.spring;

import javax.servlet.http.HttpServletRequest;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class RestMatcher implements RequestMatcher {

    // Matcher
    AntPathRequestMatcher matcher;

    // Constructor
    public RestMatcher(String url) {
        super();
        matcher = new AntPathRequestMatcher(url);
    }

    // URL match condition
    @Override
    public boolean matches(HttpServletRequest request) {

        // pass CSRF Check if method is GET
        if ("GET".equals(request.getMethod())) {
            return false;
        }

        // pass CSRF Check if url matches specific condition
        if (matcher.matches(request)) {
            return false;
        }

        return true;
    }
}
