package testcitizen;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ExpensiveInfo extends JFrame {

	JTable table;
	JScrollPane scroll; 
	String[] [] data; 
	String[] colName = {"No","월","호수","전기세","수도세","가스비","경비비","합계"};
	JButton butUpdate = new JButton("수정");
	JButton butDel = new JButton("삭제");
	JButton butSearch = new JButton("검색");
	DefaultTableModel model;
	JPanel panel = new JPanel();
	JTextField month = new JTextField(3);
	JTextField hosu = new JTextField(4);
	JTextField electricity = new JTextField(7);
	JTextField water = new JTextField(7);
	JTextField gas = new JTextField(7);
	JTextField gardmoney = new JTextField(7);
	Container contentPane = this.getContentPane();
	
	ExpensiveInfo(){
		
		this.formDesign();
		this.eventHandler();
		this.setSize(1000,400);
		this.setVisible(true);
	}

	public void formDesign() {
		
		this.setTitle("주민괸리 프로그램 v1.0 - 관리비 정보");
		this.setPreferredSize(new Dimension(700,300));
		
		SqlTest sql = new SqlTest();
		List<Expensive> listA = null;
		listA = sql.ExpensiveSearchAll();
		data = new String[listA.size()][8];
		for(int i=0; i<listA.size(); i++) {
			
			data[i][0] =listA.get(i).getId();
			data[i][1] =listA.get(i).getMonth();
			data[i][2] =listA.get(i).getHosu();
			data[i][3] =String.valueOf(listA.get(i).getElectricity());
			data[i][4] =String.valueOf(listA.get(i).getWater());
			data[i][5] =String.valueOf(listA.get(i).getGas());
			data[i][6] =String.valueOf(listA.get(i).getGuardMoney());
			data[i][7] =String.valueOf(listA.get(i).getElectricity()+listA.get(i).getWater()+listA.get(i).getGas()+listA.get(i).getGuardMoney());
		}
		
		model = new DefaultTableModel(data,colName);
		table=new JTable(model);
		contentPane.add(new JScrollPane(table),BorderLayout.CENTER);
		panel.add(new JLabel("월"));
		panel.add(month);
		panel.add(new JLabel("호수"));
		panel.add(hosu);
		panel.add(new JLabel("전기세"));
		panel.add(electricity);
		panel.add(new JLabel("수도세"));
		panel.add(water);
		panel.add(new JLabel("가스비"));
		panel.add(gas);
		panel.add(new JLabel("경비비"));
		panel.add(gardmoney);
		panel.add(butUpdate);
		panel.add(butDel);
		panel.add(butSearch);
		contentPane.add(panel,BorderLayout.SOUTH);
		
		
	}
	
	public void eventHandler() {
		
		butUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String smonth = month.getText();
				String shosu = hosu.getText();
				int selectricity = Integer.parseInt(electricity.getText());
				int swater = Integer.parseInt(water.getText());
				int sgas = Integer.parseInt(gas.getText());
				int sgardmoney = Integer.parseInt(gardmoney.getText());
				
				Expensive expensive = new Expensive();
				
				expensive.setMonth(smonth);
				expensive.setHosu(shosu);
				expensive.setElectricity(selectricity);
				expensive.setWater(swater);
				expensive.setGas(sgas);
				expensive.setGuardMoney(sgardmoney);
				
				SqlTest.updateExpensive(expensive);
				formDesign();	
			}
			
		});
		
		//삭제
		butDel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String shosu = month.getText();
				
				if(shosu.trim().length() >0) {
					SqlTest.delExpensive(shosu);
				}else {
					
					JOptionPane.showMessageDialog(null, "공백이 있습니다.", "경고", JOptionPane.WARNING_MESSAGE);
				}
				
			}
			
		});
		
		//검색
		butSearch.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
					String smonth =month.getText();
					String shosu = hosu.getText();
					String selectricity = electricity.getText();
					String swater = water.getText();
					String sgas = gas.getText();
					String sgardmoney = gardmoney.getText();
					SqlTest sql = new SqlTest();
					List<Expensive> listA = null;
					
					//월별로 검색
					if(smonth.trim().length() >0) {
							
						listA = sql.searchMonthExpensive(smonth);
						data = new String[listA.size()][8];
						model.setRowCount(0);
						for(int i=0; i<listA.size(); i++) {
							
							data[i][0]=listA.get(i).getId();
							data[i][1]=listA.get(i).getMonth();
							data[i][2]=listA.get(i).getHosu();
							data[i][3]=String.valueOf(listA.get(i).getElectricity());
							data[i][4]=String.valueOf(listA.get(i).getWater());
							data[i][5]=String.valueOf(listA.get(i).getGas());
							data[i][6]=String.valueOf(listA.get(i).getGuardMoney());
							data[i][7] =String.valueOf(listA.get(i).getElectricity()+listA.get(i).getWater()+listA.get(i).getGas()+listA.get(i).getGuardMoney());
							model.addRow(new Object[] {data[i][0],data[i][1],data[i][2],data[i][3],data[i][4],data[i][5],data[i][6],data[i][7]});
						}
						table=new JTable(model);
						contentPane.add(new JScrollPane(table),BorderLayout.CENTER);
						contentPane.add(panel,BorderLayout.SOUTH);
						
					//호수로 관리비 검색
					}else if(shosu.trim().length()>0) {
						
						listA = sql.searchHosuExpensive(shosu);
						data = new String[listA.size()][8];
						model.setRowCount(0);
						for(int i=0; i<listA.size(); i++) {
							
							data[i][0]=listA.get(i).getId();
							data[i][1]=listA.get(i).getMonth();
							data[i][2]=listA.get(i).getHosu();
							data[i][3]=String.valueOf(listA.get(i).getElectricity());
							data[i][4]=String.valueOf(listA.get(i).getWater());
							data[i][5]=String.valueOf(listA.get(i).getGas());
							data[i][6]=String.valueOf(listA.get(i).getGuardMoney());
							data[i][7] =String.valueOf(listA.get(i).getElectricity()+listA.get(i).getWater()+listA.get(i).getGas()+listA.get(i).getGuardMoney());
							model.addRow(new Object[] {data[i][0],data[i][1],data[i][2],data[i][3],data[i][4],data[i][5],data[i][6],data[i][7]});
						}
						table=new JTable(model);
						contentPane.add(new JScrollPane(table),BorderLayout.CENTER);
						contentPane.add(panel,BorderLayout.SOUTH);
					}
					
					//전기새로 검색
					else if(selectricity.trim().length()>0) {
						
						listA = sql.searchElectricityExpensive(selectricity);
						data = new String[listA.size()][8];
						model.setRowCount(0);
						for(int i=0; i<listA.size(); i++) {
							
							data[i][0]=listA.get(i).getId();
							data[i][1]=listA.get(i).getMonth();
							data[i][2]=listA.get(i).getHosu();
							data[i][3]=String.valueOf(listA.get(i).getElectricity());
							data[i][4]=String.valueOf(listA.get(i).getWater());
							data[i][5]=String.valueOf(listA.get(i).getGas());
							data[i][6]=String.valueOf(listA.get(i).getGuardMoney());
							data[i][7] =String.valueOf(listA.get(i).getElectricity()+listA.get(i).getWater()+listA.get(i).getGas()+listA.get(i).getGuardMoney());
							model.addRow(new Object[] {data[i][0],data[i][1],data[i][2],data[i][3],data[i][4],data[i][5],data[i][6],data[i][7]});
						}
						table=new JTable(model);
						contentPane.add(new JScrollPane(table),BorderLayout.CENTER);
						contentPane.add(panel,BorderLayout.SOUTH);
						
					//물세로 검색
					}else if(swater.trim().length()>0) {
						
						listA = sql.searchWaterExpensive(swater);
						data = new String[listA.size()][8];
						model.setRowCount(0);
						for(int i=0; i<listA.size(); i++) {
							
							data[i][0]=listA.get(i).getId();
							data[i][1]=listA.get(i).getMonth();
							data[i][2]=listA.get(i).getHosu();
							data[i][3]=String.valueOf(listA.get(i).getElectricity());
							data[i][4]=String.valueOf(listA.get(i).getWater());
							data[i][5]=String.valueOf(listA.get(i).getGas());
							data[i][6]=String.valueOf(listA.get(i).getGuardMoney());
							data[i][7] =String.valueOf(listA.get(i).getElectricity()+listA.get(i).getWater()+listA.get(i).getGas()+listA.get(i).getGuardMoney());
							model.addRow(new Object[] {data[i][0],data[i][1],data[i][2],data[i][3],data[i][4],data[i][5],data[i][6],data[i][7]});
						}
						table=new JTable(model);
						contentPane.add(new JScrollPane(table),BorderLayout.CENTER);
						contentPane.add(panel,BorderLayout.SOUTH);
					}
					//가스비로 검색
					else if(sgas.trim().length()>0) {
						
						listA = sql.searchGasExpensive(sgas);
						data = new String[listA.size()][8];
						model.setRowCount(0);
						for(int i=0; i<listA.size(); i++) {
							
							data[i][0]=listA.get(i).getId();
							data[i][1]=listA.get(i).getMonth();
							data[i][2]=listA.get(i).getHosu();
							data[i][3]=String.valueOf(listA.get(i).getElectricity());
							data[i][4]=String.valueOf(listA.get(i).getWater());
							data[i][5]=String.valueOf(listA.get(i).getGas());
							data[i][6]=String.valueOf(listA.get(i).getGuardMoney());
							data[i][7] =String.valueOf(listA.get(i).getElectricity()+listA.get(i).getWater()+listA.get(i).getGas()+listA.get(i).getGuardMoney());
							model.addRow(new Object[] {data[i][0],data[i][1],data[i][2],data[i][3],data[i][4],data[i][5],data[i][6],data[i][7]});
						}
						table=new JTable(model);
						contentPane.add(new JScrollPane(table),BorderLayout.CENTER);
						contentPane.add(panel,BorderLayout.SOUTH);
					}
					//경비비로 검색
					else if(sgardmoney.trim().length()>0) {
						
						listA = sql.searchGardmoneyExpensive(sgardmoney);
						data = new String[listA.size()][8];
						model.setRowCount(0);
						for(int i=0; i<listA.size(); i++) {
							
							data[i][0]=listA.get(i).getId();
							data[i][1]=listA.get(i).getMonth();
							data[i][2]=listA.get(i).getHosu();
							data[i][3]=String.valueOf(listA.get(i).getElectricity());
							data[i][4]=String.valueOf(listA.get(i).getWater());
							data[i][5]=String.valueOf(listA.get(i).getGas());
							data[i][6]=String.valueOf(listA.get(i).getGuardMoney());
							data[i][7] =String.valueOf(listA.get(i).getElectricity()+listA.get(i).getWater()+listA.get(i).getGas()+listA.get(i).getGuardMoney());
							model.addRow(new Object[] {data[i][0],data[i][1],data[i][2],data[i][3],data[i][4],data[i][5],data[i][6],data[i][7]});
						}
						table=new JTable(model);
						contentPane.add(new JScrollPane(table),BorderLayout.CENTER);
						contentPane.add(panel,BorderLayout.SOUTH);
					}
					//공백일 경우 전체 검색
					else {
						
						listA = sql.ExpensiveSearchAll();
						data = new String[listA.size()][8];
						model.setRowCount(0);
						
						for(int i=0; i<listA.size(); i++) {
							
							data[i][0] =listA.get(i).getId();
							data[i][1] =listA.get(i).getMonth();
							data[i][2] =listA.get(i).getHosu();
							data[i][3] =String.valueOf(listA.get(i).getElectricity());
							data[i][4] =String.valueOf(listA.get(i).getWater());
							data[i][5] =String.valueOf(listA.get(i).getGas());
							data[i][6] =String.valueOf(listA.get(i).getGuardMoney());
							data[i][7] =String.valueOf(listA.get(i).getElectricity()+listA.get(i).getWater()+listA.get(i).getGas()+listA.get(i).getGuardMoney());
							model.addRow(new Object[] {data[i][0],data[i][1],data[i][2],data[i][3],data[i][4],data[i][5],data[i][6],data[i][7]});
						}
						
						table=new JTable(model);
						contentPane.add(new JScrollPane(table),BorderLayout.CENTER);
						contentPane.add(panel,BorderLayout.SOUTH);
					
					}
				}
			});
			
	}
	
	public static void main(String[] args) {
		new ExpensiveInfo();
	}
}
