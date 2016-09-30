import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;

public class MatSub {
	public static void main(String[] args) {
		System.out.println("Hello, OpenCV");

		// Load the native library.
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		new ImageSubtraction().run();
	}
}

class ImageSubtraction {
	private String path="C:\\Users\\Asus\\Downloads\\crowd_tracking_ICCV_2009\\sample_video\\frame0";
	private String path2="C:\\Users\\Asus\\Downloads\\crowd_tracking_ICCV_2009\\sample_video\\result0";
	private int num = 1;
	private int max = 2;//100;
	private String extension=".jpg";
	public void run() {
		Mat image = Highgui.imread(path + String.format("%03d", num-1) + extension);
		//Mat endResult = Mat.ones(image.size(),CvType.CV_32F);
		Mat endResult = Mat.zeros(image.size(),CvType.CV_32F);
		for(num=1;num<=max;num++){
			Mat image2 = Highgui.imread(path + String.format("%03d", num) + extension);
			//Core.subtract(image,image2,endResult);
			Core.absdiff(image2, image, endResult);
			System.out.println(String.format("Writing %s", path2 + String.format("%03d", num-1) + extension));
			Highgui.imwrite(path2 + String.format("%03d", num-1) + extension, endResult);
			image=image2;
		}
	}
}
