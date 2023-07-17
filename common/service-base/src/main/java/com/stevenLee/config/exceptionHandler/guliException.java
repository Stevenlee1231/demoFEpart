package com.stevenLee.config.exceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class guliException extends RuntimeException{
    public String msg;
    public Integer code;
}
