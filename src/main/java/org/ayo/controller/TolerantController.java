package org.ayo.controller;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;

@Path("/tolerant")
@ApplicationScoped
public class TolerantController {

	private static final String WORKING = "Working :) ";
	private static final String NEVER_FROM_NORMAL_PROCESSING = "Never from normal processing";
	private static final String FALLBACK_ANSWER_DUE_TO_TIMEOUT = "Fallback answer due to timeout";

	@GET
	@Timeout(500)
    @Fallback(fallbackMethod = "fallback") // better use FallbackHandler
    public String checkTimeout() {
        try {
            Thread.sleep(700L);
        } catch (InterruptedException e) { }
        
        return NEVER_FROM_NORMAL_PROCESSING;
    }
	
	
	public String fallback() {
		return FALLBACK_ANSWER_DUE_TO_TIMEOUT;
	}
	
	
	@GET
	@Path("tenacious")
	@Retry(maxRetries = 3)
	@Fallback(fallbackMethod = "victory")
	public String tenacious() throws Exception {
		throw new Exception("mock exception");
	}
	
	
	public String victory() {
		return WORKING;
	}
	
}
