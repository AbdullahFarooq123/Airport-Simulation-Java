import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Please set name of your company : ");
        String name = new Scanner(System.in).nextLine();
        int planes = 0;
        int flights = 0;
        boolean inputMismatch;
        do {
            try {
                System.out.println("How many planes your company have : ");
                planes = new Scanner(System.in).nextInt();
                inputMismatch = true;
            }
            catch (Exception exception){
                System.out.println("Input mismatch!");
                inputMismatch = false;
            }
        }while (!inputMismatch);
        do {
            try {
                System.out.println("How many flights your company can handle : ");
                flights = new Scanner(System.in).nextInt();
                inputMismatch = true;
            }
            catch (Exception exception){
                System.out.println("Input mismatch!");
                inputMismatch = false;
            }
        }while (!inputMismatch);
        new AirlineCompany(name, new Time(0,0,0),flights, planes).run();
    }
}
