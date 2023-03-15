package base.comp;

import javax.swing.JOptionPane;

public class message {

	public message() {
		// TODO Auto-generated constructor stub
	}

	public static void error(String text) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, text, "경고", JOptionPane.ERROR_MESSAGE);
	}
	public static void info(String text) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, text, "정보", JOptionPane.INFORMATION_MESSAGE);
	}

}
