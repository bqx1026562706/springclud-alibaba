package com.xs.bqx.community.service;

import com.xs.bqx.community.pojo.Student;
import org.springframework.stereotype.Service;

@Service
public interface FileService {

  void  addStudent(Student student);

}
