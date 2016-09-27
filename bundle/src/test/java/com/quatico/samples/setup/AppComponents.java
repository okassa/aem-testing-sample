package com.quatico.samples.setup;


import com.quatico.base.aem.test.api.IAemClient;
import com.quatico.base.aem.test.api.IResources;
import com.quatico.base.aem.test.api.setup.Components;
import com.quatico.samples.components.ComponentType;

import org.apache.sling.api.resource.Resource;


public class AppComponents extends Components implements IAppComponents {
	public AppComponents(IResources resources, IAemClient client) {
		super(resources, client);
	}
	
	@Override
	public Resource anImage(Resource parent, String name, Object... properties) throws Exception {
		return createComponent(getComponentPath(parent, name), ComponentType.IMAGE, properties);
	}
	
	@Override
	public Resource aTextImage(Resource parent, String name, Object... properties) throws Exception {
		return createComponent(getComponentPath(parent, name), ComponentType.TEXT_IMAGE, properties);
	}
}
