package com.globits.hiv.receive.utilities;

import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Base64;
import java.util.List;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.globits.hiv.receive.dto.HivCaseReportDto;
import com.globits.hiv.receive.dto.PatientDto;
import com.google.gson.Gson;



public class RestTemplateUtils {
	//String ROOT_URI = "https://test.globits.net:5000";
	public static String OpenHimPath ="https://test.globits.net:5000/csapichannel/1";
		
	//////////
	// public static  KeyStore getKeystore() throws NoSuchAlgorithmException, CertificateException, IOException, KeyStoreException {
	// 	KeyStore clientStore = KeyStore.getInstance("PKCS12");
	// 	clientStore.load(ResourceUtil.Instance().getFileFromResourceAsStream("certificate.p12"),
	// 			"123456".toCharArray());
	// 	// clientStore.load(new FileInputStream("E:\\openhim-core-js-master\\resources\\certs\\globits\\certificate.p12"), "123456".toCharArray());

	// 	return clientStore;
	// }

	public static  KeyStore getKeystore() throws NoSuchAlgorithmException, CertificateException, IOException, KeyStoreException {
		KeyStore clientStore = KeyStore.getInstance("PKCS12");
		clientStore.load(ResourceUtil.Instance().getFileFromResourceAsStream("certificate.p12"),
				"123456".toCharArray());
		return clientStore;
	}
	public static ResponseEntity<String> postObjectToOpenHim(HivCaseReportDto object) {
		try {
			// request url
			String url = OpenHimPath;
			// create auth credentials
			String authStr = "GlobitsClient:123456";
			String base64Creds = Base64.getEncoder().encodeToString(authStr.getBytes());

			// create headers
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", "Basic " + base64Creds);
			headers.add("Content-Type", "text/plain;charset=UTF-8");
			headers.add("Accept-Charset", "UTF-8");
			
//			headers.add("Content-Type", "application/json");
			Gson gson = new Gson();
			String body = gson.toJson(object.getPatientDto(), PatientDto.class);
//			String body =object.getContent();
			//String bodyEncode = Base64.getMimeEncoder().encodeToString(body.getBytes(StandardCharsets.UTF_8));
//			if(IsUsingEncodeData) {
//				body = Base64.getMimeEncoder().encodeToString(body.getBytes(StandardCharsets.UTF_8));
//			}
			HttpEntity<String> request = new HttpEntity<String>(body, headers);

//			KeyStore clientStore = KeyStore.getInstance("PKCS12");
//			clientStore.load(ResourceUtil.Instance().getFileFromResourceAsStream("certificate.p12"),
//					"123456".toCharArray());
			KeyStore clientStore = getKeystore();
			SSLContextBuilder sslContextBuilder = new SSLContextBuilder();
			sslContextBuilder.useProtocol("TLS");
			sslContextBuilder.loadKeyMaterial(clientStore, "123456".toCharArray());
			sslContextBuilder.loadTrustMaterial(clientStore);

			SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(
					sslContextBuilder.build());
			CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslConnectionSocketFactory)
					.build();
			HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(
					httpClient);
//			requestFactory.setConnectTimeout(10000); // 10 seconds
//			requestFactory.setReadTimeout(10000); // 10 seconds

			// ResponseEntity<String> response = new RestTemplate().exchange(url,
			// HttpMethod.POST, request, String.class);

			// String personResultAsJsonStr = new RestTemplate().postForObject(url, request,
			// String.class);
			// System.out.println(personResultAsJsonStr);
			// Dùng exchange
			RestTemplate restTemplate = new RestTemplate(requestFactory);
			//restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
			
			boolean writeAcceptCharSet = false;
			List<HttpMessageConverter<?>> c = restTemplate.getMessageConverters();
			for (HttpMessageConverter<?> mc : c) {
			  if (mc instanceof StringHttpMessageConverter) {
			    StringHttpMessageConverter mcc = (StringHttpMessageConverter) mc;
			    mcc.setWriteAcceptCharset(writeAcceptCharSet);
			  }
			}
			
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request,
					String.class);
			
			// get JSON response
//			String json = response.getBody();
//			System.out.println(json);
			return response;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	
	
	
}
