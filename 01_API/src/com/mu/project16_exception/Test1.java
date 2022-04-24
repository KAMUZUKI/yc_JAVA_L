package src.com.mu.project16_exception;

import java.util.Scanner;

public class Test1 {
    public static void main(String[] args) {
        int x,y;

        Scanner sc = new Scanner(System.in);
        System.out.println("please input x y value");
        x = sc.nextInt();
        y = sc.nextInt();

        try{
            int aver = x/y;
        } catch (ArithmeticException ex){
            ex.printStackTrace();
            System.out.println("please confirm input");
        }

        System.out.println("before code run");


    }
}
