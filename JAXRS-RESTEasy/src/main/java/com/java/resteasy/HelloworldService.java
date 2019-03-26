package com.java.resteasy;
 
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
 
@Path("/")
public class HelloworldService {
 
    @GET
    @Path("/")
    public Response responseMsg( @PathParam("") String pathParameter,
            @DefaultValue("Nothing to say") @QueryParam("queryParameter") String queryParameter) {
 
        String response = "Hello World!";
 
        return Response.status(200).entity(response).build();
    }
}