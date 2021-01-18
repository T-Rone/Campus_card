package com.dragon.controller;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @Author tdragon.
 * @Date 2021/1/12.
 * @Time 18:08
 * @Description:
 */
@Controller
public class UploadController {
    @RequestMapping("/upload.do")
    public String test1(MultipartFile multipartFile, HttpSession session) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();
        String contentType = multipartFile.getContentType();
        //生成唯一id 并拼接成文件名
        String uuid = UUID.randomUUID().toString().substring(0, 5);
        //获取文件后缀名
        String extension = FilenameUtils.getExtension(originalFilename);
        String downloadfile = uuid + "." + extension;
        session.setAttribute("filename", downloadfile);
        System.out.println("原始文件名：" + originalFilename + " 类型：" + contentType);
        //保存文件 简单形式
        //        multipartFile.transferTo(new File("D:\\test\\test.txt"));
        String realPath = session.getServletContext().getRealPath("/upload");
        System.out.println("realPath" + realPath);
        System.out.println(downloadfile);
        multipartFile.transferTo(new File(realPath + "\\" + downloadfile));
        return "forward:download.jsp";
    }
}
