package com.luolin.config;

import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 使用功能需要在谷歌云服务秘钥注入环境或者在此处重写
 */
//@Configuration
public class GoogleCloudStorageConfiguration {

    //@Value("${google.ProjectId}")
    private String projectId;

//    @Bean
//    public Storage storage(){
//        // 项目id = "your-project-id";
//        return StorageOptions.newBuilder().setProjectId(projectId).build().getService();
//    }
}
