import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

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
			File f = new File("test.txt"); 
			  FileReader fr = null;
			try {
				fr = new FileReader(f);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			  BufferedReader br1 = new BufferedReader(fr);
			  while(true) {
			   String str = null;
			try {
				str = br1.readLine();
			} catch (IOException e1) {
				e1.printStackTrace();
			}  
			   if(str == null) {
			    break;
			   }
			   t.setText(str);
			  }
			  try {
				br1.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			  try {
				fr.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		} else if (item.equals("저장")) {
			JSONObject obj = new JSONObject();
			obj.put("Key", t.getText());
			try { 
				FileWriter file = new FileWriter("test.txt"); 
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