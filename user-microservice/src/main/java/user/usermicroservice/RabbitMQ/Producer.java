package user.usermicroservice.RabbitMQ;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    private static final Logger log = LoggerFactory.getLogger(Producer.class);
    @Value("${rabbitmq.exchange}")
    String exchange;

    @Value("${rabbitmq.routingKey}")
    private String routingKey;

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(String id) {
        rabbitTemplate.convertAndSend(exchange, routingKey, id);
        log.info("Poruka poslana, user je kreiran");
    }
}