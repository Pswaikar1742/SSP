package com.greenscale.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface NodeMonitor extends Remote {
    double getTemperature() throws RemoteException;
    boolean isAvailable() throws RemoteException;
}
