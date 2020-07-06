package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.CompanyDetails;

@RestController
public class KafkaConsumerController {
	
	List<CompanyDetails> jasonmessages;
	List<String> messages;
	CompanyDetails company;
	
	@GetMapping("/consumeStringMessage")
	public List<String> consumeStringMessage() {
		return messages;
		
	}
	
	@KafkaListener(groupId = "Aarizsoft-1", topics="Aarizsoft", containerFactory = "kafkaListenerContainerFactory")
	public List<String> getStrMessagefromTopic(String data) {
		messages=new ArrayList<String>();
		messages.add(data);
		return messages;
	}
	
	@GetMapping("/consumeJsonMessage")
	public List<CompanyDetails> consumeJsonMessage() {
		return jasonmessages;
		
	}
	
	@KafkaListener(groupId = "Aarizsoft-2", topics="Aarizsoft", containerFactory = "kafkaListenerContainerFactory")
	public List<CompanyDetails> getJsonMessagefromTopic(List<CompanyDetails> cList) {
		jasonmessages=cList;
		return jasonmessages;
	}

}
