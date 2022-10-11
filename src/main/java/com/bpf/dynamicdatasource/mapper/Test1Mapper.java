package com.bpf.dynamicdatasource.mapper;

import com.bpf.dynamicdatasource.pojo.TableName;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface Test1Mapper {

    @Select("select user from test limit 1")
    String findAll();

    Integer multiInsert(@Param("tableName") TableName tableName);
}
