package Model;

public class Student implements Comparable<Student> {
    private String id;
    private String fullName;
    private int gender;
    private String birthday;
    private String address;
    private String phone;
    private String email;
    private double gpa;

    public Student() {
    }

    public Student(String fullName, int gender, String birthday, String address, String phone, String email, double gpa) {
        if (gpa <= 0) {
            throw new RuntimeException("Điểm không được âm");
        }
        this.fullName = fullName;
        this.gender = gender;
        this.birthday = birthday;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.gpa = gpa;
    }

    public Student(String id, String fullName, int gender, String birthday, String address, String phone, String email, double gpa) {
        if (gpa <= 0) {
            throw new RuntimeException("Điểm không được âm");
        }
        this.id = id;
        this.fullName = fullName;
        this.gender = gender;
        this.birthday = birthday;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.gpa = gpa;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        if (gpa <= 0) {
            throw new RuntimeException("Điểm không được âm");
        }
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return "Student[" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", gender=" + gender +
                ", birthday='" + birthday + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", gpa=" + gpa +
                ']';
    }

    @Override
    public int compareTo(Student o) {
        if (this.gpa > o.gpa) {
            return 1;
        } else if (this.gpa < o.gpa) {
            return -1;
        }
        return 0;
    }
}
