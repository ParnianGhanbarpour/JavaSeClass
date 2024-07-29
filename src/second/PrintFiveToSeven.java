package second;
//اگر به صورت صعودی چاپ کن a<b
//   اگز غیر این بود نزولی چاپ کن (یکسان باشن یه بار  چاپ میکنه عدد رو)
public class PrintFiveToSeven {
    public static void main(String[] args) {
        int a = 17;
        int b = 5;
        if (a<b) {
            for (int i=a;i<=b;i++) {
                System.out.print(i + " ");
            }
        } else {
            for (int i = a; i >= b; i--) {
                System.out.print(i + " ");
            }
        }
    }
}
