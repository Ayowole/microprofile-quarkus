package org.ayo.controller;

import java.net.URISyntaxException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.ayo.model.MyResource;
import org.ayo.service.ResourceService;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;

@Path("/resources")
@ApplicationScoped
public class ResourceController {
    
	@Inject
	ResourceService resourceService;
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
    public Response get() {
		return Response.ok(resourceService.getResources()).build();
    }

	
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Counted(name = "postChecker", description = "How many times POST was called")
    public Response create(MyResource resource) throws URISyntaxException {
    	return Response.status(201).entity(resourceService.createResource(resource)).build();
    }

    
    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Timed(name = "checksTimer", description = "A measure of how long PUT takes to perform", unit = MetricUnits.MILLISECONDS)
    public Response update(@PathParam("id") Long id, MyResource resource) {
    	return Response.ok(resourceService.updateResource(id, resource)).build();
    }

    
    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        return Response.accepted().entity(resourceService.deleteResource(id)).build();
    }

}