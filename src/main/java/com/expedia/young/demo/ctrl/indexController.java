/**
 * Copyright@ LINE 
 * by jounghunyoung@gmail.com
 * 
 */
package com.expedia.young.demo.ctrl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;

import javax.validation.Valid;

import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.expedia.young.demo.entity.AdminQuestionInfo;
import com.expedia.young.demo.entity.ConditionInfo;
import com.expedia.young.demo.entity.PersonalInfo;
import com.expedia.young.demo.entity.Properties;
import com.expedia.young.demo.entity.PropertiesAvailability;
import com.expedia.young.demo.entity.QuestionInfo;
import com.expedia.young.demo.entity.Region;
import com.expedia.young.demo.entity.UserInfo;
import com.expedia.young.demo.entity.keyInfo;
import com.expedia.young.demo.repo.QuestionInfoRepository;
import com.expedia.young.demo.repo.UsersRepository;
import com.expedia.young.demo.service.PersonalInfoService;
import com.expedia.young.demo.service.QuestionInfoService;
import com.expedia.young.demo.service.SurveyAnswerInfoService;
import com.expedia.young.demo.service.SurveyInfoService;
import com.expedia.young.demo.util.AuthHeaderValueSingleton;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    
    @GetMapping
    public String init(Model model) {
    	
//        model.addAttribute("conditionInfo", new ConditionInfo());

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
    	String conditionModel = "";
    	String requestModel = "";
    	String responseModel = "";
    	String currency = "";
    	String country_code = "";
    	
    	String regionsUrl = keyInfo.getUri()+"regions/"+conditionInfo.getRegion_id()+"?region_id="+conditionInfo.getRegion_id()+"&language=ja-JP&include=details&include=property_ids";
        
    	ResponseEntity<Region> RegionResponse = restTemplate.exchange(regionsUrl, HttpMethod.GET, entity, Region.class);
    	List<Properties> propertiesList = getProperties(RegionResponse.getBody().getPropertyIds());
    	
    	country_code = RegionResponse.getBody().getCountryCode();
    	currency = "USD";

    	model.addAttribute("propertiesList", propertiesList);
    	
    	requestModel = headers+"/\n"+regionsUrl;
    	responseModel = RegionResponse.getStatusCodeValue()+"/\n"+RegionResponse.getHeaders()+"/\n"+RegionResponse.getBody();
    	
    	// Variable Occupancy
    	if ((!conditionInfo.getCheckin().isEmpty())&&(!conditionInfo.getCheckout().isEmpty())
    			&&(!conditionInfo.getOccupancy().isEmpty())&&(!conditionInfo.getRegion_id().isEmpty())){
    		String checkin = sdf.format(conditionInfo.getCheckin());
    		String checkout = sdf.format(conditionInfo.getCheckout());
    		String occupancy = conditionInfo.getOccupancy();
    		String region_id = conditionInfo.getRegion_id();
    		String PropertiesAvailabilityUrl = "";
    		
    		List<String> propertyIds = new ArrayList<String>();
    		int count = 0;
    		for (Properties properties: propertiesList) {
    			PropertiesAvailabilityUrl = keyInfo.getUri()+"properties/availability?checkin="+checkin+"&checkout="+checkout+"&currency="
            			+currency+"&language=ja-JP&country_code="+country_code+"&occupancy="+occupancy+"&property_id="
            				+properties.getProperty_id()+"&sales_channel=website&sales_environment=hotel_only&sort_type=preferred&rate_plan_count=50";
        		ResponseEntity<PropertiesAvailability> PropertiesAvailabilityResponse 
        			= restTemplate.exchange(PropertiesAvailabilityUrl, HttpMethod.GET, entity, PropertiesAvailability.class);
        		
            	if (PropertiesAvailabilityResponse.getStatusCodeValue() != 200) {
            		count++;
            	} else {
            		propertyIds.add(properties.getProperty_id());
            		requestModel = requestModel+"/\n"+headers+"/\n"+PropertiesAvailabilityUrl+"/\n";
                	responseModel = responseModel+"/\n"+PropertiesAvailabilityResponse.getStatusCodeValue()+"/\n"
                			+PropertiesAvailabilityResponse.getHeaders()+"/\n"+PropertiesAvailabilityResponse.getBody()+"/\n";
            	}
            	
            	if (count > 4) {
            		break;
            	}
            	count++;
    		}
    		propertiesList = getProperties(propertyIds);

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
    
    private List<Properties> getProperties(List<String> propertyIds) throws JsonParseException, JsonMappingException, IOException {
    	
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
    			}
    		}
        	PropertiesList.add(properties);
    	}
    	
    	return PropertiesList;
    }
    
//    /**
//     * Update user question will answer or not.
//     * 
//     * @param model
//     * @param q_no
//     * @param questionInfo
//     * @return
//     */
//    @PutMapping("{q_no}")
//    public String update(Model model, @PathVariable Long q_no, @ModelAttribute QuestionInfo questionInfo) {
//        boolean is_selected = questionInfo.isIs_selected();
//        questionInfo = questionInfoService.findById(q_no).get();
//        questionInfo.setIs_selected(is_selected);
//        questionInfoService.saveOfQuestionInfo(questionInfo);
//        model.addAttribute("userInfo", userInfo_);
//        return index(model, userInfo_);
//    }
}
