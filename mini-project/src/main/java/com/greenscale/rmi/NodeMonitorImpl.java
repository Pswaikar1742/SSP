package com.greenscale.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ThreadLocalRandom;

public class NodeMonitorImpl extends UnicastRemoteObject implements NodeMonitor {

    private static final long serialVersionUID = 1L;

    public NodeMonitorImpl() throws RemoteException {
        super();
    }

    @Override
    public double getTemperature() throws RemoteException {
        return ThreadLocalRandom.current().nextDouble(40.0, 95.0);
    }

    @Override
    public boolean isAvailable() throws RemoteException {
        // Randomize availability for simulation
        return ThreadLocalRandom.current().nextBoolean();
    }
}
