package com.luolin.controller.GoogleCloudStorageServer.GoogleCloudStorageServer;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * google cloud storage 存储操作接口
 *
 * @author shawn
 * @date 2020/12/25
 */
public interface GoogleCloudStorageService {
    /**
     *  google cloud storage文件上传接口
     * @param multipartFile
     * @return https://storage.googleapis.com/static-res/test/f9634577a0eb4831a6fa4235c247a102.jpg
     * @throws Exception
     */
    String uploadFile(MultipartFile multipartFile)throws Exception;

    /**
     * google cloud storage对象下载
     * @param objectName https://storage.googleapis.com/static-res/test/f9634577a0eb4831a6fa4235c247a102.jpg
     * @return byte[]
     * @throws Exception
     */
    byte[] downloadObject(String objectName)throws Exception;

    /**
     *  google cloud storage对象删除
     * @param objectName https://storage.googleapis.com/static-res/test/f9634577a0eb4831a6fa4235c247a102.jpg
     * @throws Exception
     */
    void deleteObject(String objectName)throws Exception;

    /**
     * google cloud storage查询接口
     * @param fileName f9
     * @return https://storage.googleapis.com/static-res/test/f9634577a0eb4831a6fa4235c247a102.jpg
     * @throws Exception
     */
    List<String> listObjects(String fileName)throws Exception;

    /**
     * 拆分HTTP路径接口
     * @param objectName https://storage.googleapis.com/static-res/test/f9634577a0eb4831a6fa4235c247a102.jpg
     * @return f9634577a0eb4831a6fa4235c247a102.jpg
     * @throws Exception
     */
    String replaceFileName(String objectName)throws Exception;
}
