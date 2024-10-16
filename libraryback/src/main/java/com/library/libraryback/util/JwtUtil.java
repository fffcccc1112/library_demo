
  package com.library.libraryback.util;

  import com.auth0.jwt.JWT;
  import com.auth0.jwt.algorithms.Algorithm;
  import com.auth0.jwt.exceptions.JWTVerificationException;
  import com.auth0.jwt.interfaces.Claim;
  import com.auth0.jwt.interfaces.DecodedJWT;
  import org.springframework.stereotype.Service;

  import java.util.Map;
  import java.util.Date;
  @Service
  public class JwtUtil {
      private static final String KEY = "fang";
      /**
       * 接受业务数据并生成token并返回
       *过期时间为一个小时
       */

      public static String getToken(Map<String, Object> claims) {
          String token = JWT.create()
                  .withClaim("claims", claims)
                  .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                  .sign(Algorithm.HMAC256(KEY));
//          return "Bearer " + token;
          return token;
      }
      /**
       * 接受token，验证token，并返回业务数据
       */

      public static Map<String, Object> parseToken(String token) {
          return JWT.require(Algorithm.HMAC256(KEY))
                  .build()
                  .verify(token)
                  .getClaim("claims")
                  .asMap();
      }


      public static String freshToken(String token) {
          //从原有的token里面获取用户信息
          Map<String, Object> map = JwtUtil.parseToken(token);
          //返回一个新的token
          return getToken(map);
      }
  }


