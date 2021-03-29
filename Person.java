import java.util.*;
public class Person {
    private String personID;
    private int age;
    private String medicalRisk; // make this enum; low, mid, high
    private boolean hasCovid;
    private boolean hasMaskOn;
    private boolean isVax;
    
    Random rand = new Random();
    
    public Person(String personID, int age, boolean hasCovid, boolean hasMaskOn, boolean isVax) {
        this.personID = personID;
        this.age = age;
        if (0 <= age && age <= 28) {
            this.medicalRisk = "LOW";
        } else if (29 <= age && age <= 45) {
            this.medicalRisk = "MEDIUM";
        } else if (46 <= age && age <= 80) {
            this.medicalRisk = "HIGH";
        }
        this.hasCovid = hasCovid;
        this.hasMaskOn = hasMaskOn;
        this.isVax = isVax;
    }

    public String getID() {
        return personID;
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

    public int getAge() {
        return age;
    }

    public String getMedicalRisk() {
        return medicalRisk;
    }

    public void infect(Person pInContact) {
        int maskEff = 40; // 65%?
        int vaxEff = 95; // 95%?
        if (!pInContact.isPositive()) {
            if (this.hasCovid) {
                int infectionRate = 100; // 100% chance you'll get covid if you come into contact.
                int chance = rand.nextInt(100); // 0 up to, not incl. 100 (100 numbers).
                if (pInContact.hasMaskOn || this.hasMaskOn) { // masks reduce chance of covid by 65%
                    chance -= maskEff;
                    if (pInContact.hasMaskOn && this.hasMaskOn) { // if both
                        chance -= maskEff;
                    }
                }
                if (pInContact.isVax || this.isVax) { // (technically), 99.96% of vaccinated ppl don't get covid
                    chance -= vaxEff; // we'll use 95% reduction chance..
                    if (pInContact.isVax && this.isVax) { // if both
                        chance -= vaxEff;
                    }
                }
                // almost guranteed you won't get it if you maskOn + Vax...
                switch(pInContact.medicalRisk) { // some adjustments based on health (optional)
                    case "LOW":
                        chance -= 7;
                        break;
                    case "MEDIUM":
                        chance -= 4;
                        break;
                    case "HIGH":
                        chance -= 1;
                        break;
                }
                // has to be less than b/c starts w/ 0
                // if neg. obv they will be completely safe.
                boolean infected = (0 <= chance && chance < infectionRate); 
                if (infected) {
                    pInContact.hasCovid = true;
                }
            }
        }
    }

    public void setHasCovid(boolean status) {
        this.hasCovid = status;
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
}