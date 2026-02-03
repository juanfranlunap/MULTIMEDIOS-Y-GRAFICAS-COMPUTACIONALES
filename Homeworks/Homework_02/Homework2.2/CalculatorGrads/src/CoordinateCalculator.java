import java.util.Scanner;

public class CoordinateCalculator {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int option = 0;

        while (option != 3) {

            System.out.println("\n=== Coordinate Converter ===");
            System.out.println("1. Polar to Cartesian");
            System.out.println("2. Cartesian to Polar");
            System.out.println("3. Exit");
            System.out.println("If you want to use decimal points, please use a coma (,) ");
            System.out.print("Choose an option: ");

            option = sc.nextInt();

            if (option == 1) {
                System.out.print("Enter radius (r): ");
                double r = sc.nextDouble();

                System.out.print("Enter angle (theta in radians): ");
                double theta = sc.nextDouble();

                double x = r * Math.cos(theta);
                double y = r * Math.sin(theta);

                System.out.println("\nCartesian Coordinates:");
                System.out.println("x = " + x);
                System.out.println("y = " + y);

            } else if (option == 2) {
                
                System.out.print("Enter x: ");
                double x = sc.nextDouble();

                System.out.print("Enter y: ");
                double y = sc.nextDouble();

                double r = Math.sqrt(x * x + y * y);
                double theta = Math.atan2(y, x);

                System.out.println("\nPolar Coordinates:");
                System.out.println("r = " + r);
                System.out.println("theta (radians) = " + theta);

            } else if (option == 3) {
                System.out.println("Closing program...");
            } else {
                System.out.println("Invalid option. Try again.");
            }
        }

        sc.close();
    }
}
