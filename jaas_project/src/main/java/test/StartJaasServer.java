package test;

import org.apache.ignite.Ignition;

public class StartJaasServer {
    public static void main(String[] args) {
        Ignition.start("jaas_server.xml");
    }
}
