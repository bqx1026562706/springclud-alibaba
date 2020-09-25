package com.xs.bqx.orderconsumer.mq;

import com.xs.bqx.orderconsumer.pojo.RabbitMessage;
import com.xs.bqx.orderconsumer.service.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;

@Component
@RabbitListener(queues = "TestDirectQueue")//监听的队列名称 TestDirectQueue
public class DirectReceiver {

   /* @Autowired
    private OrderService orderService;*/

    @RabbitHandler
    public void process(Map testMessage) {


        String messageId = (String) testMessage.get("messageId");
        String messageData =(String) testMessage.get("messageData");
        Integer type=1;
        RabbitMessage rabbitMessage = new RabbitMessage();
        rabbitMessage.setName(messageId);
        rabbitMessage.setMessage(messageData);
        rabbitMessage.setType(type);
        //createTime    messageId   messageData
        //orderService.addMessageToMySql(rabbitMessage);
        System.out.println("DirectReceiver消费者收到消息  : " + testMessage.toString());
    }



}
