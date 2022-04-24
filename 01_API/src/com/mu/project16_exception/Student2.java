package src.com.mu.project16_exception;

public class Student2 {
    private String name;
    private int age;


    public Student2(String name, int age) throws AgeNotRightRuntimeException {
        if (age < 15 || age>50){
            throw new AgeNotRightRuntimeException();
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
}

