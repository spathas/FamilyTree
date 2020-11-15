import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CSVReaderExample {

    public static void main(String[] args) {

        String csvFile = "C:\\Users\\spath\\Desktop\\BaratheonTreeWithRels.csv";
        CSVReader reader = null;
        Set<Human> man = new HashSet<Human>();
        Set<Relation> rel = new HashSet<>();

        //CSV to Objects
        try {
            reader = new CSVReader(new FileReader(csvFile));
            String[] line;
            while ((line = reader.readNext()) != null) {
                if(line[line.length -1] == "" || line[line.length -1] == null) {
                    man.add(new Human(line[0], line[1]));
                }
                else {
                    rel.add(new Relation(line[0], line[1], line[2]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Humans");
        System.out.println(man.toString());
        System.out.println("________________________________________________________________\n");
        System.out.println("Relationships");
        System.out.println(rel.toString());


    }

}
