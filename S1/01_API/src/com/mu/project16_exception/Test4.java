package src.com.mu.project16_exception;

public class Test4 {
    public static void main(String[] args) {

        try {
            int x = Integer.parseInt(args[0]);
            int y = Integer.parseInt(args[1]);

            int result = x/y;
            System.out.println("result = " + result);

        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        } catch (Exception e) { // 父类异常放到最后
            e.printStackTrace();
        }
    }
}
