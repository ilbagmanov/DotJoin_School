package ru.itis.dotjoinrestapi.auth;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Base64;

@Component
public class JwtUtil {

    @Value("${jwt.secretKey}")
    private String secretKey;

    @PostConstruct
    public void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String getUsername(String token) {
        var claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token);
        String username = claims.getBody().getSubject();
        return username;
    }
}
