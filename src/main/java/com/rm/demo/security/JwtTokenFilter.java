package com.rm.demo.security;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.jwk.source.RemoteJWKSet;
import com.nimbusds.jose.proc.JWSVerificationKeySelector;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.SignedJWT;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;
import java.io.IOException;
import java.net.URL;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

  private final ConfigurableJWTProcessor<SecurityContext> jwtProcessor;

  public JwtTokenFilter(@Value("${security.jwks-url}") String jwksUrl) throws Exception {
    JWKSource<SecurityContext> keySource = new RemoteJWKSet<>(new URL(jwksUrl));
    jwtProcessor = new DefaultJWTProcessor<>();
    jwtProcessor.setJWSKeySelector(new JWSVerificationKeySelector<>(JWSAlgorithm.RS256, keySource));
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    String authHeader = request.getHeader("Authorization");
    if (authHeader != null && authHeader.startsWith("Bearer ")) {
      String token = authHeader.substring(7);
      try {
        SignedJWT signedJWT = SignedJWT.parse(token);
        jwtProcessor.process(signedJWT, null); // throws if invalid
        filterChain.doFilter(request, response); // valid token, continue
        return;
      } catch (Exception e) {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT: " + e.getMessage());
        return;
      }
    } else {
      response.sendError(
          HttpServletResponse.SC_UNAUTHORIZED, "Missing or invalid Authorization header");
    }
  }
}
