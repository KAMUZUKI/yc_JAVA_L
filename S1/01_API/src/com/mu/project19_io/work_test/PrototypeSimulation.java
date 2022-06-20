package src.com.mu.project19_io.work_test;

import java.io.*;
import java.util.Objects;

public class PrototypeSimulation {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Person person1 = new Person(1,"zhangsan");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("test.tmp"));
        oos.writeObject(person1);

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("test.tmp"));
        Person person2 = (Person)ois.readObject();
        System.out.println(person2);
        System.out.println("==================");
        System.out.println(person1.hashCode());
        System.out.println(person2.hashCode());

        System.out.println("==================");
        person2.setId(2);
        person2.setName("lisi");
        System.out.println(person1.hashCode());
        System.out.println(person2.hashCode());
    }
}

class Person implements Serializable {
    private int id;
    String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return id == person.id && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
