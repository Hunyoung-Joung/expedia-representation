/**
 * Copyright@
 * by jounghunyoung@gmail.com
 * 
 */
package com.expedia.young.demo.ctrl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import com.expedia.young.demo.entity.ConditionInfo;
import com.expedia.young.demo.entity.Properties;
import com.expedia.young.demo.entity.PropertiesAvailability;
import com.expedia.young.demo.entity.Region;
import com.expedia.young.demo.entity.keyInfo;
import com.expedia.young.demo.util.AuthHeaderValueSingleton;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;

/**
 * 
 * Index controller
 * 
 * @author jounghunyoung@gmail.com
 *
 */
@Controller
@RequestMapping(value = {""})
public class indexController {
    
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    
    private String authHeaderValue = null;
    
    @GetMapping
    public String init(Model model) {
    	logger.info("## init ");
        return "index";
    }
    
    /**
     * Show users, user's questions, and survey.
     * 
     * @param model
     * @param userInfo
     * @return
     * @throws IOException 
     */
    @PostMapping(value = {"/search"})
    public String index(Model model, @ModelAttribute("conditionInfo") @Valid ConditionInfo conditionInfo) throws IOException {
    	logger.info("## index? "+conditionInfo.toString());
    	
    	HttpComponentsClientHttpRequestFactory pFactory = new HttpComponentsClientHttpRequestFactory(HttpClientBuilder.create().build());
    	HttpComponentsClientHttpRequestFactory paFactory = new HttpComponentsClientHttpRequestFactory(HttpClientBuilder.create().build());
    	RestTemplate rTemplate = new RestTemplate(pFactory);
    	RestTemplate paTemplate = new RestTemplate(paFactory);
    	AuthHeaderValueSingleton authHeaderValueSingleton = AuthHeaderValueSingleton.getInstance();
    	try {
			authHeaderValue = authHeaderValueSingleton.getAuthHeaderValue();
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
    	HttpHeaders headers = new HttpHeaders();
    	headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    	headers.set("Accept-Encoding", "gzip");
    	headers.set("Authorization", authHeaderValue);
    	headers.set("User-Agent", "Mozilla/5.0");
    	HttpEntity<?> entity = new HttpEntity<>(headers);
    	String conditionModel = "";
    	String requestModel = "";
    	String responseModel = "";
    	String currency = "";
    	String country_code = "";
    	
    	String regionsUrl = keyInfo.getUri()+"regions/"+conditionInfo.getRegion_id()+"?region_id="+conditionInfo.getRegion_id()+"&language=ja-JP&include=details&include=property_ids";
    	logger.info("## regionsUrl? "+regionsUrl);
    	
    	ResponseEntity<Region> RegionResponse = rTemplate.exchange(regionsUrl, HttpMethod.GET, entity, Region.class);
    	List<Properties> propertiesList = getProperties(RegionResponse.getBody().getPropertyIds(), false);
    	
    	country_code = RegionResponse.getBody().getCountryCode();
    	currency = "EUR";

    	model.addAttribute("propertiesList", propertiesList);
    	
    	requestModel = headers+"/\n"+regionsUrl;
    	responseModel = RegionResponse.getStatusCodeValue()+"/\n"+RegionResponse.getHeaders()+"/\n"+RegionResponse.getBody();
    	
    	// Variable Occupancy
    	if ((!conditionInfo.getCheckin().isEmpty())&&(!conditionInfo.getCheckout().isEmpty())
    			&&(!conditionInfo.getOccupancy().isEmpty())&&(!conditionInfo.getRegion_id().isEmpty())){
    		
    		String checkin = authHeaderValueSingleton.getFormattedDate(conditionInfo.getCheckin()).toString();
    		String checkout = authHeaderValueSingleton.getFormattedDate(conditionInfo.getCheckout()).toString();
    		String occupancy = conditionInfo.getOccupancy();
    		String region_id = conditionInfo.getRegion_id();
    		String PropertiesAvailabilityUrl = "";
    		
    		List<String> propertyIds = new ArrayList<String>();
        	ObjectMapper objectMapper = new ObjectMapper();
        	objectMapper.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        	objectMapper.configure(Feature.ALLOW_MISSING_VALUES, true);
        	objectMapper.configure(Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
        	TypeReference<List<Object>> typeRef = new TypeReference<List<Object>>() {};
        	TypeReference<HashMap<String,HashMap<String,Object>>> typeRef_ = new TypeReference<HashMap<String,HashMap<String,Object>>>() {};
    		int count = 0;
    		for (Properties properties: propertiesList) {
    			PropertiesAvailabilityUrl = keyInfo.getUri()+"properties/availability?checkin="+checkin+"&checkout="+checkout+"&currency="
            			+currency+"&language=ja-JP&country_code="+country_code+"&occupancy="+occupancy+"&property_id="
            				+properties.getProperty_id()+"&sales_channel=website&sales_environment=hotel_only&sort_type=preferred&rate_plan_count=50";
    			logger.info("## PropertiesAvailabilityUrl? "+PropertiesAvailabilityUrl);
    			ResponseEntity<String> PropertiesAvailabilityResponse = null;
    			try {
    				PropertiesAvailabilityResponse = paTemplate.exchange(PropertiesAvailabilityUrl, HttpMethod.GET, entity, String.class);
    			} catch(Exception e) {
    				if ((null != PropertiesAvailabilityResponse)&&(PropertiesAvailabilityResponse.getStatusCodeValue() == 404)) {
    					logger.info("## PropertiesAvailabilityResponse.getStatusCodeValue()? "+PropertiesAvailabilityResponse.getStatusCodeValue());
    				}
    				e.printStackTrace();
    			} finally {
                	if ((null != PropertiesAvailabilityResponse)&&(PropertiesAvailabilityResponse.getStatusCodeValue() == 200)) {
                		List<Object> o = objectMapper.readValue(PropertiesAvailabilityResponse.getBody(), typeRef); 
                		for (Object obj: o) {
                			logger.info("## obj? "+obj.toString());
                		}
                		
                		propertyIds.add(properties.getProperty_id());
                		requestModel = requestModel+"/\n"+headers+"/\n"+PropertiesAvailabilityUrl+"/\n";
                    	responseModel = responseModel+"/\n"+PropertiesAvailabilityResponse.getStatusCodeValue()+"/\n"
                    			+PropertiesAvailabilityResponse.getHeaders()+"/\n"+PropertiesAvailabilityResponse.getBody()+"/\n";
                    	count++;
                	}
                	
                	if (count > 4) {
                		break;
                	}
                	count++;	
    			}
    		}
    		propertiesList = getProperties(propertyIds, true);

        	model.addAttribute("propertiesList", propertiesList);
    	} 
    	conditionModel = keyInfo.getUri()+"regions/"+"/\n"+keyInfo.getUri()+"properties/content"+"/\n"+keyInfo.getUri()+"properties/availability";
    	model.addAttribute("conditionModel", conditionModel);
    	model.addAttribute("responseModel", responseModel);
    	model.addAttribute("conditionInfo", conditionInfo);
    	model.addAttribute("requestModel", requestModel);
    	model.addAttribute("responseModel", responseModel);

    	return init(model);
    }
    
    private List<Properties> getProperties(List<String> propertyIds, boolean isPossible) throws JsonParseException, JsonMappingException, IOException {
    	logger.info("## propertiesList.size()? "+propertyIds.size());
    	HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(HttpClientBuilder.create().build());
    	RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);
    
    	AuthHeaderValueSingleton authHeaderValueSingleton = AuthHeaderValueSingleton.getInstance();
    	try {
			authHeaderValue = authHeaderValueSingleton.getAuthHeaderValue();
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
    	HttpHeaders headers = new HttpHeaders();
    	headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    	headers.set("Accept-Encoding", "gzip");
    	headers.set("Authorization", authHeaderValue);
    	headers.set("User-Agent", "Mozilla/5.0");
    	HttpEntity<?> entity = new HttpEntity<>(headers);

    	List<Properties> PropertiesList = new ArrayList<Properties>(); 
    	ObjectMapper objectMapper = new ObjectMapper();
    	objectMapper.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
    	objectMapper.configure(Feature.ALLOW_MISSING_VALUES, true);
    	TypeReference<HashMap<String,HashMap<String,Object>>> typeRef = new TypeReference<HashMap<String,HashMap<String,Object>>>() {};
    	for (int i=0; i<5; i++) {
    		if (propertyIds.size() == i+1) {
    			break;
    		}
    		String propertyId = propertyIds.get(i);

    		String url = keyInfo.getUri()+"properties/content?language=ja-JP&property_id="+propertyId;
    		
    		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

    		HashMap<String,Object> o = objectMapper.readValue(response.getBody(), typeRef); 
    		Properties properties= new Properties();
    	
    		for (Iterator iter = o.keySet().iterator(); iter.hasNext();) {
    			String key = iter.next().toString();

    			HashMap<String,Object> inner = (HashMap<String, Object>) o.get(key);
    			for (Iterator iter_ = inner.keySet().iterator(); iter_.hasNext();) {
    				String key_ = iter_.next().toString();

    				if (key_.equals("property_id")) {
    					properties.setProperty_id(inner.get(key_).toString());
    				} else if (key_.equals("name")) {
    					properties.setName(inner.get(key_).toString());
    					
    				} else if (key_.equals("rank")) {
    					properties.setRank(inner.get(key_).toString());
    					
    				} else if (key_.equals("phone")) {
    					properties.setPhone(inner.get(key_).toString());
    					
    				} else if (key_.equals("fax")) {
    					properties.setFax(inner.get(key_).toString());
    				} else if (key_.equals("images")) {
    					properties.setFax(inner.get(key_).toString());
    					
    				} else if (key_.equals("onsite_payments")) {
    					properties.setFax(inner.get(key_).toString());
    				} 
    				
    				if (isPossible) {
    					properties.setPossible(true);
    				} else {
    					properties.setPossible(false);
    				}
    			}
    		}
        	PropertiesList.add(properties);
    	}
    	
    	return PropertiesList;
    }
    
}
