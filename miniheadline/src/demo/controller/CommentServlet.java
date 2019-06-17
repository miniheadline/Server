package demo.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Date;
import java.text.SimpleDateFormat;

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

public class CommentServlet extends HttpServlet{

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
    protected void getComment(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		
		  // 获取参数
        String cid = request.getParameter("cid");
        
        System.out.println("cid " + cid);

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();

        String msg = null;
        JSONObject obj = new JSONObject();
        
        
        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        CommentJDBCTemplate template1 = (CommentJDBCTemplate)context.getBean("CommentJDBCTemplate");
        Comment cmt = template1.getComment( Integer.parseInt(cid) );
        	
        if(cmt != null){
            
            obj.put("text", cmt.getText());
            obj.put("from_uid", cmt.getFid());
            obj.put("replyNum", cmt.getReplyNum());
            obj.put("likeNum",  cmt.getLikeNum());
            obj.put("time", cmt.getTime());
 
            
            msg = obj.toString();
            
        }
        else {
            msg = "failed";
        }

        out.print(msg);
        out.flush();
        out.close();
       
  }
    
    protected void add_news_comment(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		
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
    	
    	int uid = obj.getInt("uid");
    	int nid = obj.getInt("nid");
    	String text = obj.getString("text");
    	
    	ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
    	CommentJDBCTemplate template1 = (CommentJDBCTemplate)context.getBean("CommentJDBCTemplate");
    	NewsCmtJDBCTemplate template2 = (NewsCmtJDBCTemplate)context.getBean("NewsCmtJDBCTemplate");
    	
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
    	String time = (df.format(new Date()));
    	
    	int cid = template1.insert(uid, text, time, 0, 0);
    	
    	System.out.println("cid&nid: " + cid + " " + nid);
    	
		template2.insert(nid, cid);
		
		JSONObject res = new JSONObject();
    	res.put("cid", cid);
    	
    	PrintWriter out = response.getWriter();
    	out.print(res);
        out.flush();
        out.close();
    }
	
    protected void add_videos_comment(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		
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
    	
    	int uid = obj.getInt("uid");
    	int vid = obj.getInt("vid");
    	String text = obj.getString("text");
    	
    	ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
    	CommentJDBCTemplate template1 = (CommentJDBCTemplate)context.getBean("CommentJDBCTemplate");
    	VideoCmtJDBCTemplate template2 = (VideoCmtJDBCTemplate)context.getBean("VideoCmtJDBCTemplate");
    	
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
    	String time = (df.format(new Date()));
    	
    	int cid = template1.insert(uid, text, time, 0, 0);
    	
    	System.out.println("cid&vid: " + cid + " " + vid);
    	
		template2.insert(vid, cid);
		
		JSONObject res = new JSONObject();
    	res.put("cid", cid);
    	
    	PrintWriter out = response.getWriter();
    	out.print(res);
        out.flush();
        out.close();
    }
    
    protected void add_second_comment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
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
    	
    	int uid = obj.getInt("uid");
    	int parent_cid = obj.getInt("pid");
    	String text = obj.getString("text");
    	
    	ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
    	CommentJDBCTemplate template1 = (CommentJDBCTemplate)context.getBean("CommentJDBCTemplate");
    	InterCmtJDBCTemplate template2 = (InterCmtJDBCTemplate)context.getBean("InterCmtJDBCTemplate");
    	
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
    	String time = (df.format(new Date()));
    	
    	int cid = template1.insert(uid, text, time, 0, 0);
    	
    	System.out.println("cid&pid: " + cid + " " + parent_cid);
    	
		template2.insert(parent_cid, cid, uid);
		
		JSONObject res = new JSONObject();
    	res.put("cid", cid);
    	
    	PrintWriter out = response.getWriter();
    	out.print(res);
        out.flush();
        out.close();
    }
    
    
}
