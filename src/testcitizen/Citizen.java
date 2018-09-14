package testcitizen;

public class Citizen {

	private String id;
	private String hosu;
	private String name;
	private String phone;
	private String member;
	private String email;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getHosu() {
		return hosu;
	}
	public void setHosu(String hosu) {
		this.hosu = hosu;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMember() {
		return member;
	}
	public void setMember(String member) {
		this.member = member;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "citizen [id=" + id + ", name=" + name + ", + phone=" + phone +",member=" + member +" + email="+email +"]";

	}
	
}
