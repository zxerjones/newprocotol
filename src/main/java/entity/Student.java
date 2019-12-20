package entity;

import lombok.Data;

/**
 * @ClassName Student
 * @Description TODO
 * @Author zhengxin
 * @Date 2019年12月19日21:03:19
 */
@Data
public class Student {
    private int id;
    private String uid;
    private String name;
    private int age;
    private String des;

    public Student() {
    }

    public Student(String uid, String name, int age, String des) {
        this.uid = uid;
        this.name = name;
        this.age = age;
        this.des = des;
    }
}
