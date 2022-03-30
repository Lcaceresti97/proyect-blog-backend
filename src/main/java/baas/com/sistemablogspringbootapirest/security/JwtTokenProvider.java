package baas.com.sistemablogspringbootapirest.security;

import baas.com.sistemablogspringbootapirest.exception.BlogAppException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwt-expiration-milliseconds}")
    private int jwtExpirationInMs;

    public String generateToken(Authentication authentication) {
        String userName = authentication.getName();
        Date currentDate = new Date();
        Date expirationDate = new Date(currentDate.getTime() + jwtExpirationInMs);

        String token = Jwts.builder().setSubject(userName).setIssuedAt(new Date()).setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();

        return token;
    }

    public String getUsernameDelJWT(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public boolean validatedToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        }catch (SignatureException ex) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST,"JWT signature not valid");
        }
        catch (MalformedJwtException ex) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST,"JWT token not valid");
        }
        catch (ExpiredJwtException ex) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST,"Expired JWT Token");
        }
        catch (UnsupportedJwtException ex) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST,"JWT token not supported");
        }
        catch (IllegalArgumentException ex) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST,"The claims JWT string is empty");
        }
    }
}
