package com.xs.bqx.community.service;

import com.xs.bqx.community.pojo.ResumeHistoryClean;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public interface UserService {
   // ResultModel<User> getUser(String userId);

    void getImageFromHttp(String urlStr) throws IOException;

 /*   ServiceResponse insertUserExcle(List<ExcleImportInfo> list);

    ServerResponse impltreadCityExcel(List<ExcleImportCityRequest> list);*/

    ResumeHistoryClean resumeHistoryClean(String urlStr);

    List<Map> getRpoCityList();
}
