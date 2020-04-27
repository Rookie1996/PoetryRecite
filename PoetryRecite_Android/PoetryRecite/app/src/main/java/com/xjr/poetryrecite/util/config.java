package com.xjr.poetryrecite.util;

/**
 * Created by Raffello on 2019/5/8.
 */

public class config {

    //      主机 host
    //public static final String HOST = "http://192.168.56.1:8080/PoetryRecite/";
    public static final String HOST = "http://192.168.43.59:8080/PoetryRecite/";
    //      登录uri
    public static final String HOST_LOGIN = HOST + "userController/login.action";
    //      注册uri
    public static final String HOST_SIGNUP = HOST + "userController/signup.action";
    //      获取古诗uri
    public static final String HOST_POETRY = HOST + "poetryController/showPoetry";
    //      获取试题uri
    public static final String HOST_TEST = HOST + "testController/getTest";
    //      上传答题记录uri
    public static final String HOST_ANSWER = HOST + "gradeController/saveAnswer";
    //      下载答题记录uri
    public static final String HOST_ANSWER_DOWNLOAD = HOST + "gradeController/getUserAnswer";
    //      获取自动生成的古诗uri
    public static  final  String HOST_GENPOETRY = HOST+"genPoetryController/generatePoetry";
}
