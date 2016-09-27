package com.quatico.samples;


import javax.servlet.jsp.PageContext;


/**
 * Controller interface. For convenience it implements all the context interfaces as well.
 */
public interface Controller<T> {
	
	/**
	 * Setup the components from inside a JSP context. This method is used because it is easier to work with
	 * empty constructors when using reflection.
	 *
	 * @param jspPageContext {@link PageContext}
	 */
	<R extends Controller<T>> R setup(PageContext jspPageContext);
	
	T getContent();
}
