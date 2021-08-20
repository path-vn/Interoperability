import java.io.FileInputStream;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.globits.PdmaAdapterApplication;

public class TestCallTemplate {
	public static RestTemplate restTemplate() throws Exception {
	    KeyStore clientStore = KeyStore.getInstance("PKCS12");
	    clientStore.load(new FileInputStream("D:\\Working\\GLOBITS\\HIV_Cdc\\openhim-core-js-master\\resources\\certs\\globits\\certificate.pem"), "123456".toCharArray());
	 
	    SSLContextBuilder sslContextBuilder = new SSLContextBuilder();
	    sslContextBuilder.useProtocol("TLS");
	    sslContextBuilder.loadKeyMaterial(clientStore, "123456".toCharArray());
	    sslContextBuilder.loadTrustMaterial(clientStore);
	 
	    SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContextBuilder.build());
	    CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslConnectionSocketFactory).build();
	    HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
	    requestFactory.setConnectTimeout(10000); // 10 seconds
	    requestFactory.setReadTimeout(10000); // 10 seconds
	    return new RestTemplate(requestFactory);
	}
	public static void getExample() {
		try {
		    // request url
		    String url = "http://test.globits.net:5001/pdmaadapter/1";

		    // create auth credentials
		    String authStr = "GlobitsClient:123456";
		    String base64Creds = Base64.getEncoder().encodeToString(authStr.getBytes());

		    // create headers
		    HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.APPLICATION_JSON);
		    headers.add("Authorization", "Basic " + base64Creds);
		    //List<JSONObject> listPersonJsonObject = new ArrayList<JSONObject>();
		    JSONObject personJsonObject = new JSONObject();
		    personJsonObject.put("code", "code");
		    //listPersonJsonObject.add(personJsonObject);
		    // create request
		    //HttpEntity request = new HttpEntity(headers);
		    
			MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
			map.add("code", "code");		    
			
		    HttpEntity<String> request = new HttpEntity<String>(personJsonObject.toString(), headers);
		    
			//HttpEntity request = new HttpEntity(headers);
		    //HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
		    
		    // make a request
		    //ResponseEntity<String> response = new RestTemplate().exchange(url, HttpMethod.POST, request, String.class);
		    //url = "http://localhost:3443/fhir/public/encounters/1";
		    String personResultAsJsonStr =  new RestTemplate().postForObject(url, request, String.class);
		    System.out.println(personResultAsJsonStr);
		    // get JSON response
		    //String json = response.getBody();

		} catch (Exception ex) {
		    ex.printStackTrace();
		}
	}
	
	public static void getSSLExample() {
		try {
		    // request url
		    String url = "https://test.globits.net:5000/pdmaadapter/1";

		    // create auth credentials
		    String authStr = "GlobitsClient:123456";
		    String base64Creds = Base64.getEncoder().encodeToString(authStr.getBytes());

		    // create headers
		    HttpHeaders headers = new HttpHeaders();
		    headers.add("Authorization", "Basic " + base64Creds);

		    // create request
		    List<JSONObject> listPersonJsonObject = new ArrayList<JSONObject>();
		    JSONObject personJsonObject = new JSONObject();
		    personJsonObject.put("code", "code");
		    personJsonObject.put("name", "NTL");
		    listPersonJsonObject.add(personJsonObject);
		    HttpEntity<String> request = new HttpEntity<String>(listPersonJsonObject.toString(), headers);
		    
		    KeyStore clientStore = KeyStore.getInstance("PKCS12");
		    clientStore.load(new FileInputStream("D:\\Working\\GLOBITS\\HIV_Cdc\\openhim-core-js-master\\resources\\certs\\globits\\certificate.p12"), "123456".toCharArray());
		 
		    SSLContextBuilder sslContextBuilder = new SSLContextBuilder();
		    sslContextBuilder.useProtocol("TLS");
		    sslContextBuilder.loadKeyMaterial(clientStore, "123456".toCharArray());
		    sslContextBuilder.loadTrustMaterial(clientStore);
		 
		    SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContextBuilder.build());
		    CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslConnectionSocketFactory).build();
		    HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
		    requestFactory.setConnectTimeout(10000); // 10 seconds
		    requestFactory.setReadTimeout(10000); // 10 seconds
		    
		    // make a request (dùng exchange hay postForObject đều được, nhưng lưu ý JSONObject phải đúng)
		    //ResponseEntity<String> response = new RestTemplate().exchange(url, HttpMethod.POST, request, String.class);

		    //String personResultAsJsonStr =  new RestTemplate().postForObject(url, request, String.class);
		    //System.out.println(personResultAsJsonStr);
		    // Dùng exchange
		    ResponseEntity<String> response = new RestTemplate(requestFactory).exchange(url, HttpMethod.POST, request, String.class);

		    // get JSON response
		    String json = response.getBody();
		    System.out.println(json);

		} catch (Exception ex) {
		    ex.printStackTrace();
		}
	}	
	public static void main(String[] args) {
		//getExample();
		getSSLExample();
		//SpringApplication.run(OpcAssistAdapterApplication.class, args);
	}	
}
