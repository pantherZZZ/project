package com.ttwl.exception;

import com.ttwl.until.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.nio.file.AccessDeniedException;
import java.util.List;

/**
 * @Author zhang bao
 * @Date 2022/5/27 10:43
 * @Description： 全局异常
 * @Version 1.0
 */
@RestControllerAdvice
@Slf4j
public class GlobalException {
    /**
     * 捕获所有（Exception.class）中的异常并通过下面的方法返回
     *
     * @param e 错误类型
     * @return 给前端返回报错信息
     */
    @ExceptionHandler(value = Exception.class)
    public R<String> toHandleUnexpectException(Exception e) {
        e.printStackTrace();
        return R.error(500,"出现了预料外的错误请联系管理员！："+e.getMessage());

    }

    @ExceptionHandler(value = BasicException.class)
    public R<String> toHandleException(Exception e) {
        e.printStackTrace();
        return R.error(500,"Error",e.getMessage());
    }

    @ExceptionHandler(value = MaxUploadSizeExceededException.class)
    public R<String> uploadException(Exception e) {
        e.printStackTrace();
        return R.error(500,"Error","文件体积过大");
    }
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R<String> validException(MethodArgumentNotValidException e){
        BindingResult result = e.getBindingResult();
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            if (!errors.isEmpty()) {
                // 这里列出了全部错误参数，按正常逻辑，只需要第一条错误即可
                FieldError fieldError = (FieldError) errors.get(0);
                return R.error(500, "Error", fieldError.getDefaultMessage());
            }
        }
        return R.error(500, "Error", "参数验证错误");
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public R<String> accessDeniedException(){
        return R.error(401, "Error", "请先登录");
    }

    @ExceptionHandler(value = NullPointerException.class)
    public R<String> nullPointerException(NullPointerException e){
        return R.error(500,"Error",e.getMessage());
    }

//    @ExceptionHandler(value = AuthorizationServiceException.class)
//    public R<String> unAuthorization(){
//        return R.error(403, "Error", "权限不足");
//    }
}
