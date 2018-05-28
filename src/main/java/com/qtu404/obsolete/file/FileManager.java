package com.qtu404.obsolete.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FileManager {
	// 验证字符串是否为正确路径名的正则表达式
	private static String matches = "[A-Za-z]:\\\\[^:?\"><*]*";
	// 通过 sPath.matches(matches) 方法的返回值判断是否正确
	// sPath 为路径字符串
	boolean flag = false;
	File file;

	/**
	 * 创建一个文件夹
	 *
	 * @author 丁星
	 * @param userID_int
	 *            用户id
	 * @return String 文件创建情况
	 */
	public static String createDir(String userID_int) {
		String destDirName = "/NF4Slides/" + userID_int;
		File dir = new File(destDirName);
		dir.setWritable(true, false);
		if (dir.exists()) {// 判断目录是否存在
			return "创建目录失败，目标目录已存在";
		}
		if (!destDirName.endsWith(File.separator)) {// 结尾是否以"/"结束
			destDirName = destDirName + "/";
		}
		if (dir.mkdirs()) {// 创建目标目录
			return "创建目录成功！" + destDirName;
		} else {
			return "创建目录失败！" + destDirName;
		}
	}

	/**
	 * 写入文件
	 *
	 * @author 丁星
	 * @param data_String
	 *            内容
	 * @param url_String
	 *            url(userId/SlideId)
	 * @return String 文件创建情况
	 */
	public static boolean writeInto(String data_String, String url_String) {
		try {
			FileWriter fw = new FileWriter("/NF4Slides/" + url_String + ".txt");
			// BufferedWriter bufw = new BufferedWriter(fw);

			File file = new File("/NF4Slides/" + url_String + ".txt");
			BufferedWriter bufw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));

			// 使用缓冲区中的方法将数据写入到缓冲区中。
			bufw.write(data_String);
			bufw.newLine();
			// 使用缓冲区中的方法，将数据刷新到目的地文件中去。
			bufw.flush();
			// 关闭缓冲区,同时关闭了fw流对象
			bufw.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 读出放映内容
	 *
	 * @author 丁星
	 * @param url_String
	 *            userId/SlideId
	 * @return String 放映的全部html代码
	 */
	public static String readFromPlay(String url_String) {
		String result_String = "";
		try {
			// FileReader f1 = null;
			// BufferedReader br = null;
			// f1 = new FileReader("/NF4Slides/" +url_String + "_play.txt");
			// br = new BufferedReader(f1);

			File file = new File("/NF4Slides/" + url_String + "_play.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
			String s;
			s = br.readLine();
			while (s != null) {
				result_String = result_String + s;
				s = br.readLine();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result_String;
	}

	/**
	 * 读出编辑内容
	 *
	 * @author 丁星
	 * @param url_String
	 *            userId/SlideId
	 * @return String 编辑内容内所有小部件代码，以 <--nf4--> 分割每一张幻灯片
	 */
	public static String readFromSlide(String url_String) {
		String result_String = "";
		try {
			// FileReader f1 = null;
			// BufferedReader br = null;
			// f1 = new FileReader("/NF4Slides/" +url_String + ".txt");
			// BufferedReader br=new BufferedReader(f1);
			File file = new File("/NF4Slides/" + url_String + ".txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
			String s;
			s = br.readLine();
			while (s != null) {
				result_String = result_String + s;
				s = br.readLine();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result_String;
	}
}
