import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        int port = 40000;
        if (args.length > 0) {
            try { port = Integer.parseInt(args[0]); } catch (NumberFormatException ignored) {}
        }
        try {
            AdditionImpl obj = new AdditionImpl();

            // If an RMI registry is already running on the port, use it.
            try {
                Registry existing = LocateRegistry.getRegistry(port);
                existing.list(); // throws RemoteException if no registry
                System.out.println("Using existing RMI registry on port " + port);
            } catch (RemoteException re) {
                LocateRegistry.createRegistry(port);
                System.out.println("Created internal RMI registry on port " + port);
            }

            Naming.rebind("rmi://localhost:" + port + "/AdditionService", obj);

            System.out.println("Addition Server is ready on port " + port);
        } catch (Exception e) {
            System.out.println("Server error: " + e);
            e.printStackTrace(System.out);
        }
    }
}
