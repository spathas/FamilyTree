public class Person implements Comparable<Person> {

    String name;
    String gender;

    public Person(String name, String gender) {
        this.name = name;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

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