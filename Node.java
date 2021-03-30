import java.util.*;
import java.awt.*;
import java.awt.event.*;


public class Node {
    private int x;
    private int y;
    private int width;
    private int height;
	private Color color;
    private Person personObj;

    private static final int VEL_CTRL = 3; // like "steps"
    private static final int MIN = 0;
    private static final int MAX = 575; // WIDTH/HEIGHT - width of Node

	public Node(int x, int y, int width, int height, Color color, Person personObj){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.personObj = personObj;
	}

    public void move() {
        int newX = 0;
        int newY = 0;
        Random rd = new Random();
        // 0 - 9 (default) {positive. num}
        int velX = rd.nextInt(VEL_CTRL); 
        int velY = rd.nextInt(VEL_CTRL);

        boolean randomBool = rd.nextBoolean();
        // BOUNDS CHECK X.
        if ((this.x - velX) < MIN && randomBool == false) { 
            velX = -velX; 
        }
        if ((this.x + velX) > MAX && randomBool == true) {
            velX = -velX;
        }
        // BOUNDS CHECK Y.
        if ((this.y - velY) < MIN && randomBool == false) { 
            velY = -velY;
        }
        if ((this.y + velY) > MAX && randomBool == true) {
            velY = -velY;
        }
        newX = randomBool ? (this.x + velX) : (this.x - velX);
        newY = randomBool ? (this.y + velY) : (this.y - velY);
        this.x = newX;
        // gives more realistic simulation...
        if (rd.nextInt(100) < 50) { // half the time y shudn't change...
            this.y = this.y;
        } else {
            this.y = newY;
        }
    }

    public void setColor(Color color) {
        this.color = color;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public int getWidth() {
        return this.width;
    }
    public int getHeight() {
        return this.height;
    }
    public Color getColor() {
        return this.color;
    }
    public Person getPerson() {
        return this.personObj;
    }

    // equals method
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Node) {
            Node otherNode = (Node) obj;

            boolean sameX;
            boolean sameY;
            boolean sameWidth;
            boolean sameHeight;
            boolean sameColor;
            boolean samePersonObj;

            sameX = (this.x == otherNode.getX());
            sameY = (this.y == otherNode.getY());
            sameWidth = (this.width == otherNode.getWidth());
            sameHeight = (this.height == otherNode.getHeight());
            sameColor = (this.color == otherNode.getColor());
            samePersonObj = (this.personObj.equals(otherNode.getPerson()));
            return sameX && sameY && sameWidth && sameHeight && sameColor && samePersonObj;

        } else {
            return false;
        }
    }
}
