package myStudentManager_IO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;



public class StudentManagerTest {
	public static void main(String[] args) throws IOException {
        String fileName = "Students.txt";

        while(true) {
            System.out.println("-------------------欢迎来到学生管理系统------------------------");
            System.out.println("------              1. 查看所有学生                      -----");
            System.out.println("------              2. 添加学生                          -----");
            System.out.println("------              3. 删除学生                          -----");
            System.out.println("------              4. 修改学生                          -----");
            System.out.println("------              5. 退出                              -----");
            System.out.println("--------------------------------------------------------------");
            System.out.print("请输入您的选择：");
            Scanner sc = new Scanner(System.in);
            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    //查看所有学生
                    findAllStudent(fileName);
                    System.out.println();
                    break;
                case "2":
                    //添加学生
                    addStudent(fileName);
                    System.out.println();
                    break;
                case "3":
                    deleteStudent(fileName);
                    System.out.println();
                    break;
                case "4":
                    updateStudent(fileName);
                    System.out.println();
                    break;
                case "5":

                default:
                    // System.out.println("------              5. 退出                              -----");
                    System.out.println("                     感谢您的使用                               ");
                    //flag = false;
                    System.exit(0);
                    break;
            }
        }
    }

    //IO输入流：读文件
    public static void readData(String fileName, ArrayList<Student> array) throws IOException {
        //创建输入缓冲流对象
        BufferedReader br = new BufferedReader(new FileReader(fileName));

        String line;
        while((line = br.readLine()) != null){
            String[] datas = line.split(",");
            Student s = new Student(datas[0],datas[1],datas[2],datas[3]);
            array.add(s);
        }
        br.close();
    }

    //IO输出流：写文件
    public static void writeData(String fileName, ArrayList<Student> array) throws IOException {
        //创建输入缓冲流对象
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));

        for(int x = 0; x < array.size(); x++)
        {
            Student s = array.get(x);
            StringBuilder sb = new StringBuilder();
            sb.append(s.getId()).append(",").append(s.getName()).append(",").append(s.getAge()).append(",").append(s.getAddress());

            bw.write(sb.toString());
            bw.newLine();
            bw.flush();
        }

        bw.close();
    }



    //查看所有学生
    public static void findAllStudent(String fileName) throws IOException {
        ArrayList<Student> array = new ArrayList<Student>();
        readData(fileName,array);

        if(array.size() == 0){
            System.out.println("无学生信息，请重新选择");
            return;
        }
        System.out.println();
        System.out.println("学号\t\t\t\t姓名\t\t\t\t年龄\t\t\t\t居住地");
        System.out.println("-------------------------------------------------------------------------");
        for(int x = 0; x < array.size(); x++)
        {
            Student s = array.get(x);
            System.out.println(s.getId()+"\t\t\t\t" + s.getName() + "\t\t\t\t" + s.getAge() + "\t\t\t\t" + s.getAddress());
        }
        System.out.println("-------------------------------------------------------------------------");
    }

    //添加学生
    public static void addStudent(String fileName) throws IOException {
        ArrayList<Student> array = new ArrayList<Student>();
        readData(fileName,array);

        Scanner in = new Scanner(System.in);
        String id ="";

        while(true) {
            System.out.print("输入学号：");
            id = in.nextLine();
            int x;
            boolean flag = false;
            for(x = 0; x < array.size(); x++ )
            {
                Student s = array.get(x);
                if(id.equals(s.getId())) {
                    flag = true;
                    break;
                }
            }
            if(flag){
                System.out.println("学号被占用，请重新输入");
            }
            else{
                break;
            }

        }
        System.out.print("输入姓名：");
        String name = in.nextLine();
        System.out.print("输入年龄：");
        String age = in.nextLine();
        System.out.print("输入地址：");
        String address = in.nextLine();

        //创建学生对象
        Student s = new Student(id,name,age,address);
        /*
        Student s = new Student();
        s.setId(id);
        s.setName(name);
        s.setAge(age);
        s.setAddress(address);
        */
        array.add(s);
        writeData(fileName, array);
        System.out.println("添加学生成功");
    }

    //删除学生
    public static void deleteStudent(String fileName) throws IOException {
        ArrayList<Student> array = new ArrayList<>();

        readData(fileName,array);
        //思路：根据学号删除。先遍历学生列表查看学生是否存在
        Scanner in = new Scanner(System.in);
        System.out.print("输入要删除学生的学号：");
        String id = in.nextLine();

        int index = -1;
        for(int x = 0; x < array.size(); x++){
            Student s = array.get(x);
            if(id.equals(s.getId())){
                index = x;
                break;
            }
        }
        if(index == -1){
            System.out.print("该学生信息不存在，是否继续当前删除操作（Yes or No):");
            String choice = in.nextLine();
            if(choice.equalsIgnoreCase("Yes")){
                deleteStudent(fileName);
            }else if(choice.equalsIgnoreCase("No"))
            {
                return;
            }
            else{
                System.out.println("输入信息有误，返回主界面");
                return;
            }
        }else
        {
            array.remove(index);
            writeData(fileName,array);
            System.out.println("删除学生成功");
        }

    }

    //修改学生信息
    public static void updateStudent(String fileName) throws IOException {
        ArrayList<Student> array = new ArrayList<Student>();
        readData(fileName,array);

        //输入学号查找学生是否存在
        Scanner in = new Scanner(System.in);
        System.out.print("输入要更新学生的学号：");
        String id = in.nextLine();

        int index = -1;
        for(int x = 0; x < array.size(); x++){
            Student s = array.get(x);
            if(id.equals(s.getId())){
                index = x;
                break;
            }
        }
        if(index == -1){
            System.out.print("该学生信息不存在，是否继续当前更新操作（Yes or No):");
            String choice = in.nextLine();
            if(choice.equalsIgnoreCase("Yes")){
                updateStudent(fileName);
            }else if(choice.equalsIgnoreCase("No"))
            {
                return;
            }
            else{
                System.out.println("输入信息有误，返回主界面");
                return;
            }
        }else
        {
            Student s = array.get(index);
            boolean flag = true;
            System.out.println("-----选择更新学生信息选项------");
            System.out.println("--      1. 姓名         --");
            System.out.println("--      2. 年龄         --");
            System.out.println("--      3. 地址         --");
            System.out.println("--      4. 返回主界面    --");
            System.out.println("--------------------------");
            while(flag) {
                System.out.print("请输入您的选择：");
                Scanner sc = new Scanner(System.in);
                String choice = sc.nextLine();
                switch (choice) {
                    case "1":
                        System.out.print("输入修改姓名：");
                        String name = in.nextLine();
                        s.setName(name);
                        System.out.print("继续更新操作（Yes or No):");
                        String a = in.nextLine();
                        if(a.equalsIgnoreCase("Yes"))
                            break;
                        else if(a.equalsIgnoreCase("No")){
                            array.set(index,s);
                            writeData(fileName,array);
                            System.out.println("更新操作完成，返回主界面");
                            return;
                        }
                        else{
                            System.out.println("输入信息有误，返回主界面");
                            return;
                        }
                    case "2":
                        System.out.print("输入修改年龄：");
                        String age = in.nextLine();
                        s.setAge(age);
                        System.out.print("继续更新操作（Yes or No):");
                        String b = in.nextLine();
                        if(b.equalsIgnoreCase("Yes"))
                            break;
                        else if(b.equalsIgnoreCase("No")){
                            array.set(index,s);
                            writeData(fileName,array);
                            System.out.println("更新操作完成，返回主界面");
                            return;
                        }
                        else{
                            System.out.println("输入信息有误，返回主界面");
                            return;
                        }

                    case "3":
                        System.out.print("输入修改地址：");
                        String address = in.nextLine();
                        s.setAddress(address);
                        System.out.print("继续更新操作（Yes or No):");
                        String c = in.nextLine();
                        if(c.equalsIgnoreCase("Yes"))
                            break;
                        else if(c.equalsIgnoreCase("No")){
                            array.set(index,s);
                            writeData(fileName,array);
                            System.out.println("更新操作完成，返回主界面");
                            return;
                        }
                        else{
                            System.out.println("输入信息有误，返回主界面");
                            return;
                        }
                    case "4":
                        array.set(index,s);
                        writeData(fileName,array);
                        System.out.println("修改信息完成。");
                        return;
                    default:
                        System.out.println("输入信息有误。返回主界面");
                        return;
                }
            }
        }
    }
}
