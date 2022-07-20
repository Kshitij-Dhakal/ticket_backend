package com.kshitij.ticket.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kshitij.ticket.dto.ErrorResponse;
import com.kshitij.ticket.jwt.JwtHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RequiredArgsConstructor
public class CustomAuthorizationFilter extends OncePerRequestFilter {
  private final JwtHelper jwtHelper;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    if (request.getServletPath().equals("/api/login")) {
      filterChain.doFilter(request, response);
    } else {
      String authorizationHeader = request.getHeader(AUTHORIZATION);
      if (StringUtils.isNotBlank(authorizationHeader)
          && authorizationHeader.startsWith("Bearer ")) {
        try {
          String token = authorizationHeader.substring("Bearer ".length());
          UsernamePasswordAuthenticationToken authenticationToken = jwtHelper.verifyToken(token);
          SecurityContextHolder.getContext().setAuthentication(authenticationToken);
          filterChain.doFilter(request, response);
        } catch (Exception e) {
          log.error("Error authorizing the request : {}", e.getMessage());
          ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
          response.setContentType(APPLICATION_JSON_VALUE);
          response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
          new ObjectMapper().writeValue(response.getOutputStream(), errorResponse);
        }
      } else {
        ErrorResponse errorResponse = new ErrorResponse("Authorization header is missing");
        response.setContentType(APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        new ObjectMapper().writeValue(response.getOutputStream(), errorResponse);
      }
    }
  }
}
