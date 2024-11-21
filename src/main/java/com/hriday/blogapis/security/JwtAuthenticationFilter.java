package com.hriday.blogapis.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Autowired
//    private JWTTokenHelper jwtTokenHelper;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//
//        //get Token
//        String requestToken = request.getHeader("Authorization");
//        String userName = null;
//        String token = null;
//        if(requestToken != null && requestToken.startsWith("Bearer ")){
//           token = requestToken.substring(7);
//           try {
//               userName = this.jwtTokenHelper.getUserNameFromToken(token);
//           }catch (IllegalArgumentException e){
//               System.out.println("Unable to get token");
//           }
//           catch (ExpiredJwtException e){
//               System.out.println("token expires");
//           }
//           catch (MalformedJwtException e){
//               System.out.println("Invalid jwt");
//           }
//        }
//        else{
//            System.out.println("There is no bearer");
//        }
//        if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null){
//            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);
//            if(this.jwtTokenHelper.validateToken(token,userDetails)){
//                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken= new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
//                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//            }else{
//                System.out.println("invalid jwt");
//            }
//        }
//        else{
//            System.out.println("context is null");
//        }
//
//        filterChain.doFilter(request,response);
//    }
//}

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JWTTokenHelper jwtTokenHelper;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JWTTokenHelper jwtTokenHelper, UserDetailsService userDetailsService) {
        this.jwtTokenHelper = jwtTokenHelper;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestToken = request.getHeader("Authorization");
        String userName = null;
        String token = null;

        if (requestToken != null && requestToken.startsWith("Bearer")) {
            token = requestToken.substring(7);
            try {
                userName = jwtTokenHelper.getUserNameFromToken(token);
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get token");
            } catch (ExpiredJwtException e) {
                System.out.println("Token expired");
            } catch (MalformedJwtException e) {
                System.out.println("Invalid JWT");
            }
        } else {
            System.out.println("No Bearer token");
        }

        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
            if (jwtTokenHelper.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            } else {
                System.out.println("Invalid JWT token");
            }
        }
        filterChain.doFilter(request, response);
    }
}

