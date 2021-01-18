package com.dragon.resolver;

import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author tdragon.
 * @Date 2020/12/31.
 * @Time 21:51
 * @Description: 异常解析器 任何一个handle抛出异常时
 */

public class MyExceptionResovler implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        ModelAndView modelAndView = new ModelAndView();
        //        if(e instanceof myException1){
        //
        //        }
        if (e instanceof MaxUploadSizeExceededException) {
            modelAndView.setViewName("redirect:/uploaderror.jsp");
        }
        return modelAndView;
    }
}
