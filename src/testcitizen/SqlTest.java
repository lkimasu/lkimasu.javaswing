package testcitizen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SqlTest {
	
	private static Connection conn;
	private static final String USERNAME = "root";
	private static final String PASSWORD = "wlstjd12";
	private static final String URL = "jdbc:mysql://localhost:3306/swtestdb?serverTimezone=UTC&&useSSL=false";	
	
	public SqlTest() {
	
        try {
        	
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL , USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } 
	}
	
	//주민 회원 가입
	public static void insert(Citizen citizen) {
		
		String sql = "insert into citizeninput(Hosu,Name,Phone,Member,Email) values(?,?,?,?,?);";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, citizen.getHosu());
			pstmt.setString(2, citizen.getName());
			pstmt.setString(3,citizen.getPhone());
			pstmt.setString(4,citizen.getMember());
			pstmt.setString(5, citizen.getEmail());
			pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt !=null && !pstmt.isClosed()) {
					pstmt.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//주민정보 전체조회
	public static List<Citizen> CitizenSearchAll() {
		String sql = "select * from citizeninput;";
		PreparedStatement pstmt = null;
		
		List<Citizen> list = new ArrayList<Citizen>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet re = pstmt.executeQuery();
			
			while(re.next()) {
				
				Citizen c = new Citizen();
				c.setId(re.getString("id"));
				c.setHosu(re.getString("Hosu"));
				c.setName(re.getString("Name"));
				c.setPhone(re.getString("Phone"));
				c.setMember(re.getString("Member"));
				c.setEmail(re.getString("Email"));
				list.add(c);
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			
			try {
				if(pstmt !=null && !pstmt.isClosed()) {
					pstmt.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	//호수 검색
	public static List<Citizen> searchHouscitizen(String shosu) {
		
		String sql = "select * from citizeninput where Hosu LIKE ?";
		PreparedStatement pstmt = null;
		List<Citizen> list = new ArrayList<Citizen>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + shosu + "%");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Citizen c1 = new Citizen();
				c1.setId(rs.getString("Id"));
				c1.setHosu(rs.getString("Hosu"));
				c1.setName(rs.getString("Name"));
				c1.setPhone(rs.getString("Phone"));
				c1.setMember(rs.getString("Member"));
				c1.setEmail(rs.getString("Email"));
				list.add(c1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt !=null && !pstmt.isClosed()) {
					pstmt.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
		
	}
	
	//주민 정보 수정
	public static void updateCitizen(Citizen citizen) {
		String sql = "update citizeninput set Name=?,Phone=?,Member=?,Email=? where Hosu=?;";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, citizen.getName());
			pstmt.setString(2, citizen.getPhone());
			pstmt.setString(3, citizen.getMember());
			pstmt.setString(4, citizen.getEmail());
			pstmt.setString(5, citizen.getHosu());
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt !=null && !pstmt.isClosed()) {
					pstmt.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//주민 정보 제거
	public static void delCitizen(String hosu) {
		String sql = "delete from citizeninput where Hosu=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, hosu);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt !=null && !pstmt.isClosed()) {
					pstmt.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//관리비 입력
	public static void Einput(Expensive expensive) {
			
		String sql = "insert into expensiveinput(Month,Hosu,Electricity,Water,Gas,guardMoney,sum) values (?,?,?,?,?,?,?);";
		PreparedStatement pstmt = null;
			
		try {
				
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, expensive.getMonth());
			pstmt.setString(2, expensive.getHosu());
			pstmt.setInt(3,expensive.getElectricity());
			pstmt.setInt(4,expensive.getWater());
			pstmt.setInt(5, expensive.getGas());
			pstmt.setInt(6,expensive.getguardMoney());
			pstmt.setInt(7, expensive.getElectricity()+expensive.getWater()+expensive.getGas()+expensive.getguardMoney());
			pstmt.executeUpdate();
				
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				
				if(pstmt !=null && !pstmt.isClosed()) {
					pstmt.close();
				}
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//전체 관리비 출력
	public static List<Expensive> ExpensiveSearchAll() {
		String sql = "select * from expensiveinput;";
		PreparedStatement pstmt = null;
		
		List<Expensive> list = new ArrayList<Expensive>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet re = pstmt.executeQuery();
			
			while(re.next()) {
				
				Expensive c = new Expensive();
				c.setId(re.getString("Id"));
				c.setMonth(re.getString("Month"));
				c.setHosu(re.getString("Hosu"));
				c.setElectricity(re.getInt("Electricity"));
				c.setWater(re.getInt("Water"));
				c.setGas(re.getInt("Gas"));
				c.setguardMoney(re.getInt("guardMoney"));
				c.setsum(re.getInt("Electricity")+re.getInt("Water")+re.getInt("guardMoney")+re.getInt("Gas"));
				list.add(c);
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			
			try {
				if(pstmt !=null && !pstmt.isClosed()) {
					pstmt.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	//관리비 수정
	public static void updateExpensive(Expensive expensive) {
		String sql = "update expensiveinput set Month=?,Electricity=?,Water=?,Gas=?,guardMoney=? where Hosu=?;";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, expensive.getMonth());
			pstmt.setInt(2, expensive.getElectricity());
			pstmt.setInt(3, expensive.getWater());
			pstmt.setInt(4,expensive.getGas());
			pstmt.setInt(5, expensive.getguardMoney());
			pstmt.setString(6, expensive.getHosu());
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt !=null && !pstmt.isClosed()) {
					pstmt.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void delExpensive(String hosu) {
		
		String sql = "delete from expensiveinput where Hosu=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, hosu);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt !=null && !pstmt.isClosed()) {
					pstmt.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	//월별 관리비 검색
	public static List<Expensive> searchHosuExpensive(String smonth) {
		
		String sql = "select * from expensiveinput where Month like ?";
		PreparedStatement pstmt = null;
		List<Expensive> list = new ArrayList<Expensive>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+smonth+"%");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				Expensive c2 = new Expensive();
				c2.setId(rs.getString("Id"));
				c2.setMonth(rs.getString("Month"));
				c2.setHosu(rs.getString("Hosu"));
				c2.setElectricity(rs.getInt("Electricity"));
				c2.setWater(rs.getInt("Water"));
				c2.setGas(rs.getInt("Gas"));
				c2.setguardMoney(rs.getInt("guardMoney"));
				c2.setsum(rs.getInt("Electricity")+rs.getInt("Water")+rs.getInt("guardMoney")+rs.getInt("Gas"));
				list.add(c2);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt !=null && !pstmt.isClosed()) {
					pstmt.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
		
			}
}
