package magicware.demo.hashdos.client;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Hello world!
 * 
 */
public class PostCollisionData {

	public static void main(String[] args) throws IOException {
		String url = "http://127.0.0.1:8080/SimpleServlet";
		BufferedReader buf = new BufferedReader(new FileReader("CollisionData.txt"));
		SimpleHttpClient.nativePost(url, buf);
		buf.close();
	}
}
