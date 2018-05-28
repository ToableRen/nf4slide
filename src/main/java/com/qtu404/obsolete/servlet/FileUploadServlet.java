package com.qtu404.obsolete.servlet;//package com.qtu404.obsolete.servlet;
//
//import java.io.File;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebInitParam;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.commons.fileupload.FileItem;
//import org.apache.commons.fileupload.disk.DiskFileItemFactory;
//import org.apache.commons.fileupload.servlet.ServletFileUpload;
//
//import com.qtu404.dataBase.DataBaseManager;
//import com.qtu404.file.FileManager;
//import com.rxk.test.uploadSlide;
//
///**
// * Servlet implementation class FileUploadServlet
// */
//@WebServlet(
//		urlPatterns = {
//				"/FileUploadServlet",
//				"/fileUpload.do"
//		},
//		initParams = {
//				@WebInitParam(name = "FileUploadServlet", value = "")
//		})
//public class FileUploadServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//	// �ϴ��ļ��洢Ŀ¼
//	private static final String UPLOAD_DIRECTORY = "upload";
//
//	// �ϴ�����
//	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3 * 10; // 30MB
//	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40 * 10; // 400MB
//	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50 * 10; // 500MB
//
//	/**
//	 * �ϴ����ݼ������ļ�
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		// ����Ƿ�Ϊ��ý���ϴ�
//		request.setCharacterEncoding("utf-8");
//		String fileName;
//		String filePath = null;
//		if (!ServletFileUpload.isMultipartContent(request)) {
//			// ���������ֹͣ
//			PrintWriter writer = response.getWriter();
//			writer.println("Error: ��������� enctype=multipart/form-data");
//			writer.flush();
//			return;
//		}
//
//		// �����ϴ�����
//		DiskFileItemFactory factory = new DiskFileItemFactory();
//		// �����ڴ��ٽ�ֵ - �����󽫲�����ʱ�ļ����洢����ʱĿ¼��
//		factory.setSizeThreshold(MEMORY_THRESHOLD);
//		// ������ʱ�洢Ŀ¼
//		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
//
//		ServletFileUpload upload = new ServletFileUpload(factory);
//
//		// ��������ļ��ϴ�ֵ
//		upload.setFileSizeMax(MAX_FILE_SIZE);
//
//		// �����������ֵ (�����ļ��ͱ�����)
//		upload.setSizeMax(MAX_REQUEST_SIZE);
//
//		// ���Ĵ���
//		upload.setHeaderEncoding("UTF-8");
//
//		// ������ʱ·�����洢�ϴ����ļ�
//		// ���·����Ե�ǰӦ�õ�Ŀ¼
//		String uploadPath = getServletContext().getRealPath("./") + File.separator + UPLOAD_DIRECTORY;
//
//		// ���Ŀ¼�������򴴽�
//		File uploadDir = new File(uploadPath);
//		if (!uploadDir.exists()) {
//			uploadDir.mkdir();
//		}
//
//		try {
//			// ���������������ȡ�ļ�����
//			@SuppressWarnings("unchecked")
//			List<FileItem> formItems = upload.parseRequest(request);
//
//			if (formItems != null && formItems.size() > 0) {
//				// ����������
//				for (FileItem item : formItems) {
//					// �����ڱ��е��ֶ�
//					if (!item.isFormField()) {
//						fileName = new File(item.getName()).getName();
//						filePath = uploadPath + File.separator + fileName;
//						File storeFile = new File(filePath);
//						// �ڿ���̨����ļ����ϴ�·��
//						System.out.println(filePath);
//						// �����ļ���Ӳ��
//						item.write(storeFile);
//						request.setAttribute("message", "�ļ��ϴ��ɹ�!");
//					}
//				}
//			}
//		} catch (Exception ex) {
//			request.setAttribute("message", "������Ϣ: " + ex.getMessage());
//		}
//
//		//��ǰ�������û���id
//		String userId = (String)request.getSession().getAttribute("usrname");
//
//		//����Ӹ�ͼƬ�Ĵ洢Ŀ¼,�����û�id
//		String destDirName = "/home/tomcat/apache-tomcat-8.5.8/webapps/NF4Slides/"+userId;//tomcat����������·��
//		String path1 =getServletConfig().getServletContext().getRealPath("");
//		System.out.println("getServletConfig():------ "+path1);
//
//
//
//		File dir = new File(destDirName);
//		dir.setWritable(true, false);
//		if (dir.exists()) {// �ж�Ŀ¼�Ƿ����
//			System.out.println("����Ŀ¼ʧ�ܣ�Ŀ��Ŀ¼�Ѵ���");
//		}
//		if (!destDirName.endsWith(File.separator)) {// ��β�Ƿ���"/"����
//			destDirName = destDirName + "/";
//		}
//		if (dir.mkdirs()) {// ����Ŀ��Ŀ¼
//		} else {
//		}
//		/*���ݿ������һ���õ�Ƭ*/
//		//���ҵ����Ļõ�Ƭ��ҳ��
//		Object Slide_Data[][] = DataBaseManager.unionQuery("select slideId,name from slides where userId = '" + userId + "'order by slideId desc", 2);
//		int maxSlideId = 100001;
//		if(Slide_Data==null||Slide_Data.length!=0){
//			maxSlideId = Integer.parseInt((String)Slide_Data[0][0])+1 ;
//		}
//		String SQL_String = "insert into slides values('"+userId+"','"+maxSlideId+"','','�µ���õ�Ƭ')";
//		DataBaseManager.generalUpdate(SQL_String);
//		FileManager.writeInto("", userId+"/"+maxSlideId);
//		FileManager.writeInto("", userId+"/"+maxSlideId+"_play");
//
//		File file = new File(filePath);
//
//		ArrayList<String> list = new ArrayList<String>();
//		File file2 = new File(destDirName);
//		uploadSlide.doPPT2007toImage(file, file2, list,maxSlideId+"");
//		String imgCode=uploadSlide.jpegToImgString(list, userId);
//		String playCode=uploadSlide.jpegToImgPlay(list, userId);
//		System.out.println(playCode);
//		FileManager.writeInto(imgCode, userId+"/"+maxSlideId);
//		FileManager.writeInto(playCode, userId+"/"+maxSlideId+"_play");
//		System.out.println("playCode"+playCode);
//		response.sendRedirect("usercenter.jsp");
//	}
//
//}
