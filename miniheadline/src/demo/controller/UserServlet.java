package demo.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.sf.json.*;
import demo.service.*;
import demo.service.relationship.*;
import demo.domain.*;
import demo.domain.relationship.*;

public class UserServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
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
    
    
    /* GET */
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
	
	protected void UserWithNews(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
	
        int uid = Integer.parseInt( request.getParameter("uid") );
        int nid = Integer.parseInt( request.getParameter("nid") );
        int type= Integer.parseInt( request.getParameter("type") );  // type 0:浏览，1:点赞，2:收藏
        
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();
        
        System.out.println("uid&nid: " + uid + " " + nid + " " + type);

        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        UserToNewsJDBCTemplate template  = (UserToNewsJDBCTemplate)context.getBean("UserToNewsJDBCTemplate");
        
        System.out.println("uid&nid: " + uid + " " + nid);
        
        // 浏览
        if (type == 0) {
        	System.out.println("look");
        	template.hasRead(uid, nid, type);
        }
        else if (type == 1 || type == 2) {
        	System.out.println("inverse");
        	template.inverse(uid, nid, type);
        }
        
        
        out.print("success.");
        out.flush();
        out.close();
    }
	
	protected void isUserConnect(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		
        int uid = Integer.parseInt( request.getParameter("uid") );
        int nid = Integer.parseInt( request.getParameter("nid") );
        int type= Integer.parseInt( request.getParameter("type") );  // type 0:浏览，1:点赞，2:收藏
        
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();
        
        System.out.println("uid&nid: " + uid + " " + nid + " " + type);

        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        UserToNewsJDBCTemplate template  = (UserToNewsJDBCTemplate)context.getBean("UserToNewsJDBCTemplate");
        
        boolean error_code = template.isConnect(uid, nid, type);
        
        JSONObject obj = new JSONObject();
        obj.put("status", error_code);
        
        out.print(obj.toString());
        out.flush();
        out.close();
    }
	
	
	/* POST */
	protected void Login(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		
		response.setContentType("text/html;charset=UTF-8");
    	  
    	System.out.println("请求方式："+request.getMethod());
    	System.out.println("URI："+request.getRequestURI());
    	System.out.println("URL："+request.getRequestURL());
    	System.out.println("http协议版本："+request.getProtocol());
    	System.out.println();

    	String host = request.getHeader("Host");
    	System.out.println("请求头："+host);
    	Enumeration<String> enums = request.getHeaderNames();//得到所有请求头列表
    	while(enums.hasMoreElements()){//判断是否有下一个元素
    		String headerName = enums.nextElement();//取出下一位元素
    		String headerValue = request.getHeader(headerName);
    		System.out.println(headerName+"："+headerValue);
    	}

    	System.out.println();
    	
    	//获取请求头的实体内容
    	InputStream is = request.getInputStream();
    	
    	//读实体内容
    	byte[] buf = new byte[1024];
    	int length = 0;
    	String str = "";
    	
    	while ((length = is.read(buf)) > 0){
    		str += new String(buf,0,length);//把实体内容转换成字符串的形式
    		System.out.println("实体内容："+str);
    	}
    	
    	JSONObject obj = JSONObject.fromObject(str);
    	System.out.println( obj.toString() );
    	
    	int post_type = obj.getInt("post_type");
    	String username = obj.getString("username");
    	String password = obj.getString("password");
    	
    	if (post_type == 0) {
    		// 登陆
    		ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
            UserJDBCTemplate template1 = (UserJDBCTemplate)context.getBean("UserJDBCTemplate");
            
            int error_code = template1.logIn(username, password);
            int uid = 0;
            
            if (error_code > 0) {
            	uid = error_code;
            	error_code = 0;
            }
            
            JSONObject res = new JSONObject();
        	res.put("error_code", error_code);
        	res.put("uid", uid);
        	
        	PrintWriter out = response.getWriter();
        	out.print(res);
            out.flush();
            out.close();
            
    	}
    	else {
    		ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
            UserJDBCTemplate template1 = (UserJDBCTemplate)context.getBean("UserJDBCTemplate");
            
            int error_code;
            error_code  = template1.signIn(username, password);
            
            JSONObject res = new JSONObject();
        	res.put("error_code", error_code);
        	
        	PrintWriter out = response.getWriter();
        	out.print(res);
            out.flush();
            out.close();
    	}
    	
		
    }

     

}


