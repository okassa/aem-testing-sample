package com.quatico.samples.components;


public class ComponentType extends com.quatico.base.aem.test.api.values.ComponentType {
	
	public static final ComponentType IMAGE = new ComponentType("quatico/samples/components/image");
	public static final ComponentType TEXT_IMAGE = new ComponentType("quatico/samples/components/textImage");
	
	protected ComponentType(String name) throws IllegalArgumentException {
		super(name);
	}
}
