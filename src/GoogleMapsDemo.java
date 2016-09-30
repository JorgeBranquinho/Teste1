import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
public class GoogleMapsDemo {
	public static void main(String[] args) throws IOException {
		JFrame test = new JFrame("Google Maps");
		try {
			String latitude = "38.748753";
			String longitude = "-9.153692";
			String zoom = "18";
			String imageUrl = "https://maps.googleapis.com/maps/api/staticmap?center="
					+ latitude
					+ ","
					+ longitude
					+ "&zoom="
					+ zoom
					+"&size=612x612&scale=2&maptype=roadmap";

			String destinationFile = "image.jpg";
			URL url = new URL(imageUrl);
			InputStream is = url.openStream();
			OutputStream os = new FileOutputStream(destinationFile);
			byte[] b = new byte[2048];
			int length;
			while ((length = is.read(b)) != -1) {
				os.write(b, 0, length);
			}
			is.close();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}

		
		ImageIcon imageIcon = new ImageIcon((new ImageIcon("image.jpg"))
				.getImage().getScaledInstance(630, 600,
						java.awt.Image.SCALE_SMOOTH));
		test.add(new JLabel(imageIcon));
		
		test.setVisible(true);
		test.pack();
	}
}