package com.example.demo.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;

@RestController
public class DirectProducer {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@GetMapping("/sendDirect")
	public String sendDirect( @RequestParam String routingKey) {
		
		//http://localhost:5001/sendDirect?routingKey=routingKey1
		//http://localhost:5001/sendDirect?routingKey=routingKey2
		
		String massage = "Hello Direct Exchange: " + new Date();
		
		rabbitTemplate.convertAndSend("direct-exchange", routingKey, massage);
		
		return "Message: [ " + massage + " ] send to Direct Exchange with routingKey: " + routingKey;
	}
}

