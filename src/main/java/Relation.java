public class Relation {

    String interestName;
    String dependName;
    String relationship;

    public Relation(String interestName, String dependName, String relationship) {
        this.interestName = interestName;
        this.dependName = dependName;
        this.relationship = relationship;
    }

    public void setInterestName(String interestName) {
        this.interestName = interestName;
    }

    public void setDependName(String dependName) {
        this.dependName = dependName;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getInterestName() {
        return interestName;
    }

    public String getDependName() {
        return dependName;
    }

    public String getRelationship() {
        return relationship;
    }

    public String toString()
    {
        return "First Name: " + this.interestName + " Relationship: " + this.relationship + " Second Name: " + this.dependName + "\n";
    }
}
