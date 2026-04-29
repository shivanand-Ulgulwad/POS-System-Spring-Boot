package com.app.config;


import com.app.service.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component

public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private  JwtUtility jwtUtility;
    @Autowired
    private  CustomUserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")){
            String token = header.substring(7);
            String username = jwtUtility.extractUserName(token);



            Authentication authentication = null;

             if ( username != null && SecurityContextHolder.getContext().getAuthentication() == null){
               if( jwtUtility.isTokenValid(token)){
                   UserDetails details = userDetailsService.loadUserByUsername(username);
                    authentication = new UsernamePasswordAuthenticationToken(
                            details,
                            null,
                            details.getAuthorities()
                    );
               }

               if (authentication != null){
                   SecurityContextHolder.getContext().setAuthentication(authentication);
               }


            }

        }
        filterChain.doFilter(request,response);
    }
}
