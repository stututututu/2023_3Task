package framse;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JTextField;

import base.comp.BaseCombo;
import base.comp.BaseFrame;
import base.comp.BaseLable;
import base.comp.BasePanel;
import base.comp.ImageLable;
import base.comp.message;
import jdbc.DbManager;
import model.model;

public class SearchFrame extends BaseFrame {

	private Vector<Vector<String>> nation;
	private BaseCombo jcStart;
	private BaseCombo jcArrive;
	private JTextField jtDate;
	private JButton jbCk;
	private ImageLable calender;

	public SearchFrame(MainFrame mainFrame) {
		// TODO Auto-generated constructor stub
		super.BaseFrame("항공권 조회", 358, 291, mainFrame);
	}

	@Override
	public void setComp() {
		// TODO Auto-generated method stub
		nation = DbManager.db.getData("SELECT n_name FROM 2023지방_3.nation;");
		jcStart = new BaseCombo("").dataCombo(nation);
		jcArrive = new BaseCombo("").dataCombo(nation);
		jtDate = new JTextField(10);

		jbCk = new JButton("확인");

		calender = new ImageLable(null, "달력.png", 30, 30);
	}

	@Override
	public void setDesign() {
		// TODO Auto-generated method stub

		jpCenter.addChild();

		jpCenter.jpLeft.setGrid(3, 1, 15, 15).add(new BaseLable("출발지"));
		jpCenter.jpLeft.add(new BaseLable("도착지"));
		jpCenter.jpLeft.add(new BaseLable("출발날짜"));

		jpCenter.jpCenter.setGrid(3, 1, 15, 15).add(jcStart);
		jpCenter.jpCenter.add(jcArrive);
		jpCenter.jpCenter.add(jtDate);

		jpCenter.jpRight.setGrid(3, 1, 15, 15).add(new BasePanel());
		jpCenter.jpRight.add(new BasePanel());
		jpCenter.jpRight.add(calender);

		jpCenter.setEmptyBorder(10, 10, 10, 10);
		jpCenter.jpCenter.setEmptyBorder(10, 10, 10, 10);

		jpBottom.setFlowCenter().add(jbCk);

	}

	@Override
	public void events() {
		// TODO Auto-generated method stub
		calender.addMouseListener(new MouseAdapter() {
		
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				if (jcStart.getSelectedIndex() == 0 || jcArrive.getSelectedIndex() == 0) {
					message.error("출발지와 도착지를 선택하세요.");
					return;
				}
				model.depart = jcStart.getSelectedIndex();
				model.arrival = jcArrive.getSelectedIndex();
			new calenderFrame();
			}
		});
		
		
	}

}
