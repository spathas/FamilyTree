import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestIRelationshipsFinding {
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
    void calcRelationship() {
//        Steffon
        assertEquals("Husband", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Steffon Baratheon"),
                Person.findPerson(data.getPeople(), "Cassana Estermont")));
        assertEquals("Father", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Steffon Baratheon"),
                Person.findPerson(data.getPeople(), "Robert Baratheon")));
        assertEquals("Father", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Steffon Baratheon"),
                Person.findPerson(data.getPeople(), "Renly Baratheon")));
        assertEquals("Father", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Steffon Baratheon"),
                Person.findPerson(data.getPeople(), "Stannis Baratheon")));
        assertEquals("Grandfather", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Steffon Baratheon"),
                Person.findPerson(data.getPeople(), "Shireen Baratheon")));
        assertEquals("Grandfather", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Steffon Baratheon"),
                Person.findPerson(data.getPeople(), "Gendry")));

        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Steffon Baratheon"),
                Person.findPerson(data.getPeople(), "Selyse Baratheon")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Steffon Baratheon"),
                Person.findPerson(data.getPeople(), "Margaery Tyrell")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Steffon Baratheon"),
                Person.findPerson(data.getPeople(), "Cersei Lannister")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Steffon Baratheon"),
                Person.findPerson(data.getPeople(), "Myrcella Baratheon")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Steffon Baratheon"),
                Person.findPerson(data.getPeople(), "Joffrey Baratheon")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Steffon Baratheon"),
                Person.findPerson(data.getPeople(), "Tommen Baratheon")));

//    Cassana
        assertEquals("Wife", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Cassana Estermont"),
                Person.findPerson(data.getPeople(), "Steffon Baratheon")));
        assertEquals("Mother", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Cassana Estermont"),
                Person.findPerson(data.getPeople(), "Robert Baratheon")));
        assertEquals("Mother", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Cassana Estermont"),
                Person.findPerson(data.getPeople(), "Renly Baratheon")));
        assertEquals("Mother", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Cassana Estermont"),
                Person.findPerson(data.getPeople(), "Stannis Baratheon")));
        assertEquals("Grandmother", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Cassana Estermont"),
                Person.findPerson(data.getPeople(), "Shireen Baratheon")));
        assertEquals("Grandmother", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Cassana Estermont"),
                Person.findPerson(data.getPeople(), "Gendry")));

        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Cassana Estermont"),
                Person.findPerson(data.getPeople(), "Selyse Baratheon")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Cassana Estermont"),
                Person.findPerson(data.getPeople(), "Margaery Tyrell")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Cassana Estermont"),
                Person.findPerson(data.getPeople(), "Cersei Lannister")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Cassana Estermont"),
                Person.findPerson(data.getPeople(), "Myrcella Baratheon")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Cassana Estermont"),
                Person.findPerson(data.getPeople(), "Joffrey Baratheon")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Cassana Estermont"),
                Person.findPerson(data.getPeople(), "Tommen Baratheon")));

//    Robert
        assertEquals("Son", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Robert Baratheon"),
                Person.findPerson(data.getPeople(), "Cassana Estermont")));
        assertEquals("Son", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Robert Baratheon"),
                Person.findPerson(data.getPeople(), "Steffon Baratheon")));
        assertEquals("Brother", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Robert Baratheon"),
                Person.findPerson(data.getPeople(), "Renly Baratheon")));
        assertEquals("Brother", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Robert Baratheon"),
                Person.findPerson(data.getPeople(), "Stannis Baratheon")));
        assertEquals("Husband", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Robert Baratheon"),
                Person.findPerson(data.getPeople(), "Cersei Lannister")));
        assertEquals("Uncle", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Robert Baratheon"),
                Person.findPerson(data.getPeople(), "Shireen Baratheon")));
        assertEquals("Father", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Robert Baratheon"),
                Person.findPerson(data.getPeople(), "Gendry")));

        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Robert Baratheon"),
                Person.findPerson(data.getPeople(), "Selyse Baratheon")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Robert Baratheon"),
                Person.findPerson(data.getPeople(), "Margaery Tyrell")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Robert Baratheon"),
                Person.findPerson(data.getPeople(), "Myrcella Baratheon")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Robert Baratheon"),
                Person.findPerson(data.getPeople(), "Joffrey Baratheon")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Robert Baratheon"),
                Person.findPerson(data.getPeople(), "Tommen Baratheon")));


