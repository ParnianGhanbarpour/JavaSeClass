package first;

public class BMI {
    public static void main(String[] args) {
        float height= 1.65F;
        float weight=53;
        System.out.println("Height : " + height);
        System.out.println("weight : " + weight);

        float bmi =weight/(height*height);
        System.out.println("BMI : " + bmi);

    }
}
