package com.movekeep.api.movekeepapi.authorization;

import javax.servlet.http.HttpServletRequest;

public interface Authenticate {

    boolean isAuthenticated(HttpServletRequest request);
}
