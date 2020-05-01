package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

// lombok包的作用，是将普通Java类中的getter、setter、有参无参构造器等样板代码变成用注解的方式注入到POJO类中。
@Data
@AllArgsConstructor
@NoArgsConstructor
// Serializable:序列化
public class Payment implements Serializable
{
    private Long id;
    private String serial;
}
