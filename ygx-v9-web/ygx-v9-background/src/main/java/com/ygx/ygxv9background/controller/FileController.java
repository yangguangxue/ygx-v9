package com.ygx.ygxv9background.controller;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.ygx.v9.pojo.MultiUploadResultBean;
import com.ygx.v9.pojo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("file")
public class FileController {

    @Value("${image.server}")
    private String IMAGE_SERVER;

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

//    @RequestMapping("upload")
//    @ResponseBody
//    public ResultBean upload(MultipartFile file){
//        //1 获取到文件对象，将其传到fastdfs服务器上
//        String originalFilename = file.getOriginalFilename();
//        System.out.println(originalFilename);
//        String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
//        try {
//            StorePath storePath = fastFileStorageClient.uploadFile(file.getInputStream(), file.getSize(), extName, null);
//            //2 将服务器文件保存地址返回给客户端
//            String fullPath = storePath.getFullPath();
//            String path = new StringBuilder(IMAGE_SERVER).append(fullPath).toString();
//            return ResultBean.success(path);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return ResultBean.error("您的网络当前不畅通，请稍后再试！");
//    }

    @RequestMapping("upload")
    @ResponseBody
    public ResultBean upload(MultipartFile file){
        //1.获取到文件对象，将文件对象上传FastDFS上
        String originalFilename = file.getOriginalFilename();
        System.out.println(originalFilename);
        String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        try {
            StorePath storePath = fastFileStorageClient.uploadFile(file.getInputStream(), file.getSize(), extName, null);
            //2.把服务器的文件保存地址返回给客户端
            String fullPath = storePath.getFullPath();
            String path = new StringBuilder(IMAGE_SERVER).append(fullPath).toString();
            return ResultBean.success(path);
        } catch (IOException e) {
            e.printStackTrace();
            return ResultBean.error("您的网络当前不畅通，请稍后再试！");
        }
    }

    @RequestMapping("multiUpload")
    @ResponseBody
    public MultiUploadResultBean multiUpload(MultipartFile[] files){
        MultiUploadResultBean resultBean = new MultiUploadResultBean();
        String[] data = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            //1.获取到文件对象，将文件对象上传FastDFS上
            String originalFilename = files[i].getOriginalFilename();
            System.out.println(originalFilename);
            String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            try {
                StorePath storePath = fastFileStorageClient.uploadFile(files[i].getInputStream(),
                        files[i].getSize(), extName, null);
                //2.把服务器的文件保存地址返回给客户端
                String fullPath = storePath.getFullPath();
                String path = new StringBuilder(IMAGE_SERVER).append(fullPath).toString();
                data[i] = path;
            } catch (IOException e) {
                e.printStackTrace();
                resultBean.setErrno("-1");
                return resultBean;
            }
        }
        resultBean.setErrno("0");
        resultBean.setData(data);
        return resultBean;
    }

}
