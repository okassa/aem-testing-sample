package com.quatico.samples.tags;


import com.quatico.samples.Controller;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;

import com.day.cq.wcm.api.WCMMode;


/**
 * Component components tag.
 */
public class ControllerTag extends TagSupport {
	
	private static final long serialVersionUID = -7849298022671993610L;
	
	private static final String ALWAYS_SHOW_ATTRIBUTE_NAME = ControllerTag.class.getName() + "_alwaysShow";
	private           String        cls;
	private           String        var;
	private           String        scope;
	private transient Controller<?> controller;
	private transient WCMMode       wcmMode;
	
	/**
	 * Instantiate components class. Skips the body if an error or a validation error occurs.
	 *
	 * @return EVAL_BODY_INCLUDE or SKIP_BODY in case of error state.
	 * @throws JspException
	 */
	@Override
	public int doStartTag() throws JspException {
		if (this.cls == null) {
			throw new IllegalArgumentException("No class name given.");
		}
		
		this.wcmMode = WCMMode.fromRequest(this.pageContext.getRequest());
		
		initializeController();
		
		// At this point we can be sure that components != null
		boolean evaluateBodyContent = this.pageContext != null;
		if (evaluateBodyContent && StringUtils.isNotBlank(this.var)) {
			this.pageContext.setAttribute(this.var, this.controller);
		}
		
		return evaluateBodyContent ? EVAL_BODY_INCLUDE : SKIP_BODY;
	}
	
	@Override
	public int doEndTag() throws JspException {
		release();
		return EVAL_PAGE;
	}
	
	@Override
	public void release() {
		this.controller = null;
		this.cls = null;
		this.var = null;
		this.wcmMode = WCMMode.DISABLED;
		super.release();
	}
	
	protected void initializeController() throws JspException {
		Class<?> clazz = null;
		try {
			clazz = Class.forName(this.cls);
		} catch (ClassNotFoundException ex) {
			throw new JspException(ex);
		}
		if (!Controller.class.isAssignableFrom(clazz)) {
			throw new JspException("Class '" + this.cls + "' must implement [" + Controller.class.getSimpleName() + "]");
		}
		
		SlingHttpServletRequest request          = (SlingHttpServletRequest) this.pageContext.getRequest();
		boolean                 isRequestScope   = "request".equalsIgnoreCase(scope);
		boolean                 isComponentScope = "component".equalsIgnoreCase(scope);
		String                  contextKey       = this.cls + "_" + this.var;
		String                  resourceKey      = this.cls + "_" + request.getResource().getPath();
		PageContext             context          = this.pageContext;
		
		this.controller = (Controller<?>) request.getAttribute(resourceKey);
		if (isRequestScope) {
			this.controller = (Controller<?>) request.getAttribute(contextKey);
			if (this.controller != null && clazz != this.controller.getClass()) { // Class objects are immutable and final
				throw new JspException("Existing class in the request ist not compatible with requested class.");
			}
		}
		if (this.controller == null && isComponentScope) {
			this.controller = (Controller<?>) context.getAttribute(contextKey);
			if (this.controller != null && clazz != this.controller.getClass()) { // Class objects are immutable and final
				throw new JspException("Existing class in the request ist not compatible with requested class.");
			}
		}
		
		// No components found in request scope - instantiate and setup a new one
		if (this.controller == null) {
			try {
				this.controller = (Controller<?>) clazz.getConstructor().newInstance();
			} catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
				throw new JspException(ex);
			}
			this.controller.setup(this.pageContext);
			
			if (isRequestScope) {
				request.setAttribute(contextKey, this.controller);
			}
			if (isComponentScope) {
				context.setAttribute(contextKey, this.controller);
			}
		}
	}
}
