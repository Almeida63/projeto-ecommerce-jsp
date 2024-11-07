package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import connection.ConnectionBanco;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


//@WebFilter("/Filter")
@WebFilter(urlPatterns = { "/*"})
public class Filter extends HttpFilter implements jakarta.servlet.Filter {
       
    private static Connection connection;
	
    public Filter() {
    }

	
	public void destroy() {
	try {
		connection.close();
	}catch(SQLException e) {
		e.printStackTrace();
	}
	
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	   
		
        chain.doFilter(request, response);
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
	  connection = ConnectionBanco.getConnection();
	}

}
