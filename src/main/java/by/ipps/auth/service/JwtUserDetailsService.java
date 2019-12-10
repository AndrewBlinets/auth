package by.ipps.auth.service;

import by.ipps.auth.util.RestRequestToDao;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final RestRequestToDao restRequestToDao;

    public JwtUserDetailsService(RestRequestToDao restRequestToDao) {
        this.restRequestToDao = restRequestToDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        by.ipps.auth.entity.User user = restRequestToDao.getUserByLogin(username);
            return new User(user.getLogin(), user.getHashPassword(),
//                    "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
                    new ArrayList<>());
//        } else {
//            throw new UsernameNotFoundException("User not found with username: " + username);
//        }
    }
}