package DistanceBetweenTwoPoints;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split("\\s+");
        Point point1 = new Point(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
        input = scanner.nextLine().split("\\s+");
        Point point2 = new Point(Integer.parseInt(input[0]), Integer.parseInt(input[1]));

        System.out.println(calculateDistance(point1,point2));
        System.out.println(point1.calculateDistance(point2));

    }
    public static double calculateDistance(Point point1, Point point2){
        double xDistance = point1.getX() - point2.getX();
        double yDistance = point1.getY() - point2.getY();
        return Math.sqrt(xDistance*xDistance + yDistance*yDistance);
    }
}
