package com.th.system.utils;

import com.alibaba.druid.util.StringUtils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangbao
 * @date 2021-12-14
 */
public class JwtUtil {

//    //密钥
//    public static final String SECRET = "sdjhakdhajdklsl;o653632";
    //过期时间:秒
//    public static final int EXPIRE = 5;

    /** token 过期时间: 10天 */
    public static final int calendarField = Calendar.DATE;
    public static final int calendarInterval = 10;

    private static final String TOKEN_SECRET = "thefirsttoken123";
    /**
     * 生成Token
     * @param userId
     * @return
     * @throws Exception
     */
    public static String createToken(String userId) throws Exception {
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(calendarField, calendarInterval);
        Date expiresDate = nowTime.getTime();


        Map<String, Object> header = new HashMap<>();
        header.put("Type", "Jwt");
        header.put("alg", "HS256");

        String token = JWT.create()
                .withHeader(header)//头
                .withClaim("userId", userId)
//                .withClaim("userName", userName)
//                .withSubject("测试")//
                .withIssuedAt(new Date())//签名时间
                .withExpiresAt(expiresDate)//过期时间
                .sign(Algorithm.HMAC256(TOKEN_SECRET));//签名
        return token;
    }

    /**
     * 验证Token
     * @param token
     * @return
     * @throws Exception
     */
    public static Map<String, Claim> verifyToken(String token)throws Exception{
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).build();
        DecodedJWT jwt = null;
        try {
            jwt = verifier.verify(token);
        }catch (Exception e){
            throw new RuntimeException("凭证已过期，请重新登录");
        }
        return jwt.getClaims();
    }

    /**
     * 解析Token
     * @param token
     * @return
     */
    public static Integer parseToken(String token) throws Exception {
//        DecodedJWT decodedJWT = JWT.decode(token);
//        return decodedJWT.getClaims();

        Map<String, Claim> claims = verifyToken(token);
        Claim user_id_claim = claims.get("userId");
        if (null == user_id_claim || StringUtils.isEmpty(user_id_claim.asString())) {
            // token 校验失败, 抛出Token验证非法异常
        }
        return Integer.valueOf(user_id_claim.asString());
    }


}