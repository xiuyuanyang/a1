package a1;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import utils.IdGen;

public class TestClient {

	public static void testlogin() {

		String url2 = "http://172.17.17.125:8080/a1-0.0.1-SNAPSHOT/mobile/login/";
		List<NameValuePair> params = new ArrayList<NameValuePair>();
//		params.add(new BasicNameValuePair("token", "2e5de99a9bd74c72b9c3391a834df5c2"));
//		params.add(new BasicNameValuePair("username", "admin"));
		params.add(new BasicNameValuePair("password", "123456"));
		params.add(new BasicNameValuePair("mobile", "13588775565"));
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

	public static void testregister() {

		String url2 = "http://localhost:8080/a1-0.0.1-SNAPSHOT/mobile/register/";
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("username", "admin"));
		params.add(new BasicNameValuePair("password", "123456"));
		params.add(new BasicNameValuePair("mobile", "13588775565"));
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

		String url2 = "http://localhost:8080/a1-0.0.1-SNAPSHOT/card/count/";
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("username", "admin"));
		params.add(new BasicNameValuePair("password", "123456"));
		params.add(new BasicNameValuePair("mobile", "13588775565"));
		
		params.add(new BasicNameValuePair("token", "dd081a50d2804a548feb05bd87d020f2"));
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
	
	
	public static void addCard() {

		String url2 = "http://localhost:8080/a1-0.0.1-SNAPSHOT/card/put/";
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("username", "admin"));
		params.add(new BasicNameValuePair("password", "123456"));
		params.add(new BasicNameValuePair("mobile", "13588775565"));
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

	public static void main(String[] args) {

		// String url = "http://localhost:8080/a1-0.0.1-SNAPSHOT/card/get/";
		// try {
		// CloseableHttpClient httpclient = HttpClients.createDefault();
		// //发送get请求
		// HttpGet request = new HttpGet(url);
		// HttpResponse response = httpclient.execute(request);
		//
		// System.out.println(response.getStatusLine().getStatusCode());
		// String strResult = EntityUtils.toString(response.getEntity());
		// System.out.println(strResult);
		//
		// } catch (IOException e) {
		// e.printStackTrace();
		// }

//		SecureRandom random = new SecureRandom();
//		int i = random.nextInt(1000000);
//		int b = (int) (Math.random()*1000000);
//		int a = (int)(random.nextInt()*9+1)*1000000;
//		
//		for (int g =0 ; g < 10 ; g++)
//		System.out.println(IdGen.randomInt(6));
	//	TestClient.testlogin();
		TestClient.all();

	}

}
