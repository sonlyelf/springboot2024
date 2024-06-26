package com.example.demo.producer;
import java.util.Date;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class  TopicProducer {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	//http://localhost:5001/sendTopic?routingKey=topic.key1.test
	//http://localhost:5001/sendTopic?routingKey=topic.key1.demo
	//http://localhost:5001/sendTopic?routingKey=topic.key2.foo.bar
	//http://localhost:5001/sendTopic?routingKey=topic.key2.demo.name.lab
	//http://localhost:5001/sendTopic?routingKey=topic.key2.report
	@GetMapping("/sendTopic")
	public String sendTopice(@RequestParam String routingKey) {
		String massage = "Hello Topic Exchange: " + new Date();
		rabbitTemplate.convertAndSend("topic-exchange", routingKey, massage);
		return "Message: [ " + massage + " ] send to Topic Exchange with routingKey: " + routingKey;
	}
	

}
