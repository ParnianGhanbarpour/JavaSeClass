package second;

public class FibonacciDiv {
    public static void main(String[] args) {
        double a=0 ,b=1 ;
        for(int i=1;i<=50;i++){
            System.out.println("fibonacci  : " + a + "  taghsim : "+ a/b);
            double c=a+b;
            a=b;
            b=c;

        }
    }

}
