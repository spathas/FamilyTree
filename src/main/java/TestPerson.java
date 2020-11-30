import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TestPerson {
    CSVClass csvFile = new CSVClass();
    DataManager data = new DataManager();

    @BeforeEach
    void setData() {
        csvFile.readFile(data.getPeople(), data.getRelationships());
        data.CreateNewIfPartners();
        data.checkForCompletedFamily();
        data.insertFamiliesToPerson();
    }

    @Test
    void findPerson() {
        assertEquals("Gendry", Person.findPerson(data.getPeople(), "Gendry").getName());
        assertEquals("Joffrey Baratheon", Person.findPerson(data.getPeople(), "Joffrey Baratheon").getName());
        assertEquals("Myrcella Baratheon", Person.findPerson(data.getPeople(), "Myrcella Baratheon").getName());
        assertEquals("Tommen Baratheon", Person.findPerson(data.getPeople(), "Tommen Baratheon").getName());
        assertEquals("Cersei Lannister", Person.findPerson(data.getPeople(), "Cersei Lannister").getName());
        assertEquals("Robert Baratheon", Person.findPerson(data.getPeople(), "Robert Baratheon").getName());
        assertEquals("Shireen Baratheon", Person.findPerson(data.getPeople(), "Shireen Baratheon").getName());
        assertEquals("Selyse Baratheon", Person.findPerson(data.getPeople(), "Selyse Baratheon").getName());
        assertEquals("Stannis Baratheon", Person.findPerson(data.getPeople(), "Stannis Baratheon").getName());
        assertEquals("Margaery Tyrell", Person.findPerson(data.getPeople(), "Margaery Tyrell").getName());
        assertEquals("Renly Baratheon", Person.findPerson(data.getPeople(), "Renly Baratheon").getName());
        assertEquals("Cassana Estermont", Person.findPerson(data.getPeople(), "Cassana Estermont").getName());
        assertEquals("Steffon Baratheon", Person.findPerson(data.getPeople(), "Steffon Baratheon").getName());
    }

    @Test
    void getGender() {
        assertEquals("man", Person.findPerson(data.getPeople(), "Gendry").getGender());
        assertEquals("woman", Person.findPerson(data.getPeople(), "Shireen Baratheon").getGender());
        assertEquals("man", Person.findPerson(data.getPeople(), "Stannis Baratheon").getGender());
        assertEquals("woman", Person.findPerson(data.getPeople(), "Margaery Tyrell").getGender());
    }
}