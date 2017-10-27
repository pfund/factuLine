package com.thomaspfund.checkconsult.rest;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.core.Context;

public abstract class AbstractFacade {

    @Context
    private HttpServletResponse anotherServlerResponse;

	@OPTIONS
	@PermitAll
	public void getOptions() {
		anotherServlerResponse.addHeader("Access-Control-Allow-Origin", "*");
		anotherServlerResponse.addHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, OPTIONS");
		anotherServlerResponse.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
	}

	protected void addHeaders() {
		anotherServlerResponse.addHeader("Access-Control-Allow-Origin", "*");
	}

}
