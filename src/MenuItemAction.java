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

		if (item.equals("새로 만들기")) {
			t.setText("");
		} else if (item.equals("열기")) {
			//String jsonInfo = obj.toJSONString();
		} else if (item.equals("저장")) {
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
		} else if (item.equals("다른 이름으로 저장")) {
			System.out.println("다른 이름으로 저장");
		} else if (item.equals("닫기")) {
			System.exit(0);
		} else if (item.equals("적용")) {
			System.out.println("적용");
		} else {
			System.out.println("변경");
		}
	}

}