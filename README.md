# Server



### 一、java resources结构目录

![在这里插入图片描述](https://img-blog.csdnimg.cn/20190604230113573.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0NhaXRf,size_16,color_FFFFFF,t_70)

demo.controller:  控制页面列表，用于实现HTTP请求，是需要团队各人分工合作的部分；

demo.domain：基本类User、Video、News、Comment；

demo.domain.relationship：基本类之间的关系类；

demo.dao：处理基本类的接口，接口内包含了insert，delete，get等函数；

demo.dao.relationship：处理关系类的接口，接口内包含了insert，delete，get等函数；

demo.service：和基本类有关，实现接口函数的类，连接domain和底层数据库；

demo.service.relationship：和关系类有关，实现接口函数的类，连接domain和底层数据库；


### 二、配置
本地运行环境为：Eclipse Jee + MySQL + tomcat + jdk

在java resource中，有两个文件可供测试：

（1）demo.service.databaseTest， 右键Run As  ---> Java Application，运行后控制台输出如下；

![在这里插入图片描述](https://img-blog.csdnimg.cn/20190604232825773.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0NhaXRf,size_16,color_FFFFFF,t_70)

（2）选择整个文件，右键Run As --->  Run On Sever，在eclipse中访问localhost，启动界面显示404，这是由于还未输入url；

![在这里插入图片描述](https://img-blog.csdnimg.cn/20190604233215414.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0NhaXRf,size_16,color_FFFFFF,t_70)

输入: "http://localhost:8080/miniheadline/getUser?uid=2"

![在这里插入图片描述](https://img-blog.csdnimg.cn/20190604233308779.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0NhaXRf,size_16,color_FFFFFF,t_70)

### 三、如何调用数据库

在demo.service和demo.service.relationship中，我已经将一些基础的操作封装在了对应的JDBCTemplate中，并于Application.xml中注册，需要注意的是：由于update参数列表不定，所以还没有封装，若有需要烦请各位根据自己的实际情况手动添加；

以NewsJDBCTemplate为例，它可调用的方法如下：

![在这里插入图片描述](https://img-blog.csdnimg.cn/20190604234818354.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0NhaXRf,size_16,color_FFFFFF,t_70)

具体使用请参照databaseTest.java文件：

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


在getUser的HTTP请求中，我们根据uid查询，以JSON形式返回用户数据：

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

### 四、小组成员如何添加自己的功能接口

下面以UserServlet为例；

首先创建一个类UserServlet，继承HttpServlet，然后我们在这个类内添加和User有关的方法，例如getUser(int uid), deleteUser(int uid)

![在这里插入图片描述](https://img-blog.csdnimg.cn/20190604233552612.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0NhaXRf,size_16,color_FFFFFF,t_70)

当HTTP请求进入UserSerlvet之后，需要判断执行哪个方法，例如getUser或者deleteUser，通过String methodName = url.substring(url.lastIndexOf("/")+1) ，获取方法名，然后invoke相关函数；

例如，当url = "http://localhost:8080/miniheadline/getUser?uid=2"，系统自动忽略  ' ? ' 之后的部分，那么 url.substring(url.lastIndexOf("/")+1) 的结果为：'getUser'，再通过method.invoke，跳转到对应的函数中执行功能；

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

当我们添加了getUser方法之后，还需要在web.xml文件中注册这个事件；

![在这里插入图片描述](https://img-blog.csdnimg.cn/20190604234331134.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0NhaXRf,size_16,color_FFFFFF,t_70)

创建UserServlet.class之后，web.xml文件中添上：

	   <servlet>
	      <servlet-name>UserServlet</servlet-name>
	      <servlet-class>
	         demo.controller.UserServlet
	      </servlet-class>
	      <load-on-startup>1</load-on-startup>
	   </servlet>

在UserServlet.class中添加了getUser函数之后，web.xml文件中添上：

	   <servlet-mapping>
	      <servlet-name>UserServlet</servlet-name>
	      <url-pattern>/getUser</url-pattern>
	   </servlet-mapping>
	  
在UserServlet.class中添加了addUser函数之后，web.xml文件中添上：

	   <servlet-mapping>
	      <servlet-name>UserServlet</servlet-name>
	      <url-pattern>/addUser</url-pattern>
	   </servlet-mapping>
	   
那么，当我们输入地址为： "http://localhost:8080/miniheadline/getUser"之后，服务器就会判别到这个地址归属于UserServlet，然后进入UserServlet.class中执行；
