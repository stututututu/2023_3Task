package frames;

import java.text.SimpleDateFormat;

import base.comp.BaseFrame;
import base.comp.BaseLabel;
import base.comp.BaseTable;
import model.model;

public class ChoseFrame extends BaseFrame{

	private SearchFrame searchFrame;
	private BaseLabel jlDate;
	private BaseTable jtCenter;

	public ChoseFrame(SearchFrame searchFrame) {
		// TODO Auto-generated constructor stub
		this.searchFrame = searchFrame;
		super.BaseFrame("항공권 선택", 600, 500, searchFrame);
	}

	@Override
	public void setComp() {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("MM.dd(E)");
		jlDate = new BaseLabel(sdf.format(searchFrame.jtDepartDate.getText()));
		jtCenter = new BaseTable();
		
		
	}

	@Override
	public void setDesign() {
		// TODO Auto-generated method stub
		jpTop.addChild();
		jpTop.jpCenter.setFlowLeft().add(new BaseLabel(String.format("%02d", model.DepartDate) + "." +String.format("%02d", model.DepartDay)).setTitle(20));
		jpTop.jpBottom.setMatte(0,0,1,0);
	}

	@Override
	public void events() {
		// TODO Auto-generated method stub
		
	}

}
