package tech.lacambra;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import javax.ws.rs.core.Response;

public class HelloRemoteIT {

    @Rule
    public HelloClient helloClient = new HelloClient();

    @Test
    public void sayRemoteHello() {
        System.out.println("Running test");
        Response response = helloClient.sayHello();
        Assert.assertEquals(200, response.getStatus());
    }

}