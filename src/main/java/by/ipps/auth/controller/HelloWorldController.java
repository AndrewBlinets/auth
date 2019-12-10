package by.ipps.auth.controller;

import by.ipps.auth.entity.User;
import by.ipps.auth.util.RestRequestToDao;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloWorldController {

    public HelloWorldController(RestRequestToDao rest) {
        this.rest = rest;
    }

    @RequestMapping({ "/hello" })
    public String firstPage() {
        return "Hello World";
    }

    private final RestRequestToDao rest;


    @GetMapping(value = "/getInfo")
    @ResponseBody
    @CrossOrigin
    public User getBaseInfo(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        if (!username.equals("anonymousUser")){
            User user = rest.getUserByLogin(username);
            return new User(null, null, user.getName(), username, "patronic", user.getRoles());
        } else
            return null;
    }
}
