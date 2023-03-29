package base.comp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import design.IDesign;

public abstract class BaseFrame extends JFrame implements IDesign {

	public BasePanel jpCenter = new BasePanel();
	public BasePanel jpTop = new BasePanel();
	public BasePanel jpBottom = new BasePanel();
	public BasePanel jpLeft = new BasePanel();
	public BasePanel jpRight = new BasePanel();

	private BaseFrame preFrame;

	public void BaseFrame(String title, int w, int d, BaseFrame preFrame) {
		// TODO Auto-generated constructor stub
		this.preFrame = preFrame;

		setComp();
		setDesign();
		events();

		super.addComponentListener(new ComponentAdapter() {

			@Override
			public void componentResized(ComponentEvent e) {
				// TODO Auto-generated method stub
				super.componentResized(e);
				System.out.println(getSize());
			}
		});

		super.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);
				close();
			}
		});
		
		super.setIconImage(new ImageIcon("./datafiles/Ferrari F-40 [2560 x 1440].png").getImage());
		
		super.add(jpCenter, BorderLayout.CENTER);
		super.add(jpTop, BorderLayout.NORTH);
		super.add(jpBottom, BorderLayout.SOUTH);
		super.add(jpLeft, BorderLayout.WEST);
		super.add(jpRight, BorderLayout.EAST);
		
		

		super.setTitle(title);
		super.setSize(w, d);
		super.setLocationRelativeTo(null);
		super.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		super.setVisible(true);

	}

	private void close() {
		// TODO Auto-generated method stub
		if (preFrame == null) {
			System.exit(0);
		}
		this.dispose();
		preFrame.setVisible(true);
		preFrame.setState(0);
	}

	public void refresh() {
		// TODO Auto-generated method stub
		super.validate();
		super.repaint();
		
	}
}
