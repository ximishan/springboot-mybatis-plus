//package com.demo.mybatisplus;
//
//import com.demo.mybatisplus.model.UpmsUser;
//import com.demo.mybatisplus.service.IUpmsUserService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.UUID;
//
//@SpringBootTest
//class MybatisPlusApplicationTests {
//
//    @Autowired
//    private IUpmsUserService iUpmsUserService;
//
//    @Test
//    void testInsert() {
//        UpmsUser upmsUser = new UpmsUser();
//        upmsUser.setUserId(UUID.randomUUID().toString().replace("-",""));
//        upmsUser.setRealname("user1");
//        boolean result = iUpmsUserService.insert(upmsUser);
//        System.out.println(result);
//    }
//
//    @Test
//    void contextLoads() {
//        UpmsUser upmsUser = iUpmsUserService.selectById(1);
//        System.out.println(upmsUser.toString());
//    }
//
//}
