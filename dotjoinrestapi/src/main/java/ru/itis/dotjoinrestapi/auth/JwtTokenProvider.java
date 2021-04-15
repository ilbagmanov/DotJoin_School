package ru.itis.dotjoinrestapi.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import ru.itis.dotjoinrestapi.services.LoginServiceImpl;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secretKey}")
    private String secretKey;

    @Value("${jwt.header}")
    private String authorizationHeader;

    @Autowired
    private LoginServiceImpl loginServiceImpl;

    @PostConstruct
    public void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public boolean validate(String token) {
        try {
            var claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);
            var expirationDate = claims.getBody().getExpiration();
            return expirationDate.after(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String createToken(String username, String role) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("role", role);

        Date now = new Date();
        var expire = now.getTime() + 8 * 60 * 60 * 100;

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(expire))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public Authentication getAuth(String token) {

        String username = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody().getSubject();
        var user = loginServiceImpl.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(user, "", user.getAuthorities());
    }

    public String resolveToken(HttpServletRequest request){
        return request.getHeader(authorizationHeader);
    }
}
