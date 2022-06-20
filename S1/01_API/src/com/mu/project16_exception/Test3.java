package src.com.mu.project16_exception;

public class Test3 {
    public static void main(String[] args) {
        if (args == null || args.length <= 0) {
            System.out.println("没有合适的运算用参数");
            System.exit(0);
        }
        int sum = 0;
        int sum2 = 0;

        for (int i = 0; i < args.length; i++) {
            try {
                sum2 += Integer.parseInt(args[i]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        System.out.println("sum2 = " + sum2);
        try {
            for (String s : args) {
                sum += Integer.parseInt(s);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }


        System.out.println("sum = " + sum);
    }
   /*
    if (args == null || args.length <= 0) {
        System.out.println("没有合适的运算用参数");
        System.exit(0);
    }
    long sum = 0;
    long sum2 = 0;
    long start1 = System.currentTimeMillis();
        for (int i = 0; i < 10_0000_0000L; i++) {
        try {
            sum += i;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    long end1 = System.currentTimeMillis();
    long spendtime1 = end1 - start1;
        System.out.println(end1);
        System.out.println("spend time = " + spendtime1);
        System.out.println("sum = " + sum);

    long start2 = System.currentTimeMillis();
        try {
        for (int i = 0; i < 10_0000_0000L; i++) {
            sum2 += i;
        }
    } catch (NumberFormatException e) {
        e.printStackTrace();
    }
    long end2 = System.currentTimeMillis();
    long spendtime2 = end2 - start2;
        System.out.println("spend time = " + spendtime2);
        System.out.println("sum2 = " + sum2);
    }*/
}
