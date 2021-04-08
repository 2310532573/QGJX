package com.luolin.entity;

import com.luolin.common.annotation.ApiAuth;
import com.luolin.common.enums.EnumApi;
import lombok.Data;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Date;

@Data
public class ApiBase implements Serializable {

    private Long id;

    private String apiName;

    private String apiPath;

    private Date lastUpdateTime;

    private String lastUpdateUser;

    /**
     * @see EnumApi.ApiType
     */
    private String apiType;

    /**
     * @see EnumApi.ApiScope
     */
    private String apiScope;

    private Boolean apiEnabled;

    private String apiMode;

    private Integer apiLevel;

    private String developer;

    private Integer ver;

    private Long menuId;

    private Integer ctxLevel;

    private boolean coverSiteCode;

    private Boolean isHidden;

    private String info;

    public ApiBase() {
    }

    public ApiBase(ApiAuth apiAuth) {
        this.apiName = apiAuth.apiName();
        this.apiPath = apiAuth.apiPath();
        EnumApi.ApiType[] apiTypes = apiAuth.apiType();
        if(apiTypes != null) {
            String types = StringUtils.arrayToDelimitedString(apiTypes, ",");
            this.apiType = types;
        }
        this.apiScope = apiAuth.scope().name();
        this.apiMode = apiAuth.apiMode().name();
        this.apiLevel = apiAuth.apiLevel().ordinal();
        this.lastUpdateUser = "system-init";
        this.ver = apiAuth.ver();
        this.developer = apiAuth.developer();
        this.apiEnabled = Boolean.TRUE;
        this.ctxLevel = apiAuth.ctxLevel().ordinal();
        this.coverSiteCode = apiAuth.coverSiteCode();
        this.isHidden = false;

        //动态获取设备信息
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = null;
        if (ra != null) {
            ServletRequestAttributes sra = (ServletRequestAttributes) ra;
            if (sra != null) {
                request = sra.getRequest();
            }
        }
        if (apiAuth.scope() == EnumApi.ApiScope.OTHERS && request != null) {
            String requestURI = request.getRequestURI();
            //获取登录设备信息
            String deviceQuery = null;
            String[] split = requestURI.split("/");
            if (split != null) {
                for (int i = 0; i < split.length; i++) {
                    if (!"".equals(split[i])){
                        deviceQuery = split[i];
                        break;
                    }
                }
                EnumApi.ApiScope scope = EnumApi.ApiScope.getScope(deviceQuery);
                if (scope != null){
                    this.apiScope = scope.name();
                }
            }
        }
    }

    public EnumApi.ApiScope getApiScopeEnum() {
        if(this.apiScope == null) {
            return null;
        }
        return EnumApi.ApiScope.valueOf(this.apiScope);
    }
}
