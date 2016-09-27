package com.quatico.samples;


import javax.servlet.jsp.PageContext;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.NonExistingResource;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.scripting.jsp.util.TagUtil;


/**
 * Abstract components contains common code for all component controllers.
 */
public abstract class AbstractController<T> implements Controller<T> {
	
	protected T                        content;
	private   PageContext              jspPageContext;
	private   SlingHttpServletRequest  request;
	
	@SuppressWarnings("unchecked")
	public <R extends Controller<T>> R setup(PageContext jspPageContext) {
		this.jspPageContext = jspPageContext;
		this.request = TagUtil.getRequest(jspPageContext);
		
		init();
		return (R) this;
	}
	
	protected abstract void init();
	
	protected Resource getResource() {
		return this.request != null ? request.getResource() : new NonExistingResource(request.getResourceResolver(), "DOESNOTEXIST");
	}
	
	public T getContent() {
		return this.content;
	}
	
	public PageContext getPageContext() {
		return jspPageContext;
	}
}
