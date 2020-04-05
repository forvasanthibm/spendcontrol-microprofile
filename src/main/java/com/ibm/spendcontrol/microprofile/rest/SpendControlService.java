package com.ibm.spendcontrol.microprofile.rest;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.http.HttpStatus;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import com.ibm.spendcontrol.microprofile.bean.Spend;
import com.ibm.spendcontrol.microprofile.persistence.SpendDAO;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;

@ApplicationScoped
@Path("/")
@Tag(name = "Spend Control Services", description = "Spend Control application on MicroProfile")
public class SpendControlService {
	
	@Inject
	SpendDAO spendDAO;
	
	
	/*
	 * @GET @Path("/hello")
	 * 
	 * @Operation(description = "Get the value from the service")
	 * 
	 * @APIResponses({
	 * 
	 * @APIResponse(responseCode = "200", description =
	 * "Successful, returning the value") })
	 * 
	 * @Produces("text/plain") public Response doGet() { return
	 * Response.ok("Hello from Thorntail!").build(); }
	 */
	
	@POST @Path("/create/spend")
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@APIResponses({
        @APIResponse(responseCode = "201", description = "Record created")
    })
	public Response createSpend(Spend spend) {
		
		System.out.println("Creating Spend " + spend);
		String id = "S" + "-" + System.currentTimeMillis();
		spend.setId(id);
		spendDAO.createSpend(spend);		
		return Response.ok().status(HttpStatus.SC_CREATED).build();
		
	}
	
	@GET @Path("/get/spend")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Object> getAllSpends() {
		List<Object> spends = spendDAO.getAllSpends();
		return spends;
	}
	
	@GET @Path("/get/spend/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Object> findSpendById(@PathParam("id") String id){
		
		List<Object> l = spendDAO.findSpendById(id);
		if(l.size() > 0) {
			return l;
		}
		else {
			l.add("Given id not found");
			return l;
		}
	}
	
	@PUT @Path("/update/spend/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@APIResponses({
        @APIResponse(responseCode = "202", description = "Update Successful"),
        @APIResponse(responseCode = "304", description = "Record not found")
    })
	public Response updateSpend(Spend spend, @PathParam("id") String id) {
		
		boolean result = spendDAO.updateSpend(spend, id);
		if(result) {
			return Response.ok().status(HttpStatus.SC_ACCEPTED, "Update Successful").build();
		}
		else {
			return Response.ok().status(HttpStatus.SC_NOT_MODIFIED, "Record not found").build();
		}
	}
	
	@DELETE @Path("/delete/spend/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@APIResponses({
        @APIResponse(responseCode = "202", description = "Delete Successful"),
        @APIResponse(responseCode = "304", description = "Record not found")
    })
	public Response deleteSpend(@PathParam("id") String id) {
		if(spendDAO.findSpendById(id).isEmpty()) {
			return Response.ok().status(HttpStatus.SC_NOT_MODIFIED, "Record not found").build();
		}
		spendDAO.deleteSpend(id);
		return Response.ok().status(HttpStatus.SC_ACCEPTED, "Delete Successful").build();
		 
	}
	
}
