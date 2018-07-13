package tech.lacambra;

import javax.json.Json;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("hello")
public class HelloResource {

    @GET
    public Response sayHello() {
        return Response.ok(
                Json.createObjectBuilder()
                        .add("hello", "hello")
                        .build())
                .build();
    }
}