package com.thomaspfund.checkconsult.rest;

import java.net.UnknownHostException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.thomaspfund.checkconsult.dao.MonthOperationsDAO;
import com.thomaspfund.checkconsult.dto.MonthOperationResume;

@Path("monthOperations")
public class MonthOperationsFacade extends AbstractFacade {

	private MonthOperationsDAO dao = new MonthOperationsDAO();

	@GET
	@Produces({"application/json"})
	public List<MonthOperationResume> getAllMonthOperations() throws UnknownHostException {
		addHeaders();
		return dao.getAllMonthOperations();
	}
}