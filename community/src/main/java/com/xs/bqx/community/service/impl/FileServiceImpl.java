package com.xs.bqx.community.service.impl;

import com.xs.bqx.community.mapper.FileMapper;
import com.xs.bqx.community.pojo.Student;
import com.xs.bqx.community.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileMapper fileMapper;


    @Override
    @Transactional
    public void addStudent(Student student) {
        Student student2 = new Student();
        student2.setCourse("cs");
        student2.setName("sid");
        fileMapper.addStudent(student2);
    }



}
