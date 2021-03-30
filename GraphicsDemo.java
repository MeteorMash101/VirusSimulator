import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import java.util.ArrayList;

public class GraphicsDemo extends JPanel implements ActionListener {
    private Timer timer;
    private static final int DELAY = 40;
    private static final int CIRCLE_DIM = 25;
    private static final double INFECT_DIST = 30;
    private static final int NUM_OF_PPL = 21;
    
    // 4 Edges...
    private Node pNodeE1;
    private Node pNodeE2;
    private Node pNodeE3;
    private Node pNodeE4;

    private int numOfPeople = NUM_OF_PPL;
    private int numOfNodes = NUM_OF_PPL;
    private ArrayList<Person> peopleList = new ArrayList<Person>();
    private ArrayList<Node> nodeList = new ArrayList<Node>();
    private Random rd = new Random();

    // Colors...(initialization)
    private static final Color BKG_CLR = new Color(0,0,0);
    private static final Color EDGE_CLR = new Color(155,176,219); // greyish
    private static final Color INFCTD_CLR = Color.RED;
    private static final Color NOT_INF_CLR = Color.WHITE;
    private static final Color NI_MASK_CLR = new Color(126,196,108); // light green
    private static final Color NI_VAX_CLR = Color.GREEN;

    public GraphicsDemo() {
        generatePeople();
        generateNodes();
        timer = new Timer(DELAY,this);
        // hasCovid, hasMaskOn, isVax...
        Person pEdge = new Person("notAPerson", 0, false, true, true);
		// 4 Edges...
        pNodeE1 = new Node(0,0,CIRCLE_DIM,CIRCLE_DIM,EDGE_CLR,pEdge);
        pNodeE2 = new Node(600,0,CIRCLE_DIM,CIRCLE_DIM,EDGE_CLR,pEdge);
        pNodeE3 = new Node(600,600,CIRCLE_DIM,CIRCLE_DIM,EDGE_CLR,pEdge);
        pNodeE4 = new Node(0,600,CIRCLE_DIM,CIRCLE_DIM,EDGE_CLR,pEdge);
        timer.start();
    }
    public void generatePeople() {
        // hasCovid, hasMaskOn, isVax...
        // make fewer ppl have covid...?
        // int temp = 0;
        // boolean infectedBool = false;
        // int maxAge = 100;
        // for (int i = 0; i < numOfPeople; i++) {
        //     temp = rd.nextInt(100);
        //     infectedBool = (temp < 35) ? true : false;
        //     peopleList.add(new Person(stringHelper(i), 1 + rd.nextInt(maxAge), infectedBool, rd.nextBoolean(), rd.nextBoolean()));
        // }

        // * For Testing *
        // Four types of positive;
        // Person p1 = new Person("p1", 23, true, false, false);
        // Person p2 = new Person("p2", 55, true, true, false);
        // Person p3 = new Person("p3", 22, true, true, true);
        // Person p4 = new Person("p4", 88, true, false, true);

        // Test1; Has Covid & no mask/vax.
        Person p1 = new Person("p1", 23, true, false, false);
        Person p2 = new Person("p2", 55, true, false, false);
        Person p3 = new Person("p3", 22, true, false, false);
        Person p4 = new Person("p4", 88, true, false, false);
        Person p5 = new Person("p1", 23, true, false, false);
        Person p6 = new Person("p2", 55, true, false, false);
        Person p7 = new Person("p3", 22, true, false, false);
        Person p8 = new Person("p4", 88, true, false, false);
        
        // Negative control group; 
        Person p9 = new Person("pn", 5, false, true, false);
        Person p10 = new Person("pn", 66, false, true, false);
        Person p11 = new Person("pn", 66, false, true, false);
        Person p12 = new Person("pn", 66, false, true, false);
        Person p13 = new Person("pn", 66, false, true, false);
        // Negative control group; (DUPLICATE)
        Person p14 = new Person("pn", 5, false, true, false);
        Person p15 = new Person("pn", 66, false, true, false);
        Person p16 = new Person("pn", 66, false, true, false);
        Person p17 = new Person("pn", 66, false, true, false);
        Person p18 = new Person("pn", 5, false, true, false);
        Person p19 = new Person("pn", 66, false, true, false);
        Person p20 = new Person("pn", 66, false, true, false);
        Person p21 = new Person("pn", 66, false, true, false);
        

        peopleList.add(p1);
        peopleList.add(p2);
        peopleList.add(p3);
        peopleList.add(p4);
        peopleList.add(p5);
        peopleList.add(p6);
        peopleList.add(p7);
        peopleList.add(p8);
        peopleList.add(p9);
        peopleList.add(p10);
        peopleList.add(p11);
        peopleList.add(p12);
        peopleList.add(p13);
        peopleList.add(p14);
        peopleList.add(p15);
        peopleList.add(p16);
        peopleList.add(p17);
        peopleList.add(p18);
        peopleList.add(p19);
        peopleList.add(p20);
        peopleList.add(p21);
    }

