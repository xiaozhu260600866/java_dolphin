package com.xxx.server.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/*
*@auth xiaozhu
*@date 2021/5/22
*description mybatis 添加前，后事件
*params * @Param null:
*return
*/
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {



    @Override
    public void insertFill(MetaObject metaObject) {
        //属性名称，不是字段名称
        System.out.println("0097");
        this.setFieldValByName("created_at", LocalDateTime.now(), metaObject);
        this.setFieldValByName("updated_at", LocalDateTime.now(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {

        this.setFieldValByName("updated_at", LocalDateTime.now(), metaObject);
    }
}
