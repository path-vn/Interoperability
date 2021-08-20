package com.globits.adapter.pdma.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
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

import com.globits.adapter.pdma.dto.AdapterObjectDto;
import com.globits.adapter.pdma.dto.LogEntityDto;
import com.globits.adapter.pdma.dto.PdmaDto;
import com.globits.adapter.pdma.types.AdapterObjectType;
import com.google.gson.Gson;

public class RestTemplateUtils {
	public static String OpenHimPath ="https://test.globits.net:5000/pdmaadapter/1";
	public static String LogPath ="";
	public static Boolean IsUsingEncodeData =false;
	public static  KeyStore getKeystore() throws NoSuchAlgorithmException, CertificateException, IOException, KeyStoreException {
		KeyStore clientStore = KeyStore.getInstance("PKCS12");
		clientStore.load(ResourceUtil.Instance().getFileFromResourceAsStream("certificate.p12"),
				"123456".toCharArray());
		return clientStore;
	}
	
	public static KeyStore getFileKeystore() throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		FileInputStream in = new FileInputStream("D:/Projects/PATH/HIV-INTEROPERABILITY/opc-assist-adapter/opc-assist-adapter-app/src/main/resources/certificate.p12");
		
		KeyStore clientStore = KeyStore.getInstance("PKCS12");
		clientStore.load(in,"123456".toCharArray());
		return clientStore;
		
	}
	
	public static ResponseEntity<String> postObjectToOpenHim(AdapterObjectDto object) {
		try {
			// request url
			String url = OpenHimPath;
			String urlLog = LogPath;
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
			//String bodyEncode = Base64.getMimeEncoder().encodeToString(body.getBytes(StandardCharsets.UTF_8));
			if(IsUsingEncodeData) {
				body = Base64.getMimeEncoder().encodeToString(body.getBytes(StandardCharsets.UTF_8));
			}
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
			try {
				HttpHeaders headerLog = new HttpHeaders();
				headerLog.add("Content-Type", "text/plain;charset=UTF-8");
				headerLog.add("Accept-Charset", "UTF-8");
				if(object.getObjectType().equals(AdapterObjectType.SINGLE_DATA)) {
					LogEntityDto dto = new LogEntityDto();
					if(object.getSingleData() != null) {
						dto.setName(object.getSingleData().getFullname());
						dto.setArtID(object.getSingleData().getPersonId()+"");
						dto.setNationalID(object.getSingleData().getNationalIdNumber());
						dto.setPassportNumber(object.getSingleData().getPassportNumber());
						dto.setHealthInsuranceNumber(object.getSingleData().getPassportNumber());
						dto.setSyncorg("Pdma");
						dto.setLogDate(new Date());
						HttpEntity<LogEntityDto> requestLog = new HttpEntity<LogEntityDto>(dto, headerLog);
//						String bodyLog = gson.toJson(dto, LogEntityDto.class);
//						ResponseEntity<LogEntityDto> responseLogEntity = restTemplate.exchange(urlLog,HttpMethod.POST, requestLog,LogEntityDto.class);
						restTemplate.postForObject(urlLog, dto, LogEntityDto.class);
					}
				}
				if(object.getObjectType().equals(AdapterObjectType.LIST_DATA)) {
					if(object.getListData() != null && object.getListData().size()>0) {
						List<LogEntityDto> listData = new ArrayList<LogEntityDto>();
						for (PdmaDto item : object.getListData()) {
							LogEntityDto dto = new LogEntityDto();
							dto.setName(item.getFullname());
							dto.setArtID(item.getPersonId()+"");
							dto.setNationalID(item.getNationalIdNumber());
							dto.setPassportNumber(item.getPassportNumber());
							dto.setHealthInsuranceNumber(item.getPassportNumber());
							dto.setSyncorg("Pdma");
							dto.setLogDate(new Date());
							listData.add(dto);
						}
						urlLog = urlLog+"/saveList";
						HttpEntity<List<LogEntityDto>> requestLog = new HttpEntity<List<LogEntityDto>>(listData, headerLog);
						restTemplate.postForObject(urlLog, listData, LogEntityDto.class);
//						ResponseEntity<String> responseLogEntity = restTemplate.exchange(urlLog, HttpMethod.POST, requestLog,
//								String.class);
					}
				}
				
				
			} catch (Exception e) {
				// TODO: handle exception
			}

			// get JSON response
//			String json = response.getBody();
//			System.out.println(json);
			return response;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
//					 create headers
					HttpHeaders headers = new HttpHeaders();
					headers.add("Content-Type", "text/plain;charset=UTF-8");
					headers.add("Accept-Charset", "UTF-8");
		
					RestTemplate rest = new RestTemplate();

					LogEntityDto dto = new LogEntityDto();
					dto.setArtID("123456");
					dto.setName("Nguyễn Van A");
					dto.setNationalID("09876565");

					rest.postForObject("http://localhost:8089/adapterlog/public/logEntity", dto, String.class);
		
		
//		RestTemplate restTemplate = new RestTemplate();
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("Content-Type", "application/json;charset=UTF-8");
//		headers.add("Accept-Charset", "UTF-8");
//		
//		HttpEntity<String> request = new HttpEntity<String>("QsO5aSBWxINuIFRow6BuaA==", headers);
//		restTemplate.getMessageConverters()
//        .add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
//		
//		ResponseEntity<String> response = restTemplate.exchange("http://localhost:3443/fhir/public/extraction/decode", HttpMethod.POST, request,String.class);
//		System.out.println(response.getBody());
//		OpenHimPath ="https://test.globits.net:5000/opcassistadapter/1";
//		AdapterObjectDto dto = new AdapterObjectDto();
//		dto.setObjectType(AdapterObjectType.SINGLE_DATA);
//		OpcAssistDto singleData = new OpcAssistDto();
//		singleData.setCaseId(2001L);
//		singleData.setPersonId(2002L);
//		singleData.setFullname("Phạm Văn Trà");
//		singleData.setDob(1987);	
//		
//		dto.setSingleData(singleData);
//		String base64Encode = Base64.getEncoder().encodeToString("Bùi Văn Thanh 1989".getBytes(StandardCharsets.UTF_8));
//		
//		String decodedString = new String(Base64.getDecoder().decode(base64Encode.getBytes(StandardCharsets.UTF_8)));
//		System.out.println(decodedString);
//		 try {
//			 FileInputStream streamIn = new FileInputStream("D:\\Projects\\PATH\\HIV-INTEROPERABILITY\\Adapters\\Data\\test.ser");
//		     ObjectInputStream objectinputstream = new ObjectInputStream(streamIn);
//		     dto = (AdapterObjectDto) objectinputstream.readObject();
//		     dto.getSingleData().setListArvTreatment(null);
//		     dto.getSingleData().setListHivCd4BeforeArt(null);
//		     dto.getSingleData().setListDrugResistance(null);
//		     dto.getSingleData().setListCd4DuringArt(null);
//		     dto.getSingleData().setListHbv(null);
//		     dto.getSingleData().setListHcv(null);
//		     dto.getSingleData().setListHivCd4BeforeArt(null);
//		     dto.getSingleData().setListHivViralLoadDuringArt(null);
//		     dto.getSingleData().setListPregnancy(null);
//		     dto.getSingleData().setListRecency(null);
//		     dto.getSingleData().setListRiskPopulation(null);
//		     dto.getSingleData().setListTBProphylaxis(null);
//		     dto.getSingleData().setListTBTreatment(null);
//		     dto.getSingleData().setLastSynDate(null);
//		     
//		 } catch (Exception e) {
//		     e.printStackTrace();
//		 }
//	     ResponseEntity<String> res = postObjectToOpenHim(dto);
		 
		
	}

}