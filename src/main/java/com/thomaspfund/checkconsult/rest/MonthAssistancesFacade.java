package com.thomaspfund.checkconsult.rest;

import java.net.UnknownHostException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.thomaspfund.checkconsult.dao.MonthAssistancesDAO;
import com.thomaspfund.checkconsult.dto.MonthAssistanceResume;

@Path("monthAssistances")
public class MonthAssistancesFacade extends AbstractFacade {

	private MonthAssistancesDAO dao = new MonthAssistancesDAO();

	@GET
	@Produces({"application/json"})
	public List<MonthAssistanceResume> getAllMonthAssistances() throws UnknownHostException {
		addHeaders();
		return dao.getAllMonthAssistances();
	}
}