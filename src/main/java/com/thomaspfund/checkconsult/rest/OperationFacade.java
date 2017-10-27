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

import com.thomaspfund.checkconsult.convert.OperationConverter;
import com.thomaspfund.checkconsult.dao.EntityDAO;
import com.thomaspfund.checkconsult.entity.Operation;

@Path("operation")
public class OperationFacade extends AbstractFacade {

	private EntityDAO<Operation> dao = new EntityDAO<>(new OperationConverter());
	
	@OPTIONS
	@Path("{id}")
	@PermitAll
	public void getOptionsForOperation() {
		getOptions();
	}

	
	@GET
	@Produces({"application/json"})
	public List<Operation> findAll() throws UnknownHostException {
		addHeaders();
		return dao.findAll();
	}
	
	@GET @Path("{id}")
	@Produces({"application/json"})
	public Operation find(@PathParam("id") String id) throws UnknownHostException {
		addHeaders();
		return dao.find(id);
	}
	
	@GET @Path("getOperationsInMonth/{dateOperation}")
	@Produces({"application/json"})
	public List<Operation> getOperationsInMonth(@PathParam("dateOperation") Date dateOperation) throws UnknownHostException {
		addHeaders();
		return dao.findOperationsInMonth(dateOperation);
	}

	@POST
	@Produces({"application/json"})
	public Operation create(Operation operation) throws UnknownHostException {
		addHeaders();
		return dao.insert(operation);
	}
	
	@PUT
	@Produces({"application/json"})
	public Operation update(Operation operation) throws UnknownHostException {
		addHeaders();
		return dao.update(operation);
	}

	@DELETE
	@Path("{id}")
	@Produces({"application/json"})
	public void delete(@PathParam("id") String id) throws UnknownHostException {
		addHeaders();
		dao.delete(id);
	}

}
