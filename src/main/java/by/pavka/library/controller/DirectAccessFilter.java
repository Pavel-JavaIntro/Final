package by.pavka.library.controller;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(
    urlPatterns = {"/jsp/*"},
    initParams = {@WebInitParam(name = "error", value = "denied.jsp")})
public class DirectAccessFilter implements Filter {
  private String errorPage;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    errorPage = filterConfig.getInitParameter("error");
    System.out.println(errorPage);
  }

  @Override
  public void doFilter(
      ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
      throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    HttpServletResponse response = (HttpServletResponse) servletResponse;
    RequestDispatcher dispatcher = servletRequest.getRequestDispatcher(errorPage);
    dispatcher.forward(request, response);
  }

  @Override
  public void destroy() {
    errorPage = null;
  }
}
