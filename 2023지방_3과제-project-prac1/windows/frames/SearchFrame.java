package frames;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JTextField;

import base.comp.BaseCombo;
import base.comp.BaseFrame;
import base.comp.BaseLabel;
import base.comp.BasePanel;
import base.comp.ImageLabel;
import base.comp.message;
import jdbc.DbManager;
import model.model;

public class SearchFrame extends BaseFrame{

	private Vector<Vector<String>> nationData;
	private BaseCombo jcDpart;
	private BaseCombo jcArrival;
	public JTextField jtDepartDate;
	private JButton jbOk;
	private ImageLabel jlCalender;
	private SearchFrame SearchFrame;

	public SearchFrame(MainFrame mainFrame) {
		// TODO Auto-generated constructor stub
		super.BaseFrame("항공권 조회", 322, 248, mainFrame);
		SearchFrame = this;
	}

	@Override
	public void setComp() {
		// TODO Auto-generated method stub
		nationData = DbManager.db.getData("SELECT * FROM 2023지방_3.nation;");
		jcDpart = new BaseCombo(nationData);
		jcArrival = new BaseCombo(nationData);
		jtDepartDate = new JTextField();
		
		jlCalender = new ImageLabel().png(null, "달력", 40, 40);
		jbOk = new JButton("확인");
		
	}

	@Override
	public void setDesign() {
		// TODO Auto-generated method stub
		jpCenter.addChild();
		jpCenter.jpLeft.setGrid(3, 1, 10, 10).add(new BaseLabel("출발지"));
		jpCenter.jpLeft.add(new BaseLabel("도착지"));
		jpCenter.jpLeft.add(new BaseLabel("출발날짜"));
		
		jpCenter.jpCenter.setGrid(3, 1, 10, 10).add(jcDpart);
		jpCenter.jpCenter.add(jcArrival);
		jpCenter.jpCenter.add(jtDepartDate);
		
		jpCenter.jpRight.setGrid(3, 1, 10, 10).add(new BasePanel());
		jpCenter.jpRight.add(new BasePanel());
		jpCenter.jpRight.add(jlCalender);
		jpBottom.setFlowCenter().add(jbOk);

		jpCenter.jpRight.setEmpty(5, 5, 5, 5);
		jpCenter.jpCenter.setEmpty(5, 5, 5, 5);
		jpCenter.jpLeft.setEmpty(5, 5, 5, 5);
		
	}

	@Override
	public void events() {
		// TODO Auto-generated method stub
		jlCalender.addMouseListener(new MouseAdapter() {
		
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);
				int depart = jcDpart.getSelectedIndex();
				int arrival = jcArrival.getSelectedIndex();
				if (depart == 0 && arrival == 0) {
					message.error("출발지와 도착지를 선택하세요.");
					return;
				}
				
				model.getDepart = depart;
				model.getArrival = arrival;
				
//				new CalendarFrame(SearchFrame);
				new CalendatTraining(SearchFrame);
			}
		});
		jbOk.addActionListener(e -> {
			
			new ChoseFrame(this);
			
		});
		
	}

}
