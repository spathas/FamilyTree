public class Relationship {

    Person firstPerson;
    Person lastPerson;
    String relationship;

    public Relationship(Person firstPerson, String relationship, Person lastPerson) {
        this.firstPerson = firstPerson;
        this.lastPerson = lastPerson;
        this.relationship = relationship;
    }

    public static boolean checkRelationship(Person firstPerson, String relationship) {
        //Check for non recommended String in relationship slot of csv file
        if( relationship.equals("father") || relationship.equals("mother") || relationship.equals("husband") || relationship.equals("wife") )
            //Check for logical fail
            if( (relationship.equals("father") || relationship.equals("husband")) && firstPerson.getGender().equals("man") )
                return true;
        return (relationship.equals("mother") || relationship.equals("wife")) && firstPerson.getGender().equals("woman");
    }

    public Person getFirstPerson() {
        return firstPerson;
    }

    public Person getLastPerson() {
        return lastPerson;
    }

    public String getRelationship() {
        return relationship;
    }

    @Override
    public String toString() {
        return "First name=" + firstPerson.getName() + ", relationship=" + relationship +  ", Last name=" + lastPerson.getName() + "\n";
    }
}