    public String stringHelper(int input) {
        String p = "p" + input;
        return p;
    }

    public void generateNodes() {
        int min = 20;
        int max = 550;
        for (int i = 0; i < numOfNodes; i++) {
            // Min + (Math.random() * (Max - Min))
            int randomX = (int) (min + (Math.random() * (max - min)));
            int randomY = (int) (min + (Math.random() * (max - min)));
            Color colorOpt = (peopleList.get(i)).isPositive() ? INFCTD_CLR : NOT_INF_CLR;
            // if not pos, mask on, not vax...
            if (colorOpt == NOT_INF_CLR && (peopleList.get(i)).hasMaskOn() && !((peopleList.get(i)).isVax())) { 
                colorOpt = NI_MASK_CLR;
            }
            // if not pos, mask on/off, and JUST vaccinated...
            if (colorOpt == NOT_INF_CLR && (peopleList.get(i)).isVax()) { 
                colorOpt = NI_VAX_CLR;
            }
            // int x, int y, int width, int height, Color color
            nodeList.add(new Node(randomX,randomY,CIRCLE_DIM,CIRCLE_DIM,colorOpt,peopleList.get(i)));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Node nodeObj : nodeList) { // moves every node...
            nodeObj.move();
        }
        boolean gotInfected = false;
        for (int i = 0; i < nodeList.size(); i++) {
            for (int j = 0; j < nodeList.size(); j++) {
                if (nodeList.get(i) != nodeList.get(j) && checkCollision(nodeList.get(i), nodeList.get(j))) { 
                    // guranteed ONE is pos...other neg.
                    if (nodeList.get(i).getPerson().isPositive()) { // i is pos.
                        System.out.println("Contact!");
                        gotInfected = nodeList.get(i).getPerson().infect(nodeList.get(j).getPerson());
                        if (gotInfected) {
                            nodeList.get(j).setColor(INFCTD_CLR);
                            System.out.println("infection!");
                        }
                    } else { // j is pos.
                        System.out.println("Contact!");
                        gotInfected = nodeList.get(j).getPerson().infect(nodeList.get(i).getPerson());
                        if (gotInfected) {
                            nodeList.get(i).setColor(INFCTD_CLR);
                            System.out.println("infection!");
                        }
                    }
                }
            }
        }
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(BKG_CLR);
        Graphics2D g2D = (Graphics2D) g;
        for (Node nodeObj : nodeList) {
            g2D.setColor(nodeObj.getColor());
            g2D.fillOval(nodeObj.getX(),nodeObj.getY(),nodeObj.getWidth(),nodeObj.getHeight());
        }

        // 4 Edges...
		g2D.setColor(pNodeE1.getColor());
		g2D.fillOval(pNodeE1.getX(),pNodeE1.getY(),pNodeE1.getWidth(),pNodeE1.getHeight());

		g2D.setColor(pNodeE2.getColor());
		g2D.fillOval(pNodeE2.getX(),pNodeE2.getY(),pNodeE2.getWidth(),pNodeE2.getHeight());

		g2D.setColor(pNodeE3.getColor());
		g2D.fillOval(pNodeE3.getX(),pNodeE3.getY(),pNodeE3.getWidth(),pNodeE3.getHeight());

		g2D.setColor(pNodeE4.getColor());
		g2D.fillOval(pNodeE4.getX(),pNodeE4.getY(),pNodeE4.getWidth(),pNodeE4.getHeight());
    }

    public boolean checkCollision(Node pNode1, Node pNode2) {
        // if both pos, no need to checkColl. (no 'new' infection).
        // (pNode1.getPerson()).isPositive()
        if ((pNode1.getPerson()).isPositive() && (pNode2.getPerson()).isPositive()) { 
            return false;
        }
		double aSqrd = 0;
		double bSqrd = 0;
		double temp = 0;
        temp = Math.abs(pNode1.getX() - pNode2.getX());
		aSqrd = temp * temp;
        temp = Math.abs(pNode1.getY() - pNode2.getY());
		bSqrd = temp * temp;
		double c = Math.sqrt(aSqrd+bSqrd);
        // if less than 6ft apart && ONE of them positive...(see above)
		if (c <= INFECT_DIST && ((pNode1.getPerson()).isPositive() || (pNode1.getPerson()).isPositive())) { // pythagorean theorem works!
			return true;
		}
		return false; // either more than 6ft / both neg.
	}
}