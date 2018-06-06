import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.json.simple.JSONObject;


public class MenuItemAction extends App implements ActionListener {

	protected String item;
	
	public void actionPerformed(ActionEvent e) {
		Object mitem = (Object)e.getSource();
		item = ((AbstractButton) mitem).getText();

		if (item.equals("���� �����")) {
			t.setText("");
		} else if (item.equals("����")) {
			//String jsonInfo = obj.toJSONString();
		} else if (item.equals("����")) {
			JSONObject obj = new JSONObject();
			obj.put("Key", t);
			try { 
				FileWriter file = new FileWriter("c:\\test.txt"); 
				file.write(obj.toJSONString()); 
				file.flush(); 
				file.close(); 
				} catch (IOException e1) { 
					e1.printStackTrace(); 
			}
		} else if (item.equals("�ٸ� �̸����� ����")) {
			System.out.println("�ٸ� �̸����� ����");
		} else if (item.equals("�ݱ�")) {
			System.exit(0);
		} else if (item.equals("����")) {
			System.out.println("����");
		} else {
			System.out.println("����");
		}
	}

}