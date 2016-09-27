package com.quatico.samples;


import com.quatico.base.aem.test.api.IAemClient;
import com.quatico.base.aem.test.api.setup.AemClient;
import com.quatico.base.aem.test.api.setup.AemClient.Type;
import com.quatico.base.aem.test.api.setup.Assets;
import com.quatico.base.aem.test.api.setup.Pages;
import com.quatico.base.aem.test.api.setup.Resources;
import com.quatico.base.aem.test.api.setup.SetupFactory;
import com.quatico.samples.setup.AppComponents;
import com.quatico.samples.setup.ITestSetup;

import org.junit.After;
import org.junit.Before;


public class UnitTestBase {
	protected ITestSetup $;
	private IAemClient client;
	
	@Before
	public void setUpTestContext() throws Exception {
		this.client = new AemClient(Type.Unit).startUp();
		Resources resources = new Resources(client);
		$ = SetupFactory.create(ITestSetup.class).getSetup(client,
				resources,
				new Pages(client),
				new AppComponents(resources, client),
				new Assets(client));
	}
	
	@After
	public void tearDownTestContext() throws Exception {
		this.client.shutDown();
	}
}
