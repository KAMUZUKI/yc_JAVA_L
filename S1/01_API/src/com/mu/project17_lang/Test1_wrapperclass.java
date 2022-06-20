package src.com.mu.project17_lang;

public class Test1_wrapperclass {
    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);

        //转类型
        System.out.println(Integer.parseInt("123"+1));
        System.out.println(Integer.parseInt("FF",16));
        System.out.println(Integer.parseInt("010",2) + 1);

        //转进制
        System.out.println(Integer.toOctalString(16));
        System.out.println(Integer.toBinaryString(16));
        System.out.println(Integer.toHexString(16));

        Integer i1 = Integer.valueOf(9);
        Integer i2 = Integer.valueOf(10);
//        Integer i3 = new Integer(10);
//        Integer i4 = new Integer(10);
        System.out.println("i1 compareTo i2 = " + i1.compareTo(i2));
//        System.out.println("i3 compareTo i4 = " + i3.compareTo(i4));
//        System.out.println("i3 compareTo i4 = " + i3.equals(i4));

    }
}
