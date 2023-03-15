package base.comp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class BasePanel extends JPanel{
	public BasePanel jpCenter;
	public BasePanel jpTop;
	public BasePanel jpBottom;
	public BasePanel jpLeft;
	public BasePanel jpRight;
	
	public BasePanel() {
		// TODO Auto-generated constructor stub
		super.setLayout(new BorderLayout());
		
		super.setBackground(Color.white);
	}
	public BasePanel setGird(int w, int d, int hg, int vg) {
		// TODO Auto-generated constructor stub
		super.setLayout(new GridLayout(w,d,hg,vg));
		return this;
		
		
	}
	public BasePanel setFlowCenter() {
		// TODO Auto-generated constructor stub
		super.setLayout(new FlowLayout(FlowLayout.CENTER));
		return this;
	}
	public BasePanel setFlowLeft() {
		// TODO Auto-generated constructor stub
		super.setLayout(new FlowLayout(FlowLayout.LEFT));
		return this;
	}
	public BasePanel setFlowRight() {
		// TODO Auto-generated constructor stub
		super.setLayout(new FlowLayout(FlowLayout.RIGHT));
		return this;
	}

	public BasePanel addChild() {
		jpCenter = new BasePanel();
		jpTop = new BasePanel();
		jpBottom = new BasePanel();
		jpLeft = new BasePanel();
		jpRight = new BasePanel();
		
		super.add(jpCenter, BorderLayout.CENTER);
		super.add(jpTop, BorderLayout.NORTH);
		super.add(jpBottom, BorderLayout.SOUTH);
		super.add(jpLeft, BorderLayout.WEST);
		super.add(jpRight, BorderLayout.EAST);
		
		return this;
		
	}
	public BasePanel setEmptyBorder(int t, int l, int b, int r) {
		super.setBorder(new EmptyBorder(t, l, b, r));
		return this;
	}
	
	
	
	
}
