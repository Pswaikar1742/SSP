package com.greenscale;

import com.greenscale.rmi.NodeMonitorImpl;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class App {
    public static void main(String[] args) throws Exception {
        // Start RMI registry and bind Node1
        try {
            java.rmi.registry.LocateRegistry.createRegistry(1099);
        } catch (Exception ignored) {
        }
        NodeMonitorImpl node = new NodeMonitorImpl();
        java.rmi.Naming.rebind("rmi://localhost/Node1", node);
        System.out.println("RMI Node1 bound on port 1099");

        // Start embedded Tomcat
        Tomcat tomcat = new Tomcat();

        // Explicitly create and configure an HTTP connector on port 8080 so Tomcat
        // binds to the intended port instead of an ephemeral port.
        org.apache.catalina.connector.Connector connector = new org.apache.catalina.connector.Connector();
        connector.setPort(8080);
        tomcat.getService().addConnector(connector);
        tomcat.setConnector(connector);

        String webappDir = new File("src/main/webapp").getAbsolutePath();
        System.out.println("Using webapp dir: " + webappDir);

        // If a bundled sqlite JDBC jar exists in the project root, copy it
        // into the webapp's WEB-INF/lib so Tomcat will load it into the webapp classloader.
        try {
            java.nio.file.Path src = java.nio.file.Paths.get("sqlite-jdbc-3.47.0.0.jar");
            java.nio.file.Path libDir = java.nio.file.Paths.get(webappDir, "WEB-INF", "lib");
            if (java.nio.file.Files.exists(src)) {
                java.nio.file.Files.createDirectories(libDir);
                java.nio.file.Path dest = libDir.resolve(src.getFileName());
                java.nio.file.Files.copy(src, dest, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Copied sqlite driver to: " + dest);
            }
        } catch (Exception e) {
            System.err.println("Warning: failed to copy sqlite driver into webapp lib: " + e.getMessage());
        }

        Context ctx = tomcat.addWebapp("", webappDir);
        ctx.setParentClassLoader(App.class.getClassLoader());

        tomcat.start();
        System.out.println("Embedded Tomcat started on port 8080");
        tomcat.getServer().await();
    }
}
