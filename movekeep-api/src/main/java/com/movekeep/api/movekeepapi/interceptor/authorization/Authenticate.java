package com.movekeep.api.movekeepapi.interceptor.authorization;

import javax.servlet.http.HttpServletRequest;

public interface Authenticate {

    boolean isAuthenticated(HttpServletRequest request);
}
