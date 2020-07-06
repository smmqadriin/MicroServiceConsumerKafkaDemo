package com.example.demo.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.example.demo.model.CompanyDetails;

@Configuration
public class KafkaConsumerConfiguration {
	@Bean
	public ConsumerFactory<String,String> getconsumerFactory(){
		Map<String, Object> configs=new HashMap<String, Object>();
		configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
		configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);
		configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);
		configs.put(ConsumerConfig.GROUP_ID_CONFIG, "Aarizsoft-1");
		return new DefaultKafkaConsumerFactory<>(configs);
	}
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> 
	consumekafkaListenerContainerFactory(){
		ConcurrentKafkaListenerContainerFactory<String, String> concurrentKafkaListenerContainerFactory
		= new ConcurrentKafkaListenerContainerFactory<String, String>();
		 concurrentKafkaListenerContainerFactory.setConsumerFactory(getconsumerFactory());
		 return concurrentKafkaListenerContainerFactory;
	}
	
	@Bean
	public ConsumerFactory<String,CompanyDetails> getconsumerMsgFactory(){
		Map<String, Object> configs=new HashMap<String, Object>();
		configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
		configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);
		configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,JsonDeserializer.class);
		configs.put(ConsumerConfig.GROUP_ID_CONFIG, "Aarizsoft-2");
		return new DefaultKafkaConsumerFactory<>(configs,new StringDeserializer(), 
				new JsonDeserializer<>(CompanyDetails.class));
	}
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, CompanyDetails> 
	consumekafkaListenerContainerMsgFactory(){
		ConcurrentKafkaListenerContainerFactory<String, CompanyDetails> factory
		= new ConcurrentKafkaListenerContainerFactory<String, CompanyDetails>();
		 factory.setConsumerFactory(getconsumerMsgFactory());
		 return factory;
	}

}
