package testcitizen;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.util.List;


public class CitizenInfo extends JFrame{

	String colName[] = {"No","ȣ��","������","��ȭ��ȣ","������","�̸���"};
	String[] [] data;
	DefaultTableModel model;
	JTable table;
	JPanel panel = new JPanel();
	JButton butUpdate = new JButton("����");
	JButton butDel = new JButton("����");
	JButton butSearch = new JButton("�˻�");
	JTextField hosu = new JTextField(3);
	JTextField name = new JTextField(4);
	JTextField phone = new JTextField(7);
	JTextField member = new JTextField(7);
	JTextField email = new JTextField(7);
	Container contentPane = this.getContentPane();
	
	CitizenInfo(){
		
		this.formDesign();
		this.eventHandler();
		this.setSize(1000,400);
		this.setVisible(true);
	}

	private void formDesign() {
		
		this.setTitle("�ֹΰ������α׷� v1.0 - �ֹ�����");
		this.setPreferredSize(new Dimension(700,300));
	
		SqlTest sql = new SqlTest();
		List<Citizen> listA = null;
		listA = sql.CitizenSearchAll();
		data = new String[listA.size()][6];
		
		for(int i=0; i<listA.size(); i++) {
			
			data[i][0] =listA.get(i).getId();
			data[i][1] =listA.get(i).getHosu();
			data[i][2] =listA.get(i).getName();
			data[i][3] =listA.get(i).getPhone();
			data[i][4] =listA.get(i).getMember();
			data[i][5] =listA.get(i).getEmail();
		}
		
		model = new DefaultTableModel(data,colName);
		table=new JTable(model);
		contentPane.add(new JScrollPane(table),BorderLayout.CENTER);
		panel.add(new JLabel("ȣ��"));
		panel.add(hosu);
		panel.add(new JLabel("������"));
		panel.add(name);
		panel.add(new JLabel("��ȭ��ȣ"));
		panel.add(phone);
		panel.add(new JLabel("������"));
		panel.add(member);
		panel.add(new JLabel("�̸���"));
		panel.add(email);
		panel.add(butUpdate);
		panel.add(butDel);
		panel.add(butSearch);
		contentPane.add(panel,BorderLayout.SOUTH);
		
	}
	
	
	private void eventHandler() {
		
		//�ֹ� ���� ����
		butUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String shosu = hosu.getText();
				String sname = name.getText();
				String sphone = phone.getText();
				String smember = member.getText();
				String semail = email.getText();
				
				Citizen citizen = new Citizen();
				
				citizen.setHosu(shosu);
				citizen.setName(sname);
				citizen.setPhone(sphone);
				citizen.setMember(smember);
				citizen.setEmail(semail);
				
				if(shosu.trim().length() >0 && sname.trim().length() >0 
						&& sphone.trim().length() >0
						&& smember.trim().length() >0 && semail.trim().length() >0 ) {
				
				
					SqlTest.updateCitizen(citizen);
				
				}else {
					
					JOptionPane.showMessageDialog(null, "������ �ֽ��ϴ�.", "���", JOptionPane.WARNING_MESSAGE);
				}
				
			}
			
		});
		
		//�ֹ����� ����
		butDel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String shosu = hosu.getText();
				
				if(shosu.trim().length() >0) {
					SqlTest.delCitizen(shosu);
				}else {
					
					JOptionPane.showMessageDialog(null, "������ �ֽ��ϴ�.", "���", JOptionPane.WARNING_MESSAGE);
				}
			}
			
		});
		
		//�˻�
		butSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				String shosu = hosu.getText();
				SqlTest sql = new SqlTest();
				
				//ȣ���� �˻�
				if(shosu.trim().length() >0) {
					
					List<Citizen> listA = null;
					listA = sql.searchHouscitizen(shosu);
					data = new String[listA.size()][6];
					model.setRowCount(0);
					for(int i=0; i<listA.size(); i++) {
						
						data[i][0] =listA.get(i).getId();
						data[i][1] =listA.get(i).getHosu();
						data[i][2] =listA.get(i).getName();
						data[i][3] =listA.get(i).getPhone();
						data[i][4] =listA.get(i).getMember();
						data[i][5] =listA.get(i).getEmail();
						model.addRow(new Object[] {data[i][0],data[i][1],data[i][2],data[i][3],data[i][4],data[i][5]});
					}
					table=new JTable(model);
					contentPane.add(new JScrollPane(table),BorderLayout.CENTER);
					contentPane.add(panel,BorderLayout.SOUTH);
				}else {
					
					JOptionPane.showMessageDialog(null, "������ �ֽ��ϴ�.", "���", JOptionPane.WARNING_MESSAGE);
				}
				
			}
			
		});
		
	}
	
	public static void main(String[] args) {
		new CitizenInfo();
	}
}
