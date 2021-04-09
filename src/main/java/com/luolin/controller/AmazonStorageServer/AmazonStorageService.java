package com.luolin.controller.AmazonStorageServer;

import com.luolin.entity.DownFile;
import org.springframework.web.multipart.MultipartFile;

public interface AmazonStorageService {

    String pubUpload(MultipartFile file)throws Exception;

    String priUpload(MultipartFile multipartFile)throws Exception;

    DownFile priDownload(String downUrl)throws Exception;

    void delPubFile(String fileName)throws Exception;

    void delPriFile(String fileName)throws Exception;

}
