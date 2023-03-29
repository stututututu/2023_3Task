package base.comp;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImageLabel extends JLabel{

	public ImageLabel() {
		// TODO Auto-generated constructor stub
	}
	public ImageLabel(String text, String path, int w, int h) {
		// TODO Auto-generated constructor stub
		super(text);
		ImageIcon icon = new ImageIcon("./datafiles/" + path + ".jpg");
		Image img = icon.getImage();
		
		img = img.getScaledInstance(w, h, Image.SCALE_SMOOTH);
		
		super.setIcon(new ImageIcon(img));
	}
	public ImageLabel png(String text, String path, int w, int h) {
		// TODO Auto-generated constructor stub
		super.setText(text);
		ImageIcon icon = new ImageIcon("./datafiles/" + path + ".png");
		Image img = icon.getImage();
		
		img = img.getScaledInstance(w, h, Image.SCALE_SMOOTH);
		
		super.setIcon(new ImageIcon(img));
		return this;
	}

	public ImageLabel setTextBottom() {
		// TODO Auto-generated method stub
		super.setHorizontalTextPosition(JLabel.CENTER);
		super.setVerticalTextPosition(JLabel.BOTTOM);
		return this;
	}
	public ImageLabel setTitle(int size) {
		// TODO Auto-generated method stub
		super.setFont(new Font("HY헤드라인M", Font.PLAIN, size));
		return this;
	}

	public ImageLabel setCenter() {
		// TODO Auto-generated method stub
		super.setHorizontalAlignment(JLabel.CENTER);
		super.setVerticalAlignment(JLabel.CENTER);
		return this;
	}

}
