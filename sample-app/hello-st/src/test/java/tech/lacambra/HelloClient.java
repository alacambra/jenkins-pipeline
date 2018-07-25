package tech.lacambra;

import org.junit.rules.ExternalResource;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.concurrent.locks.LockSupport;
import java.util.regex.Pattern;

public class HelloClient extends ExternalResource {

    private static final int STARTUP_TIMEOUT = 30;
    private static final int STARTUP_PING_DELAY = 30;

    private String helloUri = "http://localhost:8080/app/resources/hello";
    private Pattern helloUriPattern = Pattern.compile(helloUri + "/[a-z0-9\\-]+");
    private WebTarget helloTarget;
    private Client client;

    public Response sayHello() {
        return helloTarget.request().get();
    }

    @Override
    protected void before() throws Throwable {
        client = ClientBuilder.newClient();
        helloTarget = client.target(URI.create(helloUri));
        waitForApplicationStartUp();
    }

    private void waitForApplicationStartUp() {
        LockSupport.parkNanos(1_000_000_000 * STARTUP_PING_DELAY);
//        final long timeout = System.currentTimeMillis() + STARTUP_TIMEOUT * 1000;
//        while (helloTarget.request().head().getStatusInfo().getFamily() != Response.Status.Family.SUCCESSFUL) {
//            System.out.println("waiting for application startup ...");
//            LockSupport.parkNanos(1_000_000_000 * STARTUP_PING_DELAY);
//            if (System.currentTimeMillis() > timeout)
//                throw new AssertionError("Application wasn't started before timeout!");
//        }
    }
}
