package framse;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JLabel;

import base.comp.BaseFrame;
import base.comp.BaseLable;
import base.comp.BasePanel;
import jdbc.DbManager;
import model.model;

public class calenderFrame extends BaseFrame {

	private Calendar cal;
	private int year;
	private int month;
	private JLabel up;
	private JLabel down;
	private BaseLable jl;
	private int startWeek;
	private int lastWeek;
	private BasePanel jp;
	private int nowWeek;
	private Vector<Vector<String>> schedule;
	private int now;

	public calenderFrame() {
		// TODO Auto-generated constructor stub
		super.BaseFrame("날짜선택", 500, 500, new SearchFrame(null));
	}

	@Override
	public void setComp() {
		// TODO Auto-generated method stub
		cal = Calendar.getInstance();

		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH) + 1;

		up = new JLabel("▶");
		down = new JLabel("◀");

		jl = new BaseLable(year + "년" + month + "월");

	}

	@Override
	public void setDesign() {
		// TODO Auto-generated method stub
		jpTop.setFlowCenter().add(down);
		jpTop.add(jl);
		jpTop.add(up);

		jpCenter.addChild();
		jpCenter.jpTop.setGrid(1, 7, 10, 10).add(new BaseLable("일").setCenter());
		jpCenter.jpTop.add(new BaseLable("화").setCenter());
		jpCenter.jpTop.add(new BaseLable("수").setCenter());
		jpCenter.jpTop.add(new BaseLable("목").setCenter());
		jpCenter.jpTop.add(new BaseLable("금").setCenter());
		jpCenter.jpTop.add(new BaseLable("토").setCenter());
		jpCenter.jpTop.add(new BaseLable("월").setCenter());

		Calrefresh();

	}

	@Override
	public void events() {
		// TODO Auto-generated method stub
		up.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);
				cal.add(Calendar.MONTH, 1);

				year = cal.get(Calendar.YEAR);
				month = cal.get(Calendar.MONTH) + 1;
				Calrefresh();
				refresh();

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
				Calrefresh();
				refresh();

			}
		});

	}

	public void Calrefresh() {
		jpTop.removeAll();
		jpCenter.jpCenter.removeAll();
		jpCenter.jpCenter.setGrid(6, 7, 0, 0);
		jl.setText(year + "년" + String.format("%02d", month) + "월");

		startWeek = cal.get(Calendar.DAY_OF_WEEK);
		lastWeek = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		nowWeek = cal.get(Calendar.DAY_OF_MONTH);
		now = new Date().getMonth()+1;
//		
//		int day = lastWeek - startWeek;
//		System.out.println(day);

		for (int i = lastWeek - startWeek; i <= lastWeek; i++) {
			jp = new BasePanel();

			jp.add(new BaseLable(i + "").setEnadble().setCenter());
			jp.setLine();
			jpCenter.jpCenter.add(jp);
			super.refresh();
		}
		if (now >= month) {
			for (int i = 1; i <= nowWeek; i++) {
				jp = new BasePanel();
				
				jp.add(new BaseLable(i + "").setCenter().setEnadble());
				
				
				jp.setLine();
				jpCenter.jpCenter.add(jp);
				super.refresh();
				
			}
			for (int i = nowWeek + 1; i <= lastWeek; i++) {
				
				jp = new BasePanel();
				schedule = DbManager.db.getData(
						"SELECT * FROM 2023지방_3.schedule where month(date) = ? and day(date) = ? and depart = ? and arrival = ?;",
						month, i, model.depart, model.arrival);
				jp.add(new BaseLable(i + "").setCenter());
				if (schedule.size() != 0) {
					jp.addChild();
					jp.jpCenter.add(new BaseLable(i + "").setCenter());
					jp.jpBottom.add(new BaseLable("(" + schedule.size() + ")").setTextBottom());
					
				}
				
				jp.setLine();
				jpCenter.jpCenter.add(jp);
				
				super.refresh();
				
			}
			for (int i = 1; i <= 42 - startWeek - lastWeek + 1; i++) {
				jp = new BasePanel();
				
				jp.add(new BaseLable(i + "").setEnadble().setCenter());
				jp.setLine();
				jpCenter.jpCenter.add(jp);
				super.refresh();
				
			}
			
		}
		for (int i = 1; i <= lastWeek; i++) {
			
			jp = new BasePanel();
			schedule = DbManager.db.getData(
					"SELECT * FROM 2023지방_3.schedule where month(date) = ? and day(date) = ? and depart = ? and arrival = ?;",
					month, i, model.depart, model.arrival);
			jp.add(new BaseLable(i + "").setCenter());
			if (schedule.size() != 0) {
				jp.addChild();
				jp.jpCenter.add(new BaseLable(i + "").setCenter());
				jp.jpBottom.add(new BaseLable("(" + schedule.size() + ")").setTextBottom());
				
			}
			
			jp.setLine();
			jpCenter.jpCenter.add(jp);
			
			super.refresh();
			
		}
		for (int i = 1; i <= 42 - startWeek - lastWeek + 1; i++) {
			jp = new BasePanel();

			jp.add(new BaseLable(i + "").setEnadble().setCenter());
			jp.setLine();
			jpCenter.jpCenter.add(jp);
			super.refresh();

		}

		jpTop.setFlowCenter().add(down);
		jpTop.add(jl);
		jpTop.add(up);

		super.refresh();
	}

}
