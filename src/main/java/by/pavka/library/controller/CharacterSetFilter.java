package by.pavka.library.controller;


import javax.servlet.*;
import java.io.IOException;

public class CharacterSetFilter implements Filter {
  private String code;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    code = filterConfig.getInitParameter("encoding");
  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    if (code != null) {
      servletRequest.setCharacterEncoding(code);
      //servletResponse.setContentType("text/html; charset=UTF-8");
      servletResponse.setCharacterEncoding(code);
      System.out.println("CODE = " + code);
    }
    filterChain.doFilter(servletRequest, servletResponse);
  }

  @Override
  public void destroy() {
    code = null;
  }
}
