package by.ipps.auth.util;

import by.ipps.auth.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenUtil implements Serializable {

  private static final long serialVersionUID = -2550185165626007488L;
  public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
  //    @Value("${jwt.secret}")
  private String secret = "zxcasd";
  // generate token for user
  public String generateToken(User user) {
    Map<String, Object> claims = new HashMap<>();
    claims.put("UserName", user.getName());
    claims.put("PatronicName", user.getPatronicName());
    claims.put("SurName", user.getSurName());
    claims.put("Department", user.getDepartment());
    claims.put("Email", user.getEmail());
    claims.put("Position", user.getPosition());
    claims.put("Roles", user.getRoles());
    claims.put("DateLastChangePassword", new Date());
    return doGenerateToken(claims, user.getLogin());
  }
  // while creating the token -
  // 1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
  // 2. Sign the JWT using the HS512 algorithm and secret key.
  // 3. According to JWS Compact
  // Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
  //   compaction of the JWT to a URL-safe string
  private String doGenerateToken(Map<String, Object> claims, String userName) {
    return Jwts.builder()
        .setClaims(claims).setSubject(userName)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
        .signWith(SignatureAlgorithm.HS512, secret)
        .compact();
  }
}
