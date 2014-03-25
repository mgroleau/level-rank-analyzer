import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class LoginDialog extends JDialog{
	private String[] form = new String[4];
	
	public String[] showDialog(){
		setVisible(true);
		return form; 
	}
	
	public LoginDialog(boolean flag){
		setTitle("Login to FFR");
		setBounds(200, 200, 175, 200);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		
		setLayout(new GridLayout(4,1));
		
		final JTextField compareUser;
		if(flag){
			compareUser = new JTextField("who to compare to?");
			setLayout(new GridLayout(5,1));
			add(compareUser);
		}
		else compareUser = null;
		
		final JTextField username = new JTextField("your username");
		final JPasswordField password = new JPasswordField();
		final JCheckBox tokens = new JCheckBox("Combine token ranks?");
		JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				form[0] = username.getText();
				char[] pass = password.getPassword();  
				String passString = new String(pass);  
				form[1] = passString;
				if(tokens.isSelected())
					form[2] = "true";
				else
					form[2] = "false";
				if(compareUser!=null){
					form[3] = compareUser.getText();
				}
				
				dispose();
			}
		});
		
		add(username);
		add(password);
		add(tokens);
		add(submit);
	}
}
