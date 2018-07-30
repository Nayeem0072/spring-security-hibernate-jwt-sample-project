package com.syn.security;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.syn.services.JwtService;

import static com.syn.security.JwtConstants.AUTH_HEADER;


@WebFilter(urlPatterns = { "/authusers/verify" })
public class JwtFilter implements Filter {
 
    @Override 
    public void init(FilterConfig filterConfig) throws ServletException  {}
    @Override 
    public void destroy() {}
 
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
    		throws IOException, ServletException{
    	// declaring and initiating here for thread safety
    	JwtService jwtTokenService = new JwtService();
    	String authHeader = AUTH_HEADER;
    	
        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        final HttpServletResponse httpResponse = (HttpServletResponse) response;
        final String authHeaderVal = httpRequest.getHeader(authHeader);        
        
        if (authHeaderVal == null){
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        
        try {
            String username = jwtTokenService.getUser(authHeaderVal);
            httpRequest.setAttribute("authUser", username);
        }
        catch(JwtException e){
            httpResponse.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
            return;
        }
 
        chain.doFilter(httpRequest, httpResponse);
    }
}