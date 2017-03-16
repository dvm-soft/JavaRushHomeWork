package com.javarush.test.level29.lesson15.big01.human;

import java.util.ArrayList;
import java.util.List;

public class University  {

    private String name;
    private int age;
    private List<Student> students = new ArrayList<>();

    public University(String name, int age)
    {
        this.name = name;
        this.age = age;
    }

    public String getName()
    {
        return name;
    }

    public int getAge()
    {
        return age;
    }

    public List<Student> getStudents()
    {
        return students;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public void setStudents(List<Student> students)
    {
        this.students = students;
    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        for (Student student : students)
            if (student.getAverageGrade() == averageGrade)
                return student;
        return null;
    }

    public Student getStudentWithMaxAverageGrade() {
        if (!students.isEmpty())
        {
            Student maxAverageStudent = students.get(0);
            for (Student student : students)
                if (student.getAverageGrade() > maxAverageStudent.getAverageGrade())
                    maxAverageStudent = student;
            return maxAverageStudent;
        }
        return null;
    }


    public Student getStudentWithMinAverageGrade() {
        if (!students.isEmpty())
        {
            Student minAverageStudent = students.get(0);
            for (Student student : students)
                if (student.getAverageGrade() < minAverageStudent.getAverageGrade())
                    minAverageStudent = student;
            return minAverageStudent;
        }
        return null;
    }
    public void expel(Student student) {
        students.remove(student);
    }

}
