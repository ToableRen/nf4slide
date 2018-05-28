package com.qtu404.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qtu404.obsolete.dataBase.DataBaseManager;
import com.qtu404.obsolete.file.FileManager;
import com.qtu404.obsolete.file.uploadSlide;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


/**
 * Servlet implementation class UploadServlet
 */
@WebServlet(name = "fileUploadServlet", urlPatterns = {"/fileUpload.do"})
public class FileUploadServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // 上传文件存储目录
    private static final String UPLOAD_DIRECTORY = "upload";

    // 上传配置
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3 * 10; // 30MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40 * 10; // 400MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50 * 10; // 500MB

    /**
     * 上传数据及保存文件
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 检测是否为多媒体上传
        request.setCharacterEncoding("utf-8");
        String fileName;
        String filePath = null;
        if (!ServletFileUpload.isMultipartContent(request)) {
            // 如果不是则停止
            PrintWriter writer = response.getWriter();
            writer.println("Error: 表单必须包含 enctype=multipart/form-data");
            writer.flush();
            return;
        }

        // 配置上传参数
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // 设置临时存储目录
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);

        // 设置最大文件上传值
        upload.setFileSizeMax(MAX_FILE_SIZE);

        // 设置最大请求值 (包含文件和表单数据)
        upload.setSizeMax(MAX_REQUEST_SIZE);

        // 中文处理
        upload.setHeaderEncoding("UTF-8");

        // 构造临时路径来存储上传的文件
        // 这个路径相对当前应用的目录
        String uploadPath = getServletContext().getRealPath("./") + File.separator + UPLOAD_DIRECTORY;

        // 如果目录不存在则创建
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        try {
            // 解析请求的内容提取文件数据
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);

            if (formItems != null && formItems.size() > 0) {
                // 迭代表单数据
                for (FileItem item : formItems) {
                    // 处理不在表单中的字段
                    if (!item.isFormField()) {
                        fileName = new File(item.getName()).getName();
                        filePath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filePath);
                        // 在控制台输出文件的上传路径
                        System.out.println(filePath);
                        // 保存文件到硬盘
                        item.write(storeFile);
                        request.setAttribute("message", "文件上传成功!");
                    }
                }
            }
        } catch (Exception ex) {
            request.setAttribute("message", "错误信息: " + ex.getMessage());
        }

        //当前操作的用户的id
        String userId = (String) request.getSession().getAttribute("usrname");

        //先添加给图片的存储目录,根据用户id
        String destDirName = "/home/tomcat/apache-tomcat-8.5.8/webapps/NF4Slides/" + userId;//tomcat服务器绝对路径
        String path1 = getServletConfig().getServletContext().getRealPath("");
        System.out.println("getServletConfig():------ " + path1);


        File dir = new File(destDirName);
        dir.setWritable(true, false);
        if (dir.exists()) {// 判断目录是否存在
            System.out.println("创建目录失败，目标目录已存在");
        }
        if (!destDirName.endsWith(File.separator)) {// 结尾是否以"/"结束
            destDirName = destDirName + "/";
        }
        if (dir.mkdirs()) {// 创建目标目录
        } else {
        }
        /*数据库新添加一个幻灯片*/
        //先找到最大的幻灯片的页数
        Object Slide_Data[][] = DataBaseManager.unionQuery("select slideId,name from slides where userId = '" + userId + "'order by slideId desc", 2);
        int maxSlideId = 100001;
        if (Slide_Data == null || Slide_Data.length != 0) {
            maxSlideId = Integer.parseInt((String) Slide_Data[0][0]) + 1;
        }
        String SQL_String = "insert into slides values('" + userId + "','" + maxSlideId + "','','新导入幻灯片')";
        DataBaseManager.generalUpdate(SQL_String);
        FileManager.writeInto("", userId + "/" + maxSlideId);
        FileManager.writeInto("", userId + "/" + maxSlideId + "_play");

        File file = new File(filePath);

        ArrayList<String> list = new ArrayList<String>();
        File file2 = new File(destDirName);
        uploadSlide.doPPT2007toImage(file, file2, list, maxSlideId + "");
        String imgCode = uploadSlide.jpegToImgString(list, userId);
        String playCode = uploadSlide.jpegToImgPlay(list, userId);
        System.out.println(playCode);
        FileManager.writeInto(imgCode, userId + "/" + maxSlideId);
        FileManager.writeInto(playCode, userId + "/" + maxSlideId + "_play");
        System.out.println("playCode" + playCode);
        response.sendRedirect("toUserPage");
    }
}