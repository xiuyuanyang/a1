package a1;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.imageio.stream.FileImageInputStream;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.FrameworkServlet;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HttpServletBean;

import beans.User;
import dao.UserDao;
import param.SetPhotosParam;

public class TestClient {
	
	public static void testlogout() {

		String url2 = "http://mynamecard.hengtiansoft.com/a1-0.0.1-SNAPSHOT/mobile/logout";
		String body = "{\"token\":\"d3c84c24a82d4beeaae6a11364bd0530\"}";
		
		try {
			CloseableHttpClient httpclient = HttpClients.createDefault();

			HttpPost request = new HttpPost(url2);			
			StringEntity params2 = new StringEntity(body);
			
			request.addHeader("content-type", "application/json");
			request.setEntity(params2);
			HttpResponse response = httpclient.execute(request);

			System.out.println(response.getStatusLine().getStatusCode());
			String strResult = EntityUtils.toString(response.getEntity());
			System.out.println(strResult);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void testverison() {

		String url2 = "http://mynamecard.hengtiansoft.com/a1-0.0.1-SNAPSHOT/info/version";
	//	String url2 = "http://172.17.17.125:8080/a1-0.0.1-SNAPSHOT/info/version";
		try {
			CloseableHttpClient httpclient = HttpClients.createDefault();

			HttpGet request = new HttpGet(url2);
			HttpResponse response = httpclient.execute(request);

			System.out.println(response.getStatusLine().getStatusCode());
			String strResult = EntityUtils.toString(response.getEntity());
			System.out.println(strResult);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void clean() {

		String url2 = "http://172.17.17.125:8080/a1-0.0.1-SNAPSHOT/card/remove/";
		
		
		String body = "{\"token\":\"38cbb1b4fca34d96a370079cd43a849b\"}";
		
//		List<NameValuePair> params = new ArrayList<NameValuePair>();
//		params.add(new BasicNameValuePair("token", "2e5de99a9bd74c72b9c3391a834df5c2"));

		try {
			CloseableHttpClient httpclient = HttpClients.createDefault();

			HttpPost request = new HttpPost(url2);
//			request.addHeader("content-type", "application/x-www-form-urlencoded");
//			
//			request.setEntity(new UrlEncodedFormEntity(params));
//			HttpResponse response = httpclient.execute(request);
			
			
			StringEntity params2 = new StringEntity(body);
			
			request.addHeader("content-type", "application/json");
			request.setEntity(params2);
			HttpResponse response = httpclient.execute(request);

			System.out.println(response.getStatusLine().getStatusCode());
			String strResult = EntityUtils.toString(response.getEntity());
			System.out.println(strResult);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void testlogin() {

		String url2 = "http://mynamecard.hengtiansoft.com/a1-0.0.1-SNAPSHOT/mobile/login/";
		
		
		String body = "{\"mobile\":\"13588775565\",\"password\":\"123456\"}";
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
//		params.add(new BasicNameValuePair("token", "2e5de99a9bd74c72b9c3391a834df5c2"));

		params.add(new BasicNameValuePair("password", "123456"));
		params.add(new BasicNameValuePair("mobile", "13588775565"));
		try {
			CloseableHttpClient httpclient = HttpClients.createDefault();

			HttpPost request = new HttpPost(url2);
//			request.addHeader("content-type", "application/x-www-form-urlencoded");
//			
//			request.setEntity(new UrlEncodedFormEntity(params));
//			HttpResponse response = httpclient.execute(request);
			
			
			StringEntity params2 = new StringEntity(body);
			
			request.addHeader("content-type", "application/json");
			request.setEntity(params2);
			HttpResponse response = httpclient.execute(request);

			System.out.println(response.getStatusLine().getStatusCode());
			String strResult = EntityUtils.toString(response.getEntity());
			System.out.println(strResult);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void testregister() {

		String url2 = "http://172.17.17.125:8080/a1-0.0.1-SNAPSHOT/mobile/register/";
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("username", "TingZhi"));
		params.add(new BasicNameValuePair("password", "123456"));
		params.add(new BasicNameValuePair("mobile", "13575746708"));
		try {
			CloseableHttpClient httpclient = HttpClients.createDefault();

			HttpPost request = new HttpPost(url2);
			request.setEntity(new UrlEncodedFormEntity(params));
			HttpResponse response = httpclient.execute(request);

			System.out.println(response.getStatusLine().getStatusCode());
			String strResult = EntityUtils.toString(response.getEntity());
			System.out.println(strResult);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void all() {

		String url2 = "http://172.17.17.125:8080/a1-0.0.1-SNAPSHOT/card/count/";
//		List<NameValuePair> params = new ArrayList<NameValuePair>();
//		params.add(new BasicNameValuePair("username", "admin"));
//		params.add(new BasicNameValuePair("password", "123456"));
//		params.add(new BasicNameValuePair("mobile", "13588775565"));
		
//	params.add(new BasicNameValuePair("token", "b46bc1d5cfd246b2bb84b75982e617b7"));
		try {
			CloseableHttpClient httpclient = HttpClients.createDefault();

			HttpPost request = new HttpPost(url2);

			String body = "{\"token\":\"b46bc1d5cfd246b2bb84b75982e617b7\"}";
			StringEntity params2 = new StringEntity(body);
			
			request.addHeader("content-type", "application/json");
			request.setEntity(params2);
//			request.setEntity(new UrlEncodedFormEntity(params));
			HttpResponse response = httpclient.execute(request);

			System.out.println(response.getStatusLine().getStatusCode());
			String strResult = EntityUtils.toString(response.getEntity());
			System.out.println(strResult);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void addCard() {

		String url2 = "http://172.17.17.125:8080/a1-0.0.1-SNAPSHOT/card/put/";
//		List<NameValuePair> params = new ArrayList<NameValuePair>();
//		params.add(new BasicNameValuePair("username", "admin"));
//		params.add(new BasicNameValuePair("password", "123456"));
//		params.add(new BasicNameValuePair("mobile", "13588775565"));
		try {
			CloseableHttpClient httpclient = HttpClients.createDefault();

			HttpPost request = new HttpPost(url2);

			String body = "{\"seriesNumber\" : 3,\"selected\" : 1,\"phone\" : \"\",\"mobile\" : \"\",\"company\" : \"\",\"title\" : \"\",\"fax\" : \"\",\"address\" : \"USATiburonCA1747 Steuart Street\",\"themeType\" : 0,\"language\" : 0,\"token\" : \"b9867f800b6440299b4ff403dc329e56\",\"website\" : \"\",\"photoLink\" : \"\",\"name\" : \"David Taylor\",\"email\" : \"\"}";
			StringEntity params2 = new StringEntity(body);
			
			
			
			request.addHeader("content-type", "application/json");
			
//			
//			CloseableHttpClient httpclient = HttpClients.createDefault();
//
//			HttpPost request = new HttpPost(url2);
//			request.setEntity(new UrlEncodedFormEntity(params));
			request.setEntity(params2);
			HttpResponse response = httpclient.execute(request);

			System.out.println(response.getStatusLine().getStatusCode());
			String strResult = EntityUtils.toString(response.getEntity());
			System.out.println(strResult);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void put() {

		String url2 = "http://mynamecard.hengtiansoft.com/a1-0.0.1-SNAPSHOT/card/put/";
//		List<NameValuePair> params = new ArrayList<NameValuePair>();
//		params.add(new BasicNameValuePair("token", "b46bc1d5cfd246b2bb84b75982e617b7"));
//
//		params.add(new BasicNameValuePair("password", "123456"));
//		params.add(new BasicNameValuePair("mobile", "13588775565"));
//		
//		params.add(new BasicNameValuePair("name", "zhang san"));
//		params.add(new BasicNameValuePair("photolink", "zheshiyigelink"));
//		params.add(new BasicNameValuePair("company", "hengtian"));
//		params.add(new BasicNameValuePair("title", "ceo"));
//		params.add(new BasicNameValuePair("email", "nihao@qq.com"));
//		params.add(new BasicNameValuePair("phone", "13566664444"));
//		params.add(new BasicNameValuePair("fax", "123456789"));
//		params.add(new BasicNameValuePair("mobile", "13588885555"));
//		params.add(new BasicNameValuePair("address", "fasdfadsfadf"));
//		params.add(new BasicNameValuePair("website", "web"));

			
		try {
			CloseableHttpClient httpclient = HttpClients.createDefault();

			HttpPost request = new HttpPost(url2);
			String body = "{\"id\":\"\",   \"seriesNumber\" : 3,\"selected\" : 1,\"phone\" : \"123456\",\"mobile\" : \"789\",\"company\" : \"\",\"title\" : \"\",\"fax\" : \"\",\"address\" : \"USATiburonCA1747 Steuart Street\",\"themeType\" : 0,\"language\" : 0,\"token\" : \"713e82051ba547dfa119c6853970d68e\",\"website\" : \"\",\"photolink\" : \"http://172.17.17.125:8080/a1-0.0.1-SNAPSHOT/res/photo/005d3505b6aa472bbfa1caea434ba553.png\",\"name\" : \"David Taylor\",\"email\" : \"\"\n}";
//			StringEntity params2 = new StringEntity(body);
			
			
			
			request.addHeader("content-type", "application/json");
			StringEntity params2 = new StringEntity(body);
//			request.setEntity(new UrlEncodedFormEntity(params));
			
			request.setEntity(params2);
			HttpResponse response = httpclient.execute(request);

			System.out.println(response.getStatusLine().getStatusCode());
			String strResult = EntityUtils.toString(response.getEntity());
			System.out.println(strResult);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void get() {

		String url2 = "http://mynamecard.hengtiansoft.com/a1-0.0.1-SNAPSHOT/card/get/";
		
		
		try {
			CloseableHttpClient httpclient = HttpClients.createDefault();

			HttpPost request = new HttpPost(url2);
			String body = "{\"id\" : \"db33cc6869b340b7886f205348daa7a6\",\"token\" : \"bb2f8158a09e4dfabfcba7b646fd1a28\"\n}";
//			StringEntity params2 = new StringEntity(body);
			
			
			
			request.addHeader("content-type", "application/json");
			StringEntity params2 = new StringEntity(body);
//			request.setEntity(new UrlEncodedFormEntity(params));
			
			request.setEntity(params2);
			HttpResponse response = httpclient.execute(request);

			System.out.println(response.getStatusLine().getStatusCode());
			String strResult = EntityUtils.toString(response.getEntity());
			System.out.println(strResult);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void setPhtoto() {

		String url2 = "http://mynamecard.hengtiansoft.com/a1-0.0.1-SNAPSHOT/card/setPhoto/";
		File f2 = new File("C:/Users/xiuyuanyang/Desktop/namecard.png");
		
		try {
			
			byte[] data = null;
		    FileImageInputStream input = null;
		   
		      input = new FileImageInputStream(f2);
		      ByteArrayOutputStream output = new ByteArrayOutputStream();
		      byte[] buf = new byte[1024];
		      int numBytesRead = 0;
		      while ((numBytesRead = input.read(buf)) != -1) {
		      output.write(buf, 0, numBytesRead);
		      }
		      
		      
		      data = output.toByteArray();
		      input.close();
		      output.close();
		      
			String encodedString = Base64.encodeBase64String(data);
			
			System.out.println(encodedString);
			CloseableHttpClient httpclient = HttpClients.createDefault();

			HttpPost request = new HttpPost(url2);
			String body = "{\"photo\" : \""+  encodedString + "\"}";			
			
			
			request.addHeader("content-type", "application/json");
			StringEntity params2 = new StringEntity(body);		
			request.setEntity(params2);
			HttpResponse response = httpclient.execute(request);

			System.out.println(response.getStatusLine().getStatusCode());
			String strResult = EntityUtils.toString(response.getEntity());
			System.out.println(strResult);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void setPhtotos() {

		String url2 = "http://mynamecard.hengtiansoft.com/a1-0.0.1-SNAPSHOT/card/setPhotos/";
		File f2 = new File("C:/Users/xiuyuanyang/Desktop/namecard.png");
		
		try {
			
			byte[] data = null;
		    FileImageInputStream input = null;
		   
		      input = new FileImageInputStream(f2);
		      ByteArrayOutputStream output = new ByteArrayOutputStream();
		      byte[] buf = new byte[1024];
		      int numBytesRead = 0;
		      while ((numBytesRead = input.read(buf)) != -1) {
		      output.write(buf, 0, numBytesRead);
		      }
		      
		      
		      data = output.toByteArray();
		      input.close();
		      output.close();
		      
			String encodedString = Base64.encodeBase64String(data);
			
			System.out.println(encodedString);
			CloseableHttpClient httpclient = HttpClients.createDefault();

			HttpPost request = new HttpPost(url2);
			String body = "{\"photo\": [{\"no\":\"1\",\"content\" : \""+  encodedString + "\"} ,{\"no\":\"2\",\"content\" : \""+  encodedString + "\"} ] }";			
			
			
			request.addHeader("content-type", "application/json");
			StringEntity params2 = new StringEntity(body);		
			request.setEntity(params2);
			HttpResponse response = httpclient.execute(request);

			System.out.println(response.getStatusLine().getStatusCode());
			String strResult = EntityUtils.toString(response.getEntity());
			System.out.println(strResult);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void jsontest() throws SocketException {

		String encodedString = "hehehe";
		String body = "{\"photo\": [{\"no\":\"1\",\"content\" : \""+  encodedString + "\"} ,{\"no\":\"2\",\"content\" : \""+  encodedString + "\"} ] }";			
		
		 java.lang.annotation.Annotation a;
		JSONObject o = new JSONObject(body);
		Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
		JSONArray ja = o.getJSONArray("photo");
		Socket sk = new Socket();
		
		System.out.println(new Enumeration(){

			@Override
			public boolean hasMoreElements() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public Object nextElement() {
				// TODO Auto-generated method stub
				return null;
			}
			
		}.getClass().getName());
		
//		ja.
		
//		System.out.println(" ja = "+ ja.toString());
//		
//		for (JSONObject io : ja) {
//			SetPhotosParam l = (SetPhotosParam)io;
			
		//	JSONObject l = new JSONObject(io);
			
//			
//			String a1 = l.getContent();
//			String a2 = l.getNo();
//			System.out.println(" a1 = "+ a1 + " a2 = "+a2 );
			
//		}
		
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException  {

//	    TestClient.put();
//		
		TestClient.testregister();
//		TestClient.testVerison();
//		System.out.println(int[][][].class.toString());
//		System.out.println(a.getClass());
		
//		System.out.println(Class.forName("java.util.AA").toString());

	}
}
