package base.comp;

import java.awt.Component;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class ImageLable extends JLabel {

	public ImageLable(String title, String path, int w, int d) {
		// TODO Auto-generated constructor stub
		ImageIcon icon = new ImageIcon("./datafiles/" + path);
		Image img = icon.getImage();
		
		img = img.getScaledInstance(w, d, Image.SCALE_SMOOTH);
		
		super.setIcon(new ImageIcon(img));
		
	}

	public Component setCenter() {
		// TODO Auto-generated method stub
		
		super.setHorizontalAlignment(JLabel.CENTER);
		super.setVerticalAlignment(JLabel.CENTER);
		return this;
	}

}
