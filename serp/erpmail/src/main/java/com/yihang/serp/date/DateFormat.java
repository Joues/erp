package com.yihang.serp.date;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: yihangjou(周逸航)
 * @Site: www.yihang.ml
 * @cnBlogs: https://www.cnblogs.com/yihangjou/
 * @Date: create in 2020/7/19 13:36
 */
public class DateFormat {
    private static final String dateformat = "yyyy-MM-dd HH:mm:ss";

//    格式化时间
    public static String getDateformat(Date date) {
        return new SimpleDateFormat(dateformat).format(date);
    }
}
