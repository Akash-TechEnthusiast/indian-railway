package com.india.railway.authservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
/*
 * 
 * import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
 */
/* 
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import io.jsonwebtoken.SignatureException; */

import jakarta.servlet.DispatcherType;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    // @Autowired
    // private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            try {
                username = jwtUtil.extractUsername(jwt);
            } catch (ExpiredJwtException e) {
                String isRefreshToken = request.getHeader("isRefreshToken");
                String requestURL = request.getRequestURL().toString();//
                if (isRefreshToken != null && isRefreshToken.equals("true") && requestURL.contains("refreshtoken")) {
                    allowForRefreshToken(e, request);
                } else {
                    request.setAttribute("exception", e);
                }
                // SignatureException | MalformedJwtException | UnsupportedJwtException
                // | IllegalArgumentException
            } catch (Exception ex) {
                System.err.println("Invalid JWT signature");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                response.getWriter().write("{\"error\": \"Invalid or expired token\"}");
                return;
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null)

        {

            /*
             * UserDetails userDetails =
             * this.userDetailsService.loadUserByUsername(username);
             * 
             * if (jwtUtil.validateToken(jwt, userDetails.getUsername())) {
             * 
             * UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new
             * UsernamePasswordAuthenticationToken(
             * userDetails, null, userDetails.getAuthorities());
             * usernamePasswordAuthenticationToken
             * .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
             * SecurityContextHolder.getContext().setAuthentication(
             * usernamePasswordAuthenticationToken);
             * 
             * // Generate a new token with an extended expiry time and set it in the
             * response
             * // header
             * String newJwt = jwtUtil.refreshToken(jwt);
             * response.setHeader("Authorization", "Bearer " + newJwt);
             * } else {
             * 
             * // If the token is invalid, return 401 Unauthorized error
             * response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
             * response.setContentType("application/json");
             * response.getWriter().write("{\"error\": \"Invalid or expired token\"}");
             * return; // Stop further processing
             * 
             * }
             */
        }
        chain.doFilter(request, response);
    }

    private void allowForRefreshToken(ExpiredJwtException ex, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                null, null, null);
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        request.setAttribute("claims", ex.getClaims());
    }
}
