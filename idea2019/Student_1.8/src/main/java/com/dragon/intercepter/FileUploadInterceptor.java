package com.dragon.intercepter;

import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author tdragon.
 * @Date 2021/1/12.
 * @Time 18:07
 * @Description:
 */

public class FileUploadInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //判断文件大小 1048576
        ServletRequestContext servletRequestContext = new ServletRequestContext(httpServletRequest);
        //直接就是请求中文件的大小 单位就是B
        long length = servletRequestContext.contentLength();
        if (length > 1048576) {
            throw new MaxUploadSizeExceededException(1048576);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("上传成功后执行posthandle方法");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("上传拦截器after方法");
    }
}

