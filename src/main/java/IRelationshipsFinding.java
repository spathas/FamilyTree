
public interface IRelationshipsFinding {

    static void calcRelationship(Person firstPerson, Person lastPerson) {
        String rel = "Unknown relationship";
        int firstGender, lastGender;
        firstGender = translateGender(firstPerson);
        lastGender = translateGender(lastPerson);


        for(Family family : lastPerson.getFamilies()) {
            if(firstGender == 0) {
                if (isPartners(family, firstPerson, lastPerson, firstGender, lastGender)) rel = "Husband";
                if (isParent(family, firstPerson, lastPerson, firstGender)) rel = "Father";
                if (isParent(family, lastPerson, firstPerson, lastGender)) rel = "Son"; //Reversed isParent() to find kid
                if (isBrother(family, firstPerson, lastPerson)) rel = "Brother";
                if (isGrandchild(family, firstPerson, lastPerson, lastGender)) rel = "Grandson";
                if (isGrandParent(family, firstPerson, lastPerson, firstGender)) rel = "Grandfather";
                if (isUncles(family, firstPerson, lastPerson, firstGender)) rel = "Uncle";
                if (isNephew(family, firstPerson, lastPerson, lastGender)) rel = "Nephew";
                if (isCousin(family, firstPerson, lastPerson)) rel = "Cousin";
            }

            if(firstGender == 1) {
                if (isPartners(family, firstPerson, lastPerson, firstGender, lastGender)) rel = "Wife";
                if (isParent(family, firstPerson, lastPerson, firstGender)) rel = "Mother";
                if (isParent(family, lastPerson, firstPerson, lastGender)) rel = "Daughter";//Reversed isParent() to find kid
                if (isBrother(family, firstPerson, lastPerson)) rel = "Sister";
                if (isGrandchild(family, firstPerson, lastPerson, lastGender)) rel = "Granddaughter";
                if (isGrandParent(family, firstPerson, lastPerson, firstGender)) rel = "Grandmother";
                if (isUncles(family, firstPerson, lastPerson, firstGender)) rel = "Aunt";
                if (isNephew(family, firstPerson, lastPerson, lastGender)) rel = "Niece";
                if (isCousin(family, firstPerson, lastPerson)) rel = "Cousin";
            }
        }

        System.out.println(firstPerson.getName() + " is " + rel + " of " + lastPerson.getName());
    }

    static boolean isPartners(Family family, Person firstPerson, Person lastPerson, int firstGender, int lastGender) {
        return family.parents[firstGender].equals(firstPerson) && family.parents[lastGender].equals(lastPerson);
    }

    static boolean isParent(Family family, Person firstPerson, Person lastPerson, int firstGender) {
        return family.parents[firstGender].equals(firstPerson) && family.children.contains(lastPerson);
    }

    static boolean isBrother(Family family, Person firstPerson, Person lastPerson) {
        return family.children.contains(firstPerson) && family.children.contains(lastPerson);
    }

    static boolean isGrandchild(Family family, Person firstPerson, Person lastPerson, int lastGender) {
        if(!(family.parents[lastGender].equals(lastPerson))) return false;

        for (Person child : family.children) {
            int childGender = translateGender(child);

            for (Family pFamily : child.getFamilies()) {
                //Check if child (of firstPerson) is parent of lastPerson.
                if(isParent(pFamily, child, firstPerson, childGender))
                    return true;
            }
        }

        return false;
    }

    static boolean isGrandParent(Family family, Person firstPerson, Person lastPerson, int firstGender) {
        if (!family.children.contains(lastPerson)) return false;

        for(Person parent: family.parents) {
            for (Family pFamily : parent.getFamilies()) {
                if (pFamily.parents[firstGender].equals(firstPerson)) return true;
            }
        }

        return false;
    }

    static boolean isUncles(Family family, Person firstPerson, Person lastPerson, int firstGender) {

        //Find partner of lastPerson obj.
        Person partner = findPartnerOf(firstPerson, firstGender);

        if(!family.children.contains(lastPerson)) return false;

        for(Person parent: family.parents) {
            for (Family pFamily : parent.getFamilies()) {
                if (isBrother(pFamily, parent, firstPerson)) return true;
                if (isBrother(pFamily, parent, partner)) return true;
            }
        }
        return false;
    }

    static boolean isNephew(Family family, Person firstPerson, Person lastPerson, int lastGender) {
        Person partner = findPartnerOf(lastPerson, lastGender);

        if (!family.children.contains(lastPerson)) return false;

        for (Person brother : family.children) {
            int childGender = translateGender(brother);

            for(Family bFamily : brother.getFamilies()) {
                if (isParent(bFamily, brother, firstPerson, childGender)) return true;
                if (isParent(bFamily, brother, partner, childGender)) return true;
            }
        }

        return false;
    }

    static boolean isCousin(Family family, Person firstPerson, Person lastPerson) {
        if (!family.children.contains(lastPerson)) return false;

        for(Person parent : family.parents) {
            int childGender = translateGender(parent);

            for (Family pFamily : firstPerson.getFamilies()) {
                if (isUncles(pFamily, parent, firstPerson, childGender)) return true;
            }
        }
        return false;
    }

    static Person findPartnerOf(Person person, int personGender) {
        int partnerGender = 1 - personGender;
        for (Family uFamily : person.getFamilies()) {
            if (uFamily.parents[personGender] == person) return uFamily.parents[partnerGender];
        }
        return null;
    }

    static int translateGender(Person person) {
        if(person.gender.equals("man")) return 0;
        if(person.gender.equals("woman")) return 1;

        return -1;
    }

}
