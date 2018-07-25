package tech.lacambra;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HelloResourceIT {

    HelloResource cut;

    @Before
    public void setUp() throws Exception {
        cut = new HelloResource();
    }

    @Test
    public void sayHello() {
        assertEquals(200, cut.sayHello().getStatus());
    }
}