//        Renly
        assertEquals("Son", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Renly Baratheon"),
                Person.findPerson(data.getPeople(), "Cassana Estermont")));
        assertEquals("Son", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Renly Baratheon"),
                Person.findPerson(data.getPeople(), "Steffon Baratheon")));
        assertEquals("Brother", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Renly Baratheon"),
                Person.findPerson(data.getPeople(), "Robert Baratheon")));
        assertEquals("Brother", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Renly Baratheon"),
                Person.findPerson(data.getPeople(), "Stannis Baratheon")));
        assertEquals("Husband", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Renly Baratheon"),
                Person.findPerson(data.getPeople(), "Margaery Tyrell")));
        assertEquals("Uncle", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Renly Baratheon"),
                Person.findPerson(data.getPeople(), "Shireen Baratheon")));
        assertEquals("Uncle", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Renly Baratheon"),
                Person.findPerson(data.getPeople(), "Gendry")));

        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Renly Baratheon"),
                Person.findPerson(data.getPeople(), "Selyse Baratheon")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Renly Baratheon"),
                Person.findPerson(data.getPeople(), "Cersei Lannister")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Renly Baratheon"),
                Person.findPerson(data.getPeople(), "Myrcella Baratheon")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Renly Baratheon"),
                Person.findPerson(data.getPeople(), "Joffrey Baratheon")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Renly Baratheon"),
                Person.findPerson(data.getPeople(), "Tommen Baratheon")));


//        Stannis
        assertEquals("Son", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Stannis Baratheon"),
                Person.findPerson(data.getPeople(), "Cassana Estermont")));
        assertEquals("Son", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Stannis Baratheon"),
                Person.findPerson(data.getPeople(), "Steffon Baratheon")));
        assertEquals("Brother", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Stannis Baratheon"),
                Person.findPerson(data.getPeople(), "Renly Baratheon")));
        assertEquals("Brother", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Stannis Baratheon"),
                Person.findPerson(data.getPeople(), "Robert Baratheon")));
        assertEquals("Husband", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Stannis Baratheon"),
                Person.findPerson(data.getPeople(), "Selyse Baratheon")));
        assertEquals("Father", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Stannis Baratheon"),
                Person.findPerson(data.getPeople(), "Shireen Baratheon")));

        assertEquals("Uncle", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Stannis Baratheon"),
                Person.findPerson(data.getPeople(), "Gendry")));

        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Stannis Baratheon"),
                Person.findPerson(data.getPeople(), "Margaery Tyrell")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Stannis Baratheon"),
                Person.findPerson(data.getPeople(), "Cersei Lannister")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Stannis Baratheon"),
                Person.findPerson(data.getPeople(), "Myrcella Baratheon")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Stannis Baratheon"),
                Person.findPerson(data.getPeople(), "Joffrey Baratheon")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Stannis Baratheon"),
                Person.findPerson(data.getPeople(), "Tommen Baratheon")));



//        Gendry
        assertEquals("Grandson", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Gendry"),
                Person.findPerson(data.getPeople(), "Cassana Estermont")));
        assertEquals("Grandson", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Gendry"),
                Person.findPerson(data.getPeople(), "Steffon Baratheon")));
        assertEquals("Son", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Gendry"),
                Person.findPerson(data.getPeople(), "Robert Baratheon")));
        assertEquals("Nephew", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Gendry"),
                Person.findPerson(data.getPeople(), "Renly Baratheon")));
        assertEquals("Nephew", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Gendry"),
                Person.findPerson(data.getPeople(), "Stannis Baratheon")));
        assertEquals("Nephew", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Gendry"),
                Person.findPerson(data.getPeople(), "Margaery Tyrell")));
        assertEquals("Nephew", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Gendry"),
                Person.findPerson(data.getPeople(), "Selyse Baratheon")));
        assertEquals("Cousin", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Gendry"),
                Person.findPerson(data.getPeople(), "Shireen Baratheon")));

        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Gendry"),
                Person.findPerson(data.getPeople(), "Cersei Lannister")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Gendry"),
                Person.findPerson(data.getPeople(), "Myrcella Baratheon")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Gendry"),
                Person.findPerson(data.getPeople(), "Joffrey Baratheon")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Gendry"),
                Person.findPerson(data.getPeople(), "Tommen Baratheon")));


//        Cersei
        assertEquals("Wife", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Cersei Lannister"),
                Person.findPerson(data.getPeople(), "Robert Baratheon")));
        assertEquals("Mother", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Cersei Lannister"),
                Person.findPerson(data.getPeople(), "Myrcella Baratheon")));
        assertEquals("Mother", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Cersei Lannister"),
                Person.findPerson(data.getPeople(), "Joffrey Baratheon")));
        assertEquals("Mother", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Cersei Lannister"),
                Person.findPerson(data.getPeople(), "Tommen Baratheon")));

        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Cersei Lannister"),
                Person.findPerson(data.getPeople(), "Gendry")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Cersei Lannister"),
                Person.findPerson(data.getPeople(), "Cassana Estermont")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Cersei Lannister"),
                Person.findPerson(data.getPeople(), "Steffon Baratheon")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Cersei Lannister"),
                Person.findPerson(data.getPeople(), "Renly Baratheon")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Cersei Lannister"),
                Person.findPerson(data.getPeople(), "Stannis Baratheon")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Cersei Lannister"),
                Person.findPerson(data.getPeople(), "Margaery Tyrell")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Cersei Lannister"),
                Person.findPerson(data.getPeople(), "Selyse Baratheon")));
