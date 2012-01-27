package magicware.demo.hashdos.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

public class SimpleHttpClient {
	

	public static void nativePost(String url, BufferedReader content) {
		try {
			URL TestURL = new URL(url);
			URLConnection con = TestURL.openConnection();

			// 送信するよ！指定
			con.setDoOutput(true);

			// --------------------
			// 送信する
			// --------------------
			OutputStreamWriter ow1 = new OutputStreamWriter(
					con.getOutputStream());

			BufferedWriter bw1 = new BufferedWriter(ow1);

			// POSTの内容を書き出す

			char[] buffer = new char[1024];
			while (content.read(buffer) > 0) {
				bw1.write(buffer);
			}

			// クローズ
			bw1.close();
			ow1.close();

			// --------------------
			// 受信する
			// --------------------
			InputStreamReader ir1 = new InputStreamReader(con.getInputStream());
			BufferedReader br1 = new BufferedReader(ir1);

			// １行ずつ書き出す
			String line;
			while ((line = br1.readLine()) != null) {
				// 改行がカットされてるので、printlnになる。
				System.out.println(line);
			}

			// クローズ
			br1.close();
			ir1.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
