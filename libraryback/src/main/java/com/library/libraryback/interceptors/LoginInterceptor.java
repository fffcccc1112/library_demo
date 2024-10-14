package com.library.libraryback.interceptors;
import com.library.libraryback.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //令牌验证
        String authorization = request.getHeader("Authorization");
        //验证token
        // 如果头部信息存在，并且以"Bearer "开头
        if (authorization != null && authorization.startsWith("Bearer ")) {
            // 去掉"Bearer "前缀
            String token = authorization.substring(7);

            try {
                Map<String, Object> claims = JwtUtil.parseToken(token);
                // TODO: 存疑?
                request.setAttribute("userid", claims.get("userid"));
                request.setAttribute("username", claims.get("username"));
                request.setAttribute("username", claims.get("username"));
                return true;

            } catch (Exception e) {
                response.setStatus(401);
                //不放行
                return false;
            }

        }
        return false;
    }
}
