package testcitizen;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;


public class SendMail extends JFrame {
	
	JTextField textFrom = new JTextField();
	JTextField textTo = new JTextField();
	JTextField textTitle = new JTextField();
	JTextArea textContents = new JTextArea();
	JLabel from = new JLabel("From");
	JLabel to = new JLabel("To");
	JLabel title = new JLabel("제목");
	JLabel contents = new JLabel("내용");
	JButton submit = new JButton("보내기");
	JButton cancel = new JButton("취소");
	
	SendMail(){
		
		this.formDesign();
		this.eventHandler();
		this.setSize(600,300);
		this.setVisible(true);
	}

	public void formDesign() {
		
		this.setTitle("주민괸리 프로그램 v1.0 - 알림 보내기");
		getContentPane().setLayout(null);
		
		from.setBounds(71, 13, 57, 15);
		getContentPane().add(from);
		textFrom.setBounds(140, 10, 303, 21);
		getContentPane().add(textFrom);
		textFrom.setColumns(10);
		
		to.setBounds(71, 38, 57, 15);
		getContentPane().add(to);
		textTo = new JTextField();
		textTo.setBounds(140, 35, 303, 21);
		getContentPane().add(textTo);
		textTo.setColumns(10);
		
		contents.setBounds(71, 95, 57, 15);
		getContentPane().add(contents);
		textContents.setBounds(140, 91, 303, 119);
		getContentPane().add(textContents);
			
		submit.setBounds(150, 228, 97, 23);
		getContentPane().add(submit);
		
		cancel.setBounds(274, 228, 97, 23);
		getContentPane().add(cancel);
		
		title.setBounds(71, 66, 57, 15);
		getContentPane().add(title);
	
		textTitle.setBounds(140, 66, 303, 21);
		getContentPane().add(textTitle);
		textTitle.setColumns(10);
	}
	
	public void eventHandler() {
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(textTo.getText().trim().toString().length() >0 && textFrom.getText().trim().toString().length() >0
						&&textContents.getText().trim().toString().length() >0 && textTitle.getText().trim().toString().length()>0) {
					
					SendMailTest.tomail(textTo.getText().toString(), textFrom.getText().toString(), textTitle.getText().toString(), textContents.getText().toString());
				}
			}
			
		});
	}
	
	public static void main(String[] args) {
		new SendMail();
	}
}
