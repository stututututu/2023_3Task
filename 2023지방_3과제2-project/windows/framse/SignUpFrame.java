package framse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JTextField;


import base.comp.BaseFrame;
import base.comp.BaseLable;
import base.comp.BasePanel;
import base.comp.message;
import jdbc.DbManager;

public class SignUpFrame extends BaseFrame{

	private JTextField jtId;
	private JTextField jtPw;
	private JTextField jtKName;
	private JTextField jtEName;
	private JTextField jtBirth;
	private JButton jbCheck;
	private JButton jbSignUp;
	private Vector<Vector<String>> check;
	private int ckState;

	public SignUpFrame(MainFrame mainFrame) {
		// TODO Auto-generated constructor stub
		super.BaseFrame("회원가입", 415, 326, mainFrame);
	}

	@Override
	public void setComp() {
		// TODO Auto-generated method stub
		jtId = new JTextField(10);
		jtPw = new JTextField(10);
		jtKName = new JTextField(10);
		jtEName = new JTextField(10);
		jtBirth = new JTextField(10);
		
		jbCheck = new JButton("중복확인");
		jbSignUp = new JButton("회원가입");
		
		
	}

	@Override
	public void setDesign() {
		// TODO Auto-generated method stub
		jpTop.add(new BaseLable("회원가입", 30).setCenter());
		
		jpCenter.addChild();
		jpCenter.jpLeft.setGrid(5, 1, 10, 10).add(new BaseLable("아이디"));
		jpCenter.jpLeft.add(new BaseLable("비밀번호"));
		jpCenter.jpLeft.add(new BaseLable("이름(한글)"));
		jpCenter.jpLeft.add(new BaseLable("이름(영문)"));
		jpCenter.jpLeft.add(new BaseLable("생년월일"));
		jpCenter.jpLeft.setEmptyBorder(5, 5, 5, 30);
		
		jpCenter.jpCenter.setGrid(5, 1, 10, 10).add(jtId);
		jpCenter.jpCenter.add(jtPw);
		jpCenter.jpCenter.add(jtKName);
		jpCenter.jpCenter.add(jtEName);
		jpCenter.jpCenter.add(jtBirth);
		jpCenter.jpCenter.setEmptyBorder(5, 5, 5, 5);
		
		jpCenter.jpRight.setGrid(5, 1, 12, 12).add(jbCheck);
		jpCenter.jpRight.add(new BasePanel());
		jpCenter.jpRight.add(new BasePanel());
		jpCenter.jpRight.add(new BasePanel());
		jpCenter.jpRight.add(new BasePanel());
		jpCenter.jpRight.setEmptyBorder(5, 10, 5, 10);

		jpCenter.setEmptyBorder(10, 5, 10, 5);
		
		jpBottom.setFlowCenter().add(jbSignUp);
	}

	@Override
	public void events() {
		// TODO Auto-generated method stub
		jbCheck.addActionListener(e -> {
			String id = jtId.getText();
			check = DbManager.db.getData("SELECT * FROM 2023지방_3.user where id = ?;",id);
			ckState = 0;
			if (id.isBlank()) {
				message.error("아이디를 입력해주세요.");
				return;
			}
			if (check.size() != 0) {
				message.error("중복된 아이디입니다.");
				return;
			}
			message.info("사용 가능한 아이디 입니다.");
			ckState = 1;
		});
		
		
		jbSignUp.addActionListener(e-> {
			String id = jtId.getText();
			String pw = jtPw.getText();
			String kName = jtKName.getText();
			String eName = jtEName.getText();
			String birth = jtBirth.getText();
			
			String rokPt = "^(.*[ㄱ-ㅎㅏ-ㅣ가-힣])$";
			boolean rokRs = Pattern.matches(rokPt, kName);
			
			String enPt = "^(.*[a-zA-z])$";
			boolean enRs = Pattern.matches(enPt, eName);
			
			
			if (id.isBlank()||pw.isBlank()||kName.isBlank()||eName.isBlank()||birth.isBlank()) {
				message.error("빈칸이 있습니다.");
				return;
			}
			if (ckState != 1) message.error("중복확인을 해주세요.");
			
			if (!rokRs) {
				message.error("한글 이름은 한글만 가능합니다.");
				jtId.setText("");
				jtPw.setText("");
				jtKName.setText("");
				jtEName.setText("");
				jtBirth.setText("");
				jtId.requestFocus();
				
				return;
			}
			if (!enRs) {
				message.error("영어 이름은 영어만 가능합니다");
				message.error("한글 이름은 한글만 가능합니다.");
				jtId.setText("");
				jtPw.setText("");
				jtKName.setText("");
				jtEName.setText("");
				jtBirth.setText("");
				jtId.requestFocus();
				return;
			}
			if (rsBirth(birth)) {
				message.error("생년월일을 확인해주세요.");
				message.error("한글 이름은 한글만 가능합니다.");
				jtId.setText("");
				jtPw.setText("");
				jtKName.setText("");
				jtEName.setText("");
				jtBirth.setText("");
				jtId.requestFocus();
				return;
			}
			DbManager.db.setData("INSERT INTO `2023지방_3`.`user` (`id`, `pw`, `name1`, `name2`, `birth`) VALUES (?, ?, ?, ?, ?);", id,pw,kName,eName,birth);
			message.info(kName+ "님 환영합니다.");
			super.close();
		});
		
	}

	private boolean rsBirth(String birth) {
		// TODO Auto-generated method stub
		Date now = new Date();
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		sdf.isLenient();
		
		Date ckbirth = null;
		
		try {
			ckbirth = sdf.parse(birth);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return true;
		}
		
		if (ckbirth.after(now)) {
			return true;
		}
		return false;
		
	}

}
