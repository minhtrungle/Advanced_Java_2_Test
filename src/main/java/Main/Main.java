package Main;


import Dao.StudentDAO;
import Model.Student;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static StudentDAO studentDAO = new StudentDAO();
    private static Student s = new Student();
    private static void Menu() {
        System.out.println("--- Student Management ---");
        System.out.println("1. Danh sách sinh viên theo bảng");
        System.out.println("2. Nhập một sinh viên mới");
        System.out.println("3. Xóa một sinh viên theo mã");
        System.out.println("4. Cập nhật thông tin sinh viên");
        System.out.println("5. Tìm một sinh viên theo họ tên hoặc mã");
        System.out.println("6. Sắp xếp sinh viên theo điểm số GPA tăng dần");
        System.out.println("7. In ra tất cả sinh viên nữ ở Hà Nội có GPA trên 2.5");
        System.out.println("8. Sắp xếp sinh viên theo họ tên, sắp xếp theo bảng chữ cái abc");
    }

    //Các chức năng để thực hiện
    private static void function1() throws SQLException {
        List<Student> studentList = studentDAO.getAllStudent();
        System.out.printf("%-20s %-20s %-20s %-20s", "Mã sinh viên", "Họ tên", "Giới tính", "Địa chỉ");
        System.out.println();
        for (int i = 0; i < studentList.size(); i++) {
            Student s = studentList.get(i);
            System.out.printf("%-20s %-20s %-20d %-20s\n", s.getId(), s.getFullName(), s.getGender(), s.getAddress());
        };
    }

    private static void function2(Scanner in) throws SQLException {
        System.out.println("Nhập mã sinh viên: ");
        s.setId(in.nextLine());

        System.out.println("Nhập tên sinh viên: ");
        String stdName = in.nextLine();
        s.setFullName(stdName);

        System.out.println("Nhập giới tính 1: nam, 0: nữ: ");
        int stdGender = Integer.parseInt(in.nextLine());
        s.setGender(stdGender);

        System.out.println("Nhập ngày sinh sinh viên: ");
        String stdBirthday = in.nextLine();
        s.setBirthday(stdBirthday);

        System.out.println("Nhập địa chỉ sinh viên: ");
        String stdAddress = in.nextLine();
        s.setAddress(stdAddress);

        System.out.println("Nhập số điện thoại sinh viên: ");
        String stdPhone = in.nextLine();
        s.setPhone(stdPhone);

        System.out.println("Nhập email sinh viên: ");
        String stdEmail = in.nextLine();
        s.setEmail(stdEmail);

        System.out.println("Nhập điểm GPA sinh viên: ");
        double stdGPA = Double.parseDouble(in.nextLine());
        s.setGpa(stdGPA);

        studentDAO.insertStudent(s);
    }

    private static void function3(Scanner in) throws SQLException {
        System.out.println("Nhập mã sinh viên muốn xóa: ");
        String id = in.nextLine();


        studentDAO.deleteStudent(id);
    }

    private static void function4(Scanner in) throws SQLException {
        System.out.println("Nhập mã muốn cập nhật: ");
        String stdId = in.nextLine();
        s.setFullName(stdId);

        System.out.println("Nhập tên cập nhật: ");
        String stdName = in.nextLine();
        s.setFullName(stdName);

        System.out.println("Nhập giới tính 1: nam, 0: nữ: ");
        int stdGender = Integer.parseInt(in.nextLine());
        s.setGender(stdGender);

        System.out.println("Nhập ngày sinh cập nhật: ");
        String stdBirthday = in.nextLine();
        s.setBirthday(stdBirthday);

        System.out.println("Nhập địa chỉ cập nhật: ");
        String stdAddress = in.nextLine();
        s.setAddress(stdAddress);

        System.out.println("Nhập số điện thoại cập nhật: ");
        String stdPhone = in.nextLine();
        s.setPhone(stdPhone);

        System.out.println("Nhập email cập nhật: ");
        String stdEmail = in.nextLine();
        s.setEmail(stdEmail);

        System.out.println("Nhập điểm GPA cập nhật: ");
        double stdGPA = Double.parseDouble(in.nextLine());
        s.setGpa(stdGPA);

        studentDAO.updateStudent(s, stdId);
    }

    private static void function5(Scanner in) throws SQLException {
        System.out.println("Nhập mã sinh viên muốn tìm: ");
        String stdId = in.nextLine();
        s.setId(stdId);

        studentDAO.getByID(stdId);

        System.out.println("Nhập tên sinh viên muốn tìm: ");
        String stdName = in.nextLine();
        s.setFullName(stdName);

        studentDAO.getByName(stdName);

    }

    private static void function6() throws SQLException {
        List<Student> studentList = studentDAO.getAllStudent();
        studentList.stream()
                .sorted((o1, o2) -> {
                    if (o1.getGpa() > o2.getGpa()) {
                        return 1;
                    } else if (o1.getGpa() < o2.getGpa()) {
                        return -1;
                    }
                    return 0;
                })
                .forEach(student -> System.out.println(student));
    }

    private static void function7() throws SQLException {
        List<Student> studentList = studentDAO.getAllStudent();
        studentList.stream()
                .filter(student -> student.getGender() == 0 && student.getAddress() == "Hà Nội" && student.getGpa() > 2.5)
                .sorted((o1, o2) -> {
                    if (o1.getGpa() > o2.getGpa()) {
                        return 1;
                    } else if (o1.getGpa() < o2.getGpa()) {
                        return -1;
                    }
                    return 0;
                })
                .forEach(student -> System.out.println(student));
    }

    private static void function8() throws SQLException {
        List<Student> studentList = studentDAO.getAllStudent();
        studentList.stream()
                .sorted((o1, o2) -> o1.getFullName().compareTo(o2.getFullName()))
                .forEach(student -> System.out.println(student));
    }
    //Thực hiện
    public static void main(String[] args) throws SQLException {
        Scanner in = new Scanner(System.in);
        int index = -1;
        do {
            Menu();
            System.out.println("Chọn chức năng [1-8]");
            try {
                index = Integer.parseInt(in.nextLine());
            } catch (Exception e) {
                System.out.println("Không có chức năng!");
            }
            if (index < 1 || index > 10) {
                System.out.println("Không có chức năng!");
                continue;
            }
            switch (index) {
                case 1:
                    System.out.println("1. Danh sách sinh viên theo bảng");
                    function1();
                    break;
                case 2:
                    System.out.println("2. Nhập một sinh viên mới");
                    function2(in);
                    break;
                case 3:
                    System.out.println("3. Xóa một sinh viên theo mã");
                    function3(in);
                    break;
                case 4:
                    System.out.println("4. Cập nhật thông tin sinh viên");
                    function4(in);
                    break;
                case 5:
                    System.out.println("5. Tìm một sinh viên theo họ tên hoặc mã");
                    function5(in);
                    break;
                case 6:
                    System.out.println("6. Sắp xếp sinh viên theo điểm số GPA tăng dần");
                    function6();
                    break;
                case 7:
                    System.out.println("7. In ra tất cả sinh viên nữ ở Hà Nội có GPA trên 2.5");
                    function7();
                    break;
                case 8:
                    System.out.println("8. Sắp xếp sinh viên theo họ tên");
                    function8();
                    break;
                default:
                    System.out.println("Exit");
                    break;
            }
        } while (index != 0);
    }
}
