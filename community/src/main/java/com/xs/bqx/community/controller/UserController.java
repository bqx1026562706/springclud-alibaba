package com.xs.bqx.community.controller;

import com.sun.java.browser.plugin2.liveconnect.v1.Result;
import com.xs.bqx.community.pojo.ResumeHistoryClean;
import com.xs.bqx.community.service.UserService;
import com.xs.bqx.community.utils.DESUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.lang3.StringUtils;
import javax.mail.search.ComparisonTerm;
import javax.mail.search.ReceivedDateTerm;
import javax.mail.search.SearchTerm;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;


    public static void main(String[] args) throws ParseException {
        LocalDate localDate = LocalDate.of(2020, 9, 13);

        long until = LocalDate.now().until(localDate, ChronoUnit.DAYS);
        System.out.println(until);

     /*
        LocalDate startDate = LocalDate.of(2019, 1, 1);
        LocalDate endDate = LocalDate.now();

        Period period = Period.between(startDate, endDate);
        System.out.println(period);*/

    /*    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date date = sdf.parse("2020-9-13");

        Calendar cld = Calendar.getInstance();
        cld.setTime(date);
        cld.set(Calendar.DATE, cld.get(Calendar.DATE));

        String format = sdf.format(cld.getTime());
        System.out.println(format);*/


    }

   // int firstWork = LocalDate.now().getYear() - Integer.parseInt(firstWorkStr);


    private static String getNumber(String str) {
        if (org.springframework.util.StringUtils.isEmpty(str)) {
            return null;
        }
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }













    /**
     * rpo访问 测试
     * @param
     * @return
     * @throws IOException
     */
    @RequestMapping("/getRpoCityList")
    public  List<Map> getRpoCityList(HttpServletResponse servletResponse) throws Exception {
      List<Map> city =    userService.getRpoCityList();

        // 设置cookie
        String  companyId="968";
        Cookie cookie = setCompanyIdCookie(companyId);
        servletResponse.addCookie(cookie);
        System.out.println(cookie);
        return  city;
    }

    @RequestMapping("/getCookie")
    public  String getCookie(HttpServletRequest request) throws Exception {
        //cookie 中取companyId
        String cookieCompanyId = "";
        String cookieCompanyId2 = null;
        if(StringUtils.isEmpty(cookieCompanyId2)){
            // 判断cookie 是否存在
            String value = null;
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    //在查询c端职位列表 时 /positionPage，存的 cookie
                   /* if ("rpoCompanyId".equals(cookie.getName())) {
                        value = cookie.getValue();
                    }*/
                    if ("".equals(cookie.getName())) {
                        value = cookie.getValue();
                    }
                }
            }
            cookieCompanyId = DESUtils.decryptByDESCompanyId(value);
        }
        System.out.println(cookieCompanyId);

        return cookieCompanyId;
    }





    /**
     * description: 设置登录cookie
     * @param sessionId
     * @return
     */
    private Cookie setCompanyIdCookie(String sessionId) throws Exception {
        // 加密
        String encryptUserId = DESUtils.encryptByDesCompanyId(sessionId);

        // 设置 cookie
        Cookie cookie = new Cookie("rpoCompanyId", encryptUserId);
      //  cookie.setMaxAge(Integer.parseInt("30000"));//3天太长，换
        cookie.setPath("/");
        //cookie.setDomain(".baidu.com");
        return cookie;
    }

    /**
     * description: 删除cookies
     * @param request
     * @return
     */
    @RequestMapping("/deleteCookie")
    public Cookie removeCookie (HttpServletRequest request,HttpServletResponse re
    ) {
        Cookie[] cookies = request.getCookies();
        for (Cookie c : cookies) {
            if ("".equals(c.getName())) {
                Cookie cookie = new Cookie(c.getName(), "");
                //路径要相同
                cookie.setPath("/");
                cookie.setMaxAge(0);
                re.addCookie(cookie);
                return cookie;
            }
        }
        return null;
    }



    /**
     * 获取昨天零点
     *
     * @return date
     */
    public static Date getYesterdayZero() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取今天零时的时间
     *
     * @return date
     */
    public static Date getTodayZero() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }











    @RequestMapping("getImageFromHttp")
    public  String getImageFromHttp(String urlStr) throws IOException {
        userService.getImageFromHttp(urlStr);
        return  "";
    }


    @RequestMapping("resumeHistoryClean")
    public  String resumeHistoryClean(String urlStr)  {
        ResumeHistoryClean p=  userService.resumeHistoryClean(urlStr);
        return  "";
    }










    public List<Map<String, Object>> getAllPartitionChild2(int companyId) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("companyId",companyId);
        //查询出全部的数据
       // List<Map<String, Object>> mapAllPartition = dao.getNamedParamterDao().queryForList(ResumeSql.SELECT_ALL_PARTITION, parameterSource);
        List<Map<String, Object>> mapAllPartition = new ArrayList<>();
        //所有父级为0
        List<Map> provinceMapList = new ArrayList<>();
        // pid + 下一级的全部 list 只包含当前的下属
        Map<Integer,List<Map<String,Object>>>  pidObjectMap =new HashMap<>();

        for (Map<String, Object> province:mapAllPartition ) {
            Integer parentId = (Integer) province.get("parentId");
            if(0==parentId){
                provinceMapList.add(province);
            }
            List<Map<String, Object>> childList =new ArrayList<>();
            if(CollectionUtils.isEmpty(childList)){
                childList = new ArrayList<>();
            }
            childList.add(province);
            pidObjectMap.put((Integer) province.get("parentId"),childList);
        }
        //返回数据
        List<Map<String, Object>> result =new ArrayList<>();
        for (Map provinceMap: provinceMapList) {
            Map<String, Object> value =  PartitionChild(provinceMap,pidObjectMap);
            result.add(value);
        }

        return result;
    }



    //递归
    private Map<String, Object> PartitionChild(Map provinceMap, Map<Integer, List<Map<String,Object>>> pidObjectMap){
        List<Map<String,Object>> childMapList =  pidObjectMap.get( (Integer) provinceMap.get("parentId") );
        if(CollectionUtils.isEmpty(childMapList)){
            return provinceMap;
        }else {
            for (Map<String,Object> childM:childMapList){
                Map<String, Object> value =PartitionChild(provinceMap,pidObjectMap);
                List<Map<String,Object>> dxList =null;

                if(CollectionUtils.isEmpty(provinceMap)){
                    dxList=new ArrayList<>();
                }else {
                    dxList= childMapList;
                }
                dxList.add(value);
                provinceMap.put("child",dxList);

            }
        }

        return  provinceMap;
    }


    /**
     * 转化天数
     * @param num
     */
    public static void nowDateMove(int num) {
        //获取当前日期
        LocalDate ld = LocalDate.now();
        //要移动的天数num，可以是整数或者负数但是后退推荐使用minusDays
        LocalDate ll = ld.plusDays(num);
        System.out.println("今天是： " + ld + "------再过" + num + "天是：" + ll);
    }

    /**
     * 替换字符
     *
     * @param stb    source
     * @param oldStr 被替换的字符串
     * @param newStr 替换oldStr
     */
    public static StringBuilder replaceAll(StringBuilder stb, String oldStr, String newStr) {
        if (stb == null || oldStr == null || newStr == null || stb.length() == 0 || oldStr.length() == 0) {
            return stb;
        }
        int index = stb.indexOf(oldStr);
        if (index > -1 && !oldStr.equals(newStr)) {
            int lastIndex = 0;
            while (index > -1) {
                stb.replace(index, index + oldStr.length(), newStr);
                lastIndex = index + newStr.length();
                index = stb.indexOf(oldStr, lastIndex);
            }
        }
        return stb;
    }


    @PostMapping("/mapUpload")
    public Result uploadPhoto(@RequestParam("file") MultipartFile file,HttpServletRequest request){
        if(!file.isEmpty()) {
            // 获取文件名称,包含后缀
            String fileName = file.getOriginalFilename();
        }
        return null;
    }

    @RequestMapping("/excleDownLoad")
    public  byte[]  excleDownLoad(HttpServletRequest req , HttpServletResponse res){
            try {
                //获取输入流，原始模板位置
                String filePath = getClass().getResource("/excelTemplate.xlsx").getPath();
                InputStream bis = new BufferedInputStream(new FileInputStream(new File(filePath)));
                //假如以中文名下载的话，设置下载文件名称
                String filename = "简历导入模板.xlsx";
                //转码，免得文件名中文乱码
                filename = URLEncoder.encode(filename, "UTF-8");
                //设置文件下载头
                res.addHeader("Content-Disposition", "attachment;filename=" + filename);
                //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
                res.setContentType("multipart/form-data");
                BufferedOutputStream out = new BufferedOutputStream(res.getOutputStream());
                int len = 0;
                while ((len = bis.read()) != -1) {
                    out.write(len);
                    out.flush();
                }
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        return null;
    }

    // 本地单个文件的上传
    @PostMapping("/upload")
    public String upload(MultipartFile uploadFile, HttpServletRequest request) {


        String realPath = request.getSession().getServletContext().getRealPath("/uploadFile/");
        File dir = new File(realPath);
        if (!dir.isDirectory()) {//文件目录不存在，就创建一个
            dir.mkdirs();
        }
        try {
            String filename = uploadFile.getOriginalFilename();
            String suffix = filename.substring(filename.indexOf(".") + 1).toLowerCase();

            //服务端保存的文件对象
            File fileServer = new File(dir, filename);
            System.out.println("file文件真实路径:" + fileServer.getAbsolutePath());
            //2，实现上传
            uploadFile.transferTo(fileServer);
            String filePath = request.getScheme() + "://" +
                    request.getServerName() + ":"
                    + request.getServerPort()
                    + "/uploadFile/" + filename;
            //3，返回可供访问的网络路径
            return filePath;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败";
    }
    //多个文件的上传
    @PostMapping("/uploads")
    public String uploads(MultipartFile[] uploadFiles, HttpServletRequest request) {
        //1，对文件数组做判空操作
        if (uploadFiles == null || uploadFiles.length < 1) {
            return "文件不能为空";
        }
        //2，定义文件的存储路径,
        String realPath = request.getSession().getServletContext().getRealPath("/uploadFile/");
        File dir = new File(realPath);
        if (!dir.isDirectory()) {//文件目录不存在，就创建一个
            dir.mkdirs();
        }
        try {
            String filePathS = "";
            //3，遍历文件数组，一个个上传
            for (int i = 0; i < uploadFiles.length; i++) {
                MultipartFile uploadFile = uploadFiles[i];
                String filename = uploadFile.getOriginalFilename();
                //服务端保存的文件对象
                File fileServer = new File(dir, filename);
                System.out.println("file文件真实路径:" + fileServer.getAbsolutePath());
                //2，实现上传
                uploadFile.transferTo(fileServer);
                String filePath = request.getScheme() + "://" +
                        request.getServerName() + ":"
                        + request.getServerPort()
                        + "/uploadFile/" + filename;
                filePathS = filePathS + "\n" + filePath;
            }
            //4，返回可供访问的网络路径
            return filePathS;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败";
    }






    @RequestMapping("/log")
    public String testLog() {
        logger.debug("=====测试日志debug级别打印====");
        logger.info("======测试日志info级别打印=====");
        logger.error("=====测试日志error级别打印====");
        logger.warn("======测试日志warn级别打印=====");
        // 可以使用占位符打印出一些参数信息
        String str1 = "blog.itcodai.com";
        String str2 = "blog.csdn.net/eson_15";
        logger.info("======倪升武的个人博客：{}；倪升武的CSDN博客：{}", str1, str2);
        return "success";
    }

    @RequestMapping("/login")
    public String testLogin() {
        System.out.println("=========================" + "启动成功");
        return "=========================" + "启动成功";
    }

    /**
     * 根据id 找对应使用用户
     * @param
     * @return
     */
/*    @RequestMapping("/findByUserId/{userId}")
    public ResultModel<User> getUser(@PathVariable(value = "userId") String userId) {
        ResultModel<User> user=   userService.getUser(userId);
        return user;
    }*/



    @RequestMapping("/map")
    public Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap<>(3);
        map.put("博客地址", "55");
        map.put("CSDN地址", "56");
        map.put("粉丝数量", 4153);
        return map;
    }



}
