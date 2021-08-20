package com.globits.adapter.eclinica.utils;

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

import com.globits.adapter.eclinica.data.dto.AdapterObjectDto;
import com.globits.adapter.eclinica.data.dto.LogEntityDto;
import com.globits.adapter.eclinica.data.dto.PatientDto;
import com.globits.adapter.eclinica.data.types.AdapterObjectType;
import com.google.gson.Gson;

public class RestTemplateUtils {
	public static String LogPath ="";
	public static String OpenHimPath = "https://openhim.globits.net:5000/opceclinicaadapter/1";
	public static Boolean IsUsingEncodeData = false;
	public static  KeyStore getKeystore() throws NoSuchAlgorithmException, CertificateException, IOException, KeyStoreException {
		KeyStore clientStore = KeyStore.getInstance("PKCS12");
		clientStore.load(ResourceUtil.Instance().getFileFromResourceAsStream("certificate.p12"),
				"123456".toCharArray());
		return clientStore;
	}

	public static KeyStore getFileKeystore()
			throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		FileInputStream in = new FileInputStream(
//				"D:/OpenHIM/opc-eclinica-adapter/opc-eclinica-adapter-app/src/main/resources/certificate.p12"
				"D:/Working/GLOBITS/HIV_Cdc/openhim-core-js-master/resources/certs/globits/certificate.p12"
				);

		KeyStore clientStore = KeyStore.getInstance("PKCS12");
		clientStore.load(in, "123456".toCharArray());
		return clientStore;

	}

	public static ResponseEntity<String> postObjectToOpenHim(AdapterObjectDto object) {
		try {
			// request url
			String urlLog = LogPath;
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
			// String bodyEncode =
			// Base64.getMimeEncoder().encodeToString(body.getBytes(StandardCharsets.UTF_8));
			if (IsUsingEncodeData) {
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

			// Dùng exchange
			RestTemplate restTemplate = new RestTemplate(requestFactory);
			// restTemplate.getMessageConverters().add(0, new
			// StringHttpMessageConverter(StandardCharsets.UTF_8));

			boolean writeAcceptCharSet = false;
			List<HttpMessageConverter<?>> c = restTemplate.getMessageConverters();
			for (HttpMessageConverter<?> mc : c) {
				if (mc instanceof StringHttpMessageConverter) {
					StringHttpMessageConverter mcc = (StringHttpMessageConverter) mc;
					mcc.setWriteAcceptCharset(writeAcceptCharSet);
				}
			}

			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
			
			try {
				HttpHeaders headerLog = new HttpHeaders();
				headerLog.add("Content-Type", "text/plain;charset=UTF-8");
				headerLog.add("Accept-Charset", "UTF-8");
				if(object.getObjectType().equals(AdapterObjectType.SINGLE_DATA)) {
					LogEntityDto dto = new LogEntityDto();
					if(object.getSingleData() != null) {
						dto.setName(object.getSingleData().getDisplayName());
						dto.setArtID(object.getSingleData().getIdentifier());
						dto.setNationalID(object.getSingleData().getBplCardNo());
						dto.setHealthInsuranceNumber(object.getSingleData().getMedicalInsurance());
						dto.setSyncorg("Eclinica");
						dto.setLogDate(new Date());
						HttpEntity<LogEntityDto> requestLog = new HttpEntity<LogEntityDto>(dto, headerLog);
						restTemplate.postForObject(urlLog, dto, LogEntityDto.class);
					}
				}
				if(object.getObjectType().equals(AdapterObjectType.LIST_DATA)) {
					if(object.getListData() != null && object.getListData().size()>0) {
						List<LogEntityDto> listData = new ArrayList<LogEntityDto>();
						for (PatientDto item : object.getListData()) {
							LogEntityDto dto = new LogEntityDto();
							dto.setName(object.getSingleData().getDisplayName());
							dto.setArtID(object.getSingleData().getIdentifier());
							dto.setNationalID(object.getSingleData().getBplCardNo());
							dto.setHealthInsuranceNumber(object.getSingleData().getMedicalInsurance());
							dto.setSyncorg("Eclinica");
							dto.setLogDate(new Date());
							listData.add(dto);
						}
						urlLog = urlLog+"/saveList";
						HttpEntity<List<LogEntityDto>> requestLog = new HttpEntity<List<LogEntityDto>>(listData, headerLog);
						restTemplate.postForObject(urlLog, listData, LogEntityDto.class);
					}
				}
				
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			return response;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}