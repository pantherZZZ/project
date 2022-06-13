package com.ttwl.until;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author zhang bao
 * @Date 2022/5/25 10:38
 * @Description：
 * @Version 1.0
 */
public class JWTUtil {
    // 过期时间 2 小时
    private static final long EXPIRE_TIME = 2 * 60 * 60 * 1000;
    // 密钥
    private static final String SECRET = "jwt+shiro";

//    @Autowired
//    private UserMapper userMapper;

    /**
     * 生成 token
     */
    public static String createToken(String username) {
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            //jwt的header部分
            Map<String ,Object> map=new HashMap<>();
            map.put("alg","HS256");
            map.put("typ","JWT");
            // 附带username信息
            return JWT.create()
                    .withHeader(map)//jwt的header部分
                    .withClaim("username", username)//私有声明
                    .withExpiresAt(date)//过期时间
                    .withIssuedAt(new Date())//签发时间
                    .sign(algorithm);//签名
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 校验 token 是否正确
     */
    //校验token的有效性，1、token的header和payload是否没改过；2、没有过期
    public static boolean verify(String token) {
        try {
            //解密
            JWTVerifier verifier=JWT.require(Algorithm.HMAC256(SECRET)).build();
//            System.out.println("5555555->error1111111111");
            verifier.verify(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * 获得token中的信息，无需secret解密也能获得
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    public static String getCurrentUsername(HttpServletRequest request){
        String accessToken = request.getHeader("Jmt-token");
        return getUsername(accessToken);
    }
}
