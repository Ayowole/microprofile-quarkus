package org.ayo.service;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;

import org.ayo.model.MyResource;

@Singleton
public class ResourceService {
	
	private Set<MyResource> resources = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));
	
	@PostConstruct
    void init() {
    	resources.add(new MyResource(1L, "Resource Default 1"));
    	resources.add(new MyResource(2L, "Resource Default 2"));    	
    }
	
	public Set<MyResource> createResource(MyResource resource) {
		resource.setId(Long.valueOf(resources.size()) + 1L);
        resources.add(resource);
    	return resources;
	}

	public Set<MyResource> getResources() {
		return resources;
	}

	public Set<MyResource> updateResource(Long id, MyResource resource) {	
    	if (removeResource(id)) {
    		resource.setId(id);
    		resources.add(resource);
    	}
    	
    	return resources;
	}
	
	public Set<MyResource> deleteResource(Long id) {
		removeResource(id);
		return resources;
	}
	
	private boolean removeResource(Long id) {
		return resources.removeIf(existingResource -> existingResource.getId().longValue() == id);
	}

	public boolean hasAnyResource() {
		return !resources.isEmpty();
	}

}
