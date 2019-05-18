package com.sun.fastdfs.api;

import com.sun.fastdfs.util.FastClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author 喻湘东
 * @email jyxd1997@163.com
 * @create 2019-05-18 17:00:19
 * @describe
 */
@RestController
@RequestMapping("/api")
public class UploadFileApi {

    @Autowired
    private FastClient fastClient;

    @RequestMapping("/upload")
    public String upload(MultipartFile file){
        try {
            return fastClient.upload(file);
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }
    @RequestMapping("/delete")
    public String upload(String group,String filePath){
        try {
            return fastClient.delete(group,filePath);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

}
