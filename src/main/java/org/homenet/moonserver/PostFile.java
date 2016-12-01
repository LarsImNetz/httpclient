package org.homenet.moonserver;

import java.io.File;
import java.io.FileNotFoundException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
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
			
			File file = new File("src/main/resources/justATest.txt");
			// TODO: das upload definitiv stärker schützen!
			// File file = new File("src/main/resources/killer.php");
			if (!file.exists()) {
				throw new FileNotFoundException("file: " + file.getAbsolutePath());
			}

			FileBody bin = new FileBody(file, ContentType.DEFAULT_TEXT, "just-a-test.txt");
			StringBody comment = new StringBody("A binary file of some kind", ContentType.TEXT_PLAIN);

			HttpEntity httpEntity = MultipartEntityBuilder.create().addPart("userfile", bin).addPart("comment", comment)
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