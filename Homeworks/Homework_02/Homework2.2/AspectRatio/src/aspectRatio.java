import java.util.Scanner;

public class aspectRatio {

    // Greatest Common Divisor
    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        char choice = 'y';

        while (choice == 'y' || choice == 'Y') {

            System.out.print("Enter the width: ");
            int width = input.nextInt();

            System.out.print("Enter the height: ");
            int height = input.nextInt();

            int divisor = gcd(width, height);
            int aspectWidth = width / divisor;
            int aspectHeight = height / divisor;

            System.out.println("Aspect Ratio: " + aspectWidth + ":" + aspectHeight);

            System.out.print("Do you want to calculate another aspect ratio? (y/n): ");
            choice = input.next().charAt(0);
        }

        input.close();
        System.out.println(".......");
    }
}
