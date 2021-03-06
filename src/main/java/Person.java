import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Person implements Comparable<Person> {

    String name;
    String gender;

    Set<Family> families = new HashSet<>();

    public Person(String gender) {
        this.name = "Unknown";
        this.gender = gender;
    }

    public Person(String name, String gender) {
        this.name = name;
        this.gender = gender;
    }

    //Getters

    public String getName() {
        return name;
    }
    public String getGender() { return gender; }
    public Set<Family> getFamilies() { return families; }
    @Override
    //Alphabetic sorting
    public int compareTo(Person o) {
        //Sorted by name.
        return this.name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return "Name=" + name + ", gander='" + gender + '\'' + "}\n";
    }

    public void printFamilies() {
        for(Family family: families) {
            System.out.println();
            System.out.println(family.toString());
            System.out.println("-----------------------------------------------");
        }
    }

    public static Person findPerson(TreeSet<Person> person, String pName){
        for(Person per : person) {
            if(pName.equals(per.name)) {
                return per;
            }
        }
        return null;
    }

    public static void createPerson(TreeSet<Person> people, String name, String gender, int lineCount) {
        if(gender.equals("man") || gender.equals("woman"))
            people.add(new Person(name, gender));
        else {
            System.out.println("There is an error on CSV file to line: " + lineCount + ".");
            System.exit(-1);
        }
    }
}