//        assertEquals("Aunt", IRelationshipsFinding.calcRelationship(
//                Person.findPerson(data.getPeople(), "Cersei Lannister"),
//                Person.findPerson(data.getPeople(), "Shireen Baratheon")));


//        Margaery
        assertEquals("Wife", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Margaery Tyrell"),
                Person.findPerson(data.getPeople(), "Renly Baratheon")));
        assertEquals("Aunt", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Margaery Tyrell"),
                Person.findPerson(data.getPeople(), "Shireen Baratheon")));
        assertEquals("Aunt", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Margaery Tyrell"),
                Person.findPerson(data.getPeople(), "Gendry")));

        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Margaery Tyrell"),
                Person.findPerson(data.getPeople(), "Robert Baratheon")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Margaery Tyrell"),
                Person.findPerson(data.getPeople(), "Myrcella Baratheon")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Margaery Tyrell"),
                Person.findPerson(data.getPeople(), "Joffrey Baratheon")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Margaery Tyrell"),
                Person.findPerson(data.getPeople(), "Tommen Baratheon")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Margaery Tyrell"),
                Person.findPerson(data.getPeople(), "Cassana Estermont")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Margaery Tyrell"),
                Person.findPerson(data.getPeople(), "Steffon Baratheon")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Margaery Tyrell"),
                Person.findPerson(data.getPeople(), "Stannis Baratheon")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Margaery Tyrell"),
                Person.findPerson(data.getPeople(), "Cersei Lannister")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Margaery Tyrell"),
                Person.findPerson(data.getPeople(), "Selyse Baratheon")));


//        Selyse
        assertEquals("Wife", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Selyse Baratheon"),
                Person.findPerson(data.getPeople(), "Stannis Baratheon")));
        assertEquals("Mother", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Selyse Baratheon"),
                Person.findPerson(data.getPeople(), "Shireen Baratheon")));
        assertEquals("Aunt", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Selyse Baratheon"),
                Person.findPerson(data.getPeople(), "Gendry")));

        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Selyse Baratheon"),
                Person.findPerson(data.getPeople(), "Robert Baratheon")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Selyse Baratheon"),
                Person.findPerson(data.getPeople(), "Myrcella Baratheon")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Selyse Baratheon"),
                Person.findPerson(data.getPeople(), "Joffrey Baratheon")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Selyse Baratheon"),
                Person.findPerson(data.getPeople(), "Tommen Baratheon")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Selyse Baratheon"),
                Person.findPerson(data.getPeople(), "Cassana Estermont")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Selyse Baratheon"),
                Person.findPerson(data.getPeople(), "Steffon Baratheon")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Selyse Baratheon"),
                Person.findPerson(data.getPeople(), "Renly Baratheon")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Selyse Baratheon"),
                Person.findPerson(data.getPeople(), "Margaery Tyrell")));


//        Myrcella
        assertEquals("Daughter", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Myrcella Baratheon"),
                Person.findPerson(data.getPeople(), "Cersei Lannister")));
        assertEquals("Sister", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Myrcella Baratheon"),
                Person.findPerson(data.getPeople(), "Joffrey Baratheon")));
        assertEquals("Sister", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Myrcella Baratheon"),
                Person.findPerson(data.getPeople(), "Tommen Baratheon")));

        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Myrcella Baratheon"),
                Person.findPerson(data.getPeople(), "Robert Baratheon")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Myrcella Baratheon"),
                Person.findPerson(data.getPeople(), "Gendry")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Myrcella Baratheon"),
                Person.findPerson(data.getPeople(), "Cassana Estermont")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Myrcella Baratheon"),
                Person.findPerson(data.getPeople(), "Steffon Baratheon")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Myrcella Baratheon"),
                Person.findPerson(data.getPeople(), "Renly Baratheon")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Myrcella Baratheon"),
                Person.findPerson(data.getPeople(), "Stannis Baratheon")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Myrcella Baratheon"),
                Person.findPerson(data.getPeople(), "Margaery Tyrell")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Myrcella Baratheon"),
                Person.findPerson(data.getPeople(), "Selyse Baratheon")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Myrcella Baratheon"),
                Person.findPerson(data.getPeople(), "Shireen Baratheon")));


