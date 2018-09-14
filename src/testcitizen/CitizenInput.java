package testcitizen;


import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class CitizenInput extends JFrame {
	
	Label labelHo,labelName, labelHp,labelPerson,labelEmail;
    TextField ho,name,person,email;
    TextField txtHp;
    JButton btnSubmit = new JButton("등록");
    JButton btnCancel = new JButton("취소");       
    
	CitizenInput(){
		
	this.formDesign();
	this.eventHandler();
	this.setSize(400,400);
	this.setVisible(true);
	
	}
	
	public void formDesign() {
		
		this.setTitle("주민괸리 프로그램 v1.0 - 회원 가입");
		getContentPane().setLayout(null);
		labelHo = new Label("호수");
        labelName=new Label("이름");
        labelHp=new Label("핸드폰");
        labelPerson = new Label("구성원 이름");
        labelEmail = new Label("이메일");
        
        labelHo.setBounds(20,50,100,20);
        labelName.setBounds(20, 80, 100 , 20);
        labelHp.setBounds(20,110,100,20);
        labelPerson.setBounds(20, 140, 100, 20);
        labelEmail.setBounds(20,170,100,20);
        
        getContentPane().add(labelHo);
        getContentPane().add(labelName);
        getContentPane().add(labelHp);
        getContentPane().add(labelPerson);
        getContentPane().add(labelEmail);
       
        ho = new TextField();
        name= new TextField();
        txtHp = new TextField();
        person = new TextField();
        email = new TextField();
        
        ho.setBounds(120, 50, 150, 20);
        name.setBounds(120, 80, 150, 20);
        txtHp.setBounds(120, 110, 150, 20);
        person.setBounds(120,140,150,20);
        email.setBounds(120,170,150,20);
        btnSubmit.setBounds(86, 247, 75, 50);
        btnCancel.setBounds(176, 247 ,75, 50);
          
        getContentPane().add(ho);
        getContentPane().add(name);
        getContentPane().add(txtHp);
        getContentPane().add(person);
        getContentPane().add(email);
        getContentPane().add(btnSubmit);
        getContentPane().add(btnCancel);

	}
	
	public void eventHandler() {
		btnSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			
					String Hosu = ho.getText();
					String Nameing = name.getText();
					String Hp = txtHp.getText();
					String sarm = person.getText();
					String Eamil1 = email.getText();
					
					Citizen citizen = new Citizen();
					
					citizen.setHosu(ho.getText());
					citizen.setName(name.getText());
					citizen.setPhone(txtHp.getText());
					citizen.setMember(person.getText());
					citizen.setEmail(email.getText());
					
					if(Hosu.trim().length() >0 && Nameing.trim().length() >0 
							&& Hp.trim().length() >0
							&& sarm.trim().length() >0 && Eamil1.trim().length() >0 ) {
					
						SqlTest sql = new SqlTest();
						SqlTest.insert(citizen);
						
						//잘 입력 되었다고 넣고, 메인 화면으로 돌아가기
						
					}else {
						
						JOptionPane.showMessageDialog(null, "경고", "공백이 있습니다.", JOptionPane.WARNING_MESSAGE);
					}
				
			}
			
		});
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//취소 버튼				
			}
			
		});
	}
	
	public static void main(String[] args) {
		new CitizenInput();
	}
}
