package org.ayo.controller;

import java.util.Set;

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

import org.ayo.model.MyResource;
import org.ayo.service.ResourceService;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;

@Path("/resources")
@ApplicationScoped
public class ResourceController {
    
	@Inject
	private ResourceService resourceService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
    public Set<MyResource> get() {
		return resourceService.getResources();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Counted(name = "postChecker", description = "How many times POST was called")
    public Set<MyResource> create(MyResource resource) {
    	return resourceService.createResource(resource);
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Timed(name = "checksTimer", description = "A measure of how long PUT takes to perform", unit = MetricUnits.MILLISECONDS)
    public Set<MyResource> update(@PathParam("id") Long id, MyResource resource) {
    	return resourceService.updateResource(id, resource);
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Set<MyResource> delete(@PathParam("id") Long id) {
        return resourceService.deleteResource(id);
    }

}