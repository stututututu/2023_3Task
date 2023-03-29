package frames;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.Iterator;

import javax.swing.JLabel;

import base.comp.BaseFrame;
import base.comp.BaseLabel;

public class CalendarFrame extends BaseFrame {

	private Calendar cal;
	private int year;
	private int month;
	private JLabel up;
	private JLabel down;
	private BaseLabel jlDate;
	private int startWeek;
	private int lastWeek;

	public CalendarFrame(SearchFrame searchFrame) {
		// TODO Auto-generated constructor stub
		super.BaseFrame("날짜선택", 500, 500, searchFrame);
	}

	@Override
	public void setComp() {
		// TODO Auto-generated method stub
		cal = Calendar.getInstance();

		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH) + 1;

		up = new JLabel("▶");
		down = new JLabel("▶");

		jlDate = new BaseLabel(year + "년" + month + "월");
	}

	@Override
	public void setDesign() {
		// TODO Auto-generated method stub
		jpTop.addChild();
		jpTop.jpTop.setFlowCenter().add(jlDate);
		jpTop.jpBottom.setGrid(1, 7, 10, 10).add(new BaseLabel("일").setCenter());
		jpTop.jpBottom.add(new BaseLabel("월").setCenter());
		jpTop.jpBottom.add(new BaseLabel("화").setCenter());
		jpTop.jpBottom.add(new BaseLabel("수").setCenter());
		jpTop.jpBottom.add(new BaseLabel("목").setCenter());
		jpTop.jpBottom.add(new BaseLabel("금").setCenter());
		jpTop.jpBottom.add(new BaseLabel("토").setCenter());
		
		calenderRefresh();
	}


	@Override
	public void events() {
		// TODO Auto-generated method stub
		up.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);
				cal.add(Calendar.MONTH, 1);
				
				year = cal.get(Calendar.YEAR);
				month = cal.get(Calendar.MONTH) + 1;
				
				calenderRefresh();
				refresh();
			}
		});
		down.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);
				cal.add(Calendar.MONDAY, -1);

				year = cal.get(Calendar.YEAR);
				month = cal.get(Calendar.MONTH) + 1;
				
				calenderRefresh();
				refresh();
				
				
			}
		});
	}
	private void calenderRefresh() {
		// TODO Auto-generated method stub
		jpTop.removeAll();
		jpCenter.jpCenter.removeAll();
		jpCenter.jpCenter.setGrid(6, 7, 10, 10);
		jlDate.setText(year + "년" + String.format("%02d", month) + "월");
		startWeek = cal.get(Calendar.DAY_OF_WEEK);
		lastWeek = cal.get(Calendar.DAY_OF_WEEK);
		
		for (int i = lastWeek - (startWeek - 1); i <= lastWeek; i++) {
			jpCenter.jpCenter.add(new BaseLabel(i + "").setCenter().setline().setEnabled());
			super.refresh();
		}
		
		
	}

}
