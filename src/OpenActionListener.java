import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class OpenActionListener implements ActionListener {
	
	JFileChooser chooser;
	
	OpenActionListener(){
		chooser = new JFileChooser();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		FileNameExtensionFilter filter = new FileNameExtensionFilter("test", "txt");
		chooser.setFileFilter(filter);
		
		int ret = chooser.showOpenDialog(null);
		if(ret != JFileChooser.APPROVE_OPTION) {
			JOptionPane.showMessageDialog(null, "������ �������� �ʾҽ��ϴ�");
			return;
		}
		
		String filePath = chooser.getSelectedFile().getPath();
		
	}

	

}
