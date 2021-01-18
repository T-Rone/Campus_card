package com.dragon.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.FilterInputStream;

/**
 * @Author tdragon.
 * @Date 2021/1/12.
 * @Time 19:19
 * @Description:
 */
@Controller
public class DownloadController {
    @RequestMapping("/download.do")
    public void test1(String name, HttpSession session, HttpServletResponse servletResponse) throws Exception {
        String realPath = session.getServletContext().getRealPath("/upload");
        String filepath = realPath + "\\" + name;
        //设置响应头 告知浏览器 要以附件形式保存内容
        servletResponse.setHeader("content-disposition", "attachment;filename=" + name);
        //响应 保存文件
        IOUtils.copy(new FileInputStream(filepath), servletResponse.getOutputStream());
    }
}
