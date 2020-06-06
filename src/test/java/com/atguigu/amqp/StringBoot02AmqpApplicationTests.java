package com.atguigu.amqp;

import com.atguigu.amqp.bean.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StringBoot02AmqpApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    AmqpAdmin amqpAdmin;

    /**使用AmqpAdmin创建消息队列相关
     * 创建Exchage
     */
    @Test
    public void createExchage(){
        // 创建一个Direct类型的Exchange（交换器）
//        amqpAdmin.declareExchange(new DirectExchange("amqpadmin.exchage"));
//        System.out.println("创建完成");

        // 创建一个消息队列
//        amqpAdmin.declareQueue(new Queue("amqpadmin.queue",true));



        // 创建绑定规则
        amqpAdmin.declareBinding(new Binding("amqpadmin.queue",Binding.DestinationType.QUEUE,
                "amqpadmin.exchage","adminmq.hh",null));
    }


    /**
     * 1.点对点（单播）
     */
    @Test
    public void contextLoads() {
        // message需要自己定义，自己构造，定义消息体内容
        //rabbitTemplate.send(exchage,routeKey,message);

        // 只需要传入发送的对象（object意思就是传入一个对象，默认被当作是消息体），自动序列化发送给rabbitmq；
        // rabbitTemplate.convertAndSend(exchage,routeKey,object);

        Map<String,Object> map = new HashMap<>();
        map.put("msg","这是第一个rabbitMQ的消息");
        map.put("data", Arrays.asList("helloworld",123,true));

        // 对象被默认序列化发送出去
        rabbitTemplate.convertAndSend("exchange.direct","atguigu.news",new Book("西游记","吴承恩"));
    }

    /**
     * 从队列中接收消息
     *
     *
     */
    @Test
    public void receive(){
        Object o = rabbitTemplate.receiveAndConvert("atguigu.news");
        System.out.println(o.getClass());
        System.out.println(o);
    }


    /**
     * 广播
     */
    @Test
    public void sendMessage(){
        rabbitTemplate.convertAndSend("exchange.fanout","",new Book("三国演义01","罗贯中01"));
    }
}
