import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.opencv.objdetect.CascadeClassifier;

public class Hello
{
	public static void main(String[] args) {
	    System.out.println("Hello, OpenCV");

	    // Load the native library.
	    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	    new DetectFaceDemo().run();
	  }
}

class DetectFaceDemo {
	  public void run() {
	    System.out.println("\nRunning DetectFaceDemo");

	    // Create a face detector from the cascade file in the resources
	    // directory.
	    //CascadeClassifier faceDetector = new CascadeClassifier("C:/Users/Asus/Downloads/opencv/build/share/OpenCV/lbpcascades/lbpcascade_frontalcatface.xml");
	    //CascadeClassifier faceDetector = new CascadeClassifier("C:/Users/Asus/Downloads/opencv/build/share/OpenCV/lbpcascades/lbpcascade_frontalface.xml");
	    //CascadeClassifier faceDetector = new CascadeClassifier("C:/Users/Asus/Downloads/opencv/build/share/OpenCV/lbpcascades/lbpcascade_profileface.xml");
	    //CascadeClassifier faceDetector = new CascadeClassifier("C:/Users/Asus/Downloads/opencv/build/share/OpenCV/lbpcascades/lbpcascade_silverware.xml");
//	    CascadeClassifier faceDetector = new CascadeClassifier("C:/Users/Asus/Downloads/opencv/build/share/OpenCV/lbpcascades/haarcascade_frontalface_alt.xml");
	    CascadeClassifier faceDetector = new CascadeClassifier("C:/Users/Asus/Downloads/opencv/sources/data/haarcascades/haarcascade_upperbody.xml");
	    Mat image = Highgui.imread("C:/Users/Asus/Pictures/grupo_teste4.jpg");

	    // Detect faces in the image.
	    // MatOfRect is a special container class for Rect.
	    MatOfRect faceDetections = new MatOfRect();
	    faceDetector.detectMultiScale(image, faceDetections);

	    System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));

	    // Draw a bounding box around each face.
	    for (Rect rect : faceDetections.toArray()) {
	        Core.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
	    }

	    // Save the visualized detection.
	    String filename = "C:/Users/Asus/Pictures/faceDetection.png";
	    System.out.println(String.format("Writing %s", filename));
	    Highgui.imwrite(filename, image);
	  }
	}
