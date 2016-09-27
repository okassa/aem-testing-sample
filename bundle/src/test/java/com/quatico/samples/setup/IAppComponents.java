package com.quatico.samples.setup;


import com.quatico.base.aem.test.api.IComponents;

import org.apache.sling.api.resource.Resource;


public interface IAppComponents extends IComponents {
	Resource aTextImage(Resource parent, String name, Object... properties) throws Exception;

	Resource anImage(Resource parent, String name, Object... properties) throws Exception;

    Resource aText(Resource page, String name, Object... properties) throws Exception;
}
