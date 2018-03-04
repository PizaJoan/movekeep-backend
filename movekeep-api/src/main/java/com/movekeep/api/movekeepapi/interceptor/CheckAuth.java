package com.movekeep.api.movekeepapi.interceptor;

import com.movekeep.api.movekeepapi.interceptor.authorization.Authenticate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CheckAuth extends HandlerInterceptorAdapter {

    @Autowired
    private Authenticate authenticator;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if ("OPTIONS".equals(request.getMethod())) return super.preHandle(request, response, handler);

        if (!authenticator.isAuthenticated(request)) {

            response.sendError(401, "Unauthorized");
            return false;
        }

        return super.preHandle(request, response, handler);
    }

}