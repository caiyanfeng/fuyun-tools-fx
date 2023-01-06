package com.fuyun.utils;

import com.madgag.gif.fmsware.GifDecoder;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import sun.awt.image.GifImageDecoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

/**
 * @describe: 描述
 * @Author: Cai_YF
 * @Date: 2022/12/23 14:20
 * @Version: v1.0
 */
public class ImageUtil {

    public static final String base = "@#&$%*o!;.";// 字符串由复杂到简单

    public static final char[] ss = " `.^,:~\"<!ct+{i7?u30pw4A8DX%#HWM".toCharArray();

    public static void main(String[] args) throws Exception {
        File file = new File("C:\\Users\\lp\\Pictures\\Saved Pictures\\znq.jpeg");
        createAsciiPic(ImageIO.read(file));
        // BufferedImage bri = new BufferedImage();
        // GifImageDecoder decoder = new GifImageDecoder();
        // GifDecoder gifDecoder = new GifDecoder();
        // gifDecoder.read(""); // 读取GIF文件
        // int n = gifDecoder.getFrameCount(); // 得到frame数
        // String[] subPic = new String[n];
        // for (int i = 0; i < n; i++) {
        //     BufferedImage frame = gifDecoder.getFrame(i);  //得到帧
        //     //int delay = decoder.getDelay(i); //得到延迟时间
        //     //生成小的JPG文件
        //     subPic[i] ="d:"+ String.valueOf(i)+ ".jpg";
        //     //高度和宽度
        //     int width=frame.getWidth();
        //     int height=frame.getHeight();
        //     //绘制的图
        //     BufferedImage tag = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        //     tag.getGraphics().drawImage(frame,0,0,width,height,null);
        //     //输出
        //     FileOutputStream out = new FileOutputStream(subPic[i]);
        //     // ImageIO.write(tag, "jpeg", out);
        //     JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        //     encoder.encode(tag); //存盘
        //     out.flush();
        //     out.close();
        // }
    }

    // 图片转字符画
    public static void createAsciiPic(BufferedImage image){
        for (int y = 0; y < image.getHeight(); y+=6) {
            for (int x = 0; x < image.getWidth(); x+=4) {
                final int pixel = image.getRGB(x,y);
                final int r = (pixel & 0xff0000) >> 16, g = (pixel & 0xff00) >> 8, b = pixel & 0xff;
                final float gray = 0.299f * r + 0.578f * g + 0.114f * b;
                final int index = Math.round(gray * (base.length() + 1) / 255);
                System.out.print(index >= base.length()?" ": String.valueOf(base.charAt(index)));
                // 第二种算法
                // final int r = pixel >> 16 & 0xff, g = pixel >> 8 & 0xff, b = pixel & 0xff;
                // int Gray = (r * 28 + g * 151 + b * 77) >> 8; //通过三原色值计算像素点的灰度
                // int index = Gray * ss.length / 255 % ss.length;
                // System.out.print(ss[index]);
            }
            System.out.println();
        }
    }
}
