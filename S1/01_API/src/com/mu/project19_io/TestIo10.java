package src.com.mu.project19_io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TestIo10 {
    public static void main(String[] args) {
        List<Product> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Product p = new Product(1+i,"苹果"+i,20+i,"好吃"+i);
            list.add(p);
        }

        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(new File("product.tmp")))
        ){
            os.writeObject(list);
            os.flush();
        }catch (Exception e){
            e.printStackTrace();
        }

        try(ObjectInputStream os = new ObjectInputStream(new FileInputStream(new File("product.tmp")))
        ){
            Object o = os.readObject();
            if(o instanceof List){
                List<Product> list1 = (List)o;
                System.out.println(list1);
                System.out.println("读取对象成功");}
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}


class Product implements Serializable{
    private int pid;
    private String pname;
    private transient double price;
    private String description;

    public Product(int pid, String pname, double price, String description) {
        this.pid = pid;
        this.pname = pname;
        this.price = price;
        this.description = description;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product{" +
                "pid=" + pid +
                ", pname='" + pname + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
