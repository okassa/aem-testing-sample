package com.quatico.samples.components;

import com.day.cq.commons.jcr.JcrConstants;
import com.quatico.samples.UnitTestBase;
import org.apache.sling.api.resource.Resource;
import org.junit.Test;

import static com.day.cq.commons.DownloadResource.PN_FILE_NAME;
import static com.day.cq.commons.DownloadResource.PN_REFERENCE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TextImageItemTest extends UnitTestBase {
    @Test
    public void fillWithValidResourceAndTextAndImageChildrenWithContentsYieldsValidComponent() throws Exception {
        Resource page = $.aPageWithParents("/content/test/ko/home/page");
        Resource target = $.aTextImage(page, "foobar");
        $.aText(target, "text", "text", "whatever");
        $.anImage(target, "image", PN_FILE_NAME, "whatever.png", PN_REFERENCE, $.anAsset("/path/to/asset").getPath());

        TextImageItem testObj = new TextImageItem().fill(target);

        assertTrue(testObj.isValid());
    }

    @Test
    public void fillWithValidResourceAndTextAndImageChildrenButNoImageContentYieldsInvalidComponent() throws Exception {
        Resource page = $.aPageWithParents("/content/test/ko/home/page");
        Resource target = $.aTextImage(page, "foobar");
        $.aText(target, "text", "text", "whatever", JcrConstants.JCR_TITLE, "a title");
        $.anImage(target, "image");

        TextImageItem testObj = new TextImageItem().fill(target);

        assertTrue(testObj.isValid());
    }

    @Test
    public void fillWithValidImageChildButNoTextChildYieldsValidComponent() throws Exception {
        Resource page = $.aPageWithParents("/content/test/ko/home/page");
        Resource target = $.aTextImage(page, "foobar");
        $.aText(target, "text");
        $.anImage(target, "image", PN_FILE_NAME, "whatever.png", PN_REFERENCE, $.anAsset("/path/to/asset").getPath());

        TextImageItem testObj = new TextImageItem().fill(target);

        assertTrue(testObj.isValid());
    }

    @Test
    public void getTextWithValidResourceAndTextPropertyReturnsPropertyValue() throws Exception {
        Resource page = $.aPageWithParents("/content/test/ko/home/page");
        Resource target = $.aTextImage(page, "foobar");
        $.aText(target, "text", "text", "expectedValue");

        TextImageItem testObj = new TextImageItem().fill(target);

        assertEquals("expectedValue", testObj.getText().getText());
    }

    @Test
    public void getImageWithValidResourceAndImagePropertyReturnsPropertyValue() throws Exception {
        Resource page = $.aPageWithParents("/content/test/ko/home/page");
        Resource target = $.aTextImage(page, "foobar");
        $.anImage(target, "image");

        TextImageItem testObj = new TextImageItem().fill(target);

        assertEquals("/content/test/ko/home/page/jcr:content/foobar/image", testObj.getImage().getPath());
    }
}