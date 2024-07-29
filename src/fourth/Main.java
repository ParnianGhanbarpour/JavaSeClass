package fourth;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);

        Kitchen kitchen = new Kitchen( 2100,true);
        BedRoom bedRoom = new BedRoom(12);
        BathRoom bathRoom = new BathRoom(4,"black");
        ReceptionRoom receptionRoom =new ReceptionRoom(12,"Tv");
        Refrigerator refrigerator = new Refrigerator(2025,false,8);

        System.out.print(" there are spoiledFood in Refrigerator : ");
        System.out.println("It's" + refrigerator.isSpoiledFood());

        System.out.println("--------------------------------------------------");

        System.out.print("sum of bedroom and bathroom LED ");
        System.out.println(bedRoom.getLedLight()+bathRoom.getLedLight());

        System.out.println("--------------------------------------------------");

        //?????
        System.out.print("Enter Refrigerator height : ");
        float h = Float.parseFloat(String.valueOf(refrigerator.getHeight()));
        System.out.println(h + " meter");




    }
}
