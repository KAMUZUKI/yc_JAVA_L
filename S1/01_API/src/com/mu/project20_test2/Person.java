package src.com.mu.project20_test2;

import java.io.*;
import java.util.Objects;

class Person implements Cloneable, Serializable {
    String name;
    int age;
    Pet pet;

    public Person(String name, int age, Pet pet) {
        this.name = name;
        this.age = age;
        this.pet = pet;
    }

    @Override
    protected Object clone() {
        return this.deepClone();
    }

    public Object deepClone() {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(this);
            oos.flush();

            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);
            Person copy = (Person) ois.readObject();
            copy.pet = new Pet("Pet", 0);
            return copy;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return age == person.age && Objects.equals(name, person.name) && Objects.equals(pet, person.pet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, pet);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", pet=" + pet +
                '}';
    }
}
