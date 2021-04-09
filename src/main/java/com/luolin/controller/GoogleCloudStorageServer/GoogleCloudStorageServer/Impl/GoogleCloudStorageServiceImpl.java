package com.luolin.controller.GoogleCloudStorageServer.GoogleCloudStorageServer.Impl;

import com.github.pagehelper.util.StringUtil;
import com.google.api.gax.paging.Page;
import com.google.cloud.storage.*;
import com.luolin.common.enums.ErrorCodeEnum;
import com.luolin.controller.GoogleCloudStorageServer.GoogleCloudStorageServer.GoogleCloudStorageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Log4j2
public class GoogleCloudStorageServiceImpl implements GoogleCloudStorageService {
    //@Resource
    private Storage storage;
    /**
     * 桶
     */
    //@Value("${google.bucketName}")
    private String bucketName;
    /**
     * 文件路径
     */
    //@Value("${google.pathFile}")
    private String pathFile;

    //@Value("${google.downUrl}")
    private String downUrl;

    //@Value("${google.downUrl2}")
    private String downUrl2;

    private static final String DOT = ".";
    private static final String SLASH = "/";
    /**
     * 上传对象
     * @param objectName
     * @param inputStream
     */
    public String uploadObject(String objectName, InputStream inputStream) {
        String type = "";
        if(objectName.contains(DOT) == Boolean.TRUE){
            type = objectName.substring(objectName.lastIndexOf(DOT));
        }
        String fileName = UUID.randomUUID().toString().replace("-", "")+type;
        BlobId blobId = BlobId.of(bucketName, pathFile+fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        try {
            storage.createFrom(blobInfo, inputStream);
        }catch (Exception e){
            log.error("谷歌云存储上传失败",e.getMessage());
            throw new SecurityException(ErrorCodeEnum.GOOGLE_CLOUD_STORAGE_UPLOAD_ERROR.getMsg());
        }
        String url = downUrl + bucketName + SLASH + pathFile + fileName;
        log.info("文件上传完成,路径："+url);
        return url;
    }

    /**
     * 下载对象
     * @param fileName
     */
    @Override
    public byte[] downloadObject(String fileName)throws Exception{
        if(StringUtil.isEmpty(fileName)){
            throw new SecurityException(ErrorCodeEnum.GOOGLE_CLOUD_STORAGE_DOWNLOAD_ERROR.getMsg());
        }
        String replace =  replaceFileName(fileName);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            Blob blob = storage.get(BlobId.of(bucketName, replace));
            blob.downloadTo(output);
        }catch (Exception e){
            throw new SecurityException(ErrorCodeEnum.GOOGLE_CLOUD_STORAGE_ERROR.getMsg());
        }
        return output.toByteArray();
    }


    @Override
    public String uploadFile(MultipartFile fileStream)throws Exception{
        if(fileStream == null){
            throw new SecurityException(ErrorCodeEnum.GOOGLE_CLOUD_STORAGE_UPLOAD_ERROR.getMsg());
        }
        if(StringUtil.isEmpty(fileStream.getOriginalFilename())){
            throw new SecurityException(ErrorCodeEnum.GOOGLE_CLOUD_STORAGE_UPLOAD_ERROR.getMsg());
        }
        return uploadObject(fileStream.getOriginalFilename(),fileStream.getInputStream());
    }

    @Override
    public void deleteObject(String fileName)throws Exception{
        //https://storage.googleapis.com/static-res/test/f9634577a0eb4831a6fa4235c247a102.jpg
        //https://storage.cloud.google.com/static-res/test/f9634577a0eb4831a6fa4235c247a102.jpg
        if(StringUtil.isEmpty(fileName)){
            throw new SecurityException(ErrorCodeEnum.GOOGLE_CLOUD_STORAGE_NULL.getMsg());
        }
        String replace =  replaceFileName(fileName);
        try{
            storage.delete(bucketName, replace);
        }catch (Exception e){
            log.error("谷歌云存储删除失败",e.getMessage());
            throw new SecurityException(ErrorCodeEnum.GOOGLE_CLOUD_STORAGE_DELETE_ERROR.getMsg());
        }
    }


    @Override
    public List<String> listObjects(String fileName)throws Exception{
        String replace = replaceFileName(fileName);
        List<String> list = new ArrayList<>();
        try {
            Bucket bucket = storage.get(bucketName);
            Page<Blob> blobs = bucket.list(Storage.BlobListOption.prefix(replace),
                    Storage.BlobListOption.currentDirectory());
            for (Blob blob : blobs.iterateAll()) {
                list.add(downUrl+bucketName+SLASH+blob.getName());
            }
        }catch (Exception e){
            log.error("谷歌云存储查询失败",e.getMessage());
            throw new SecurityException(ErrorCodeEnum.GOOGLE_CLOUD_STORAGE_NULL.getMsg());
        }
        return list;
    }

    @Override
    public String replaceFileName(String objectName) throws Exception{
        String replace = "";
        if(objectName.contains(downUrl+bucketName+SLASH)){
            replace = objectName.replace(downUrl+bucketName+SLASH, "");
        }else if(objectName.contains(downUrl2+bucketName+SLASH)){
            replace = objectName.replace(downUrl2+bucketName+SLASH, "");
        }else {
            replace = pathFile+objectName;
        }
        return replace;
    }
}
