import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;

public class CSVClass {

            String csvFile = "/home/cspathas/Desktop/BaratheonTreeWithRels.csv";
//    String csvFile = "C:\\Users\\spath\\Desktop\\BaratheonTreeWithRels.csv";

    public CSVClass() {

    }

    public CSVClass(String csvFile) {
        this.csvFile = csvFile;
    }

    void readFile(TreeSet<Person> people, ArrayList<Relationship> relationships) {
        try {

            CSVReader familyTree = new CSVReader(new FileReader(this.csvFile));
            String[] line;
            int lineCount = 0;

            while ((line = familyTree.readNext()) != null) {
                lineCount++;

                if (line[line.length - 1].equals("") || line[line.length - 1] == null) {
                    String name = line[0];
                    String gender = line[1];

                    Person.createPerson(people, name, gender, lineCount);
                } else {
                    //Find people objects base on 2 names by CSV line and relationship between them.
                    Person firstPerson = Person.findPerson(people, line[0]);
                    Person lastPerson = Person.findPerson(people, line[2]);
                    String rel = line[1];

                    if (!checkRelationship(firstPerson, rel)) {
                        System.out.println("There is a bad relationship in CSV file.\nPlease check your relationship stack.");
                        System.exit(-1);
                    } else {
                        //Create a stack of all relationships
                        assert firstPerson != null && lastPerson != null;
                        relationships.add(new Relationship(firstPerson, rel, lastPerson));

                    }


                }
            }

        } catch (IOException e) {
            System.out.println("\"Check the path or type of CSV file.\"");
            e.printStackTrace();
        }
    }

    static void exportTXT(TreeSet<Person> person) {
        try {
            FileWriter myWriter = new FileWriter("names.txt");
            for(Person per : person) {
                myWriter.write("Name: " + per.getName() + ", Gender: " + per.getGender() + ",\n");
            }
            myWriter.write("\n\n The length of list is: " + person.size());
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred with export txt file.");
            e.printStackTrace();
        }

    }



    static boolean checkRelationship(Person firstPerson, String relationship) {
        if( relationship.equals("father") || relationship.equals("mother") || relationship.equals("husband") || relationship.equals("wife") )
            //Check for logical fail
            if( (relationship.equals("father") || relationship.equals("husband")) && firstPerson.getGender().equals("man") )
                return true;
        return (relationship.equals("mother") || relationship.equals("wife")) && firstPerson.getGender().equals("woman");
    }

}
