package third;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Product> Products = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int option;
        float sum = 0;

        do{
            System.out.println("1)Add Product");
            System.out.println("2)Sum price");
            System.out.println("3)print products");
            System.out.println("0)Exit");

            System.out.print("Enter Option : ");
            option = Integer.parseInt(sc.nextLine());

            System.out.println("-----------------------------------------------");

            switch (option) {
                case 1:     //Add
                    Product product =new Product();
                    System.out.print("Enter Name : ");
                    product.name =sc.nextLine();
                    System.out.println("Enter price :");
                    product.price= Float.parseFloat(sc.nextLine());
                    Products.add(product);
                    break;

                case 2:     //sum
                    for(int i = 0; i<Products.size();i++){
                        sum+=Products.get(i).price;}
                    System.out.println("sum of price : " + sum);
                    break;

                case 3:     //print
                    System.out.println("print :  " );
                    for( int i=0;i<Products.size();i++){
                        System.out.println(Products.get(i).name+ "   "+Products.get(i).price);
                    }

                case 0:
                    System.out.println("Exit");
                    break;
                default:
                    System.out.println("Invalid Option !!!");
            }

            System.out.println("-----------------------------------------------");
        }while (option !=0);

    }
}
