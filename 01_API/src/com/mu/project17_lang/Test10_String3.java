package src.com.mu.project17_lang;

public class Test10_String3 {
    public static void main(String[] args) {
        // 提取
        String s = "hello";
        // 加密
        int key = 5;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            sb.append((char)(ch + 5));
        }
        //得到密文
        System.out.println(sb.toString());
        //解密 -5

        //concat()  追加
        String r = s.concat("world");
        System.out.println(r);

        //replace() 替换
        String s2 = new String("US is a big country");
        String r2 = s2.replace("US","China");
        System.out.println(r2);

        //trim()  去掉前后空格
        String s3 = " select * from emp";
        String r3 = s3.trim();
        System.out.println(r3);

        //split() 分隔，返回按一定条件切割的字符串数组
        String  s4 = "1,张三,20,;2,李四,30,;3,王五,40";
        String [] ss1 =  s4.split(";");
        for (String ss2:ss1) {
            String [] r4 = ss2.split(",");
            String id = r4[0];
            String name = r4[1];
            String age = r4[2];
            System.out.print("[id = " + id + ",");
            System.out.print("name = " + name + ",");
            System.out.print("age = " + age + "]  ");
        }
    }
}
