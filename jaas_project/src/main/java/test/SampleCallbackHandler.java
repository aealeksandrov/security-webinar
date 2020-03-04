package test;

import org.apache.ignite.plugin.security.AuthenticationContext;

import javax.security.auth.callback.*;

/**
 * Demonstrates how to write a callback handler for use with SASL.
 * Used with UseCallback.java, GssExample.java, and Mutual.java.
 * <p>
 * Standalone test: java SampleCallbackHandler
 */
public class SampleCallbackHandler implements CallbackHandler {
    private final AuthenticationContext authenticationContext;

    public SampleCallbackHandler(AuthenticationContext authenticationContext) {
        this.authenticationContext = authenticationContext;
    }

    public void handle(Callback[] callbacks)
            throws UnsupportedCallbackException {
        String username = (String) authenticationContext.credentials().getLogin();
        String pw =(String) authenticationContext.credentials().getPassword();

        for (int i = 0; i < callbacks.length; i++) {
            if (callbacks[i] instanceof NameCallback) {
                NameCallback cb = (NameCallback) callbacks[i];
                System.out.println("LDAP Authentication for user - " + username);
                cb.setName(username);
            } else if (callbacks[i] instanceof PasswordCallback) {
                PasswordCallback cb = (PasswordCallback) callbacks[i];
                char[] passwd = null;
                if (pw == null || pw.isEmpty()) {
                    System.out.println("LDAP password for user - " + username + " will be gotten from callback");

                    //do some logic here
                    if ("vagrant".equals(username))
                        passwd = "vagrant".toCharArray();
                    else if ("jane.doe".equals(username))
                        passwd = "HeyH0Password".toCharArray();
                    else
                        passwd = "".toCharArray();
                } else {
                    System.out.println("LDAP password for user - " + username + " will be gotten from XML");

                    passwd =  pw.toCharArray();
                }

                cb.setPassword(passwd);
            } else {
                throw new UnsupportedCallbackException(callbacks[i]);
            }
        }
    }
}
