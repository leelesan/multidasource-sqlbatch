package com.bpf.dynamicdatasource;

import com.bpf.dynamicdatasource.pojo.TableName;
import com.bpf.dynamicdatasource.service.TestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("mapper")
public class DynamicDatasourceApplicationTests {

    @Autowired
    private TestService testService;

    @Test
    public void test() {
        System.out.println(testService.findTest1());
    }

    @Test
    public void test2() {
        System.out.println(testService.findTest2());
    }

    @Test
    public void test3() {
        List<TableName> tableNames = new ArrayList<>();
        for (int i = 0; i < 2000; i++) {
            String city = "城市";
            String people = "用户";
            TableName tableName = new TableName(city + i, people + i);
            tableNames.add(tableName);
        }
        Integer integer = testService.multiInsert(tableNames);
       // Integer integer = testService.multiInsertTest1(tableNames);
        //System.out.println(integer);
    }

}
