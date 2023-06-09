package framse;

import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import base.comp.BaseFrame;
import base.comp.BaseLable;
import base.comp.ImageLable;
import base.comp.message;
import jdbc.DbManager;
import model.model;

public class MainFrame extends BaseFrame {

	private JTextField jtId;
	private JPasswordField jtPw;
	private JButton jbAnalysis;
	private JButton jbSignUp;
	private JButton jbLogin;
	private JButton jbMyPage;
	private JButton jbSearch;
	private JButton jbInfo;
	private JButton jbLogout;
	private JButton jbAnAge;

	public MainFrame() {
		// TODO Auto-generated constructor stub
		super.BaseFrame("메인", 666, 529, null);

	}

	@Override
	public void setComp() {
		// TODO Auto-generated method stub
		jtId = new JTextField(10);
		jtPw = new JPasswordField(10);

		jbLogin = new JButton("로그인");
		jbSignUp = new JButton("회원가입");
		jbAnalysis = new JButton("대륙별분석");
		
		//LogIN
		jbLogout = new JButton("로그아웃");
		jbInfo = new JButton("정보수정");
		jbSearch = new JButton("항공권 조회");
		jbMyPage = new JButton("마이페이지");
		jbAnAge = new JButton("연령별분석");
		

	}

	@Override
	public void setDesign() {
		// TODO Auto-generated method stub
		jpTop.addChild();
		jpTop.jpCenter.add(new ImageLable(null, "구름.jpg", 666, 200));
		jpTop.jpBottom.add(new BaseLable("SKY AIRLINE", 30).setCenter());
		jpCenter.add(new ImageLable(null, "비행기.jpg", 150, 150).setCenter());

		LogoutState();
	
	}

	@Override
	public void events() {
		// TODO Auto-generated method stub
		jbLogin.addActionListener(e -> {
			String id = jtId.getText();
			String pw = jtPw.getText();
			Vector<Vector<String>> check = DbManager.db.getData("SELECT * FROM 2023지방_3.user where id = ? and pw = ?;", id, pw);
			
			if (id.isBlank() || pw.isBlank()) {
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
			message.info(model.LogState.get(3)+"님 환영합니다");
			LogInState();
			
		});
		jbSignUp.addActionListener(e -> {
			new SignUpFrame(this);
		});
		jbInfo.addActionListener(e -> {
			new EditInfoFrame(this);
		});
		jbLogout.addActionListener(e -> {
			LogoutState();
		});
		jbSearch.addActionListener(e -> {
			new SearchFrame(this);
		});
	}

	private void LogoutState() {
		// TODO Auto-generated method stub
		jpBottom.removeAll();
		
		jpBottom.addChild();
		jpBottom.jpCenter.addChild();
		jpBottom.jpCenter.setEmptyBorder(5, 200, 5, 200);
		 
		jpBottom.jpCenter.jpLeft.setGrid(2, 1, 15, 15).add(new BaseLable("ID:"));
		jpBottom.jpCenter.jpLeft.add(new BaseLable("PW:"));
		jpBottom.jpCenter.jpLeft.setEmptyBorder(10, 10, 10, 10);

		jpBottom.jpCenter.jpCenter.setGrid(2, 1, 15, 15).add(jtId);
		jpBottom.jpCenter.jpCenter.add(jtPw);

		jpBottom.jpBottom.setFlowCenter().add(jbLogin);
		jpBottom.jpBottom.add(jbSignUp);
		jpBottom.jpBottom.add(jbAnalysis);
		super.refresh();
		
	}

	private void LogInState() {
		// TODO Auto-generated method stub
		jpBottom.removeAll();
		jpBottom.setFlowCenter().add(jbLogout);
		jpBottom.add(jbInfo);
		jpBottom.add(jbSearch);
		jpBottom.add(jbMyPage);
		jpBottom.add(jbAnAge);
		
		super.refresh();
	}

}
