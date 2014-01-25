package org.opencv.samples.tutorial1;

/**
 * Created by MarkLai on 1/24/14.
 */

import android.util.Log;
import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.opencv.video.Video;

import java.util.ArrayList;
import java.util.List;


public class Algorithm {

    public double xVariance;
    public double yVariance;

    public Algorithm()
    {
        clear();
    }

    public void clear() {
        xVariance = 0;
        yVariance = 0;
    }

    public boolean getAnalyseResult(int interval) {
        if (xVariance / interval> 3000) {

            return true;
        }

        return false;
    }

    public Mat detection(Mat currentFrame, Mat lastFrame)
    {
        if (lastFrame == null) {
            return currentFrame;
        }

        //Mat video;
//        MatOfPoint myPoint = new MatOfPoint();
//        int iGFFTMax = 3;
        int testSize = 20;
        Mat gray = new Mat();
        Mat preGray = new Mat();

        List<Point> cornersList= new ArrayList<Point>();
        for(int i = 0; i< testSize ; i++)
        {
            for(int j = 0; j < testSize ; j++)
            {
//                Point test = new Point(i*currentFrame.size().width/testSize, j*currentFrame.size().height/testSize);
                Point test = new Point(i*currentFrame.size().width /(testSize * 2), currentFrame.size().height/4+j*currentFrame.size().height/(2*testSize));
                cornersList.add(test);
            }
        }
        MatOfPoint prevPts = new MatOfPoint();
        MatOfPoint nextPts = new MatOfPoint();
        MatOfByte status = new MatOfByte();
        MatOfFloat err = new MatOfFloat();
        Size winSize = new Size(15,15);
        int maxLevel = 0;
        Imgproc.cvtColor(currentFrame, gray, Imgproc.COLOR_RGB2GRAY);
        Imgproc.cvtColor(lastFrame, preGray, Imgproc.COLOR_RGB2GRAY);
         prevPts.fromList(cornersList);
        //Imgproc.goodFeaturesToTrack(preGray, prevPts, 40, 0.1 ,3);
        //
        //Imgproc.goodFeaturesToTrack(gray, nextPts, 40, 0.1 ,3);



        MatOfPoint2f  prevPtsNew = new MatOfPoint2f( prevPts.toArray() );
        MatOfPoint2f  nextPtsNew = new MatOfPoint2f( nextPts.toArray() );

        Video.calcOpticalFlowPyrLK(preGray, gray, prevPtsNew, nextPtsNew, status, err, winSize, maxLevel);
//        Video.calcOpticalFlowPyrLK(lastFrame, currentFrame, prevPtsNew, nextPtsNew, status, err, winSize, maxLevel);
        //Video.calcOpticalFlowFarneback(preGray, gray, flow, 0.5, 1, 15 , 1, 5, 1.2, 0);

       // outputFrame = drawOptFlowMap(flow, currentFrame, 16, 1.5);
          // Core.line(currentFrame, new Point(0,0), new Point(100,50), new Scalar(0,255,0), 3);
        Mat outputFrame = drawLine(currentFrame, prevPtsNew, nextPtsNew, status);
        //outputFrame = currentFrame - lastFrame;
        //System.out.print(outputFrame.toString());
//        Imgproc.goodFeaturesToTrack(currentFrame, myPoint,iGFFTMax, 0.01, 20 );
//
//        Video.calcOpticalFlowPyrLK(lastFrame, currentFrame, );
//        Video.calcOpticalFlowPyrLK(lastFrame, currentFrame, mMOP2fptsPrev, mMOP2fptsThis, mMOBStatus, mMOFerr);
         return outputFrame;

    }

    Mat drawLine(Mat result, MatOfPoint2f corners, MatOfPoint2f points, MatOfByte status)
    {
        Mat tmp = result.clone();
        List<Point> cornersList  = corners.toList();
        List<Point> pointsList = points.toList();
        List<Byte> statusList = status.toList();
        int change = 0;

        for (int i = 0; i < pointsList.size(); i++)
        {
            if (statusList.get(i) == 1)
            {
                Point p1 = new Point();
                Point p2 = new Point();
                p1.x = (int) pointsList.get(i).x;
                p1.y = (int) pointsList.get(i).y;
                p2.x = (int) cornersList.get(i).x;
                p2.y = (int) cornersList.get(i).y;
                change = Math.abs((int)(p1.x - p2.x))/5;
                Log.i("ZZZZ", change + "");
                if (change < 3)
                    change = 0;
                if (change > 10)
                    change = 10;
                Core.line(tmp, p2, p2, new Scalar(255, 0, 0, 255), 3+change);
                xVariance += Math.abs(p1.x - p2.x);
                yVariance += Math.abs(p1.y - p2.y);
            }

        }
        return tmp;
    }
//    Mat drawOptFlowMap(Mat pre, Mat current, int step, double n)
//    {
//        Mat tmp = null;
//        Scalar color = new Scalar(0, 0, 255);
//        for(int y = 0; y < current.rows(); y += step)
//            for(int x = 0; x < current.cols(); x += step)
//            {
//
//               // MatOfPoint2f fxy = pre.;
////                const Point2f& fxy = pre.at<Point2f>(y, x);
//                Core.line(current, new Point (x,y), new Point (x+y, y+x),color);
//               // line(current, Point(x,y), Point(cvRound(x+fxy.x), cvRound(y+fxy.y)),
//               //         color);
//                Core.circle(current,new Point (x,y),2,color, -1 );
//                //circle(current, Point(x,y), 2, color, -1);
//            }
//        tmp = current;
//
//        return tmp;
//    }
}
