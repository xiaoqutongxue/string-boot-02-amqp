package com.atguigu.amqp;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** rabbitmq的自动配置类
 * 1.RabbitAutoConfiguration自动配置类
 * 2.自动配置了链接工厂
 * 3.RabbitProperties封装了RabbitMQ的配置
 * 4.RabbitTemplate:给RabbitMQ发送和接收消息的
 * 5.AmqpAdmin：RabbitMQ的系统功能管理组件
 *     AmqpAdmin:创建和删除 Queue，Exchage，Binding
 * 6.@EnableRabbit + @RabbitListener监听消息队列中的内容
 *
 */

// 为了使注解生效，开启基于注解的RabbitMQ模式
@EnableRabbit
@SpringBootApplication
public class StringBoot02AmqpApplication {

    public static void main(String[] args) {
        SpringApplication.run(StringBoot02AmqpApplication.class, args);
    }

}