//        Joffrey
        assertEquals("Son", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Joffrey Baratheon"),
                Person.findPerson(data.getPeople(), "Cersei Lannister")));
        assertEquals("Brother", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Joffrey Baratheon"),
                Person.findPerson(data.getPeople(), "Myrcella Baratheon")));
        assertEquals("Brother", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Joffrey Baratheon"),
                Person.findPerson(data.getPeople(), "Tommen Baratheon")));

        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Joffrey Baratheon"),
                Person.findPerson(data.getPeople(), "Robert Baratheon")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Joffrey Baratheon"),
                Person.findPerson(data.getPeople(), "Gendry")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Joffrey Baratheon"),
                Person.findPerson(data.getPeople(), "Cassana Estermont")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Joffrey Baratheon"),
                Person.findPerson(data.getPeople(), "Steffon Baratheon")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Joffrey Baratheon"),
                Person.findPerson(data.getPeople(), "Renly Baratheon")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Joffrey Baratheon"),
                Person.findPerson(data.getPeople(), "Stannis Baratheon")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Joffrey Baratheon"),
                Person.findPerson(data.getPeople(), "Margaery Tyrell")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Joffrey Baratheon"),
                Person.findPerson(data.getPeople(), "Selyse Baratheon")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Joffrey Baratheon"),
                Person.findPerson(data.getPeople(), "Shireen Baratheon")));


//    Tommen
        assertEquals("Son", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Tommen Baratheon"),
                Person.findPerson(data.getPeople(), "Cersei Lannister")));
        assertEquals("Brother", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Tommen Baratheon"),
                Person.findPerson(data.getPeople(), "Myrcella Baratheon")));
        assertEquals("Brother", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Tommen Baratheon"),
                Person.findPerson(data.getPeople(), "Joffrey Baratheon")));

        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Tommen Baratheon"),
                Person.findPerson(data.getPeople(), "Robert Baratheon")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Tommen Baratheon"),
                Person.findPerson(data.getPeople(), "Gendry")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Tommen Baratheon"),
                Person.findPerson(data.getPeople(), "Cassana Estermont")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Tommen Baratheon"),
                Person.findPerson(data.getPeople(), "Steffon Baratheon")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Tommen Baratheon"),
                Person.findPerson(data.getPeople(), "Renly Baratheon")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Tommen Baratheon"),
                Person.findPerson(data.getPeople(), "Stannis Baratheon")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Tommen Baratheon"),
                Person.findPerson(data.getPeople(), "Margaery Tyrell")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Tommen Baratheon"),
                Person.findPerson(data.getPeople(), "Selyse Baratheon")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Tommen Baratheon"),
                Person.findPerson(data.getPeople(), "Shireen Baratheon")));


//        Shireen
        assertEquals("Granddaughter", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Shireen Baratheon"),
                Person.findPerson(data.getPeople(), "Cassana Estermont")));
        assertEquals("Granddaughter", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Shireen Baratheon"),
                Person.findPerson(data.getPeople(), "Steffon Baratheon")));
        assertEquals("Daughter", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Shireen Baratheon"),
                Person.findPerson(data.getPeople(), "Stannis Baratheon")));
        assertEquals("Daughter", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Shireen Baratheon"),
                Person.findPerson(data.getPeople(), "Selyse Baratheon")));
        assertEquals("Niece", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Shireen Baratheon"),
                Person.findPerson(data.getPeople(), "Renly Baratheon")));
        assertEquals("Niece", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Shireen Baratheon"),
                Person.findPerson(data.getPeople(), "Robert Baratheon")));
        assertEquals("Niece", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Shireen Baratheon"),
                Person.findPerson(data.getPeople(), "Margaery Tyrell")));
        assertEquals("Cousin", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Shireen Baratheon"),
                Person.findPerson(data.getPeople(), "Gendry")));
//        assertEquals("Niece", IRelationshipsFinding.calcRelationship(
//                Person.findPerson(data.getPeople(), "Shireen Baratheon"),
//                Person.findPerson(data.getPeople(), "Cersei Lannister")));

        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Shireen Baratheon"),
                Person.findPerson(data.getPeople(), "Myrcella Baratheon")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Shireen Baratheon"),
                Person.findPerson(data.getPeople(), "Joffrey Baratheon")));
        assertEquals("Unknown relationship", IRelationshipsFinding.calcRelationship(
                Person.findPerson(data.getPeople(), "Shireen Baratheon"),
                Person.findPerson(data.getPeople(), "Tommen Baratheon")));

    }
}