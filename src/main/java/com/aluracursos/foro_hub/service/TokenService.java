package com.aluracursos.foro_hub.service;
import com.aluracursos.foro_hub.domain.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Date;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;



@Service
public class TokenService {


    @Value("${foro_hub.jwt.secret}")
    private String claveSecreta;

    @Value("${foro_hub.jwt.expiration}")
    private long duracionToken;


    public String generarToken(Usuario usuario) {
        Date ahora = new Date();
        Date expiracion = new Date(ahora.getTime() + duracionToken);

        // Convertir tu clave secreta a Key usando HS256
        Key key = Keys.hmacShaKeyFor(claveSecreta.getBytes());

        return Jwts.builder()
                .setIssuer("API foro_hub")
                .setSubject(usuario.getCorreo())
                .setIssuedAt(ahora)
                .setExpiration(expiracion)
                .signWith(key, SignatureAlgorithm.HS256) // 🔁 ¡nuevo formato!
                .compact();
    }

    public String getSubject(String tokenJwt) {
        try {
            if (tokenJwt == null || tokenJwt.isBlank()) {
                return null;
            }

            Key key = Keys.hmacShaKeyFor(claveSecreta.getBytes());

            Claims claims = Jwts
                    .parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(tokenJwt)
                    .getBody();

            return claims.getSubject();

        } catch (ExpiredJwtException | MalformedJwtException | SignatureException |
                 UnsupportedJwtException | IllegalArgumentException e) {

            System.out.println("❌ Token inválido, expirado, mal formado o firmado incorrectamente");
            // También puedes usar un logger: logger.warn("Token inválido...", e);
            return null;
        }
    }
}


