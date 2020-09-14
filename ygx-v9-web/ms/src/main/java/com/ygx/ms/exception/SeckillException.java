package com.ygx.ms.exception;

/**
 * 自定义异常  不是什么语法错误的问题 而是一些业务规则上的提醒
 */

public class SeckillException extends RuntimeException{

     public SeckillException(String message){
         super(message);
     }
}
