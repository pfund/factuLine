package com.thomaspfund.checkconsult.rest;

import java.net.UnknownHostException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.thomaspfund.checkconsult.dao.MonthsDAO;
import com.thomaspfund.checkconsult.dto.MonthResume;

@Path("months")
public class MonthsFacade extends AbstractFacade {

	private MonthsDAO dao = new MonthsDAO();

	@GET
	@Produces({"application/json"})
	public List<MonthResume> getAllMonths() throws UnknownHostException {
		addHeaders();
		return dao.getAllMonths();
	}
}
