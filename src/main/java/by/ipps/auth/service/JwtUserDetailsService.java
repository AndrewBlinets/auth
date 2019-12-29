package by.ipps.auth.service;

import by.ipps.auth.util.RestRequestToDao;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

  private final RestRequestToDao restRequestToDao;

  public JwtUserDetailsService(RestRequestToDao restRequestToDao) {
    this.restRequestToDao = restRequestToDao;
  }

  @Override
  public UserDetails loadUserByUsername(String username) {
    by.ipps.auth.entity.User user = restRequestToDao.getUserByLogin(username);
    return new User(
        user.getLogin(),
        user.getHashPassword(),
        user.isEnabled(),
        true,
        true,
        !user.isBlock(),
        getAuthorities(user.getRoles()));
  }

  private Collection<? extends GrantedAuthority> getAuthorities(List<String> roles) {
    return getGrantedAuthorities(getPrivileges(roles));
  }

  private List<String> getPrivileges(List<String> roles) {
    return new ArrayList<>(roles);
  }

  private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
    List<GrantedAuthority> authorities = new ArrayList<>();
    for (String privilege : privileges) {
      authorities.add(new SimpleGrantedAuthority(privilege));
    }
    return authorities;
  }
}
