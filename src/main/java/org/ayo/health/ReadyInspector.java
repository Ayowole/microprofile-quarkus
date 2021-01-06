package org.ayo.health;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.ayo.service.ResourceService;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

@Readiness
@ApplicationScoped
public class ReadyInspector implements HealthCheck {

	@Inject
	ResourceService resourceService;
	
	@Override
	public HealthCheckResponse call() {
		if (resourceService.hasAnyResource()) {
			return HealthCheckResponse.named("I'm ready :)").up().build();
		}
		
		return HealthCheckResponse.named("I'm not ready yet :(").down().build();
	}
	
}
