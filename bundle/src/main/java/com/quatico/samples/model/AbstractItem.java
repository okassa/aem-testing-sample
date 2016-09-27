package com.quatico.samples.model;


import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;


public abstract class AbstractItem<T extends AbstractItem<T>> {
	public static final String NULL_PATH = "DOESNOTEXIST";
	
	private String path;
	
	public AbstractItem() {
		this.path = StringUtils.EMPTY;
	}
	
	public String getPath() {
		return this.path;
	}
	
	public <R extends AbstractItem<T>> R setPath(String path) {
		this.path = path;
		
		//noinspection unchecked
		return (R) this;
	}
	
	public abstract T fill(Resource resource);
	
	public boolean isValid() {
		return false;
	}
}
