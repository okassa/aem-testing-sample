package com.quatico.samples.components;

import com.quatico.samples.UnitTestBase;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.junit.Test;

import static org.junit.Assert.*;

public class ImageControllerTest extends UnitTestBase {
    @Test
    public void getSourceWithValidImageAndSourcePropertyReturnsImageSource() throws Exception {
        Resource page = $.aPageWithParents("/content/test/ko/home/page");
        Resource target = $.anImage(page, "image", "source", "path/to/link");

        ImageController testObj = new ImageController().setup($.aPageContext().page(page).component(target).build());

        assertEquals("path/to/link", testObj.getSource());
    }

    @Test
    public void getSourceWithValidImageAndNoSourcePropertyReturnsEmptyString() throws Exception {
        Resource page = $.aPageWithParents("/content/test/ko/home/page");
        Resource target = $.anImage(page, "image");

        ImageController testObj = new ImageController().setup($.aPageContext().page(page).component(target).build());

        assertEquals(StringUtils.EMPTY, testObj.getSource());
    }

    @Test
    public void getCaptionWithValidImageAndCaptionPropertyReturnsImageCaption() throws Exception {
        Resource page = $.aPageWithParents("/content/test/ko/home/page");
        Resource target = $.anImage(page, "image", "caption", "expectedValue");

        ImageController testObj = new ImageController().setup($.aPageContext().page(page).component(target).build());

        assertEquals("expectedValue", testObj.getCaption());
    }

    @Test
    public void getCaptionWithValidImageAndNoCaptionPropertyReturnsEmptyString() throws Exception {
        Resource page = $.aPageWithParents("/content/test/ko/home/page");
        Resource target = $.anImage(page, "image");

        ImageController testObj = new ImageController().setup($.aPageContext().page(page).component(target).build());

        assertEquals(StringUtils.EMPTY, testObj.getCaption());
    }

    @Test
    public void isHasCaptionWithValidImageAndCaptionPropertyReturnsTrue() throws Exception {
        Resource page = $.aPageWithParents("/content/test/ko/home/page");
        Resource target = $.anImage(page, "image", "caption", "expectedValue");

        ImageController testObj = new ImageController().setup($.aPageContext().page(page).component(target).build());

        assertTrue(testObj.isHasCaption());
    }

    @Test
    public void isHasCaptionWithValidImageAndNoCaptionPropertyReturnsFalse() throws Exception {
        Resource page = $.aPageWithParents("/content/test/ko/home/page");
        Resource target = $.anImage(page, "image");

        ImageController testObj = new ImageController().setup($.aPageContext().page(page).component(target).build());

        assertFalse(testObj.isHasCaption());
    }

    @Test
    public void getAltWithValidImageAndAltPropertyReturnsImageAlt() throws Exception {
        Resource page = $.aPageWithParents("/content/test/ko/home/page");
        Resource target = $.anImage(page, "image", "alt", "expectedValue");

        ImageController testObj = new ImageController().setup($.aPageContext().page(page).component(target).build());

        assertEquals("expectedValue", testObj.getAlt());
    }

    @Test
    public void getAltWithValidImageAndNoAltPropertyReturnsEmptyString() throws Exception {
        Resource page = $.aPageWithParents("/content/test/ko/home/page");
        Resource target = $.anImage(page, "image");

        ImageController testObj = new ImageController().setup($.aPageContext().page(page).component(target).build());

        assertEquals(StringUtils.EMPTY, testObj.getAlt());
    }

}