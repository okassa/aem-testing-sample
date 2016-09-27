package com.quatico.samples.components;

import com.quatico.samples.UnitTestBase;
import org.apache.sling.api.resource.Resource;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ImageControllerTest extends UnitTestBase {
    @Test
    public void isHasCaptionWithCaptionPropertyReturnsTrue() throws Exception {
        Resource page = $.aPageWithParents("/content/test/ko/home/page");
        Resource target = $.anImage(page, "image", "caption", "whatever");

        ImageController testObj = new ImageController().setup($.aPageContext().component(target).page(page).build());

        assertTrue(testObj.isHasCaption());
    }

    @Test
    public void isHasCaptionWithNoCaptionPropertyReturnsFalse() throws Exception {
        Resource page = $.aPageWithParents("/content/test/ko/home/page");
        Resource target = $.anImage(page, "image");

        ImageController testObj = new ImageController().setup($.aPageContext().component(target).page(page).build());

        assertFalse(testObj.isHasCaption());
    }

    @Test
    public void getSource() throws Exception {

    }

    @Test
    public void getAlt() throws Exception {

    }

    @Test
    public void getCaption() throws Exception {

    }

}