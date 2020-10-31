package by.pavka.library.controller;


import javax.servlet.*;
import java.io.IOException;

public class CharacterSetFilter implements Filter {

  private FilterConfig config;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    config = filterConfig;
  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    servletRequest.setCharacterEncoding("UTF-8");
    servletResponse.setContentType("text/html; charset=UTF-8");
    servletResponse.setCharacterEncoding("UTF-8");
    filterChain.doFilter(servletRequest, servletResponse);
  }

  @Override
  public void destroy() {

  }
}
