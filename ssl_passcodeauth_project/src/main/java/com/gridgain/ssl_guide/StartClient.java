package com.gridgain.ssl_guide;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.ignite.Ignition;

public class StartClient {
    public static void main(String[] args) throws ClassNotFoundException {
        String pathToClientJks = args.length > 4 ? args[0] : "src/scripts/node.jks";
        String clientPassword = args.length > 4 ? args[1] : "qwerty";
        String pathToTrustStoreJks = args.length > 4 ? args[2] : "src/scripts/trust.jks";
        String trustorePassword = args.length > 4 ? args[3] : "qwerty";

        Ignition.start("client.xml");

        // Register JDBC driver.
        Class.forName("org.apache.ignite.IgniteJdbcThinDriver");

        // Open the JDBC connection.
        try (Connection conn = DriverManager.getConnection("jdbc:ignite:thin://127.0.0.1:10800?" +
            "user=client&password=password&" +
            "sslMode=require&" +
            "sslClientCertificateKeyStoreUrl=" + pathToClientJks + "&" +
            "sslClientCertificateKeyStorePassword=" + clientPassword + "&" +
            "sslTrustCertificateKeyStoreUrl=" + pathToTrustStoreJks + "&" +
            "sslTrustCertificateKeyStorePassword=" + trustorePassword)) {

            conn.createStatement().executeQuery("select 1");

            System.out.println("Done");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
