package frames;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import base.comp.BaseFrame;
import base.comp.BaseLabel;
import base.comp.BasePanel;
import base.comp.message;
import jdbc.DbManager;

public class SignUpFrame extends BaseFrame{

	private JTextField jtId;
	private JTextField jtPw;
	private JTextField jtKname;
	private JTextField jtBirth;
	private JButton jbCk;
	private JButton jbSignUp;
	private JTextField jtEname;
	private int ck;
	private Vector<Vector<String>> check;

	public SignUpFrame(MainFrame mainFrame) {
		// TODO Auto-generated constructor stub
		super.BaseFrame("회원가입", 389, 328, mainFrame);
	}

	@Override
	public void setComp() {
		// TODO Auto-generated method stub
		jtId = new JTextField(10);
		jtPw = new JPasswordField(10);
		jtKname = new JTextField(10);
		jtEname = new JTextField(10);
		jtBirth = new JTextField(10);
		
		jbCk = new JButton("중복확인");
		jbSignUp = new JButton("회원가입");
		
		
	}

	@Override
	public void setDesign() {
		// TODO Auto-generated method stub
		jpTop.add(new BaseLabel("회원가입").setCenter().setTitle(25));
		
		jpCenter.addChild();
		jpCenter.jpLeft.setGrid(5, 1, 10, 10).add(new BaseLabel("아이디"));
		jpCenter.jpLeft.add(new BaseLabel("비밀번호"));
		jpCenter.jpLeft.add(new BaseLabel("이름(한글)"));
		jpCenter.jpLeft.add(new BaseLabel("이름(영어)"));
		jpCenter.jpLeft.add(new BaseLabel("생년월일"));
		
		jpCenter.jpCenter.setGrid(5, 1, 10, 10).add(jtId);
		jpCenter.jpCenter.add(jtPw);
		jpCenter.jpCenter.add(jtKname);
		jpCenter.jpCenter.add(jtEname);
		jpCenter.jpCenter.add(jtBirth);

		jpCenter.jpRight.setGrid(5, 1, 10, 10).add(jbCk);
		jpCenter.jpRight.add(new BasePanel());
		jpCenter.jpRight.add(new BasePanel());
		jpCenter.jpRight.add(new BasePanel());
		jpCenter.jpRight.add(new BasePanel());
		
		jpBottom.setFlowCenter().add(jbSignUp);
	
		jpCenter.jpLeft.setEmpty(5, 5, 5, 5);
		jpCenter.jpCenter.setEmpty(5, 5, 5, 5);
		jpCenter.jpRight.setEmpty(5, 5, 5, 5);
	
	}

	@Override
	public void events() {
		// TODO Auto-generated method stub
		ck = 0;
		check = DbManager.db.getData("SELECT * FROM 2023지방_3.user where id = ?;", jtId.getText());
		jbCk.addActionListener(e -> {
			if (check.size()==0) {
				message.info("사용 가능한 아이디 입니다.");
				ck = 1;
				return;
			}
			message.error("중복된 아이디입니다.");
			jtId.setText("");
			jtId.requestFocus();
			ck = 0;
			
		});
		
		jbSignUp.addActionListener(e -> {
			String id = jtId.getText();
			String pw = jtPw.getText();
			String kName = jtKname.getText();
			String Ename = jtEname.getText();
			String birth = jtBirth.getText();
			
			String ckKname = "^(.*[ㄱ-ㅎ ㅏ-ㅣ 가-힣])$";
			boolean rsKname = Pattern.matches(ckKname, kName);
			boolean rsEname = Pattern.matches(ckKname, Ename);
			if (id.isBlank()||pw.isBlank()||kName.isBlank()||Ename.isBlank()||birth.isBlank()) {
				message.error("빈칸이 있습니다.");
				return;
			}
			if (ck==0) {
				message.error("중복확인을 해주세요.");
				return;
			}
			if (!rsKname) {
				message.error("한글 이름은 한글만 가능합니다.");
				jtKname.setText("");
				jtKname.requestFocus();
				return;
			}
			if (rsEname) {
				message.error("영어 이름은 영문만 가능합니다.");
				jtKname.setText("");
				jtKname.requestFocus();
				return;
			}
			
			String ckSpace = "^.+ .+$";
			if (!Pattern.matches(ckSpace, Ename)) {
				message.error("영문 이름은 성과 이름을 구분해주세요.");
				jtEname.setText("");
				jtEname.requestFocus();
				return;
				}
			if (rsBirth(birth)) {
				message.error("생년월일을 확인해주세요.");
				return;
			}
			
			DbManager.db.setData("INSERT INTO `2023지방_3`.`user` (`id`, `pw`, `name1`, `name2`, `birth`) VALUES (?, ?, ?, ?, ?);\r\n"
					+ "", id,pw,kName,Ename,birth);
			message.info(kName+ "님 회원가입이 완료되었습니다.");
			dispose();
			
		});
		
	}

	private boolean rsBirth(String birth) {
		// TODO Auto-generated method stub
		
		Date now = new Date();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		sdf.isLenient();
		Date ckBirth = null;
		
		try {
			ckBirth = sdf.parse(birth);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return true;
		}
		if (ckBirth.after(now)) {
			return true;
		}
		return false;
		
	}

}
