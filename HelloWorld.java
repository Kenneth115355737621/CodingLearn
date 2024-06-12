public class HelloWorld {
    public static void main(String[] args) {
        int number = exponential(5, 5);
        System.out.println(number);
    }
    public static int exponential(int base, int num){
        int rNum = base;
        for (int i = 1; i < num; i++){
            rNum = rNum*base;
        }
        return rNum;
    }

}