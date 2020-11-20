//import java.util.ArrayList;
import java.util.TreeSet;

public class Person implements Comparable<Person> {

    String name;
    String gender;

//    ArrayList<Family> Family = new ArrayList<>();

    public Person(String name, String gender) {
        this.name = name;
        this.gender = gender;
    }

    public static Person findPerson(TreeSet<Person> person, String pName){
        for(Person per : person) {
            if(pName.equals(per.name)) {
                return per;
            }
        }
        return null;
    }

    public static boolean isPerson(TreeSet<Person> person, String pName) {
        for(Person per : person) {
            if(!pName.equals(per.name)) {
                return false;
            }
        }
        return true;
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