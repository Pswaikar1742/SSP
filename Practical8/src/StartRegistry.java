import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class StartRegistry {
    public static void main(String[] args) {
        int port = 40000;
        if (args.length > 0) {
            try { port = Integer.parseInt(args[0]); } catch (NumberFormatException ignored) {}
        }
        try {
            Registry reg = LocateRegistry.createRegistry(port);
            System.out.println("Started RMI registry on port " + port);
            // keep running
            synchronized (StartRegistry.class) {
                StartRegistry.class.wait();
            }
        } catch (Exception e) {
            System.out.println("Failed to start registry on port " + port + ": " + e);
            e.printStackTrace(System.out);
        }
    }
}
