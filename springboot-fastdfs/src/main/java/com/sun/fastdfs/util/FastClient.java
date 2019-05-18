package com.sun.fastdfs.util;

import com.github.tobato.fastdfs.domain.MataData;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.sun.fastdfs.config.PropertiesConfig;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.Set;

/**
 * @author 喻湘东
 * @email jyxd1997@163.com
 * @create 2019-05-18 16:58:03
 * @describe
 */
@Component
public class FastClient {

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    private String filePath=null;

    public String upload(MultipartFile file) throws IOException {
        // 设置文件信息
        Set<MataData> mataData = new HashSet<>();
        // 上传   （文件上传可不填文件信息，填入null即可）
        StorePath storePath = fastFileStorageClient.uploadFile(file.getInputStream(), file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()), mataData);
        filePath = PropertiesConfig.FILE_PATH+"/"+storePath.getFullPath();
        return filePath;
    }

    public String delete(String group,String path) {

        // 第一种删除：参数：完整地址
        //fastFileStorageClient.deleteFile(path);

        // 第二种删除：参数：组名加文件路径
        fastFileStorageClient.deleteFile(group,path);

        return "恭喜恭喜，删除成功！";
    }

    public void downLoad(String group, String path, String fileName, HttpServletResponse response) throws IOException {

        // 获取文件
        byte[] bytes = fastFileStorageClient.downloadFile(group, path, new DownloadByteArray());

        //设置相应类型application/octet-stream        （注：applicatoin/octet-stream 为通用，一些其它的类型苹果浏览器下载内容可能为空）
        response.reset();
        response.setContentType("applicatoin/octet-stream");
        //设置头信息                 Content-Disposition为属性名  附件形式打开下载文件   指定名称  为 设定的fileName
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        // 写入到流
        ServletOutputStream out = response.getOutputStream();
        out.write(bytes);
        out.close();
    }

}
