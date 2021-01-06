package org.ayo.health;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

@Liveness
@ApplicationScoped
public class LiveInspector implements HealthCheck {
	
	@Override
	public HealthCheckResponse call() {
		return HealthCheckResponse.named("app is alive and kicking").up().build();
	}
	
}
