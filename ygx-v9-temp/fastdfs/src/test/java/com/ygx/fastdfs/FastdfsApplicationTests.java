package com.ygx.fastdfs;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@SpringBootTest
class FastdfsApplicationTests {

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @Test
    void contextLoads() throws FileNotFoundException {
//        File file = new File("d://TIM.jpg");
//        String fileName = file.getName();
//        String extName = fileName.substring(fileName.lastIndexOf(".") + 1);
//        FileInputStream fileInputStream = new FileInputStream(file);
//        StorePath storePath = fastFileStorageClient.uploadFile(fileInputStream, file.length(), extName, null);
//        System.out.println(storePath.getFullPath());
//        System.out.println(storePath.getGroup());
//        System.out.println(storePath.getPath());


        fastFileStorageClient.deleteFile("group1/M00/00/00/wKgNgV8WwSqAMeCfAAF8Dyp8S9k139.jpg");

    }

}
