package base.comp;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.border.LineBorder;


public class BaseLabel extends JLabel {

	public BaseLabel(String text) {
		// TODO Auto-generated constructor stub
		super(text);
	}

	public BaseLabel setCenter() {
		// TODO Auto-generated method stub
		super.setHorizontalAlignment(JLabel.CENTER);
		super.setVerticalAlignment(JLabel.CENTER);
		return this;
	}
	public BaseLabel setTitle(int size) {
		// TODO Auto-generated method stub
		super.setFont(new Font("HY헤드라인M", Font.PLAIN, size));
		
		return this;
	}
	public BaseLabel setline() {
		// TODO Auto-generated method stub

		super.setBorder(new LineBorder(Color.black));
		return this;
	}

	public BaseLabel setEnabled() {
		// TODO Auto-generated method stub
		super.setEnabled(false);
		return this;
	}

}
