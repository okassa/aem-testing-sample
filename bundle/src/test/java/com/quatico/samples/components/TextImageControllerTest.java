package com.quatico.samples.components;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import com.quatico.samples.UnitTestBase;
import com.quatico.samples.model.ImageItem;

import org.apache.sling.api.resource.Resource;
import org.junit.Test;


public class TextImageControllerTest extends UnitTestBase {
	@Test
	public void isShowTitleBelowWithImagePresentAndSizeSmallAndPositionLeftReturnsFalse() throws Exception {
		Resource asset  = $.anAsset("/content/dam/test/foo.jpg");
		Resource page   = $.aPageWithParents("/content/text/ko/home/page");
		Resource target = $.aTextImage(page, "/jcr:content/foobar", "text", "<b>hello</b>");
		$.anImage(target, "image", "fileReference", asset.getPath(), "imageSize", ImageItem.ImageSize.SMALL, "imagePosition", ImageItem.ImagePosition.LEFT_ABOVE);
		
		TextImageController testObj = new TextImageController().setup($.aPageContext().component(target).page(page).build());
		
		assertFalse(testObj.isShowTitleBelow());
	}
	
	@Test
	public void getImageLegendWithNoSourceButTitleReturnsCaption() throws Exception {
		Resource page   = $.aPageWithParents("/content/test/ko/home/page");
		Resource target = $.aTextImage(page, "/jcr:content/foobar");
		$.anImage(target, "image", "caption", "expectedValue");
		
		TextImageController testObj = new TextImageController().setup($.aPageContext().component(target).page(page).build());
		
		assertEquals("expectedValue", testObj.getImageLegend());
		
	}
}
