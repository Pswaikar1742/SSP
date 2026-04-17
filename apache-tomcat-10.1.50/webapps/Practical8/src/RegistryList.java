import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RegistryList {
    public static void main(String[] args) {
        int port = 40000;
        if (args.length > 0) {
            try { port = Integer.parseInt(args[0]); } catch (NumberFormatException ignored) {}
        }
        try {
            Registry reg = LocateRegistry.getRegistry(port);
            String[] names = reg.list();
            System.out.println("Registry on port " + port + " contains:");
            for (String n : names) System.out.println(" - " + n);
        } catch (Exception e) {
            System.out.println("Failed to list registry on port " + port + ": " + e);
        }
    }
}
