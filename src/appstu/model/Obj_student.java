package appstu.model;

public class Obj_student {
	private String stuid; // ����ѧ����Ϣ��ű���
	private String classID; // ����༶��ű���
	private String stuname; // ����ѧ����������
	private String sex; // ����ѧ���Ա����
	private int age; // ����ѧ���������
	private String address; // ����ѧ����ַ����
	private String phone; // ����ѧ���绰����

	public String getStuid() {
		return stuid;
	}

	public String getClassID() {
		return classID;
	}

	public String getStuname() {
		return stuname;
	}

	public String getSex() {
		return sex;
	}

	public int getAge() {
		return age;
	}

	public String getAddress() {
		return address;
	}

	public String getPhone() {
		return phone;
	}

	public void setStuid(String stuid) {
		this.stuid = stuid;
	}

	public void setClassID(String classID) {
		this.classID = classID;
	}

	public void setStuname(String stuname) {
		this.stuname = stuname;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
