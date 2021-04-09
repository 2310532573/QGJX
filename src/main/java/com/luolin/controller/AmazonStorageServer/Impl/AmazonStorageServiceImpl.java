package com.luolin.controller.AmazonStorageServer.Impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.luolin.common.enums.BuketEnums;
import com.luolin.controller.AmazonStorageServer.AmazonStorageService;
import com.luolin.entity.DownFile;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 *亚马逊云存储服务
 */
@Log4j2
@Service
public class AmazonStorageServiceImpl implements AmazonStorageService {

    //@Value("${aws.filePath}")
    private String filePath;
    private  String url = "https://{bucketName}.s3.{region}.amazonaws.com/{fileKey}";
    //@Resource
    private AmazonS3 s3Client;

    private static final String DOT = ".";

    @Override
    public String pubUpload(MultipartFile file)throws Exception {
        if(file == null){
            throw new SecurityException("文件不存在!");
        }
        String bucketName = BuketEnums.A2x3Z.getName();
        String name = file.getOriginalFilename();
        String type = "";
        if(name != null && name.contains(DOT)){
            type = name.substring(name.lastIndexOf(DOT), name.length());
        }
        String fileKey = filePath + UUID.randomUUID().toString().replace("-","")+type;
        ObjectMetadata metadata = new ObjectMetadata();
        //设置文件权限 公共读取 默认为私有文件
        metadata.setHeader("x-amz-acl","public-read");
        PutObjectRequest request = null;
        try {
            request = new PutObjectRequest(bucketName,fileKey,file.getInputStream(),metadata);
        } catch (IOException e) {
            e.printStackTrace();
            throw new SecurityException("读取文件失败！");
        }
        try {
            s3Client.putObject(request);
        }catch (Exception e){
            e.printStackTrace();
            throw new SecurityException("文件上传失败！");
        }
        return getUrl(bucketName,s3Client.getRegionName(),fileKey);
    }

    @Override
    public String priUpload(MultipartFile file)throws Exception {
        if(file == null){
            throw new SecurityException("文件不存在!");
        }
        String bucketName = BuketEnums.A2x3Z.getName();
        String name = file.getOriginalFilename();
        String type = "";
        if(name != null && name.contains(DOT)){
            type = name.substring(name.lastIndexOf(DOT), name.length());
        }
        String fileKey = filePath + UUID.randomUUID().toString().replace("-","")+type;
        ObjectMetadata metadata = new ObjectMetadata();
        PutObjectRequest request = null;
        try {
            request = new PutObjectRequest(bucketName,fileKey,file.getInputStream(),metadata);
        } catch (IOException e) {
            e.printStackTrace();
            throw new SecurityException("读取文件失败！");
        }
        try {
            s3Client.putObject(request);
        }catch (Exception e){
            e.printStackTrace();
            throw new SecurityException("文件上传失败！");
        }
        return bucketName+"/s3/"+fileKey;
    }

    @Override
    public DownFile priDownload(String downUrl) throws Exception {
        String s = "/s3/";
        if(!downUrl.contains(s)){
            throw new SecurityException("下载路径错误");
        }
        String[] split = downUrl.split(s);
        if(split.length != 2){
            throw new SecurityException("下载路径错误");
        }
        String bucketName = split[0];
        String fileKey = split[1];
        String fileName = fileKey.substring(fileKey.lastIndexOf("/")+1,fileKey.length());
        S3Object fullObject = null;
        try {
            GetObjectRequest request = new GetObjectRequest(bucketName, fileKey);
            fullObject = s3Client.getObject(request);
            S3ObjectInputStream content = fullObject.getObjectContent();
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024*8];
            int n = 0;
            while (-1 != (n = content.read(buffer))) {
                output.write(buffer, 0, n);
            }
            DownFile file = new DownFile();
            file.setName(fileName);
            file.setFile(output.toByteArray());
            return file;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SecurityException("文件下载失败");
        }  finally {
            if (fullObject != null) {
                fullObject.close();
            }

        }
    }

    @Override
    public void delPubFile(String fileName) throws Exception {
        String replace = fileName.replace("https://", "").
                replace("amazonaws.com/","").
                replace(".s3","");
        String[] split = replace.split("\\.");
        String bucketName = split[0];
        String fileKey = split[2]+DOT+split[3];
        try {
            s3Client.deleteObject(new DeleteObjectRequest(bucketName, fileKey));
        }catch (Exception e){
            e.printStackTrace();
            throw new SecurityException("文件删除失败！");
        }
    }

    @Override
    public void delPriFile(String fileName) throws Exception {
        if(!fileName.contains("/s3/")){
            throw new SecurityException("文件路径错误");
        }
        String[] split = fileName.split("/s3/");
        if(split.length != 2){
            throw new SecurityException("文件路径错误");
        }
        String bucketName = split[0];
        String fileKey = split[1];
        try {
            s3Client.deleteObject(new DeleteObjectRequest(bucketName, fileKey));
        }catch (Exception e){
            e.printStackTrace();
            throw new SecurityException("文件删除失败！");
        }
    }

    private String getUrl(String bucketName,String region,String fileKey){
        return url.replace("{bucketName}",bucketName).
                replace("{region}",region).
                replace("{fileKey}",fileKey);
    }
}
