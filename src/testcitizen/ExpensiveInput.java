package testcitizen;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;


public class ExpensiveInput extends JFrame {
	
	JButton inputBtn = new JButton("입력");
	JButton exitBtn = new JButton("취소");
	
	private JTextField textMonth;
	private JTextField textHosu;
	private JTextField textElectricity;
	private JTextField textWater;
	private JTextField textGas;
	private JTextField textGuardMoney;

	ExpensiveInput(){
		
		this.formDesign();
		this.eventHandler();
		this.setSize(600,333);
		this.setVisible(true);
	}

	public void formDesign() {
		
		this.setTitle("주민괸리 프로그램 v1.0 - 관리비 입력");
		getContentPane().setLayout(null);
		inputBtn.setBounds(127, 254, 120, 30);
		getContentPane().add(inputBtn);
		exitBtn.setBounds(259, 254, 120, 30);
		getContentPane().add(exitBtn);
		
		JLabel month = new JLabel("월");
		month.setBounds(111, 30, 57, 15);
		getContentPane().add(month);
		
		textMonth = new JTextField();
		textMonth.setBounds(195, 27, 116, 21);
		getContentPane().add(textMonth);
		textMonth.setColumns(10);
		
		JLabel Hosu = new JLabel("호수");
		Hosu.setBounds(111, 62, 57, 15);
		getContentPane().add(Hosu);
		
		textHosu = new JTextField();
		textHosu.setBounds(195, 58, 116, 21);
		getContentPane().add(textHosu);
		textHosu.setColumns(10);
		
		JLabel Electricity  = new JLabel("전기세");
		Electricity .setBounds(111, 98, 57, 15);
		getContentPane().add(Electricity );
		
		textElectricity = new JTextField();
		textElectricity.setBounds(195, 95, 116, 21);
		getContentPane().add(textElectricity);
		textElectricity.setColumns(10);
		
		JLabel Water = new JLabel("수도세");
		Water .setBounds(111, 134, 57, 15);
		getContentPane().add(Water);
		
		textWater = new JTextField();
		textWater.setBounds(195, 131, 116, 21);
		getContentPane().add(textWater);
		textWater.setColumns(10);
		
		JLabel Gas = new JLabel("가스세");
		Gas.setBounds(111, 168, 57, 15);
		getContentPane().add(Gas);
		
		textGas = new JTextField();
		textGas.setBounds(195, 165, 116, 21);
		getContentPane().add(textGas);
		textGas.setColumns(10);
		
		JLabel guardMoney = new JLabel("경비비");
		guardMoney.setBounds(111, 203, 57, 15);
		getContentPane().add(guardMoney);
		
		textGuardMoney = new JTextField();
		textGuardMoney.setBounds(195, 197, 116, 21);
		getContentPane().add(textGuardMoney);
		textGuardMoney.setColumns(10);
		
	}
	
	public void eventHandler() {
	
		inputBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String month = textMonth.getText();
				String hosu = textHosu.getText();
				int electricity = Integer.parseInt(textElectricity.getText());
				int water = Integer.parseInt(textWater.getText());
				int gas = Integer.parseInt(textGas.getText());
				int guardMoney = Integer.parseInt(textGuardMoney.getText());

				
				Expensive expensive = new Expensive();
				
				expensive.setMonth(month);
				expensive.setHosu(hosu);
				expensive.setElectricity(electricity);
				expensive.setWater(water);
				expensive.setGas(gas);
				expensive.setguardMoney(guardMoney);
				
				if(month.trim().length() >0 && hosu.trim().length() >0 
						&& textElectricity.getText().trim().length() >0
						&& textWater.getText().trim().length() >0 && textGas.getText().trim().length() >0
						&& textGuardMoney.getText().trim().length() >0) {
				
					SqlTest sql = new SqlTest();
					SqlTest.Einput(expensive);
					
					//잘 입력 되었다고 넣고, 메인 화면으로 돌아가기
					
				}else {
					
					JOptionPane.showMessageDialog(null, "경고", "공백이 있습니다.", JOptionPane.WARNING_MESSAGE);
				}
			}
			
		});
	}
	
	public static void main(String[] args) {
		new ExpensiveInput();
	}
}
