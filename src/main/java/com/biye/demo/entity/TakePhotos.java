package com.biye.demo.entity;

import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_imgcodecs;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.video.Video;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.util.Date;

public class TakePhotos {
//        static OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();
        static  int ex = 1;
    public static void photos()
    {
        OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();
//        System.out.println(0);
        OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
        try{
            grabber.start();   //开始获取摄像头数据
        }catch(Exception e){
            e.printStackTrace();
        }

        CanvasFrame canvas = new CanvasFrame("摄像头");//新建一个窗口
        canvas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        canvas.setAlwaysOnTop(true);
        canvas.setVisible(true);

        while(true)
        {
            if(!canvas.isDisplayable())
            {//窗口是否关闭
                try{
                    grabber.stop();//停止抓取
                }catch(Exception e){
                    e.printStackTrace();
                }
//                System.exit(2);//退出
                canvas.dispose();
                return;
            }
            try{
                canvas.showImage(grabber.grab());//获取摄像头图像并放到窗口上显示， 这里的Frame frame=grabber.grab(); frame是一帧视频图像
            }catch (Exception e){
                e.printStackTrace();
            }
            try{
                opencv_core.Mat mat = converter.convertToMat(grabber.grabFrame());
//                File d=new File("D:\\bysheji\\src\\main\\resources\\img");
////                System.out.println(1);
//                int x=d.listFiles().length;

                opencv_imgcodecs.imwrite("D:\\bysheji\\src\\main\\resources\\img\\plate" + ex + ".png", mat);
                ex++;
            }catch (Exception e){
                e.printStackTrace();
            }

            try{
                Thread.sleep(200);//200毫秒刷新一次图像
            }catch(Exception e){
                e.printStackTrace();
            }

        }
    }
//    public static void main(String[] args) {
//        try{
//            photos();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//    public static void do_still_captures(int frames, int lapse, int camera_id) {
//        VideoCapture camera = new VideoCapture(camera_id);
//        camera.set(Videoio.CV_CAP_PROP_FRAME_WIDTH, 320);
//        camera.set(Videoio.CV_CAP_PROP_FRAME_HEIGHT, 240);
//        Mat frame = new Mat();
//        for (int i = 0; i < frames; i++) {
//            if (camera.read(frame)) {
//                String filename = "E:\\bysheji\\src\\main\\resources\\img" + new Date() + ".jpg";
//                Imgcodecs.imwrite(filename, frame);
//                try {
//                    Thread.sleep(lapse * 1000);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    public static BufferedImage MatToBufferedImage(Mat frame) {
//        int type = 0;
//        if (frame == null)
//            return null;
//        if (frame.channels() == 1) {
//            type = BufferedImage.TYPE_BYTE_GRAY;
//        } else if (frame.channels() == 3) {
//            type = BufferedImage.TYPE_3BYTE_BGR;
//        }
//        BufferedImage image = new BufferedImage(frame.width(), frame.height(), type);
//        WritableRaster raster = image.getRaster();
//        DataBufferByte dataBuffer = (DataBufferByte) raster.getDataBuffer();
//        byte[] data = dataBuffer.getData();
//        frame.get(0, 0, data);
//        return image;
//    }
//
//    class MatPanel extends JPanel {
//        public Mat mat;
//
//        public void paint(Graphics g) {
//            g.drawImage(TakePhotos.MatToBufferedImage(mat), 0, 0, this);
//        }
//    }
//
//    public  void photos() {
//        MatPanel t = new MatPanel();
//        JFrame frame0 = new JFrame();
//        frame0.getContentPane().add(t);
//        frame0.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        frame0.setSize(320, 240);
//        frame0.setVisible(true);
//        frame0.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        VideoCapture camera = new VideoCapture(0);
//
//        camera.set(Videoio.CV_CAP_PROP_FRAME_WIDTH, 320);
//        camera.set(Videoio.CV_CAP_PROP_FRAME_HEIGHT, 240);
//        Mat frame = new Mat();
//        while (true) {
//            if (camera.read(frame)) {
//                String filename = "E:\\bysheji\\src\\main\\resources\\img\\img" + new Date() + ".jpg";
//                Imgcodecs.imwrite(filename, frame);
//                try {
//                    Thread.sleep(1000);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//    public static void main(String[] args) throws Exception {
//        TakePhotos t=new TakePhotos();
//        t.photos();
//    }
//    public void testCamera() throws FrameGrabber.Exception, InterruptedException {
//        OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);//0表示本机摄像头 当然这里也可以换成网络摄像头地址 
//         grabber.start(); //开始获取摄像头数据
//        CanvasFrame canvas = new CanvasFrame("摄像头");//新建一个窗口
//        canvas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//窗口关闭时程序运行结束
//         canvas.setAlwaysOnTop(true);
//         while(true){
//            if(!canvas.isDisplayable()) {//窗口是否关闭
//                System.out.println("已关闭");
//                grabber.stop();//停止抓取
//                System.exit(2);//退出
//                }
//            Thread.sleep(50);//50毫秒刷新一次图像
//         }
//    }
//        public static void main(String[] args) throws Exception {
//        TakePhotos t=new TakePhotos();
//        t.testCamera();
//    }
}
