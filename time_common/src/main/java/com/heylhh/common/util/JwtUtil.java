package com.heylhh.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private String key = "heylhh" ;

    private long ttl = 3600000 ;//一个小时

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getTtl() {
        return ttl;
    }

    public void setTtl(long ttl) {
        this.ttl = ttl;
    }

    /**
     * 生成JWT
     *
     * @param id
     * @param subject
     * @return
     */
    public String createJWT(String id, String subject, String roles) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        JwtBuilder builder = Jwts.builder().setId(id)
                .setSubject(subject)
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, key).claim("roles", roles);
        if (ttl > 0) {
            builder.setExpiration( new Date( nowMillis + ttl));
        }
        return builder.compact();
    }

    /**
     * 解析JWT
     * @param jwtStr
     * @return
     */
    public Claims parseJWT(String jwtStr){
        return  Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(jwtStr)
                .getBody();
    }

    public static void main(String[] args) {
        String jwt = new JwtUtil().createJWT("2", "admin", "admin");
        System.out.println(jwt);

        Claims claims = new JwtUtil().parseJWT("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyIiwic3ViIjoiYWRtaW4iLCJpYXQiOjE1ODA2MTI1MzEsInJvbGVzIjoiYWRtaW4iLCJleHAiOjE1ODA2MTYxMzF9.Ul2TjfA_844gFd55oYGrxBpkSP4fVUpSON-hYh79PNs");
        System.out.println(claims);
    }

}
