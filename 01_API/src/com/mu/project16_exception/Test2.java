package src.com.mu.project16_exception;

public class Test2 {
    public static void main(String[] args) {
        int x = 0;

        try {
            String s = args[1];
            System.out.println("s = " + s );
            x = Integer.parseInt(s);
        } catch (ArrayIndexOutOfBoundsException ex){
            ex.printStackTrace();
        }

        System.out.println("x add 2 = " + (x+2));
    }
}
