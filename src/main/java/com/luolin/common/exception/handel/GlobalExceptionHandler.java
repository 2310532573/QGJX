package com.luolin.common.exception.handel;

import com.luolin.common.enums.ErrorCodeEnum;
import com.luolin.common.exception.AuthException;
import com.luolin.common.exception.DatabaseException;
import com.luolin.common.exception.TokenException;
import com.luolin.utils.Result;
import com.luolin.utils.Status;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DeadlockLoserDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ValidationException;
import java.util.List;
import java.util.Objects;

@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final  String  DENIED_STR = "Access is denied";

    /**
     * 400 - Bad Request
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("could_not_read_json...", e.getMessage());
        return new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErrorCodeEnum.NETWORK_ERROR.getMsg());
    }

    /**
     * 认证错误
     */
    @ExceptionHandler(AuthException.class)
    public Result handleAuthenticationException(AuthException e) {
        log.error("认证错误", e.getMessage());
        return new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErrorCodeEnum.USER_AUTH_ERROR.getMsg());
    }


    /**
     * token失效
     */
    @ExceptionHandler(TokenException.class)
    public Result handleAuthenticationException(TokenException e) {
        log.error("无效token", e.getMessage());
        return new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErrorCodeEnum.PARAM_TOKEN_NULL_ERROR.getMsg());
    }

    /**
     * 400 - Bad Request
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Result handleValidationException(MethodArgumentNotValidException e) {
        log.error("parameter_validation_exception...", e);
        return new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErrorCodeEnum.NETWORK_ERROR.getMsg());
    }

    /**
     * ValidationException
     * 500, "Internal Server Error"
     */
    @ExceptionHandler(ValidationException.class)
    public Result handleValidationException(ValidationException e) {
        log.error("请求参数校验失败："+e.getMessage(), e);
        return new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErrorCodeEnum.NETWORK_ERROR.getMsg()+ e.getCause().getMessage());
    }

    /**
     * 405 - Method Not Allowed。HttpRequestMethodNotSupportedException
     * 是ServletException的子类,需要Servlet API支持
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("request_method_not_supported...", e);
        return new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErrorCodeEnum.NETWORK_ERROR.getMsg());
    }

    /**
     * 415 - Unsupported Media Type。HttpMediaTypeNotSupportedException
     * 是ServletException的子类,需要Servlet API支持
     */
    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    public Result handleHttpMediaTypeNotSupportedException(Exception e) {
        log.error("content_type_not_supported...", e);
        return new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErrorCodeEnum.NETWORK_ERROR.getMsg());
    }

    /**
     * 参数绑定异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)
    public Result bindExceptionHandler(BindException e){
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        //只输出第一个校验异常信息
        String errorMsg = fieldErrors.get(0).getDefaultMessage();
        //@开头的message 替换为LanguageEnum中的国际化提示，例如:@2051
        Result resultModel = new Result();
        resultModel.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        resultModel.setMsg(errorMsg);
        return resultModel;
    }

    @ExceptionHandler(value = DeadlockLoserDataAccessException.class)
    public Result deadlockLoserDataAccessExceptionHandler(DeadlockLoserDataAccessException e){
        log.error("事务锁异常！" + e);
        Result result = new Result();
        result.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        result.setMsg(ErrorCodeEnum.NETWORK_ERROR.getMsg());
        return result;
    }

    @ExceptionHandler(value = DatabaseException.class)
    public Result DatabaseExceptionHandler(DatabaseException e){
        log.error(e.getErrContent());
        Result result = new Result();
        result.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        if (Objects.nonNull(e.getErrContent())) {
            result.setMsg(Status.ERROR.toString());
        }
        result.setMsg(e.getErrContent());
        return result;
    }

    /**
     * 捕捉其他所有异常
     * @param e
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public Result exceptionHandler(Exception e) {
        log.error("捕获异常~",e);
        boolean flag = false;
        boolean methodSecurity = (e.getCause() != null
                && e.getCause().getMessage().indexOf("org.springframework.security.access.expression.method.MethodSecurityExpressionRoot") != -1);

        if(DENIED_STR.equals(e.getMessage())
                || "不允许访问".equals(e.getMessage())
                || methodSecurity) {
            flag = true;
        }
        Result result;
        if(flag) {
            result = new Result(HttpStatus.UNAUTHORIZED.value(), ErrorCodeEnum.NETWORK_ERROR.getMsg());
        }else {
            result = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErrorCodeEnum.NETWORK_ERROR.getMsg());
        }
        return result;

    }


}
