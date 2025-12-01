package com.example.jwt_auth_with_authorisation.filter;

import com.example.jwt_auth_with_authorisation.JWTUtil.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
//will activate for each request
public class JWTAuthFilter extends OncePerRequestFilter {

    @Autowired
    @Lazy
    private UserDetailsService userDetailsService;

    @Autowired
    private JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = null;
        String username = null;

        // so the incoming request will have the jwt in authorization header  so extract it
        String authorization = request.getHeader("Authorization");
        //it will be something like Bearer klfjdllj.kfdjs.fdsk

        //check it should not be null
        if (authorization != null && authorization.startsWith("Bearer "))
        {
            //extract the token
            token=authorization.substring(7);
            //here extract the username;
            username=jwtUtil.extractUsername(token);
        }

        //if correct we will get the username
        // then check if the username is not null and the principal object should be null then set the context
        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
        {
            UserDetails userDetails=userDetailsService.loadUserByUsername(username);
            //validate both the username are equal or not in the utils
           if( jwtUtil.validateUsernames(userDetails,username,token))
           {
               UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
               authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
               SecurityContextHolder.getContext().setAuthentication(authToken);
           }
        }
        filterChain.doFilter(request,response);

    }
}
