package com.xs.bqx.community.mapper;

import com.xs.bqx.community.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

@Mapper
public interface FileMapper {

    @Transactional
    void addStudent(Student student);
}
