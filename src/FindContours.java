import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;
import java.util.*;

public class FindContours {
	public static void main(String[] args) {
		System.out.println("Hello, OpenCV");

		// Load the native library.
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		new ImageContours().run();
	}
}

class ImageContours{
	private String path="C:\\Users\\Asus\\Downloads\\crowd_tracking_ICCV_2009\\sample_video\\frame0";
	private String path2="C:\\Users\\Asus\\Downloads\\crowd_tracking_ICCV_2009\\sample_video\\result1";
	private int num = 0;
	private String extension=".jpg";
	public void run() {

		//System.loadLibrary("opencv_java245");

		// Consider the image for processing
		Mat image = Highgui.imread(path + String.format("%03d", num) + extension, Imgproc.COLOR_BGR2GRAY);
		Mat imageHSV = new Mat(image.size(), Core.DEPTH_MASK_8U);
		Mat imageBlurr = new Mat(image.size(), Core.DEPTH_MASK_8U);
		Mat imageA = new Mat(image.size(), Core.DEPTH_MASK_ALL);
		Imgproc.cvtColor(image, imageHSV, Imgproc.COLOR_BGR2GRAY);
		Imgproc.GaussianBlur(imageHSV, imageBlurr, new Size(5,5), 0);
		Imgproc.adaptiveThreshold(imageBlurr, imageA, 255,Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY,7, 5);

		Highgui.imwrite(path2 + String.format("%03d", num) + extension,imageBlurr);

		List<MatOfPoint> contours = new ArrayList<MatOfPoint>();    
		Imgproc.findContours(imageA, contours, new Mat(), Imgproc.RETR_LIST,Imgproc.CHAIN_APPROX_SIMPLE);
		//Imgproc.drawContours(imageBlurr, contours, 1, new Scalar(0,0,255));
		for(int i=0; i< contours.size();i++){
			System.out.println(Imgproc.contourArea(contours.get(i)));
			if (Imgproc.contourArea(contours.get(i)) > 50 ){
				Rect rect = Imgproc.boundingRect(contours.get(i));
				System.out.println(rect.height);
				if (rect.height > 28){
					//System.out.println(rect.x +","+rect.y+","+rect.height+","+rect.width);
					Core.rectangle(image, new Point(rect.x,rect.height), new Point(rect.y,rect.width),new Scalar(0,0,255));
				}
			}
		}
		Highgui.imwrite(path2 + String.format("%03d", num+1) + extension,image);
	}
}
