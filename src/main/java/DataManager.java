import java.util.*;

public class DataManager {

    //Instances
    TreeSet<Person> people = new TreeSet<>();
    ArrayList<Relationship> relationships = new ArrayList<>();
    Set<Family> families = new HashSet<>();

    //Getters
    public TreeSet<Person> getPeople() {
        return people;
    }

    public ArrayList<Relationship> getRelationships() {
        return relationships;
    }

    //Methods
    private HashSet<Family> addFamiliesToPerson(Person firstPerson) {
        HashSet<Family> fam = new HashSet<>();
        for(Family family: this.families) {
            int index = IRelationshipsFinding.translateGender(firstPerson);

            if(family.parents[index].getName().equals(firstPerson.getName())) {
                fam.add(family);
            }
            for (Person person : family.children) {
                if(person.getName().equals(firstPerson.getName())) fam.add(family);
            }
        }
        return fam;
    }

    public void insertFamiliesToPerson() {
        for(Person person : this.getPeople()) {
            person.families.addAll(this.addFamiliesToPerson(person));
        }
    }

    private boolean isPartners(Family family, Person firstPerson, Person lastPerson) {
        int firstGender, lastGender;
        firstGender = IRelationshipsFinding.translateGender(firstPerson);
        lastGender = IRelationshipsFinding.translateGender(lastPerson);

        return IRelationshipsFinding.isPartners(family, firstPerson, lastPerson, firstGender, lastGender);
    }

    private boolean isParent(Family family, Person firstPerson, Person lastPerson) {
        int firstGender;
        firstGender = IRelationshipsFinding.translateGender(firstPerson);

        return IRelationshipsFinding.isParent(family, firstPerson, lastPerson, firstGender);
    }

    private Family findFamilyByRelationship(Relationship relationship) {
        for(Family family: this.families) {
            if (isPartners(family, relationship.getFirstPerson(), relationship.getLastPerson()))
                return family;
            if (isParent(family, relationship.getFirstPerson(), relationship.getLastPerson()))
                return family;
        }
        return null;
    }

    private Family findFamilyByPartners(Person firstPerson, Person lastPerson) {
        for(Family family: this.families) {
            if(isPartners(family, firstPerson, lastPerson))
                return family;
        }
        return null;
    }
    //This is a bad idea, don't handle null values because is risky.
    //Γενικά να απόφεύγουμε τετοιο κώδικα που διαχειρίζεται null τιμές μπορεί να σκάσει πολύ εύκολα.

    private Family findFamilyByNullParents(Person nullPerson, Person lastPerson) {
        if(nullPerson == null) {
            if (IRelationshipsFinding.translateGender(lastPerson) == 0) nullPerson = new Person("woman");
            if (IRelationshipsFinding.translateGender(lastPerson) == 1) nullPerson = new Person("man");
            int nullGender = IRelationshipsFinding.translateGender(nullPerson);
            int lastGender = IRelationshipsFinding.translateGender(lastPerson);

            for(Family family: this.families) {
                if( family.parents[nullGender].getName().equals(nullPerson.getName()) && family.parents[lastGender].getName().equals(lastPerson.getName()) )
                    return family;
            }
        }
        return null;
    }

    public void CreateNewIfPartners() {
        for(Relationship rel : this.relationships) {
            switch (rel.getRelationship()) {
                case "husband":
                    if (findFamilyByRelationship(rel) == null) {
                        families.add(new Family(rel.getFirstPerson(), rel.getLastPerson()));
                    }
                    else System.out.println("There is a duplicate here: " + rel.toString() + "\nCheck your CSV file for duplicates");
                    break;
                case "wife":
                    if (findFamilyByRelationship(rel) == null)
                        families.add(new Family(rel.getLastPerson(), rel.getFirstPerson()));
                    else System.out.println("There is a duplicate here: " + rel.toString() + "\nCheck your CSV file for duplicates");
                    break;
            }
        }
    }

    private void insertChildToNullFamilies(Relationship rel, String parentType) {
        String parentGender = "";
        if (parentType.equals("father")) parentGender = "man";
        if (parentType.equals("mother")) parentGender = "woman";

        Family newFam;
        newFam = findFamilyByNullParents(null, rel.getFirstPerson());
        if (newFam != null) newFam.children.add(rel.getLastPerson());
        if (newFam == null && parentGender.equals("man")) this.families.add(new Family(new Person(parentGender), rel.getFirstPerson(), rel.getLastPerson()));
        if (newFam == null && parentGender.equals("woman")) this.families.add(new Family(rel.getFirstPerson(), new Person(parentGender), rel.getLastPerson()));
    }

    private void insertChild(Relationship rel, String parentType) {
        for(Relationship relationship : this.relationships) {
            if (!(rel.getFirstPerson().equals(relationship.getFirstPerson())) && relationship.getLastPerson().equals(rel.getLastPerson()) && relationship.getRelationship().equals(parentType)) {
                Family fam = findFamilyByPartners(rel.getFirstPerson(), relationship.getFirstPerson());
                if (fam != null) {
                    fam.children.add(relationship.getLastPerson());
                    return;
                }
            }
        }
        insertChildToNullFamilies(rel, parentType);
    }

    public void checkForCompletedFamily() {
        for (Relationship rel : this.relationships) {
            if(rel.getRelationship().equals("mother"))
                insertChild(rel, "father");
            if(rel.getRelationship().equals("father"))
                insertChild(rel, "mother");
        }

    }

    //Printers
    public void printListOfPeople() {
        System.out.println("People");
        System.out.println(this.people.toString());
        System.out.println("________________________________________________________________\n");
    }

    public void printListOfRelationships() {
        System.out.println("Relationships");
        System.out.println(this.relationships.toString());
        System.out.println("________________________________________________________________\n");
    }

    public void printListOfFamilies() {
        System.out.println("Families");
        System.out.println(this.families.toString());
        System.out.println("________________________________________________________________\n");
    }

    public void printFamiliesPerPerson(Person person) {
        System.out.println("Families of person: " +person.getName());
        person.printFamilies();
        System.out.println("________________________________________________________________\n");
    }

}
