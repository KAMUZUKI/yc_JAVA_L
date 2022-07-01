package src.com.mu.project16_exception;

public class Student {
    private String name;
    private int age;


    public Student(String name, int age) {
        if (age < 15 || age>120){
            throw new RuntimeException("年龄范围不合法");
        }
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
