package testcitizen;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class MainEx extends JFrame{

	JButton btn = new JButton("회원 가입");
	JButton btn1 = new JButton("주민 정보");
	JButton btn2 = new JButton("관리비 입력");
	JButton btn3 = new JButton("관리비 정보");
	JButton btn4 = new JButton("알림 보내기");
	JTextField tf = new JTextField(12);
	JPasswordField pf = new JPasswordField(10);
	JButton login = new JButton("Login");
	JLabel loginText = new JLabel();
	boolean isLogin = false;
	boolean btnEnable = false;
	JPanel idPanel = new JPanel();
	JPanel passPanel = new JPanel();
	JLabel idLabel = new JLabel("ID : ");
	JLabel passLabel = new JLabel("PASSWORD : ");
	String id = "admin";
	String pw = "1111";
	MainEx(){
		
		this.formDesign();
		this.eventHandler();
		this.setSize(650,300);
		this.setVisible(true);
	}
	
	public void formDesign() {
		
		this.setTitle("주민괸리 프로그램 v1.0 - 관리자 모드");
		getContentPane().setLayout(null);
		btn.setEnabled(btnEnable);
		btn.setBounds(40, 5, 95, 45);
		getContentPane().add(btn);
		btn1.setEnabled(btnEnable);
		btn1.setBounds(140, 5, 95, 45);
		getContentPane().add(btn1);
		btn2.setEnabled(btnEnable);
		btn2.setBounds(240, 5, 110, 45);
		getContentPane().add(btn2);
		btn3.setEnabled(btnEnable);
		btn3.setBounds(360, 5, 110, 45);
		getContentPane().add(btn3);
		btn4.setEnabled(btnEnable);
		btn4.setBounds(480, 5, 110, 45);
		getContentPane().add(btn4);
		idPanel.setBounds(152, 78, 209, 31);
		idPanel.add(idLabel);
		idPanel.add(tf);
		passPanel.setBounds(151, 119, 210, 31);
		passPanel.add(passLabel);
		passPanel.add(pf);
		getContentPane().add(idPanel);
		getContentPane().add(passPanel);
		login.setBounds(186, 160, 95, 45);
		getContentPane().add(login);
		loginText.setBounds(126, 215, 295, 30);
		getContentPane().add(loginText);
		loginText.setForeground(Color.RED);
	}

	public void eventHandler() {
		
		login.addActionListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton b =(JButton) e.getSource();
				if(b.getText() == "Login") {
					try {
						
					    if (id.equals(tf.getText()) && pw.equals(pf.getText())) {
					     isLogin = true;
					    }
					    else
					     isLogin = false;
					    if (isLogin) {
					    	
					        loginText.setText("로그인되었습니다. 관리자님 안녕하세요.");
					        btnEnable = true;
					        btn.setEnabled(btnEnable);
					        btn1.setEnabled(btnEnable);
					        btn2.setEnabled(btnEnable);
					        btn3.setEnabled(btnEnable);
					        btn4.setEnabled(btnEnable); 
					        tf.setEnabled(false);
					        pf.setEnabled(false);
					        
					       } else {
					    	   
					        loginText.setText("ID 또는 password가 잘못되었습니다.");
					        
					       }
					    
					      } catch (Exception e1) {
					    	  
					       System.out.println("false");
					       
					      }
				}
		}});
		
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e1) {
				
				JButton b =(JButton) e1.getSource();
				if(b.getText() == "회원 가입") {
					CitizenInput sign = new CitizenInput();
					sign.setVisible(true);
				}
			}
			
		});
		
		btn1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JButton b =(JButton) e.getSource();
				if(b.getText() == "주민 정보") {
					CitizenInfo info = new CitizenInfo();
					info.setVisible(true);
				}
			}
			
		});
		
		btn2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JButton b =(JButton) e.getSource();
				if(b.getText() == "관리비 입력") {
					ExpensiveInput input = new ExpensiveInput();
					input.setVisible(true);
				}
			}
			
		});
		
		btn3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JButton b =(JButton) e.getSource();
				if(b.getText() == "관리비 정보") {
					ExpensiveInfo search = new ExpensiveInfo();
					search.setVisible(true);
				}
			}
		});
		
		btn4.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				JButton b =(JButton) e.getSource();
				if(b.getText() == "알림 보내기") {
					SendMail send = new SendMail();
					send.setVisible(true);
				}
				
			}
			
		});
	}
			
	public static void main(String[] args) {
		new MainEx();
	}
}