package com.hli.services.config;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CorsFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse corsResponse = (HttpServletResponse) response;
        HttpServletRequest corsRequest = (HttpServletRequest) request;
        corsResponse.setHeader("Access-Control-Allow-Origin", "*");
        corsResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, PATCH");
        corsResponse.setHeader("Access-Control-Max-Age", "3800");
        corsResponse.setHeader("Access-Control-Allow-Headers", "content-type, Authorizations");
        if ("OPTIONS".equalsIgnoreCase(corsRequest.getMethod())) {
            corsResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
            corsResponse.setHeader("Access-Control-Max-Age", "3800");
            corsResponse.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
            corsResponse.setStatus(HttpServletResponse.SC_OK);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}