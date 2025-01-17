package com.tamscrap.config;
 
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

//    @Override
//    public void commence(HttpServletRequest request,
//                         HttpServletResponse response,
//                         AuthenticationException authException)
//                         throws IOException {
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No autorizado");
//    }

	  @Override
	    public void commence(HttpServletRequest request,
	                         HttpServletResponse response,
	                         AuthenticationException authException)
	            throws IOException {
		  System.err.println(authException.getMessage()+"aaaaa");
		  
	        response.setContentType("application/json");
	        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	        response.getWriter().write("{\"error\": \"No autorizado. Token inválido o expirado.\"}");
	    }
}
 