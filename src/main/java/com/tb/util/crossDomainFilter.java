package com.tb.util;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * DESC :  解决跨域问题的过滤器
 * @author Lonely
 *
 */
public class crossDomainFilter implements Filter{


        @Override
        public void init(FilterConfig filterConfig) throws ServletException {}

        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
                throws IOException, ServletException {
            try {
                HttpServletRequest httpRequest = (HttpServletRequest) request;
                HttpServletResponse httpResponse = (HttpServletResponse) response;

                // 跨域
                String origin = httpRequest.getHeader("Origin");
                if (origin == null) {
                    httpResponse.addHeader("Access-Control-Allow-Origin", "*");
                } else {
                    httpResponse.addHeader("Access-Control-Allow-Origin", origin);
                }
                httpResponse.addHeader("Access-Control-Allow-Headers",
                        "Origin, x-requested-with, Content-Type, Accept,X-Cookie");
                httpResponse.addHeader("Access-Control-Allow-Credentials", "true");
                httpResponse.addHeader("Access-Control-Allow-Methods", "GET,POST,PUT,OPTIONS,DELETE");
                if (httpRequest.getMethod().equals("OPTIONS")) {
                    httpResponse.setStatus(HttpServletResponse.SC_OK);
                    return;
                }
                chain.doFilter(request, response);
            } catch (Exception e) {
                throw e;
            }
        }

        @Override
        public void destroy() {}
    }

