package com.thomaspfund.checkconsult.rest;

import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.thomaspfund.checkconsult.convert.AssistanceConverter;
import com.thomaspfund.checkconsult.dao.EntityDAO;
import com.thomaspfund.checkconsult.entity.Assistance;

@Path("assistance")
public class AssistanceFacade extends AbstractFacade {

	private EntityDAO<Assistance> dao = new EntityDAO<>(new AssistanceConverter());
	
	@OPTIONS
	@Path("{id}")
	@PermitAll
	public void getOptionsForOperation() {
		getOptions();
	}

	
	@GET
	@Produces({"application/json"})
	public List<Assistance> findAll() throws UnknownHostException {
		addHeaders();
		return dao.findAll();
	}
	
	@GET @Path("{id}")
	@Produces({"application/json"})
	public Assistance find(@PathParam("id") String id) throws UnknownHostException {
		addHeaders();
		return dao.find(id);
	}
	
	@GET @Path("getAssistancesInMonth/{dateAssistance}")
	@Produces({"application/json"})
	public List<Assistance> getAssistancesInMonth(@PathParam("dateAssistance") Date dateAssistance) throws UnknownHostException {
		addHeaders();
		return dao.findAssistancesInMonth(dateAssistance);
	}

	@POST
	@Produces({"application/json"})
	public Assistance create(Assistance assistance) throws UnknownHostException {
		addHeaders();
		return dao.insert(assistance);
	}
	
	@PUT
	@Produces({"application/json"})
	public Assistance update(Assistance assistance) throws UnknownHostException {
		addHeaders();
		return dao.update(assistance);
	}

	@DELETE
	@Path("{id}")
	@Produces({"application/json"})
	public void delete(@PathParam("id") String id) throws UnknownHostException {
		addHeaders();
		dao.delete(id);
	}

}
