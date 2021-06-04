package com.xxx.server.controller;

import com.xxx.server.pojo.RespBean;
import io.swagger.annotations.ApiOperation;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/ajax")
public class AjaxController {
    // 文件的真实路径
    @Value("${file.uploadFolder}")
    private String realBasePath;
    @Value("${file.accessPath}")
    private String accessPath;
    @ApiOperation("上传图片")
    @PostMapping("/uploadpic")
    public RespBean upload(HttpServletRequest request){
        String path= realBasePath;
        String upurl = request.getParameter("upurl");
        path = path+="images/"+upurl;
        System.out.println("path");
        System.out.println(path);
        File pathFile = new File(path);
        if(!pathFile.isDirectory()){
            pathFile.mkdir();
        }
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        String fileName = "";
        for (MultipartFile file : files) {
            if(file.isEmpty()){
                return RespBean.error("上传失败");
            }
            String filename = file.getOriginalFilename();
            String[] fileNameArr = filename.split("\\.");
            if(fileNameArr.length!=2){
                return RespBean.error("上传失败");
            }
            List<String> arrowUploadType = new ArrayList<>();
            arrowUploadType.add("jpg");
            arrowUploadType.add("jpeg");
            arrowUploadType.add("png");
            arrowUploadType.add("gif");
            arrowUploadType.add("png");
            arrowUploadType.add("jpeg");
            arrowUploadType.add("PNG");
            arrowUploadType.add("JPEG");
            if(!arrowUploadType.contains(fileNameArr[1])){
                return RespBean.error("上传失败不是图片格式");
            }
            filename = UUID.randomUUID()+"."+fileNameArr[1];
            fileName = filename;
            File dest = new File(path +"/"+ filename);
            try {
                file.transferTo(dest);
                Thumbnails.of(path +"/"+ filename).size(300,500).toFile(path +"/300_"+ filename);//变为400*300,遵循原图比例缩或放到400*某个高度

            } catch (IOException e) {
                System.out.println(e.getMessage());
                return RespBean.error("上传失败"+e.getMessage());
            }
        }
        Map<String,Object> result = new HashMap<>();
        result.put("filename",fileName);
        return RespBean.success("成功18",result);
    }

}
