package frames;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.CellEditor;
import javax.swing.JLabel;
import javax.xml.crypto.Data;

import base.comp.BaseFrame;
import base.comp.BaseLabel;
import base.comp.message;
import jdbc.DbManager;
import model.model;

public class CalendatTraining extends BaseFrame {

	private Calendar cal;
	private int nowDay;
	private int nowYear;
	private int nowMonth;
	private int year;
	private int month;
	private int day;
	private JLabel down;
	private JLabel up;
	public  BaseLabel date;
	private SearchFrame searchFrame;

	public CalendatTraining(SearchFrame searchFrame) {
		// TODO Auto-generated constructor stub
		this.searchFrame = searchFrame;
		super.BaseFrame("날짜 선택", 500, 500, searchFrame);
	}

	@Override
	public void setComp() {
		// TODO Auto-generated method stub
		cal = Calendar.getInstance();

		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH) + 1;
		day = cal.get(Calendar.DAY_OF_MONTH);

		nowYear = cal.get(Calendar.YEAR);
		nowMonth = cal.get(Calendar.MONTH) + 1;
		nowDay = cal.get(Calendar.DAY_OF_MONTH);

		down = new JLabel("◀");
		up = new JLabel("▶");
		date = new BaseLabel(year + "년" + String.format("%02d", month) + "월");
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

		calenderChange();

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


				date.setText(year + "년" + String.format("%02d", month) + "월");

				refresh();
				calenderChange();
				if (year == nowYear && month <= nowMonth) {
					down.setEnabled(false);
					return;
				}

			}
		});

		down.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);
				cal.add(Calendar.MONTH, -1);

				year = cal.get(Calendar.YEAR);
				month = cal.get(Calendar.MONTH) + 1;


				date.setText(year + "년" + String.format("%02d", month) + "월");

				refresh();
				calenderChange();
				if (year == nowYear && month <= nowMonth) {
					down.setEnabled(false);
					return;
				}

			}
		});

	}

	private void calenderChange() {
		// TODO Auto-generated method stub
		jpTop.jpTop.removeAll();
		jpCenter.removeAll();
		jpCenter.setGrid(6, 7, 0, 0);

		int startWeek = cal.get(Calendar.DAY_OF_WEEK);
		int lastWeek = cal.get(Calendar.DAY_OF_MONTH);
		int maxDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		int nextDate = 42 - lastWeek - startWeek;

		jpTop.jpTop.setFlowCenter().add(down);
		jpTop.jpTop.add(date);
		jpTop.jpTop.add(up);

		for (int i = maxDate - (startWeek - 1); i <= maxDate; i++) {

			jpCenter.add(new BaseLabel(i + "").setCenter().setline().setEnabled());
		}

		for (int i = 1; i <= lastWeek; i++) {
			Vector<Vector<String>> scheduleData = DbManager.db.getData("SELECT * FROM 2023지방_3.schedule where day(date) = ? and month(date) = ? and year(date) = ? and depart = ? and arrival = ?;", i, month, year, model.getDepart, model.getArrival);
			String text = i + "";
			
			if (scheduleData.size() != 0) {
				text = "<html>" + text + "<br>" + "(" + scheduleData.size() + ")";
			}
			
			BaseLabel jlTmp = new BaseLabel(text).setCenter().setline();
			if (year == nowYear && month == nowMonth && nowDay == i) {
				jlTmp.setEnabled();
			}
			
			
			jpCenter.add(jlTmp);
			int ii = i;
			jlTmp.addMouseListener(new MouseAdapter() {
			
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mousePressed(e);
					if (scheduleData.size() == 0) {
						message.error("해당날짜에는 항공스케줄이 없습니다.");
						return;
					}
					jlTmp.setlineBlue();
					
					if (e.getClickCount() == 2) {
						searchFrame.jtDepartDate.setText(year + "-" + String.format("%02d",month) + "-" + String.format("%02d",ii));
						model.DepartDate = month;
						model.DepartDay = ii;
						dispose();
					}
					
				}
			});
			
			
		}
		for (int i = 1; i <= nextDate; i++) {
			jpCenter.add(new BaseLabel(i + "").setEnabled().setCenter().setline());
		}
		super.refresh();
	}

}
