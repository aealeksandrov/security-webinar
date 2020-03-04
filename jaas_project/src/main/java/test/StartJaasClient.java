package test;

import org.apache.ignite.Ignition;

public class StartJaasClient {
    public static void main(String[] args) {
        Ignition.start("jaas_client.xml");
    }
}
