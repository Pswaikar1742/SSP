import java.rmi.Naming;
import java.util.Scanner;

public class Client {
    private static int readInt(Scanner sc) {
        while (true) {
            String line = sc.nextLine();
            try {
                return Integer.parseInt(line.trim());
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid integer: ");
            }
        }
    }

    public static void main(String[] args) {
        int port = 40000;
        if (args.length > 0) {
            try { port = Integer.parseInt(args[0]); } catch (NumberFormatException ignored) {}
        }
        int a = 0, b = 0;
        boolean gotArgsNumbers = false;
        if (args.length >= 3) {
            try {
                a = Integer.parseInt(args[1]);
                b = Integer.parseInt(args[2]);
                gotArgsNumbers = true;
            } catch (NumberFormatException ignored) {
                gotArgsNumbers = false;
            }
        }

        try (Scanner sc = new Scanner(System.in)) {
            if (!gotArgsNumbers) {
                System.out.print("Enter first integer: ");
                a = readInt(sc);
                System.out.print("Enter second integer: ");
                b = readInt(sc);
            }

            Addition stub = (Addition) Naming.lookup("rmi://localhost:" + port + "/AdditionService");
            int result = stub.add(a, b);
            System.out.println("Result: " + result);

        } catch (Exception e) {
            System.out.println("Client error: " + e);
            e.printStackTrace(System.out);
        }
    }
}
