package com.qtu404.obsolete.file;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.poi.xslf.usermodel.XSLFTextRun;
import org.apache.poi.xslf.usermodel.XSLFTextShape;

public class UploadSlideUnit {
    public static void main(String[] args) {
        File file = new File("E:\\Workspaces\\tomcat for IDEA\\webapps\\nf4slide\\PPTXFileDateBase\\100001\\_软件设计案例分析_Struts2_3.pptx");
        ArrayList<String> list = new ArrayList<String>();
        File file2 = new File("E:\\Workspaces\\tomcat for IDEA\\webapps\\nf4slide\\PPTXFileDateBase\\100001");
        doPPT2007toImage(file, file2, list, "123");
    }

    /**
     * ppt2007文档的转换 后缀为.pptx
     *
     * @param pptFile PPT文件
     * @param imgFile 图片将要保存的路径目录（不是文件）
     * @param list    存放文件名的 list
     * @return
     */
    public static boolean doPPT2007toImage(File pptFile, File imgFile, List<String> list, String slide) {
        FileInputStream is = null;
        try {
            is = new FileInputStream(pptFile);
            XMLSlideShow xmlSlideShow = new XMLSlideShow(is);
            is.close();
            // 获取大小
            Dimension pgsize = xmlSlideShow.getPageSize();
            // 获取幻灯片
            XSLFSlide[] slides = xmlSlideShow.getSlides();
            for (int i = 0; i < slides.length; i++) {
                // 解决乱码问题
                XSLFShape[] shapes = slides[i].getShapes();
                for (XSLFShape shape : shapes) {
                    if (shape instanceof XSLFTextShape) {
                        XSLFTextShape sh = (XSLFTextShape) shape;
                        List<XSLFTextParagraph> textParagraphs = sh.getTextParagraphs();
                        for (XSLFTextParagraph xslfTextParagraph : textParagraphs) {
                            List<XSLFTextRun> textRuns = xslfTextParagraph.getTextRuns();
                            for (XSLFTextRun xslfTextRun : textRuns) {
                                xslfTextRun.setFontFamily("宋体");
                            }
                        }
                    }
                }
                // 根据幻灯片大小生成图片
                BufferedImage img = new BufferedImage(pgsize.width, pgsize.height, BufferedImage.TYPE_INT_RGB);
                Graphics2D graphics = img.createGraphics();

                graphics.setPaint(Color.white);
                graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));
                // 最核心的代码
                slides[i].draw(graphics);
                // 图片将要存放的路径
                String absolutePath = imgFile.getAbsolutePath() + "/" + slide + "_" + +(i + 1) + ".jpeg";
                File jpegFile = new File(absolutePath);
                // 图片路径存放
                list.add(slide + "_" + (i + 1) + ".jpeg");
                // 如果图片存在，则不再生成
                if (jpegFile.exists()) {
                    continue;
                }
                // 这里设置图片的存放路径和图片的格式(jpeg,png,bmp等等),注意生成文件路径
                FileOutputStream out = new FileOutputStream(jpegFile);
                // 写入到图片中去
                ImageIO.write(img, "jpeg", out);
                out.close();
            }
            return true;

        } catch (Exception e) {
        }
        return false;
    }

    public static String jpegToImgString(ArrayList<String> list, String userId) {
        StringBuffer result_String = new StringBuffer("");
        for (int i = 0; i < list.size(); i++) {
            result_String.append("<div id=\"nf4-image-" + (i + 1) + "\" class=\"nf4-image nf4-module ui-resizable ui-draggable ui-draggable-handle\" style=\"position: absolute; z-index: 51;height:700px;width:960px; left: 0px; top: 0px;\"><img data-selected=\"false\" id=\"imgPre-" + (i + 1) + "\" class=\"nf4-image-editor\" style=\"height: 100%; width: 100%; cursor: move;\" src=\"");
            result_String.append("/NF4Slides/" + userId + "/" + list.get(i) + "\"><div class=\"ui-resizable-handle ui-resizable-nw\" style=\"display: none;\"></div><div class=\"ui-resizable-handle ui-resizable-ne\" style=\"display: none;\"></div><div class=\"ui-resizable-handle ui-resizable-sw\" style=\"display: none;\"></div><div class=\"ui-resizable-handle ui-resizable-se\" style=\"display: none;\"></div><div class=\"ui-resizable-handle ui-resizable-n\" style=\"display: none;\"></div><div class=\"ui-resizable-handle ui-resizable-s\" style=\"display: none;\"></div><div class=\"ui-resizable-handle ui-resizable-e\" style=\"display: none;\"></div><div class=\"ui-resizable-handle ui-resizable-w\" style=\"display: none;\"></div></div>");
            result_String.append("<--nf4-->");
        }
        return result_String.toString();
    }

    public static String jpegToImgPlay(ArrayList<String> list, String userId) {
        String result_String = null;
        StringBuffer playCode = new StringBuffer("");
        for (int i = 0; i < list.size(); i++) {
            playCode.append("<section> <div style=\"height:700px;width:960px\">");
            playCode.append("<div id=\"nf4-image-" + (i + 1) + "\" class=\"nf4-image nf4-module ui-resizable ui-draggable ui-draggable-handle\" style=\"position: absolute; z-index: 51;height:700px;width:960px; left: 0px; top: 0px;\"><img data-selected=\"false\" id=\"imgPre-" + (i + 1) + "\" class=\"nf4-image-editor\" style=\"height: 100%; width: 100%; cursor: move;\" src=\"");
            playCode.append("/NF4Slides/" + userId + "/" + list.get(i) + "\"><div class=\"ui-resizable-handle ui-resizable-nw\" style=\"display: none;\"></div><div class=\"ui-resizable-handle ui-resizable-ne\" style=\"display: none;\"></div><div class=\"ui-resizable-handle ui-resizable-sw\" style=\"display: none;\"></div><div class=\"ui-resizable-handle ui-resizable-se\" style=\"display: none;\"></div><div class=\"ui-resizable-handle ui-resizable-n\" style=\"display: none;\"></div><div class=\"ui-resizable-handle ui-resizable-s\" style=\"display: none;\"></div><div class=\"ui-resizable-handle ui-resizable-e\" style=\"display: none;\"></div><div class=\"ui-resizable-handle ui-resizable-w\" style=\"display: none;\"></div></div>");
            playCode.append("</div></section>");
        }
        result_String = playCode.toString();
        return result_String;
    }
}
