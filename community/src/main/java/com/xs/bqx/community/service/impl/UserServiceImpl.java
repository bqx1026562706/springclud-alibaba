package com.xs.bqx.community.service.impl;

import com.xs.bqx.community.mapper.FileMapper;
import com.xs.bqx.community.mapper.UserMapper;
import com.xs.bqx.community.pojo.ResumeHistoryClean;
import com.xs.bqx.community.pojo.Student;
import com.xs.bqx.community.service.FileService;
import com.xs.bqx.community.service.UserService;
import com.xs.bqx.community.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FileMapper fileMapper;

    @Autowired(required = false)
    private  FileService fileService;

  /*  @Override
    public ResultModel<User> getUser(String userId) {
        ResultModel<User> userResultModel=  userDao.getUser(userId);
        return userResultModel;
    }*/

    @Override
    public void getImageFromHttp( String urlStr) throws IOException {
        ByteArrayOutputStream output = null;
        InputStream inputStream = null;
        InputStream result = null;
        try {
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(20 * 1000);
            inputStream = connection.getInputStream();
            output = FileUtils.inputStreamCache(inputStream);
            result = new ByteArrayInputStream(output.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (output != null) {
                output.close();
                output = null;
            }
            if (inputStream != null) {
                inputStream.close();
                inputStream = null;
            }
        }
    }

   /* @Override
    public ServiceResponse insertUserExcle(List<ExcleImportInfo> list) {
           userMapper.insertUserExcle(list);

        return  ServiceResponse.ok();
    }

    @Override
    public ServerResponse impltreadCityExcel(List<ExcleImportCityRequest> list) {
        userMapper.impltreadCityExcel(list);
        return null;
    }*/

    @Override
    public ResumeHistoryClean resumeHistoryClean(String urlStr) {




        return null;
    }

    @Override
    @Transactional
    public List<Map> getRpoCityList() {
        //测试事务回滚
        List<Map> m=   userMapper.getRpoCityList();
        //调用别的类  加入事务控制
        Student student = new Student();

        try {
            fileService.addStudent(student);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("手动异常");
        }
/*
        Student student = new Student();
        student.setCourse("cs");
        student.setName("sid");
        try {
            fileMapper.addStudent(student);
        }catch (Exception e){
            //不抛出
            e.printStackTrace();
            System.out.println("手动异常");
        }*/


        return m;
    }
}
