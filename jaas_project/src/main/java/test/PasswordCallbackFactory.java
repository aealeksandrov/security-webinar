package test;

import org.apache.ignite.IgniteException;
import org.apache.ignite.plugin.security.AuthenticationContext;
import org.apache.ignite.plugin.security.SecuritySubjectType;
import org.gridgain.grid.security.jaas.JaasCallbackHandlerFactory;

import javax.security.auth.callback.CallbackHandler;

public class PasswordCallbackFactory implements JaasCallbackHandlerFactory {
    @Override
    public boolean supported(SecuritySubjectType securitySubjectType) {
        return true;
    }

    @Override
    public CallbackHandler newInstance(AuthenticationContext authenticationContext) throws IgniteException {
        return new SampleCallbackHandler(authenticationContext);
    }
}
