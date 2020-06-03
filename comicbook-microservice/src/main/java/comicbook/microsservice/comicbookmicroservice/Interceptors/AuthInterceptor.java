package comicbook.microsservice.comicbookmicroservice.Interceptors;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.netflix.discovery.converters.Auto;
import comicbook.microsservice.comicbookmicroservice.exceptions.ApiRequestException;
import comicbook.microsservice.comicbookmicroservice.grpc.EventSubmission;
import comicbook.microsservice.comicbookmicroservice.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@ComponentScan
public class AuthInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private EventSubmission eventSubmission;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        String username = jwtUtil.extractUsername(request.getHeader("authorization").substring(7));
        //fetch user from user service
        ResponseEntity<Map> res = restTemplate.getForEntity("http://user-service/user/single/" + username, Map.class);
        //grpc
        Integer id_raw = (Integer) res.getBody().get("id");
        Long id = id_raw.longValue();

        if(modelAndView!=null){
            String resurs = ((TextNode) modelAndView.getModel().get("nazivResursa")).textValue();
            eventSubmission.submitEvent(id, eventSubmission.action(request.getMethod()), resurs);
        }
        else{
            if(response.getStatus() == 500){
                eventSubmission.submitEvent(id, eventSubmission.action(request.getMethod()), "Greška!");
            }
        }
    }

}
