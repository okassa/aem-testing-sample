package com.quatico.samples;


import javax.servlet.jsp.PageContext;

import lombok.Getter;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.NonExistingResource;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.scripting.jsp.util.TagUtil;


/**
 * Abstract components contains common code for all component controllers.
 */
@Getter
public abstract class AbstractController<T> implements Controller<T> {
	
	protected T                        content;
	private   PageContext              pageContext;
	private   SlingHttpServletRequest  request;
	
	@SuppressWarnings("unchecked")
	public <R extends Controller<T>> R setup(PageContext pageContext) {
		this.pageContext = pageContext;
		this.request = TagUtil.getRequest(pageContext);
		
		init();
		return (R) this;
	}
	
	protected abstract void init();
	
	protected Resource getResource() {
		return this.request != null ? request.getResource() : new NonExistingResource(request.getResourceResolver(), "DOESNOTEXIST");
	}

	public <R> R getValue(R value, R defaultValue) {
        return value != null ? value : defaultValue;
    }
}
