import java.util.*;

public class VirusTrialTesting {
    public static void main(String[] args) {
        // welcomeMessage();
        // promptUser();
        // hasCovid, hasMaskOn, isVax...
        Person p1 = new Person("p1", 20, true, false, false);
        Person p2 = new Person("p2", 55, false, true, false);
        //  *TESTING*
        int result = 0;
        for (int i = 0; i < 100; i++) { // out of 100 trials
            if (p1.infect(p2)) {
                result++;
            }
            p2.setHasCovid(false);
        }
        System.out.println(result); // # of ppl infected...
    }
    // private static void welcomeMessage() { // welcome/inform user
    //     System.out.println(
    //             "Hello! Welcome to Virus Simulator! Let's inject a virus into a city's population! =)");
    //     System.out.println("Please input some information to help get us started.");
    // }

    // private static void promptUser() { // prompt input from user
    //     System.out.println("Please enter the number of People...");
    //     // numOfPeople = scan.nextInt();
    //     System.out.println("Please enter the virus transmission rate...");
    //     // transRate = scan.nextInt();
    // }
} // end class