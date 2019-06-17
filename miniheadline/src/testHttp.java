import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import net.sf.json.*;

public class testHttp {

	public static String sendGet(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);
			// �򿪺�URL֮�������
			URLConnection connection = realUrl.openConnection();
			// ����ͨ�õ���������
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
			"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// ����ʵ�ʵ�����
			connection.connect();
			// ��ȡ������Ӧͷ�ֶ�
			
			Map<String, List<String>> map = connection.getHeaderFields();
			// �������е���Ӧͷ�ֶ�
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// ���� BufferedReader����������ȡURL����Ӧ
			in = new BufferedReader(new InputStreamReader(
			connection.getInputStream()));
			
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
			
			System.out.println(result);
			
		} catch (Exception e) {
		System.out.println("����GET��������쳣��" + e);
		e.printStackTrace();
		}
		// ʹ��finally�����ر�������
		finally {
		try {
		if (in != null) {
		in.close();
		}
		} catch (Exception e2) {
		e2.printStackTrace();
		}
		}
		return result;
	}
	
	public static void doPost(String httpUrl, String param)  {
	
		try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("connection", "Keep-Alive");
            //connection.setRequestProperty("Content-Type", "text/plain; charset=utf-8");
            
            connection.setDoInput(true);  
            connection.setDoOutput(true);  
            
            connection.connect();
            //POST����
            DataOutputStream out = new DataOutputStream(
                    connection.getOutputStream());
            JSONObject obj = new JSONObject();

            obj.put("url", "www.baibaidudu.com");
            obj.put("title", "caitan");
            obj.put("from_uid", 3);
            obj.put("introduction", "dididididid");

            out.writeBytes(obj.toString());
            System.out.println("data="+obj.toString());
            out.flush();
            out.close();
            //��ȡ��Ӧ
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String lines;
            StringBuffer sb = new StringBuffer("");
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes(), "utf-8");
                sb.append(lines);
            }
            
            System.out.println("��Ӧ: " + sb);
            
            System.out.println(sb);
            reader.close();
            connection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	public static void doPost2(String httpUrl, String param)  {
		
		try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("connection", "Keep-Alive");
            //connection.setRequestProperty("Content-Type", "text/plain; charset=utf-8");
            
            connection.setDoInput(true);  
            connection.setDoOutput(true);  
            
            connection.connect();
            //POST����
            DataOutputStream out = new DataOutputStream(
                    connection.getOutputStream());
            JSONObject obj = new JSONObject();

            obj.put("uid", "3");
            obj.put("pid", "3");
            obj.put("text", "123456789");

            out.writeBytes(obj.toString());
            System.out.println("data="+obj.toString());
            out.flush();
            out.close();
            //��ȡ��Ӧ
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String lines;
            StringBuffer sb = new StringBuffer("");
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes(), "utf-8");
                sb.append(lines);
            }
            
            System.out.println("��Ӧ: " + sb);
            
            System.out.println(sb);
            reader.close();
            connection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	
		
	
	public static void main(String[] args)  {
		
		sendGet("http://149.28.26.98:8082/miniheadline/getUser", "uid=2");
		
		
		//doPost("http://localhost:8080/miniheadline/Login", "uid=2");
		
		//sendGet("http://149.28.26.98:8082/miniheadline/UserWithNews", "uid=2&nid=3&type=1");
		
		//sendGet("http://localhost:8080/miniheadline/isUserConnect?uid=2&nid=3&type=1");
		
		//sendGet("http://localhost:8080/miniheadline/isUserConnectWithVideo", "uid=2&vid=5&type=0");
		
		//sendGet("http://localhost:8080/miniheadline/getVideo", "vid=3");
		
		//doPost("http://149.28.16.98:8082/miniheadline/uploadVideo", "uid=2");
	}
	
}
