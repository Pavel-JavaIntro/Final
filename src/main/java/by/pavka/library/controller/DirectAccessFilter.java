package by.pavka.library.controller;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/library?command*"}, servletNames = {"LibraryServlet"})
public class DirectAccessFilter implements Filter {
  private String indexPage;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    indexPage = "/index.jsp";
  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest)servletRequest;
    HttpServletResponse response = (HttpServletResponse)servletResponse;
    HttpSession session = ((HttpServletRequest) servletRequest).getSession();
    System.out.println(request.getMethod());

//    session.setAttribute("page", indexPage);
//    RequestDispatcher dispatcher = servletRequest.getRequestDispatcher(indexPage);
//    dispatcher.forward(request, response);
    System.out.println("INSIDE ACCESS FILTER");
    filterChain.doFilter(servletRequest, servletResponse);
  }

  @Override
  public void destroy() {
    indexPage = null;
  }
}
