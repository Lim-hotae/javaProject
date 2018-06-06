import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JToolBar;

public class ToolbarApp extends MenuItemAction{

	private JButton TnewItem = new JButton("새로 만들기");
	private JButton TopenItem = new JButton("열기");
	private JButton TsaveItem = new JButton("저장");
	private JButton TsaveAsItem = new JButton("다른 이름으로 저장");
	private JButton Texit = new JButton("닫기");
	private JButton TapplyItem = new JButton("적용");
	private JButton TchangeItem = new JButton("변경");
	
	public ToolbarApp(JToolBar toolBar){
		
		toolBar.setForeground(Color.WHITE);
		toolBar.setBackground(new Color(150, 150, 200));
		TapplyItem.setForeground(Color.RED);
	    TapplyItem.setBackground(Color.LIGHT_GRAY);
	    TchangeItem.setForeground(Color.RED);
	    TchangeItem.setBackground(Color.LIGHT_GRAY);
	    toolBar.add(TnewItem);
	    toolBar.add(TopenItem);
	    toolBar.add(TsaveItem);
	    toolBar.add(TsaveAsItem);
	    toolBar.add(Texit);
	    toolBar.add(TapplyItem);
	    toolBar.add(TchangeItem);

	    TnewItem.addActionListener(new MenuItemAction());
		TopenItem.addActionListener(new MenuItemAction());
		TsaveItem.addActionListener(new MenuItemAction());
		TsaveAsItem.addActionListener(new MenuItemAction());
		Texit.addActionListener(new MenuItemAction());
		TapplyItem.addActionListener(new MenuItemAction());
		TchangeItem.addActionListener(new MenuItemAction());
	}
}
	
