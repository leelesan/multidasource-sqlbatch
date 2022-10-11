package com.bpf.dynamicdatasource.service;

import com.bpf.dynamicdatasource.config.DataSource;
import com.bpf.dynamicdatasource.config.DataSourceEnum;
import com.bpf.dynamicdatasource.mapper.Test1Mapper;
import com.bpf.dynamicdatasource.mapper.Test2Mapper;
import com.bpf.dynamicdatasource.pojo.FinalPicBaseReqDto;
import com.bpf.dynamicdatasource.pojo.TableName;
import com.bpf.dynamicdatasource.util.MybatisBatchUtils;
import com.bpf.dynamicdatasource.util.PicBeanAddPropertiesUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TestService {

    @Resource
    private Test1Mapper test1Mapper;
    @Resource
    private Test2Mapper test2Mapper;
    @Resource
    private MybatisBatchUtils mybatisBatchUtils;


    public String findTest1() {
        return test1Mapper.findAll();
    }

    @DataSource(DataSourceEnum.TEST2)
    public String findTest2() {
        return test2Mapper.findAll();
    }

    /**
     * 如果切面不加@Order，添加Transactional注解会导致切换数据源失效
     */

    @DataSource(DataSourceEnum.TEST2)
    public String insert() {
        String insert = test2Mapper.insert();
        return insert;
    }

    @DataSource(DataSourceEnum.TEST2)
    public Integer multiInsert(List<TableName> tableNameList) {
        long start = System.currentTimeMillis();
        int i = mybatisBatchUtils.batchUpdateOrInsert(tableNameList, Test2Mapper.class,
                (TableName, test2Mapper) -> test2Mapper.multiInsert(TableName));
        System.out.println(System.currentTimeMillis()-start);
        return i;
    }

    @DataSource(DataSourceEnum.TEST1)
    public Integer multiInsertTest1(List<TableName> tableNameList) {
        long start = System.currentTimeMillis();
        int i = mybatisBatchUtils.batchUpdateOrInsert(tableNameList, Test2Mapper.class,
                (TableName, test2Mapper) -> test2Mapper.multiInsert(TableName));
        System.out.println(System.currentTimeMillis() - start);
        return i;
    }



    public static void main(String[] args) {


        FinalPicBaseReqDto entity = new FinalPicBaseReqDto();
        entity.setAppKey("eee");
        entity.setContent("222");
        Map<String, Object> addProperties = new HashMap() {{
            put("name", "22");
            put("hobby", "swim");
        }};
        FinalPicBaseReqDto finalPicBaseReqVo = (FinalPicBaseReqDto) PicBeanAddPropertiesUtil.getTarget(entity, addProperties);
        System.out.println(finalPicBaseReqVo.toString());
    }
}
