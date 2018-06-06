import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuApp extends App {
	protected JMenuItem newItem = new JMenuItem("새로 만들기");
	protected JMenuItem openItem = new JMenuItem("열기");
	protected JMenuItem saveItem = new JMenuItem("저장");
	protected JMenuItem saveAsItem = new JMenuItem("다른 이름으로 저장");
	protected JMenuItem exit = new JMenuItem("닫기");
	protected JMenuItem applyItem = new JMenuItem("적용");
	protected JMenuItem changeItem = new JMenuItem("변경");
	protected ActionEvent e;

	public MenuApp() {

	}

	public MenuApp(ActionEvent e) {
		this.e = e;
	}

	public MenuApp(JMenuBar mb) {

		mb.setBackground(new Color(150, 150, 250));
		JMenu screenMenu = new JMenu("Menu Bar");
		screenMenu.setForeground(Color.WHITE);
		screenMenu.add(newItem);
		screenMenu.add(openItem);
		screenMenu.add(saveItem);
		screenMenu.add(saveAsItem);
		screenMenu.add(exit);
		screenMenu.addSeparator();
		screenMenu.add(applyItem);
		screenMenu.add(changeItem);
		mb.add(screenMenu);
		

		newItem.addActionListener(new MenuItemAction());
		openItem.addActionListener(new MenuItemAction());
		saveItem.addActionListener(new MenuItemAction());
		saveAsItem.addActionListener(new MenuItemAction());
		exit.addActionListener(new MenuItemAction());
		applyItem.addActionListener(new MenuItemAction());
		changeItem.addActionListener(new MenuItemAction());

	}

}
