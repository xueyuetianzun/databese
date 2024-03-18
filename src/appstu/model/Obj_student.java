package appstu.model;

public class Obj_student {
	private String stuid; // 定义学生信息编号变量
	private String classID; // 定义班级编号变量
	private String stuname; // 定义学生姓名变量
	private String sex; // 定义学生性别变量
	private int age; // 定义学生年龄变量
	private String address; // 定义学生地址变量
	private String phone; // 定义学生电话变量

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
