package com.trinity.controller;

import com.commons.entity.Sender;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/message")
public class MessageController {

    private static final String QUE_A = "A";

    private static final String QUE_B = "B";

    @Autowired
    private AmqpTemplate amqpTemplate;

    private Map<String, String> map = new HashMap<>(20);

    @PostMapping("/getMessage")
    public Map getMessage(@RequestBody Sender sender) {
        Map<String, Object> map1 = new HashMap<>(20);
        if (sender != null && QUE_A.equals(sender.getUser())) {
            String s = this.map.get("A");
            if (s != null) {
                map1.put("flag", true);
                map1.put("val", s);
                this.map.remove("A");
                return map1;
            }
        }
        if (sender != null && QUE_B.equals(sender.getUser())) {
            String s = this.map.get("B");
            if (s != null) {
                map1.put("flag", true);
                map1.put("val", s);
                this.map.remove("B");
                return map1;
            }
        }
        map1.put("flag", false);
        return map1;
    }

    @PostMapping("/sendMessage")
    public void sendMessage(@RequestBody Sender sender) {
        if (sender != null && QUE_A.equals(sender.getUser())) {
            amqpTemplate.convertAndSend("chatExchange", "B", sender.getMessage());
        }
        if (sender != null && QUE_B.equals(sender.getUser())) {
            amqpTemplate.convertAndSend("chatExchange", "A", sender.getMessage());
        }
    }

    @RabbitListener(queues = "A")
    public void pro1(String s, Channel channel, Message message) {
        try {
            this.map.put("A", s);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            try {
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    @RabbitListener(queues = "B")
    public void pro2(String s, Channel channel, Message message) {
        try {
            this.map.put("B", s);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            try {
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
