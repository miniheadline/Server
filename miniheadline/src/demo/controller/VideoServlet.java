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

public class VideoServlet extends HttpServlet{

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
   
    protected void getVideo(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		
		// 获取参数
        String vid = request.getParameter("vid");
        
        System.out.println("vid " + vid);

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();

        String msg = null;
        JSONObject obj = new JSONObject();
        
        
        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        VideoJDBCTemplate template1 = (VideoJDBCTemplate)context.getBean("VideoJDBCTemplate");
        Video video = template1.getVideo(Integer.parseInt(vid));
        	
        if(video != null){
            
            obj.put("vid", video.getId());
            obj.put("url", video.getUrl());
            obj.put("title", video.getTitle());
            obj.put("info", video.getInfo());
            obj.put("from_uid", video.getUid());

            
            msg = obj.toString();
            
        }
        else {
            msg = "failed";
        }

        out.print(msg);
        out.flush();
        out.close();
       
    }
    
    protected void UserWithVideo(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
    	
        int uid = Integer.parseInt( request.getParameter("uid") );
        int vid = Integer.parseInt( request.getParameter("vid") );
        int type= Integer.parseInt( request.getParameter("type") );  // type 0:浏览，1:点赞，2:收藏
        
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();
        
        System.out.println("uid&vid: " + uid + " " + vid + " " + type);

        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        UserToVideoJDBCTemplate template  = (UserToVideoJDBCTemplate)context.getBean("UserToVideoJDBCTemplate");
        
        
        // 浏览
        if (type == 0) {
        	System.out.println("look");
        	template.hasRead(uid, vid, type);
        }
        else if (type == 1 || type == 2) {
        	System.out.println("inverse");
        	template.inverse(uid, vid, type);
        }
        
        
        out.print("success.");
        out.flush();
        out.close();
    }
    
    protected void isUserConnectWithVideo(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		
        int uid = Integer.parseInt( request.getParameter("uid") );
        int vid = Integer.parseInt( request.getParameter("vid") );
        int type= Integer.parseInt( request.getParameter("type") );  // type 0:浏览，1:点赞，2:收藏
        
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();
        
        System.out.println("uid&vid: " + uid + " " + vid + " " + type);

        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        UserToVideoJDBCTemplate template  = (UserToVideoJDBCTemplate)context.getBean("UserToVideoJDBCTemplate");
        
        boolean error_code = template.isConnect(uid, vid, type);
        
        JSONObject obj = new JSONObject();
        obj.put("status", error_code);
        
        out.print(obj.toString());
        out.flush();
        out.close();
    }
    
    
    protected void uploadVideo(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		
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
    	
    	String url = obj.getString("url");
    	String title = obj.getString("title");
    	String info = obj.getString("introduction");
    	int uid = obj.getInt("from_uid");
    	
    	ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
    	VideoJDBCTemplate template1 = (VideoJDBCTemplate)context.getBean("VideoJDBCTemplate");
        
    	int vid = template1.upload(url, info, title, uid);
    	
        JSONObject res = new JSONObject();
        res.put("vid", vid);
        	
        PrintWriter out = response.getWriter();
       	out.print(res);
       	out.flush();
        out.close();
       
    	
    }
	
}
// 0:浏览，1:点赞，2:收藏，3:评论