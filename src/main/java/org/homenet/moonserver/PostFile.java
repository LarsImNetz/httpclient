package org.homenet.moonserver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class PostFile {
	public void post() throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpPost postRequest = new HttpPost("http://qa.moonserver.homenet.org/upload.php");
			// postRequest.addHeader("Authorization",authHeader);
//			String boundary = "-------------" + System.currentTimeMillis();
// 			postRequest.addHeader("Content-type", "multipart/form-data; boundary=" + boundary);
			
			File file = new File("src/main/resources/justATest.txt");
			// TODO: das upload definitiv stärker schützen! Wir wollen nur bestimmte Typen zulassen.
			// File file = new File("src/main/resources/killer.php");
			if (!file.exists()) {
				throw new FileNotFoundException("file: " + file.getAbsolutePath());
			}

			FileBody bin = new FileBody(file, ContentType.DEFAULT_TEXT, "just-a-test.txt");

			FileInputStream fis = new FileInputStream(file);
			InputStreamBody isb = new InputStreamBody(fis, ContentType.DEFAULT_TEXT);

			StringBody comment = new StringBody("A binary file of some kind", ContentType.TEXT_PLAIN);

			HttpEntity httpEntity = MultipartEntityBuilder.create()
//					.setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
//					.setBoundary(boundary)
					.addPart("other", isb)
					.addPart("comment", comment)
					.addPart("userfile", bin)
					.build();

			postRequest.setEntity(httpEntity);

			System.out.println("executing request " + postRequest.getRequestLine());
			CloseableHttpResponse response = httpclient.execute(postRequest);
			try {
				System.out.println("----------------------------------------");
				System.out.println(response.getStatusLine());
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					System.out.println("Response: " + EntityUtils.toString(resEntity));
					System.out.println("Response content length: " + resEntity.getContentLength());
				}
				EntityUtils.consume(resEntity);
			} finally {
				response.close();
			}
		} finally {
			httpclient.close();
		}
	}
}