package src.com.mu.project16_exception;

public class Test5 {
    public static void main(String[] args) {
        int x = 5;
        int y = 0;
        try {
            int result = x/y;
            System.out.println("result = " + result);
        } catch (ArithmeticException e){
            e.printStackTrace();
        } finally {
            System.out.println("bye bye...");
        }
    }
}
