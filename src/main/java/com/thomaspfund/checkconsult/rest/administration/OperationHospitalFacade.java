package com.thomaspfund.checkconsult.rest.administration;

import java.net.UnknownHostException;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.thomaspfund.checkconsult.convert.administration.OperationHospitalConverter;
import com.thomaspfund.checkconsult.dao.EntityDAO;
import com.thomaspfund.checkconsult.entity.administration.OperationHospital;
import com.thomaspfund.checkconsult.rest.AbstractFacade;

@Path("administration/operationHospital")
public class OperationHospitalFacade extends AbstractFacade {

	private EntityDAO<OperationHospital> dao = new EntityDAO<>(new OperationHospitalConverter());
	
	@OPTIONS
	@Path("{id}")
	@PermitAll
	public void getOptionsForConsult() {
		getOptions();
	}

	
	@GET
	@Produces({"application/json"})
	public List<OperationHospital> findAll() throws UnknownHostException {
		addHeaders();
		return dao.findAll();
	}
	
	@POST
	@Produces({"application/json"})
	public OperationHospital create(OperationHospital operationHospital) throws UnknownHostException {
		addHeaders();
		return dao.insert(operationHospital);
	}
	
	@PUT 
	@Produces({"application/json"})
	public OperationHospital update(OperationHospital operationHospital) throws UnknownHostException {
		addHeaders();
		return dao.update(operationHospital);
	}
}
