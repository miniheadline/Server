package demo.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import demo.service.*;
import demo.service.relationship.*;

public class databaseTest {
	
   public static void main(String[] args) {
      ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");

      UserJDBCTemplate template1 = (UserJDBCTemplate)context.getBean("UserJDBCTemplate");
      NewsJDBCTemplate template2 = (NewsJDBCTemplate)context.getBean("NewsJDBCTemplate");
      VideoJDBCTemplate template3 = (VideoJDBCTemplate)context.getBean("VideoJDBCTemplate");
      CommentJDBCTemplate template4 = (CommentJDBCTemplate)context.getBean("CommentJDBCTemplate");
      
      template1.insert("蔡倓", "123456", "urlxxx", "本地测试", "内环东路", "1998.12.11");
      
      template2.insert("www.baidu.com");
      template3.insert("www.baidu.com");
      
      template4.insert(1, "这是一条评论", "2019.1.1.00.11.22", 0, 0);
      
      
      InterCmtJDBCTemplate template5  = (InterCmtJDBCTemplate)context.getBean("InterCmtJDBCTemplate");
      NewsCmtJDBCTemplate template6  = (NewsCmtJDBCTemplate)context.getBean("NewsCmtJDBCTemplate");
      UserRelationJDBCTemplate template7  = (UserRelationJDBCTemplate)context.getBean("UserRelationJDBCTemplate");
      UsersCmtJDBCTemplate template8  = (UsersCmtJDBCTemplate)context.getBean("UsersCmtJDBCTemplate");
      UserToNewsJDBCTemplate template9  = (UserToNewsJDBCTemplate)context.getBean("UserToNewsJDBCTemplate");
      UserToVideoJDBCTemplate template10 = (UserToVideoJDBCTemplate)context.getBean("UserToVideoJDBCTemplate");
      VideoCmtJDBCTemplate template11 = (VideoCmtJDBCTemplate)context.getBean("VideoCmtJDBCTemplate");
      
      template5.insert(1, 2, 2);
      template6.insert(2, 2);
      template7.insert(2, 2);
      template8.insert(2, 2);
      template9.insert(2, 2, 3, 2);
      template10.insert(2, 2, 2, 2);
      template11.insert(2, 2);
      
   }
}