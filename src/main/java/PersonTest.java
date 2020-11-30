import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    Person person;
    Family family;

    @Test
    void getName() {
        person = new Person("Tester", "man");
        assertEquals("Tester", person.getName());
    }

    @Test
    void getGender() {
        person = new Person("Tester", "man");
        assertEquals("man", person.getGender());
    }

    @Test
    void getFamilies() {
        person = new Person("Tester", "man");
        Person father = new Person("Father", "man");
        Person mother = new Person("Mother", "woman");
        family = new Family(father, mother, person);
        assertEquals("Father", family.parents[0].getName());
        assertEquals("Mother", family.parents[1].getName());
        assertTrue(family.children.contains(person));
    }
}