package com.chinahitech.shop.exception;

import com.chinahitech.shop.utils.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public Result handle(AccessDeniedException e) {
        return Result.error().message(e.getMessage());
    }

    @ExceptionHandler(DeleteException.class)
    @ResponseBody
    public Result handle(DeleteException e) {
        return Result.error().message(e.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseBody
    public Result handle(EntityNotFoundException e) {
        return Result.error().message(e.getMessage());
    }

    @ExceptionHandler(FileEmptyException.class)
    @ResponseBody
    public Result handle(FileEmptyException e) {
        return Result.error().message(e.getMessage());
    }

    @ExceptionHandler(FileSizeException.class)
    @ResponseBody
    public Result handle(FileSizeException e) {
        return Result.error().message(e.getMessage());
    }

    @ExceptionHandler(FileStateException.class)
    @ResponseBody
    public Result handle(FileStateException e) {
        return Result.error().message(e.getMessage());
    }

    @ExceptionHandler(FileTypeException.class)
    @ResponseBody
    public Result handle(FileTypeException e) {
        return Result.error().message(e.getMessage());
    }

    @ExceptionHandler(FileUploadException.class)
    @ResponseBody
    public Result handle(FileUploadException e) {
        return Result.error().message(e.getMessage());
    }

    @ExceptionHandler(InsertException.class)
    @ResponseBody
    public Result handle(InsertException e) {
        return Result.error().message(e.getMessage());
    }

    @ExceptionHandler(UpdateException.class)
    @ResponseBody
    public Result handle(UpdateException e) {
        return Result.error().message(e.getMessage());
    }

    @ExceptionHandler(UseridDuplicateException.class)
    @ResponseBody
    public Result handle(UseridDuplicateException e) {
        return Result.error().message(e.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    public Result handle(UserNotFoundException e) {
        return Result.error().message(e.getMessage());
    }

    @ExceptionHandler(RedisAddException.class)
    @ResponseBody
    public Result handle(RedisAddException e) {
        return Result.error().message(e.getMessage());
    }

    @ExceptionHandler(EmailException.class)
    @ResponseBody
    public Result handle(EmailException e) {
        return Result.error().message(e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Result handle(RuntimeException e) {
        return Result.error().message(e.getMessage());
    }
}
