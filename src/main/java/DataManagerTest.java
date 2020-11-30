import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class DataManagerTest {
    TreeSet<Person> people;
    ArrayList<Relationship> relationships;
    Set<Family> families;

    Person person1;
    Person person2;
    Person person3;
    Person person4;
    Person person5;
    Person person6;
    Person person7;
    Person person8;

    Relationship rel1;
    Relationship rel2;
    Relationship rel3;
    Relationship rel4;

    Family fam1;
    Family fam2;
    Family fam3;

    @BeforeEach
    void setup() {
        //People
        Person person1 = new Person("Person1", "man");
        Person person2 = new Person("Person2", "man");
        Person person3 = new Person("Person3", "woman");
        Person person4 = new Person("Person4", "woman");
        Person person5 = new Person("Person5", "man");
        Person person6 = new Person("Person6", "man");
        Person person7 = new Person("Person7", "woman");
        Person person8 = new Person("Person8", "woman");

        people.add(person1);
        people.add(person2);
        people.add(person3);
        people.add(person4);
        people.add(person5);
        people.add(person6);
        people.add(person7);
        people.add(person8);

        //Relationships
        Relationship rel1 = new Relationship(person1, "father", person2);
        Relationship rel2 = new Relationship(person3, "father", person4);
        Relationship rel3 = new Relationship(person5, "father", person6);
        Relationship rel4 = new Relationship(person7, "father", person8);

        relationships.add(rel1);
        relationships.add(rel2);
        relationships.add(rel3);
        relationships.add(rel4);

        //Families
        fam1 = new Family(person1, person3, person2);
        fam2 = new Family(person5, person4, person6);
        fam3 = new Family(person7, person8);

        families.add(fam1);
        families.add(fam2);
        families.add(fam3);
    }

    @Test
    void getPeople() {
        assertTrue(people.contains(person1));
        assertTrue(people.contains(person2));
        assertTrue(people.contains(person3));
        assertTrue(people.contains(person4));
        assertTrue(people.contains(person5));
        assertTrue(people.contains(person6));
        assertTrue(people.contains(person7));
        assertTrue(people.contains(person8));
    }
//
//    @Test
//    void getRelationships() {
//        assertTrue(relationships.contains(rel1));
//        assertTrue(relationships.contains(rel2));
//        assertTrue(relationships.contains(rel3));
//        assertTrue(relationships.contains(rel4));
//    }
//
//    @Test
//    void getFamilies() {
//        assertTrue(families.contains(fam1));
//        assertTrue(families.contains(fam2));
//        assertTrue(families.contains(fam3));
//
//
//    }
//
//    @Test
//    void insertFamiliesToPerson() {
//        insertFamiliesToPerson();
//        assertTrue(person1.getFamilies().contains(fam1));
//        assertTrue(person2.getFamilies().contains(fam1));
//        assertTrue(person4.getFamilies().contains(fam2));
//        assertTrue(person5.getFamilies().contains(fam2));
//        assertTrue(person7.getFamilies().contains(fam3));
//        assertTrue(person8.getFamilies().contains(fam3));
//    }
//
//    @Test
//    void createNewIfPartners() {
//    }
//
//    @Test
//    void checkForCompletedFamily() {
//    }
}