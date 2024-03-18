package Login_view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class TeacherMenu extends JFrame{
	private JButton jb = new JButton("查询");
	public 	TeacherMenu() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		Container cp = getContentPane();
		cp.add(jb);
		jb.setBounds(10, 10, 100, 30);
		jb.addActionListener(new jbAction());
		setVisible(true);
	}
	class jbAction implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			jb.setText("我被单击了");	
		}
	}
	public void sendObject(Login father) {
		// TODO 自动生成的方法存根
		
	}

	public void sendID(String text) {
		// TODO 自动生成的方法存
	}

	public void init() {
		this.setSize(500, 420);
        this.setLocationRelativeTo(null);
        this.setTitle("教师端");
        this.setResizable(false);
        init();
        this.setVisible(true);
	}


}
