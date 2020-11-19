import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Person implements Comparable<Person> {

    String name;
    String gender;

    ArrayList Family = new ArrayList<>();

    public Person(String name, String gender) {
        this.name = name;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public String getGender() { return gender; }
    //Print list with alphabet priority

    @Override
    public int compareTo(Person o) {
        //Sorted by name.
        return this.name.compareTo(o.name);
    }
    //To String printing

    @Override
    public String toString() {
        return "Person{" + "name=" + name + ", gander='" + gender + '\'' +  "}\n";
    }
}