package framse;

import javax.swing.JButton;
import javax.swing.JTextField;

import base.comp.BaseFrame;
import base.comp.BaseLable;
import base.comp.BasePanel;
import base.comp.message;
import jdbc.DbManager;
import model.model;

public class EditInfoFrame extends BaseFrame{

	private JTextField jtId;
	private JTextField jtPw;
	private JTextField jtKName;
	private JTextField jtEName;
	private JTextField jtBirth;
	private JButton jbUpdata;


	public EditInfoFrame(MainFrame mainFrame) {
		// TODO Auto-generated constructor stub
		super.BaseFrame("정보수정", 400, 400, mainFrame);
	}
	@Override
	public void setComp() {
		// TODO Auto-generated method stub
		jtId = new JTextField(10);
		jtId.setText(model.LogState.get(1));
		jtId.setEnabled(false);
		
		jtPw = new JTextField(10);
		jtKName = new JTextField(10);
		jtEName = new JTextField(10);
		jtBirth = new JTextField(10);
		
		jbUpdata = new JButton("정보수정");
		
		
	}

	@Override
	public void setDesign() {
		// TODO Auto-generated method stub
		jpTop.add(new BaseLable("정보수정", 30).setCenter());
		
		jpCenter.addChild();
		jpCenter.jpLeft.setGird(5, 1, 10, 10).add(new BaseLable("아이디"));
		jpCenter.jpLeft.add(new BaseLable("비밀번호"));
		jpCenter.jpLeft.add(new BaseLable("이름(한글)"));
		jpCenter.jpLeft.add(new BaseLable("이름(영문)"));
		jpCenter.jpLeft.add(new BaseLable("생년월일"));
		jpCenter.jpLeft.setEmptyBorder(5, 5, 5, 30);
		
		jpCenter.jpCenter.setGird(5, 1, 10, 10).add(jtId);
		jpCenter.jpCenter.add(jtPw);
		jpCenter.jpCenter.add(jtKName);
		jpCenter.jpCenter.add(jtEName);
		jpCenter.jpCenter.add(jtBirth);
		jpCenter.jpCenter.setEmptyBorder(5, 5, 5, 5);
		
		jpCenter.jpRight.setGird(5, 1, 12, 12).add(jbUpdata);
		jpCenter.jpRight.add(new BasePanel());
		jpCenter.jpRight.add(new BasePanel());
		jpCenter.jpRight.add(new BasePanel());
		jpCenter.jpRight.add(new BasePanel());
		jpCenter.jpRight.setEmptyBorder(5, 10, 5, 10);

		jpCenter.setEmptyBorder(10, 5, 10, 5);
		
		jpBottom.setFlowCenter().add(jbUpdata);
	}


	@Override
	public void events() {
		// TODO Auto-generated method stub
		jbUpdata.addActionListener(e -> {
			
			String id = model.LogState.get(1);
			String pw = jtPw.getText();
			String kName = jtKName.getText();
			String eName = jtEName.getText();
			String birth = jtBirth.getText();
			
			
			DbManager.db.setData("UPDATE `2023지방_3`.`user` SET  `pw` = ?, `name1` = ?, `name2` = ?, `birth` = ? WHERE (`id` = ?);\r\n"
					+ "",pw,kName,eName,birth,id );
			
			message.info(kName + "님 정보가 수정되었습니다.");
		});
	}

}
