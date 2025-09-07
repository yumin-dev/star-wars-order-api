package com.rm.demo.security;

import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JwtTokenFilterTest {

  private JwtTokenFilter jwtTokenFilter;
  private HttpServletRequest request;
  private HttpServletResponse response;
  private FilterChain filterChain;

  @BeforeEach
  void setUp() throws Exception {
    jwtTokenFilter = new JwtTokenFilter("http://dummy-jwks-url");
    request = mock(HttpServletRequest.class);
    response = mock(HttpServletResponse.class);
    filterChain = mock(FilterChain.class);
  }

  @Test
  void testDoFilterInternal_withMissingAuthorizationHeader() throws Exception {
    when(request.getHeader("Authorization")).thenReturn(null);

    jwtTokenFilter.doFilterInternal(request, response, filterChain);

    verify(response)
        .sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing or invalid Authorization header");
    verifyNoInteractions(filterChain);
  }

  @Test
  void testDoFilterInternal_withInvalidToken() throws Exception {
    when(request.getHeader("Authorization")).thenReturn("Bearer invalid.token.value");

    jwtTokenFilter.doFilterInternal(request, response, filterChain);

    verify(response).sendError(eq(HttpServletResponse.SC_UNAUTHORIZED), contains("Invalid JWT"));
    verifyNoInteractions(filterChain);
  }
}
