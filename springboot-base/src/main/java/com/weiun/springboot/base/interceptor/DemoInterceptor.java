package com.weiun.springboot.base.interceptor;

import com.weiun.springboot.base.config.WebMvcConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * @author William
 * @Date 2019/2/27
 * @Description 拦截器示例
 */
public class DemoInterceptor implements HandlerInterceptor {

    private ApplicationContext applicationContext;

    private Set<RequestMappingInfo> requestMappingInfos;

    public void init() {
        RequestMappingHandlerMapping requestMappingHandlerMapping = (RequestMappingHandlerMapping) WebMvcConfig.getBean("requestMappingHandlerMapping");
        requestMappingInfos = requestMappingHandlerMapping.getHandlerMethods().keySet();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle");
        // 返回false 将会拦截本次请求，不进行处理
        // 返回true 正常响应
        if (requestMappingInfos == null) {
            init();
        }
        if (requestMappingInfos != null && !requestMappingInfos.isEmpty()) {
            RequestMappingInfo match = null;
            for (RequestMappingInfo requestMappingInfo : requestMappingInfos) {
                match = requestMappingInfo.getMatchingCondition(request);
                if (match != null) {
                    break;
                }
            }
            if (match != null) {
                Set<String> patterns = match.getPatternsCondition().getPatterns();
                System.out.println(patterns);
            }
        }
        response.setStatus(HttpStatus.FORBIDDEN.value());
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
        //System.out.println("ViewName:" + modelAndView.getViewName());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion");
    }
}