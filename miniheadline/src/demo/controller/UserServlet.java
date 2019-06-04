package demo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.sf.json.*;
import demo.service.UserJDBCTemplate;
import demo.domain.User;

public class UserServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);    
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     
        String url = request.getRequestURI();
        
        System.out.println(url);
        
        String methodName = url.substring(url.lastIndexOf("/")+1);
        Method method = null;
        System.out.println(methodName);
        
        try {
            
            method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, request, response);
            
        } catch (Exception e) {
            throw new RuntimeException("调用方法出错！");
        }
    }
    
	protected void getUser(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		
		  // 获取参数
          String uid = request.getParameter("uid");
          
          System.out.println("uid " + uid);

          response.setContentType("text/html");
          response.setCharacterEncoding("utf-8");

          PrintWriter out = response.getWriter();

          String msg = null;
          JSONObject obj = new JSONObject();
          
          
          ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
          UserJDBCTemplate template1 = (UserJDBCTemplate)context.getBean("UserJDBCTemplate");
          User user = template1.getUser(Integer.parseInt(uid));
          	
          if(user != null){
              
              obj.put("username", user.getId());
              obj.put("password", user.getPassword());
              obj.put("birthday", user.getDate());
              obj.put("description", user.getDesription());
              obj.put("pic_url", user.getPicUrl());
              obj.put("address", user.getAddress());
              
              msg = obj.toString();
              
          }
          else {
              msg = "failed";
          }

          out.print(msg);
          out.flush();
          out.close();
         
    }
	
	protected void addUser(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		
		
        
    }

     

}
