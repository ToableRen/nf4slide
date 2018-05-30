package com.qtu404.obsolete.file;

import org.omg.CORBA.StringHolder;

import java.io.File;

public class FileManage {
    public final static String twitterImageUrl = "imageLib" + File.separator + "cloudEase" + File.separator + "twitter";
    public final static String userAvatorUrl = "imageLib" + File.separator + "cloudEase" + File.separator + "avator";
    public final static String testRootUrl = "E:" + File.separator + "Workspaces" + File.separator + "tomcat for IDEA" + File.separator + "webapps" + File.separator + "CloudEase" + File.separator;
    public final static String avatorName = "user.jpg";

    public static String getTwitterImgPath(String rootPath) {
        File webFile = new File(rootPath);
        String twitterImageFilePath = webFile.getParent() + File.separator + twitterImageUrl + File.separator;
        return twitterImageFilePath;
    }

    public static String getAvatorPath(String rootPath, long userId) {
        File webFile = new File(rootPath);
        String avatprImageFilePath = webFile.getParent() + File.separator + userAvatorUrl + File.separator + userId + File.separator;
        return avatprImageFilePath + avatorName;
    }

    public static String getAvatorUrl(long userId) {
        String avatprImageFilePath = File.separator + userAvatorUrl + File.separator + userId + File.separator;
        return avatprImageFilePath + avatorName;
    }

    public static boolean createUserAvatorFIle(String rootUrl, long userId) {
        File webFile = new File(rootUrl);
        String avatprImageFilePath = webFile.getParent() + File.separator + userAvatorUrl + File.separator + userId + File.separator;
        File twitterImageFile = new File(avatprImageFilePath);
        System.out.println(twitterImageFile.getAbsolutePath());
        if (twitterImageFile.exists()) {// 判断目录是否存在
            System.out.println("创建目录失败，目标目录已存在");
            return false;
        }
        if (twitterImageFile.mkdirs()) {// 创建目标目录
            System.out.println("创建目录成功！" + avatprImageFilePath);
            return true;
        } else {
            System.out.println("创建目录失败！" + avatprImageFilePath);
            return false;
        }
    }

    public static boolean createTwitterImageFIle(String rootUrl, long twitterId) {
        File webFile = new File(rootUrl);
        String twitterImageFilePath = webFile.getParent() + File.separator + twitterImageUrl + File.separator + twitterId + File.separator;
        File twitterImageFile = new File(twitterImageFilePath);
        System.out.println(twitterImageFile.getAbsolutePath());
        if (twitterImageFile.exists()) {// 判断目录是否存在
            System.out.println("创建目录失败，目标目录已存在");
            return false;
        }
        if (twitterImageFile.mkdirs()) {// 创建目标目录
            System.out.println("创建目录成功！" + twitterImageFilePath);
            return true;
        } else {
            System.out.println("创建目录失败！" + twitterImageFilePath);
            return false;
        }
    }


    public static void main(String[] args) {
        createUserAvatorFIle(testRootUrl, 100001L);
    }
}
