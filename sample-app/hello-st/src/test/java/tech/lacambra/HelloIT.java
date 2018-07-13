package tech.lacambra;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import javax.ws.rs.core.Response;

public class HelloIT {

    @Rule
    public HelloClient helloClient = new HelloClient();

    @Test
    public void sayHello() {
        Response response = helloClient.sayHello();
        Assert.assertEquals(200, response.getStatus());
    }

}