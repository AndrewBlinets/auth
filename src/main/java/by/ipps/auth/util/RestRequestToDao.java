package by.ipps.auth.util;

import by.ipps.auth.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestRequestToDao {

  //    protected static final String URL_SERVER = "http://192.168.1.125:8080/dao/users/auth";// server
//    protected static final String URL_SERVER = "http://192.168.1.89:8082/dao/users/auth";// local work
  protected static final String URL_SERVER = "http://localhost:8082/dao/users/auth";// local home

  public User getUserByLogin(String login) {
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<User> response =
        restTemplate.postForEntity(URL_SERVER, login, User.class);
    return response.getBody();
  }
}
