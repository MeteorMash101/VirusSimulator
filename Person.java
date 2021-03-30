import java.util.*;
public class Person {
    private String personID;
    private int age;
    private String medicalRisk; // make this enum; low, mid, high
    private boolean hasCovid;
    private boolean hasMaskOn;
    private boolean isVax;
    private static final int MASK_EFFICIENCY = 25; // _%?
    private static final int VAX_EFFICIENCY = 95; // 95%?
    private static final int INFECTION_RATE = 100; // 100% chance you'll get covid if you come into contact & no protect.
    
    Random rand = new Random();
    
    public Person(String personID, int age, boolean hasCovid, boolean hasMaskOn, boolean isVax) {
        this.personID = personID;
        this.age = age;
        this.hasCovid = hasCovid;
        this.hasMaskOn = hasMaskOn;
        this.isVax = isVax;
        if (0 <= age && age <= 28) {
            this.medicalRisk = "LOW";
        } else if (29 <= age && age <= 45) {
            this.medicalRisk = "MEDIUM";
        } else if (46 <= age && age <= 80) {
            this.medicalRisk = "HIGH";
        }
    }

    public String getID() {
        return personID;
    }

    public int getAge() {
        return age;
    }

    // getters & setters
    public boolean isPositive() {
        return hasCovid;
    }

    public boolean isVax() {
        return isVax;
    }

    public boolean hasMaskOn() {
        return hasMaskOn;
    }

    public String getMedicalRisk() {
        return medicalRisk;
    }

    public void setHasCovid(boolean status) {
        this.hasCovid = status;
    }

    // Assumes base case scenario (infected ppl w/ vaccine will NOT spread...)
    // can make more realistic w/ weighted mask/vax % depending on whos wearing it...
    public boolean infect(Person pInContact) {
        int infectionRate = INFECTION_RATE;
        if (pInContact.hasMaskOn() || this.hasMaskOn) { // masks reduce chance of covid by _%
            infectionRate -= MASK_EFFICIENCY;
            System.out.println("a node HAS MASK!");
            if (pInContact.hasMaskOn() && this.hasMaskOn) { // if both
                infectionRate -= MASK_EFFICIENCY;
                System.out.println("BOTH HAS MASK!");
            }
        }
        // (technically), 99.96% of vaccinated ppl don't get covid
        if (pInContact.isVax() || this.isVax) { // vaxs reduce chance of covid by _%
            infectionRate -= VAX_EFFICIENCY; // we'll use 95% reduction chance..
            System.out.println("a node HAS VAX!");
            if (pInContact.isVax() && this.isVax) { // if both
                infectionRate -= VAX_EFFICIENCY;
                System.out.println("BOTH HAS VAX!"); 
            }
        }
        // almost guranteed you won't get it if you maskOn + Vax...
        // BELOW CAUSES CRASH...?
        // switch(pInContact.getMedicalRisk()) { // some minor adjustments based on health (optional)
        //     case "LOW":
        //         infectionRate -= 7;
        //         break;
        //     case "MEDIUM":
        //         infectionRate -= 4;
        //         break;
        //     case "HIGH":
        //         infectionRate -= 1;
        //         break;
        // }
        // has to be less than b/c starts w/ 0
        boolean infected = rand.nextInt(100) < infectionRate; // 0 up to, not incl. 100 (100 numbers).
        if (infected) {
            pInContact.setHasCovid(true);
            return true;
        } else {
            return false; // no transmission - cud be lucky!
        }
    }

    @Override
    public String toString() {
        String result = "\n";
        String hasMaskOnStr = hasMaskOn ? "Yes" : "No";
        String hasCovidStr = hasCovid ? "Positive" : "Negative";
        String isVaxStr = isVax ? "Vaccinated" : "Not Vaccinated";
        result += "PersonID: " + personID + ", Age: " + age + ", Medical Risk: " + medicalRisk 
        + ", Covid Status: " + hasCovidStr + ", Mask On?: " + hasMaskOnStr + ", Vax Status: " + isVaxStr + "\n"; 
        return result;
    }

    // equals method
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Person) {
            Person otherPerson = (Person) obj;

            boolean sameID;
            boolean sameAge;
            boolean sameCovid;
            boolean sameMask;
            boolean sameVax;

            sameID = (this.personID == otherPerson.getID());
            sameAge = (this.age == otherPerson.getAge());
            sameCovid = (this.hasCovid == otherPerson.isPositive());
            sameMask = (this.hasMaskOn == otherPerson.hasMaskOn());
            sameVax = (this.isVax == otherPerson.isVax());
            return sameID && sameAge && sameCovid && sameMask && sameVax;
        } else {
            return false;
        }
    }
}