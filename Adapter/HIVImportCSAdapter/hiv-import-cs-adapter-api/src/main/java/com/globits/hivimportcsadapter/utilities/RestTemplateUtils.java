package com.globits.hivimportcsadapter.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
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
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;


import com.globits.hivimportcsadapter.dto.AdapterObjectDto;
import com.globits.hivimportcsadapter.dto.HtcElogDto;
import com.globits.hivimportcsadapter.functiondto.HIVInfoDto;
import com.google.gson.Gson;



public class RestTemplateUtils {
	//String ROOT_URI = "https://test.globits.net:5000";
	public static String OpenHimPath ="https://openhim.globits.net:5000/htcelogrouter/1";
	public static String hIVInfoToOpenHim(List<HIVInfoDto> listData) {
		try {
		    // request url
		    String url = "https://test.globits.net:5000/htcelogrouter/1";

		    // create auth credentials
		    String authStr = "GlobitsClient:123456";
		    String base64Creds = Base64.getEncoder().encodeToString(authStr.getBytes());

		    // create headers
		    HttpHeaders headers = new HttpHeaders();
		    headers.add("Authorization", "Basic " + base64Creds);

		    // create request
		    List<JSONObject> listHIVInfoJsonObject = new ArrayList<JSONObject>();
		    JSONObject hIVInfoJsonObject = new JSONObject();
		    for ( HIVInfoDto eachListHIV: listData) {
		    	hIVInfoJsonObject.put("MaSo", eachListHIV.getMaSo());
		    	hIVInfoJsonObject.put("HoTen", eachListHIV.getHoTen());
		    	hIVInfoJsonObject.put("SoCMND", eachListHIV.getSoCMND());
		    	hIVInfoJsonObject.put("DuongPhoHK", eachListHIV.getDuongPhoHK());
		    	hIVInfoJsonObject.put("ToHK", eachListHIV.getToHK());
		    	hIVInfoJsonObject.put("SoNhaHK", eachListHIV.getSoNhaHK());
		    	hIVInfoJsonObject.put("XaHK", eachListHIV.getXaHK());
		    	hIVInfoJsonObject.put("HuyenHK", eachListHIV.getHuyenHK());
		    	hIVInfoJsonObject.put("TinhHK", eachListHIV.getTinhHK());
		    	hIVInfoJsonObject.put("DuongPhoTT", eachListHIV.getDuongPhoTT());
		    	hIVInfoJsonObject.put("ToTT", eachListHIV.getToTT());
		    	hIVInfoJsonObject.put("SoNhaTT", eachListHIV.getSoNhaTT());
		    	hIVInfoJsonObject.put("XaTT", eachListHIV.getXaTT());
		    	hIVInfoJsonObject.put("HuyenTT", eachListHIV.getHuyenTT());
		    	hIVInfoJsonObject.put("TinhTT", eachListHIV.getTinhTT());
		    	hIVInfoJsonObject.put("DanToc", eachListHIV.getDanToc());
		    	hIVInfoJsonObject.put("GioiTinh", eachListHIV.getGioiTinh());
		    	hIVInfoJsonObject.put("NamSinh", eachListHIV.getNamSinh());
		    	hIVInfoJsonObject.put("NgheNghiep", eachListHIV.getNgheNghiep());
		    	hIVInfoJsonObject.put("IdDoiTuong", eachListHIV.getIdDoiTuong());
		    	hIVInfoJsonObject.put("DoiTuong", eachListHIV.getDoiTuong());
		    	hIVInfoJsonObject.put("IdNguyCo", eachListHIV.getIdNguyCo());
		    	hIVInfoJsonObject.put("NguyCo", eachListHIV.getNguyCo());
		    	hIVInfoJsonObject.put("IdDuongLay", eachListHIV.getIdDuongLay());
		    	hIVInfoJsonObject.put("DuongLay", eachListHIV.getDuongLay());
		    	hIVInfoJsonObject.put("NgayXetNghiem", eachListHIV.getNgayXetNghiem());
		    	hIVInfoJsonObject.put("TenNoiXN", eachListHIV.getTenNoiXN());
		    	hIVInfoJsonObject.put("TenNoiLayMau", eachListHIV.getTenNoiLayMau());
		    	listHIVInfoJsonObject.add(hIVInfoJsonObject);
		    }
		    HttpEntity<String> request = new HttpEntity<String>(listHIVInfoJsonObject.toString(), headers);
		    
		    KeyStore clientStore = KeyStore.getInstance("PKCS12");
//		    clientStore.load(new FileInputStream("D:\\Working\\GLOBITS\\HIV_Cdc\\openhim-core-js-master\\resources\\certs\\globits\\certificate.p12"), "123456".toCharArray());
		    clientStore.load(new FileInputStream("E:\\openhim-core-js-master\\resources\\certs\\globits\\certificate.p12"), "123456".toCharArray());
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

		    String response =  new RestTemplate().postForObject(url, request, String.class);
		    //System.out.println(personResultAsJsonStr);
		    // Dùng exchange
		    //ResponseEntity<String> response = new RestTemplate(requestFactory).exchange(url, HttpMethod.POST, request, String.class);

		    // get JSON response
		    //String json = response.getBody();
		    //System.out.println(json);
		    return response;

		} catch (Exception ex) {
		    ex.printStackTrace();
		}
		return null;
	}	
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
	public static ResponseEntity<String> postObjectToOpenHim(AdapterObjectDto object) {
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
			String body = gson.toJson(object, AdapterObjectDto.class);
			
			System.out.println(body);
			HttpEntity<String> request = new HttpEntity<String>(body, headers);
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
			RestTemplate restTemplate = new RestTemplate(requestFactory);
			
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
			String json = response.getBody();
			System.out.println(json);
			return response;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	
	
	
}
