package comicbook.microsservice.comicbookmicroservice.RabbitMQ;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import comicbook.microsservice.comicbookmicroservice.DTO.RatingDTO;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange}")
    String exchange;

    @Value("${rabbitmq.routingKey}")
    private String routingKey;

    public void send(RatingDTO ratingDTO) throws JsonProcessingException {
        //objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        String ratingJson=objectMapper.writeValueAsString(ratingDTO);
        Message message= MessageBuilder.withBody(ratingJson.getBytes())
                                       .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                                       .build();
        this.rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }
}
