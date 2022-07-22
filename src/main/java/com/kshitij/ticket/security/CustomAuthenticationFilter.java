package com.kshitij.ticket.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kshitij.ticket.jwt.JwtHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RequiredArgsConstructor
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
  private final AuthenticationManager authenticationManager;
  private final JwtHelper jwtHelper;

  @Override
  public Authentication attemptAuthentication(
      HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    // TODO: 7/21/22 fix cors issue
    response.setHeader("Access-Control-Allow-Origin", "*");

    String email = request.getParameter("email");
    String password = request.getParameter("password");
    log.info("Attempt login : {}", email);
    UsernamePasswordAuthenticationToken token =
        new UsernamePasswordAuthenticationToken(email, password);
    return authenticationManager.authenticate(token);
  }

  @Override
  protected void successfulAuthentication(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain chain,
      Authentication authResult)
      throws IOException, ServletException {
    User principal = (User) authResult.getPrincipal();
    String at = jwtHelper.generateAccessToken(principal, request.getRequestURL().toString());
    String rt = jwtHelper.generateRefreshToken(principal, request.getRequestURL().toString());

    Map<String, String> tokens = new LinkedHashMap<>();
    tokens.put("access_token", at);
    tokens.put("refresh_token", rt);
    response.setContentType(APPLICATION_JSON_VALUE);
    new ObjectMapper().writeValue(response.getOutputStream(), tokens);
  }
}
