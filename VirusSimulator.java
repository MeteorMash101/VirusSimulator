import java.util.*;

public class VirusSimulator {
    private static int numOfPeople = 10;
    private static double transRate = 0.85;

    public static void main(String[] args) {
        // welcomeMessage(); // welcome lets infect a population!
        // promptUser(); // input virus name & transmission rate
        ArrayList<Person> peopleList = new ArrayList<>();
        // hasCovid, hasMaskOn, isVax...
        Person p1 = new Person("p1", 20, true, true, false);
        Person p2 = new Person("p2", 55, false, true, false);
		peopleList.add(p1);
        peopleList.add(p2);
        // // Initial
        // System.out.println("B4 CONTACT: " + peopleList);
        // // when collision...
        // p1.infect(p2);
        // // After
        // System.out.println("AFTER CONTACT:" + peopleList);
        int result = 0;

        for (int i = 0; i < 100; i++) {
            p1.infect(p2);
            if (p2.isPositive()) {
                result++;
            }
            p2.setHasCovid(false);
        }
        System.out.println(result);
    }

    private static void welcomeMessage() { // welcome/inform user
        System.out.println(
                "Hello! Welcome to Virus Simulator! Let's inject a virus into a city's population! =)");
        System.out.println("Please input some information to help get us started.");
    }

    private static void promptUser() { // prompt input from user
        System.out.println("Please enter the number of People...");
        // numOfPeople = scan.nextInt();
        System.out.println("Please enter the virus transmission rate...");
        // transRate = scan.nextInt();
    }
} // end class