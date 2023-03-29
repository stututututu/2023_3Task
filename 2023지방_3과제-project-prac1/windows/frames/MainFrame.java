package frames;

import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import base.comp.BaseFrame;
import base.comp.BaseLabel;
import base.comp.ImageLabel;
import base.comp.message;
import jdbc.DbManager;
import model.model;

public class MainFrame extends BaseFrame{

	private ImageLabel jlTop;
	private ImageLabel jlCenter;
	private JTextField jtId;
	private JPasswordField jtPw;
	private JButton jbLogIn;
	private JButton jbContinent;
	private JButton jbSignUp;
	private JButton jbLogOut;
	private JButton jbUpDateInfo;
	private JButton jbTicketSearch;
	private JButton jbMyPage;
	private JButton jbAgeAn;

	public MainFrame() {
		// TODO Auto-generated constructor stub
		super.BaseFrame("메인", 665, 614, null);
	}

	@Override
	public void setComp() {
		// TODO Auto-generated method stub
		jlTop = new ImageLabel("SKY AIRLINE","구름", 665, 152).setTextBottom().setTitle(30);
		jlCenter = new ImageLabel(null, "비행기", 200, 200).setCenter();
		
		jtId = new JTextField(10);
		jtPw = new JPasswordField(10);
		
		jbLogIn = new JButton("로그인");
		jbSignUp= new JButton("회원가입");
		jbContinent = new JButton("대륙별분석");
		
		jbLogOut = new JButton("로그아웃");
		jbUpDateInfo = new JButton("정보수정");
		jbTicketSearch = new JButton("항공권 조회");
		jbMyPage = new JButton("마이페이지");
		jbAgeAn = new JButton("연령별 분석");
		
	}

	@Override
	public void setDesign() {
		// TODO Auto-generated method stub
		jpTop.add(jlTop);
		jpCenter.add(jlCenter);
		
		LogOutState();
	}

	@Override
	public void events() {
		// TODO Auto-generated method stub
		jbLogIn.addActionListener(e -> {
			String id = jtId.getText();
			String pw = jtPw.getText();
			
			Vector<Vector<String>> check = DbManager.db.getData("SELECT * FROM 2023지방_3.user where id = ? and pw = ?;", id, pw);
			
			if (id.isBlank()|| pw.isBlank()) {
				message.error("공백이 있습니다.");
				return;
			}
			if (check.size() == 0) {
				message.error("ID 또는 PW를 확인하세요.");
				jtId.setText("");
				jtPw.setText("");
				jtId.requestFocus();
				
				return;
			}
			model.LogState = check.get(0);
			message.info(model.LogState.get(3)+"님 환영합니다.");
			LogInState();
			jtId.setText("");
			jtPw.setText("");
		});
		jbLogOut.addActionListener(e -> {
			LogOutState();
		});
		jbSignUp.addActionListener(e -> {
			new SignUpFrame(this);
		});
		jbUpDateInfo.addActionListener(e -> {
			new UpDateInfo(this);
		});
		jbTicketSearch.addActionListener(e -> {
			new SearchFrame(this);
		});
		
	}
	
	private void LogInState() {
		// TODO Auto-generated method stub
		jpBottom.removeAll();
		
		jpBottom.addChild();
		
		jpBottom.jpBottom.setFlowCenter().add(jbLogOut);
		jpBottom.jpBottom.setFlowCenter().add(jbUpDateInfo);
		jpBottom.jpBottom.setFlowCenter().add(jbTicketSearch);
		jpBottom.jpBottom.setFlowCenter().add(jbMyPage);
		jpBottom.jpBottom.setFlowCenter().add(jbAgeAn);
		super.refresh();
		
	}

	private void LogOutState() {
		// TODO Auto-generated method stub
		jpBottom.removeAll();
		
		jpBottom.addChild();
		
		jpBottom.jpCenter.addChild();
		jpBottom.jpCenter.jpLeft.setGrid(2, 1, 10, 10).add(new BaseLabel("ID:"));
		jpBottom.jpCenter.jpLeft.add(new BaseLabel("PW:"));
		jpBottom.jpCenter.jpLeft.setEmpty(5, 5, 5, 5);
		
		jpBottom.jpCenter.jpCenter.setGrid(2, 1, 10, 10).add(jtId);
		jpBottom.jpCenter.jpCenter.add(jtPw);
		jpBottom.jpCenter.jpCenter.setEmpty(5, 5, 5, 5);
		
		jpBottom.jpCenter.setEmpty(0,200,0,200);
		
		
		jpBottom.jpBottom.setFlowCenter().add(jbLogIn);
		jpBottom.jpBottom.setFlowCenter().add(jbSignUp);
		jpBottom.jpBottom.setFlowCenter().add(jbContinent);
	}

}
