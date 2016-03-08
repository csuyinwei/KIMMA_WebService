package cn.edu.csu.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.csu.pojo.Admin;

public class SecurityFilter extends HttpServlet implements Filter {
    private static final long serialVersionUID = 1L;

    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
            
    	HttpServletRequest request=(HttpServletRequest)arg0;   
    	HttpServletResponse response  =(HttpServletResponse)arg1;    
    	HttpSession session = request.getSession(true);    
    	

    	Admin admin = (Admin)session.getAttribute("admin");//登录人角色
    	String url=request.getRequestURI();   

    	if(admin == null) {      
    		//判断获取的路径不为空且不是访问登录页面或执行登录操作   
    		if(url!=null && !url.equals("") && ( url.indexOf("Login")<0 && url.indexOf("login")<0 )) {   
    			//System.out.println("拦截："+url);
    			//request.setAttribute("nextstep", url);
    			//request.getRequestDispatcher("/admin/login").forward(request, response);
    			response.sendRedirect(request.getContextPath() + "/admin/login?nextstep="+url);
    			
    			return ;   
    		}              
    	}   
    	//System.out.println(url);
    	arg2.doFilter(arg0, arg1);    
    }
    
    public void init(FilterConfig arg0) throws ServletException {
    	
    }

}