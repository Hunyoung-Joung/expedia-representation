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

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import com.expedia.young.demo.entity.QuestionInfo;
import com.expedia.young.demo.entity.UserInfo;
import com.expedia.young.demo.entity.keyInfo;
import com.expedia.young.demo.repo.QuestionInfoRepository;
import com.expedia.young.demo.repo.UsersRepository;
import com.expedia.young.demo.service.PersonalInfoService;
import com.expedia.young.demo.service.QuestionInfoService;
import com.expedia.young.demo.service.SurveyAnswerInfoService;
import com.expedia.young.demo.service.SurveyInfoService;
import com.expedia.young.demo.util.AuthHeaderValueSingleton;

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
     */
    @PostMapping(value = {"/search"})
    public String index(Model model, @ModelAttribute("conditionInfo") @Valid ConditionInfo conditionInfo) {

    	logger.info("######################conditionInfo? "+conditionInfo.toString());
    	RestTemplate restTemplate = new RestTemplate();
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
    	String url = keyInfo.getUri()+"regions/"+conditionInfo.getRegion_id()+"?region_id="+conditionInfo.getRegion_id()+"&language=en-US&include=details&include=property_ids";
    
    	ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
//    	JsonParser springParser = JsonParserFactory.getJsonParser();
//    	List<Object> list = springParser.parseList(response.toString());
//    	for(Object o : list) {
//    		if(o instanceof Map) {
//    			Map<String,Object> map = (Map<String,Object>) o;
//    			System.out.println("Items found: " + map.size());
//
//    			int i = 0;
//    			for (Map.Entry<String, Object> entry : map.entrySet()) {
//    				System.out.println(entry.getKey() + " = " + entry.getValue());
//    				i++;
//    			}
//
//    		}
//    	}
//    	
    	logger.info("######################response? "+response.getBody());

    	return "index";
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
