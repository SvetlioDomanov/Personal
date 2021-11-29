package DistanceBetweenTwoPoints;

public class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double calculateDistance(Point point2){
        double xDistance = this.x - point2.getX();
        double yDistance = this.y - point2.getY();
        return Math.sqrt(xDistance*xDistance + yDistance*yDistance);

    }

}
