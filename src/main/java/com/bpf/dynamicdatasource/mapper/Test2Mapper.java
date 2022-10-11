package com.bpf.dynamicdatasource.mapper;

import com.bpf.dynamicdatasource.pojo.TableName;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface Test2Mapper {
    @Select("select city from table_name limit 1")
    String findAll();

    @Select("select city from table_name limit 1")
    String insert();

    @Insert("insert into table_name(city, people) VALUE (#{tableName.city}, #{tableName.people})")
    Integer multiInsert(@Param("tableName") TableName tableName);
}
