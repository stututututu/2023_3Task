package frames;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import base.comp.BaseFrame;
import base.comp.BaseLabel;
import base.comp.message;
import jdbc.DbManager;
import model.model;

public class UpDateInfo extends BaseFrame{

	private JTextField jtId;
	private JTextField jtKname;
	private JTextField jtEname;
	private JTextField jtBirth;
	private JPasswordField jtPw;
	private JButton jbUpDate;

	public UpDateInfo(MainFrame mainFrame) {
		// TODO Auto-generated constructor stub
		super.BaseFrame("정보수정", 389, 328, mainFrame);
		
	}

	@Override
	public void setComp() {
		// TODO Auto-generated method stub
		jtId = new JTextField(10);
		jtId.setText(model.LogState.get(1));
		jtId.setEnabled(false);
		jtPw = new JPasswordField(10);
		jtKname = new JTextField(10);
		jtEname = new JTextField(10);
		jtBirth = new JTextField(10);
		
		jbUpDate = new JButton("정보수정");
		
		
	}

	@Override
	public void setDesign() {
		// TODO Auto-generated method stub
		jpTop.add(new BaseLabel("정보수정").setCenter().setTitle(25));
		
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
		
		jpBottom.setFlowCenter().add(jbUpDate);

		jpCenter.jpLeft.setEmpty(5, 5, 5, 5);
		jpCenter.jpCenter.setEmpty(5, 5, 5, 5);
		jpCenter.jpRight.setEmpty(5, 5, 5, 5);
	}

	@Override
	public void events() {
		// TODO Auto-generated method stub
		jbUpDate.addActionListener(e -> {
			
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
			
			DbManager.db.setData("UPDATE `2023지방_3`.`user` SET `pw` = ?, `name1` = ?, `name2` = ?, `birth` = ? WHERE (`id` = ?);\r\n"
					+ "",pw,kName,Ename,birth, id);
			message.info(kName+ "님 정보가 수정되었습니다.");
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
