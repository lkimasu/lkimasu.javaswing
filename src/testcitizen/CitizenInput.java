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
    JButton btnSubmit = new JButton("���");
    JButton btnCancel = new JButton("���");       
    
	CitizenInput(){
		
	this.formDesign();
	this.eventHandler();
	this.setSize(400,400);
	this.setVisible(true);
	
	}
	
	public void formDesign() {
		
		this.setTitle("�ֹα��� ���α׷� v1.0 - ȸ�� ����");
		getContentPane().setLayout(null);
		labelHo = new Label("ȣ��");
        labelName=new Label("�̸�");
        labelHp=new Label("�ڵ���");
        labelPerson = new Label("������ �̸�");
        labelEmail = new Label("�̸���");
        
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
						
						//�� �Է� �Ǿ��ٰ� �ְ�, ���� ȭ������ ���ư���
						
					}else {
						
						JOptionPane.showMessageDialog(null, "���", "������ �ֽ��ϴ�.", JOptionPane.WARNING_MESSAGE);
					}
				
			}
			
		});
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//��� ��ư				
			}
			
		});
	}
	
	public static void main(String[] args) {
		new CitizenInput();
	}
}
