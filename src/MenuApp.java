import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuApp extends App {
	protected JMenuItem newItem = new JMenuItem("���� �����");
	protected JMenuItem openItem = new JMenuItem("����");
	protected JMenuItem saveItem = new JMenuItem("����");
	protected JMenuItem saveAsItem = new JMenuItem("�ٸ� �̸����� ����");
	protected JMenuItem exit = new JMenuItem("�ݱ�");
	protected JMenuItem applyItem = new JMenuItem("����");
	protected JMenuItem changeItem = new JMenuItem("����");
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
