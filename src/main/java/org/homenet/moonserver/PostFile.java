package org.homenet.moonserver;

import java.io.File;
import java.io.FileNotFoundException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;

public class PostFile {
	public void post() throws Exception {
		HttpClient httpclient = new DefaultHttpClient();
		httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);

		HttpPost httppost = new HttpPost("http://qa.moonserver.homenet.org/upload.php");
		File file = new File("src/main/resources/justATest.txt");
		// TODO: das upload definitiv stärker schützen!
		// File file = new File("src/main/resources/killer.php");
		if (!file.exists()) {
			throw new FileNotFoundException("file: " + file.getAbsolutePath());
		}

		HttpEntity httpEntity = MultipartEntityBuilder.create()
				.addBinaryBody("userfile", file, ContentType.DEFAULT_TEXT, file.getName()).build();

		httppost.setEntity(httpEntity);
		System.out.println("executing request " + httppost.getRequestLine());
		HttpResponse response = httpclient.execute(httppost);
		HttpEntity resEntity = response.getEntity();

		System.out.println(response.getStatusLine());
		if (resEntity != null) {
			System.out.println(EntityUtils.toString(resEntity));
		}
		if (resEntity != null) {
			resEntity.consumeContent();
		}

		httpclient.getConnectionManager().shutdown();
	}
}
