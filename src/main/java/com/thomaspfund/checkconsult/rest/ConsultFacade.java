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

import com.thomaspfund.checkconsult.convert.ConsultConverter;
import com.thomaspfund.checkconsult.dao.EntityDAO;
import com.thomaspfund.checkconsult.entity.Consult;

@Path("consult")
public class ConsultFacade extends AbstractFacade {

	private EntityDAO<Consult> dao = new EntityDAO<>(new ConsultConverter());
	
	@OPTIONS
	@Path("{id}")
	@PermitAll
	public void getOptionsForConsult() {
		getOptions();
	}

	
	@GET
	@Produces({"application/json"})
	public List<Consult> findAll() throws UnknownHostException {
		addHeaders();
		return dao.findAll();
	}
	
	@GET @Path("{id}")
	@Produces({"application/json"})
	public Consult find(@PathParam("id") String id) throws UnknownHostException {
		addHeaders();
		return dao.find(id);
	}
	
	@GET @Path("getByDateConsult/{dateConsult}")
	@Produces({"application/json"})
	public List<Consult> getByDateConsult(@PathParam("dateConsult") Date dateConsult) throws UnknownHostException {
		addHeaders();
		return dao.findByDateConsult(dateConsult);
	}

	@POST
	@Produces({"application/json"})
	public Consult create(Consult consult) throws UnknownHostException {
		addHeaders();
		return dao.insert(consult);
	}
	
	@PUT
	@Produces({"application/json"})
	public Consult update(Consult consult) throws UnknownHostException {
		addHeaders();
		return dao.update(consult);
	}

	@DELETE
	@Path("{id}")
	@Produces({"application/json"})
	public void delete(@PathParam("id") String id) throws UnknownHostException {
		addHeaders();
		dao.delete(id);
	}
	
}
