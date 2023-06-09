package frames;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JLabel;


import base.comp.BaseFrame;
import base.comp.BaseLabel;
import base.comp.message;
import jdbc.DbManager;
import model.model;

public class CalendarFrame extends BaseFrame {

	private Calendar cal;
	private int year;
	private int month;
	private JLabel up;
	private JLabel down;
	private BaseLabel jlDate;
	private int startWeek;
	private int lastWeek;
	private int nextDay;
	private int nowDay;
	private int nowYear;
	private int nowMonth;
	private SearchFrame searchFrame;

	public CalendarFrame(SearchFrame searchFrame) {
		// TODO Auto-generated constructor stub
		super.BaseFrame("날짜선택", 500, 500, searchFrame);
		this.searchFrame = searchFrame;
	}

	@Override
	public void setComp() {
		// TODO Auto-generated method stub
		cal = Calendar.getInstance();

		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH) + 1;

		up = new JLabel("▶");
		down = new JLabel("◀");

		nowYear= cal.get(Calendar.YEAR);
		nowMonth= cal.get(Calendar.MONTH);
		nowDay = cal.get(Calendar.DAY_OF_MONTH);
		
		jlDate = new BaseLabel(year + "년" + String.format("%02d", month) + "월");
	}

	@Override
	public void setDesign() {
		// TODO Auto-generated method stub
		jpTop.addChild();
	

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

				jlDate.setText(year + "년" + String.format("%02d", month) + "월");
				
				if (!(nowYear == year && month <= nowMonth)) {
					down.setEnabled(false);
					return;
					
				}
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
				

				jlDate.setText(year + "년" + String.format("%02d", month) + "월");
				
				if (!(nowYear == year && month <= nowMonth)) {
					down.setEnabled(false);
					return;
					
				}
				calenderRefresh();
				refresh();

			}
		});
	}

	private void calenderRefresh() {
		// TODO Auto-generated method stub
		jpTop.jpTop.removeAll();
		jpCenter.removeAll();
		jpCenter.setGrid(6, 7, 0, 0);
		
		int maxDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		startWeek = cal.get(Calendar.DAY_OF_WEEK);
		lastWeek = cal.get(Calendar.DAY_OF_MONTH);
		nextDay = 42 - lastWeek - startWeek;
		jpTop.jpTop.setFlowCenter().add(down);
		jpTop.jpTop.add(jlDate);
		jpTop.jpTop.add(up);
		
		System.out.println("last" + lastWeek);
		
		for (int i = maxDate - (startWeek - 1); i <= maxDate; i++) {
			jpCenter.add(new BaseLabel(i + "").setCenter().setline().setEnabled());
			super.refresh();
		}
		
		for (int i = 1; i <= lastWeek; i++) {
			String text = i + "";
			int ii = i;
			
			Vector<Vector<String>> scheduleData = DbManager.db.getData("SELECT * FROM 2023지방_3.schedule where day(date) = ? and month(date) = ? and year(date) = ? and depart = ? and arrival = ?;",i,month,year,model.getDepart,model.getArrival);
			
			if (scheduleData.size() != 0) {
				text = "<html>" + text + "<br>" + "(" + scheduleData.size() + ")";
				
			}
			BaseLabel jlTmp = new BaseLabel(text).setCenter().setline();
			jpCenter.add(jlTmp);
			
			jlTmp.addMouseListener(new MouseAdapter() {
			
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mousePressed(e);
					if (scheduleData.size() == 0) {
						
						message.error("해당날짜게는 스케줄이 없습니다.");
						return;
					}
					
					int day = ii;
					String choseData = year + "-" + String.format("%02d",month) + "-" + day;
					
					if (e.getClickCount() == 2) {
						searchFrame.jtDepartDate.setText(choseData);
						dispose();
					}
				}
			});
			
			
			super.refresh();
		}
		for (int i = 1; i <= nextDay; i++) {
			jpCenter.add(new BaseLabel(i + "").setCenter().setline().setEnabled());
			super.refresh();
		}
		
		
	}

}
