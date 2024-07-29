package second;

public class Fibonacci {
    public static void main(String[] args) {
        int a=0, b=1,c , i ;
        for (i=1;i<=40;i++){
            if (i>=20)
                System.out.println(a);
            c=a+b;
            a=b;
            b=c;
        }
    }
}
