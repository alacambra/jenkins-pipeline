package tech.lacambra;

import org.junit.rules.ExternalResource;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.regex.Pattern;

public class HelloClient extends ExternalResource {

    private static final int STARTUP_TIMEOUT = 30;
    private static final int STARTUP_PING_DELAY = 30;

    private String testServer = "localhost";
    private Supplier<String> helloUri = () -> "http://" + testServer + ":8080/app/resources/hello";
    private Pattern helloUriPattern = Pattern.compile(helloUri + "/[a-z0-9\\-]+");
    private WebTarget helloTarget;
    private Client client;

    public Response sayHello() {
        return helloTarget.request().get();
    }

    @Override
    protected void before() throws Throwable {
        client = ClientBuilder.newClient();
        testServer = Optional.ofNullable(System.getenv("TEST_SERVER")).orElse(testServer);

        System.out.println("Running test on uri=" + helloUri.get());
        helloTarget = client.target(URI.create(helloUri.get()));
        waitForApplicationStartUp();
    }

    private void waitForApplicationStartUp() {
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
//        final long timeout = System.currentTimeMillis() + STARTUP_TIMEOUT * 1000;
//        while (helloTarget.request().head().getStatusInfo().getFamily() != Response.Status.Family.SUCCESSFUL) {
//            System.out.println("waiting for application startup ...");
//            LockSupport.parkNanos(1_000_000_000 * STARTUP_PING_DELAY);
//            if (System.currentTimeMillis() > timeout)
//                throw new AssertionError("Application wasn't started before timeout!");
//        }
    }
}
