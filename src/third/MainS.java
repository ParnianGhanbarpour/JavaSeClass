package third;

import java.util.ArrayList;
import java.util.Scanner;

public class MainS {
    public static void main(String[] args) {
        ArrayList<Student> Students = new ArrayList<Student>();
        Scanner sc = new Scanner(System.in);
        int option;
        float sum = 0F;

        do{
            System.out.println("1)Add student");
            System.out.println("2)print age average");
            System.out.println("3)count");
            System.out.println("4)print all");
            System.out.println("5)Exit");
            System.out.print("Enter Option : ");
            option = Integer.parseInt(sc.nextLine());

            System.out.println("-----------------------------------------------");

            switch (option) {
                case 1:     //Add
                    Student student =new Student();
                    System.out.print("Enter Name : ");
                    student.name =sc.nextLine();
                    System.out.println("Enter AGE :");
                    student.age= Integer.parseInt(sc.nextLine());
                    Students.add(student);

                    break;

                case 2:
                    for(int i = 0 ; i < Students.size(); i++)
                        sum+=Students.get(i).age;

                    System.out.println("Average : " + sum/Students.size());
                    break;

                case 3:
                    System.out.println("counter : " + Students.size());
                    break;

                case 4:
                    System.out.println("print :  " );
                    for( int i=0;i<Students.size();i++){
                        System.out.println(Students.get(i).name+ "   "+Students.get(i).age);
                    }
                    break;

                case 5:
                    System.out.println("Exit");
                    break;

                default:
                    System.out.println("Invalid Option !");
            }

            System.out.println("-----------------------------------------------");
        }while (option !=0);
    }
}
