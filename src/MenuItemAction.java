import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.json.simple.JSONObject;

public class MenuItemAction extends App implements ActionListener {

	private JFileChooser jfc = new JFileChooser();
	protected String item;

	public void actionPerformed(ActionEvent e) {
		Object mitem = (Object) e.getSource();
		item = ((AbstractButton) mitem).getText();

		if (item.equals("���� �����")) {
			t.setText("");
			// ���Ͽ���
		} else if (item.equals("����")) {
			if (jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				File f = new File(jfc.getSelectedFile().toString());
				FileReader fr = null;
				try {
					fr = new FileReader(f);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				BufferedReader br1 = new BufferedReader(fr);
				while (true) {
					String str = null;
					try {

						str = br1.readLine();

					} catch (IOException e1) {
						e1.printStackTrace();
					}
					if (str == null) {
						break;
					}
					t.setText(str);
				}
	
			}
		} else if (item.equals("����")) {
			JFileChooser jc=new JFileChooser();
            jc.showSaveDialog(this);
            File saveFile=jc.getSelectedFile();
            if(saveFile!=null)
                setTitle(saveFile.getName());
        try{
            String contents=t.getText();
            if(contents==null|| contents.trim().equals("")){
                JOptionPane.showMessageDialog(this,"������ ������"+"�Է��ؾ� �մϴ�.");
                t.requestFocus();
                return;
            }
            FileWriter fw=new FileWriter(saveFile); 
            BufferedWriter bw=new BufferedWriter(fw);
             
            bw.write(contents);
            bw.flush();
            if(bw!=null) bw.close();
            if(fw!=null) fw.close();
        }catch(IOException ex){
            System.out.println("IO Error: "+ex.getMessage());
        }
		} else if (item.equals("�ٸ� �̸����� ����")) {
			JFileChooser jc=new JFileChooser();
            jc.showSaveDialog(this);
            File saveFile=jc.getSelectedFile();
            if(saveFile!=null)
                setTitle(saveFile.getName());
        try{
            String contents=t.getText();
            if(contents==null|| contents.trim().equals("")){
                JOptionPane.showMessageDialog(this,"������ ������"+"�Է��ؾ� �մϴ�.");
                t.requestFocus();
                return;
            }
            FileWriter fw=new FileWriter(saveFile); 
            BufferedWriter bw=new BufferedWriter(fw);
             
            bw.write(contents);
            bw.flush();
            if(bw!=null) bw.close();
            if(fw!=null) fw.close();
        }catch(IOException ex){
            System.out.println("IO Error: "+ex.getMessage());
        }

		} else if (item.equals("�ݱ�")) {
			System.exit(0);
		} else if (item.equals("����")) {
			System.out.println("����");
		} else {
			System.out.println("����");
		}
	}

}