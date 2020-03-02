package sample;
import java.util.UUID;

public class Student {
    private String name;
    private UUID id;
    private int age;
    private String major;
    private double GPA;

    @Override
    public String toString() {
        return (this.name + " " + this.age + " " + this.major);
    }
}