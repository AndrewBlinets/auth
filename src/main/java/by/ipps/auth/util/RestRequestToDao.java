package by.ipps.auth.util;

import by.ipps.auth.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestRequestToDao {

    public User getUserByLogin(String login) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object> response = restTemplate.postForEntity( "http://192.168.1.89:8080/dao/users/auth",
                login, Object.class);
        return null;
//        return response.getBody();
    }
}
