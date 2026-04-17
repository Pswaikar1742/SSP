package com.greenscale.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RmiServer {
    public static void main(String[] args) {
        try {
            // create registry on port 1099
            LocateRegistry.createRegistry(1099);

            // create and bind NodeMonitor implementation
            NodeMonitorImpl node = new NodeMonitorImpl();
            Registry registry = LocateRegistry.getRegistry(1099);
            registry.rebind("Node1", node);

            System.out.println("RMI server bound 'Node1' and ready on port 1099.");

            // keep the server running
            new java.util.concurrent.CountDownLatch(1).await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
