public class Human {

    String name;
    String gender;

    public Human(String name, String gender) {
        this.name = name;
        this.gender = gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String toString()
    {
        return "Name: " + this.name + " Gender: " + this.gender + "\n";
    }
}