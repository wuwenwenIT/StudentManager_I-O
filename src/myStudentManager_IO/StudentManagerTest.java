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
            System.out.println("-------------------��ӭ����ѧ������ϵͳ------------------------");
            System.out.println("------              1. �鿴����ѧ��                      -----");
            System.out.println("------              2. ���ѧ��                          -----");
            System.out.println("------              3. ɾ��ѧ��                          -----");
            System.out.println("------              4. �޸�ѧ��                          -----");
            System.out.println("------              5. �˳�                              -----");
            System.out.println("--------------------------------------------------------------");
            System.out.print("����������ѡ��");
            Scanner sc = new Scanner(System.in);
            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    //�鿴����ѧ��
                    findAllStudent(fileName);
                    System.out.println();
                    break;
                case "2":
                    //���ѧ��
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
                    // System.out.println("------              5. �˳�                              -----");
                    System.out.println("                     ��л����ʹ��                               ");
                    //flag = false;
                    System.exit(0);
                    break;
            }
        }
    }

    //IO�����������ļ�
    public static void readData(String fileName, ArrayList<Student> array) throws IOException {
        //�������뻺��������
        BufferedReader br = new BufferedReader(new FileReader(fileName));

        String line;
        while((line = br.readLine()) != null){
            String[] datas = line.split(",");
            Student s = new Student(datas[0],datas[1],datas[2],datas[3]);
            array.add(s);
        }
        br.close();
    }

    //IO�������д�ļ�
    public static void writeData(String fileName, ArrayList<Student> array) throws IOException {
        //�������뻺��������
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



    //�鿴����ѧ��
    public static void findAllStudent(String fileName) throws IOException {
        ArrayList<Student> array = new ArrayList<Student>();
        readData(fileName,array);

        if(array.size() == 0){
            System.out.println("��ѧ����Ϣ��������ѡ��");
            return;
        }
        System.out.println();
        System.out.println("ѧ��\t\t\t\t����\t\t\t\t����\t\t\t\t��ס��");
        System.out.println("-------------------------------------------------------------------------");
        for(int x = 0; x < array.size(); x++)
        {
            Student s = array.get(x);
            System.out.println(s.getId()+"\t\t\t\t" + s.getName() + "\t\t\t\t" + s.getAge() + "\t\t\t\t" + s.getAddress());
        }
        System.out.println("-------------------------------------------------------------------------");
    }

    //���ѧ��
    public static void addStudent(String fileName) throws IOException {
        ArrayList<Student> array = new ArrayList<Student>();
        readData(fileName,array);

        Scanner in = new Scanner(System.in);
        String id ="";

        while(true) {
            System.out.print("����ѧ�ţ�");
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
                System.out.println("ѧ�ű�ռ�ã�����������");
            }
            else{
                break;
            }

        }
        System.out.print("����������");
        String name = in.nextLine();
        System.out.print("�������䣺");
        String age = in.nextLine();
        System.out.print("�����ַ��");
        String address = in.nextLine();

        //����ѧ������
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
        System.out.println("���ѧ���ɹ�");
    }

    //ɾ��ѧ��
    public static void deleteStudent(String fileName) throws IOException {
        ArrayList<Student> array = new ArrayList<>();

        readData(fileName,array);
        //˼·������ѧ��ɾ�����ȱ���ѧ���б�鿴ѧ���Ƿ����
        Scanner in = new Scanner(System.in);
        System.out.print("����Ҫɾ��ѧ����ѧ�ţ�");
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
            System.out.print("��ѧ����Ϣ�����ڣ��Ƿ������ǰɾ��������Yes or No):");
            String choice = in.nextLine();
            if(choice.equalsIgnoreCase("Yes")){
                deleteStudent(fileName);
            }else if(choice.equalsIgnoreCase("No"))
            {
                return;
            }
            else{
                System.out.println("������Ϣ���󣬷���������");
                return;
            }
        }else
        {
            array.remove(index);
            writeData(fileName,array);
            System.out.println("ɾ��ѧ���ɹ�");
        }

    }

    //�޸�ѧ����Ϣ
    public static void updateStudent(String fileName) throws IOException {
        ArrayList<Student> array = new ArrayList<Student>();
        readData(fileName,array);

        //����ѧ�Ų���ѧ���Ƿ����
        Scanner in = new Scanner(System.in);
        System.out.print("����Ҫ����ѧ����ѧ�ţ�");
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
            System.out.print("��ѧ����Ϣ�����ڣ��Ƿ������ǰ���²�����Yes or No):");
            String choice = in.nextLine();
            if(choice.equalsIgnoreCase("Yes")){
                updateStudent(fileName);
            }else if(choice.equalsIgnoreCase("No"))
            {
                return;
            }
            else{
                System.out.println("������Ϣ���󣬷���������");
                return;
            }
        }else
        {
            Student s = array.get(index);
            boolean flag = true;
            System.out.println("-----ѡ�����ѧ����Ϣѡ��------");
            System.out.println("--      1. ����         --");
            System.out.println("--      2. ����         --");
            System.out.println("--      3. ��ַ         --");
            System.out.println("--      4. ����������    --");
            System.out.println("--------------------------");
            while(flag) {
                System.out.print("����������ѡ��");
                Scanner sc = new Scanner(System.in);
                String choice = sc.nextLine();
                switch (choice) {
                    case "1":
                        System.out.print("�����޸�������");
                        String name = in.nextLine();
                        s.setName(name);
                        System.out.print("�������²�����Yes or No):");
                        String a = in.nextLine();
                        if(a.equalsIgnoreCase("Yes"))
                            break;
                        else if(a.equalsIgnoreCase("No")){
                            array.set(index,s);
                            writeData(fileName,array);
                            System.out.println("���²�����ɣ�����������");
                            return;
                        }
                        else{
                            System.out.println("������Ϣ���󣬷���������");
                            return;
                        }
                    case "2":
                        System.out.print("�����޸����䣺");
                        String age = in.nextLine();
                        s.setAge(age);
                        System.out.print("�������²�����Yes or No):");
                        String b = in.nextLine();
                        if(b.equalsIgnoreCase("Yes"))
                            break;
                        else if(b.equalsIgnoreCase("No")){
                            array.set(index,s);
                            writeData(fileName,array);
                            System.out.println("���²�����ɣ�����������");
                            return;
                        }
                        else{
                            System.out.println("������Ϣ���󣬷���������");
                            return;
                        }

                    case "3":
                        System.out.print("�����޸ĵ�ַ��");
                        String address = in.nextLine();
                        s.setAddress(address);
                        System.out.print("�������²�����Yes or No):");
                        String c = in.nextLine();
                        if(c.equalsIgnoreCase("Yes"))
                            break;
                        else if(c.equalsIgnoreCase("No")){
                            array.set(index,s);
                            writeData(fileName,array);
                            System.out.println("���²�����ɣ�����������");
                            return;
                        }
                        else{
                            System.out.println("������Ϣ���󣬷���������");
                            return;
                        }
                    case "4":
                        array.set(index,s);
                        writeData(fileName,array);
                        System.out.println("�޸���Ϣ��ɡ�");
                        return;
                    default:
                        System.out.println("������Ϣ���󡣷���������");
                        return;
                }
            }
        }
    }
}
