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
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Timestamp;
import java.util.Date;
import java.util.HashMap;

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
    
//    @Autowired
//    private UsersRepository usersRepository;
//    
//    @Autowired
//    private QuestionInfoService questionInfoService;
//    
//    @Autowired
//    private SurveyAnswerInfoService surveyAnswerInfoService;
//    
//    @Autowired
//    private PersonalInfoService personalInfoService;
    
    private UserInfo userInfo_ = new UserInfo();
    
    private String authHeaderValue = null;
    
    @GetMapping
    public String init(Model model) {
    	
        model.addAttribute("conditionInfo", new ConditionInfo());

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

    	logger.info("######################conditionInfo? "+conditionInfo.toString());
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
//    	 https://test.ean.com/2.3/properties/availability?checkin=2019-10-15&checkout=2019-10-17&currency=USD&language=ko-KR&country_code=US&occupancy=1&property_id=7946632&sales_channel=website&sales_environment=hotel_only&sort_type=preferred&rate_plan_count=50
    	String url = keyInfo.getUri()+"regions/"+conditionInfo.getRegion_id()+"?region_id="+conditionInfo.getRegion_id()+"&language=ja-JP&include=details&include=property_ids";
    
    	ResponseEntity<Region> response = restTemplate.exchange(url, HttpMethod.GET, entity, Region.class);

    	logger.info("######################getStatusCodeValue? "+response.getStatusCodeValue());
    	logger.info("######################getStatusCodeValue? "+response.getBody().getCoordinates().getBoundingPolygon().getCoordinates().size());

    	model.addAttribute("conditionInfo", conditionInfo);
    	model.addAttribute("response", response.getStatusCodeValue()+"<br>"+entity.toString());
    	model.addAttribute("propertiesList", getProperties(response.getBody().getPropertyIds()));

    	return init(model);
    }
    
    private List<Properties> getProperties(List<String> propertyIds) {
    	
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
//    	 https://test.ean.com/2.3/properties/content?language=en-US&property_id=9526696

    	List<Properties> PropertiesList = new ArrayList<Properties>(); 

    	for (int i=0; i<10; i++) {
    		String propertyId = propertyIds.get(i);
    		logger.info("######################conditionInfo? "+propertyIds.get(i));
    		String url = keyInfo.getUri()+"properties/content?language=ja-JP&property_id"+propertyId;
    		ResponseEntity<Properties> response = restTemplate.exchange(url, HttpMethod.GET, entity, Properties.class);
        	logger.info("######################getStatusCodeValue? "+response.getStatusCodeValue());
        	logger.info("######################getStatusCodeValue? "+response.getBody().getName());
        	PropertiesList.add(response.getBody());
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
