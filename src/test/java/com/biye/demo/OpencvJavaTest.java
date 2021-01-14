package com.biye.demo;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;

import  static org.opencv.core.CvType.CV_8UC1;
import  static org.opencv.core.CvType.CV_8UC3;
/**
 * Created by better on 2014/10/4.
 */
public class OpencvJavaTest {

    static {
       System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static void main(String[] args) {
//       Mat mat=Mat.eye(3,3,CV_8UC1);
//       System.out.println("mat = ");
//       System.out.println(mat.dump());
//
//       Mat mat2=Mat.zeros(3,3,CV_8UC1);
//       System.out.println("mat2 = ");
//       System.out.println(mat2.dump());
//
//       Mat mat3=Mat.ones(3,3,CV_8UC1);
//       System.out.println("mat3 = ");
//       System.out.println(mat3.dump());
//
//       Mat mat4=Mat.zeros(1,1,CV_8UC3);
//       System.out.println("mat4 = ");
//       System.out.println(mat4.dump());
        Mat mat= Imgcodecs.imread("src/test/java/image/ptactice.jpg");
        Imgcodecs.imwrite("src/test/java/target/output.png",mat);
//        System.out.println("mat="+mat.width()+" x "+mat.height()+" ,"+mat.type());




    }
}