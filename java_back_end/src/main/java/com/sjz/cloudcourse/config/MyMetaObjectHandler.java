package com.sjz.cloudcourse.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;

import com.sjz.cloudcourse.common.BaseContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @description mybatis-plus自动填充字段
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    /**
     * 插入数据时自动填充字段
     *
     * @param metaObject metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill.....");
        this.setFieldValByName("gmt_create", new Date(), metaObject);
        this.setFieldValByName("gmt_modified", new Date(), metaObject);
    }


    /**
     * 更新数据时自动填充字段
     *
     * @param metaObject metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill.....");
        this.setFieldValByName("gmt_modified", new Date(), metaObject);
//        metaObject.setValue("updatedBy", BaseContext.getCurrentId());
    }

}
