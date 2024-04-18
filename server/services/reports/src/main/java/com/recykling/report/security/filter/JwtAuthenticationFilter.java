package com.recykling.report.security.filter;

import com.recykling.report.security.service.JwtService;
import com.recykling.report.security.user.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsServiceImpl userDetailsService;
    @Override
    protected void doFilterInternal
            (
                   @NonNull HttpServletRequest request,
                   @NonNull HttpServletResponse response,
                   @NonNull FilterChain filterChain
            )
            throws ServletException, IOException
    {
        String authHeader = getAuthHeader.apply(request);

        if(headerIsInvalid.apply(authHeader)) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = getToken.apply(authHeader);
        String username = jwtService.extractUsername(token);

        if(userIsUnauthorized.apply(username)){
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if(jwtService.isValid(token, userDetails)){
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }

    private final Function<String, Boolean>
            headerIsInvalid = authHeader ->
            authHeader == null || !authHeader.startsWith("Bearer ");
    private final Function<HttpServletRequest, String>
            getAuthHeader = request ->
            request.getHeader("Authorization");
    private final Function<String, String>
            getToken = authHeader ->
            authHeader.substring(7);
    private final Function<String, Boolean>
            userIsUnauthorized = username ->
            username != null && SecurityContextHolder.getContext().getAuthentication() == null;
}
