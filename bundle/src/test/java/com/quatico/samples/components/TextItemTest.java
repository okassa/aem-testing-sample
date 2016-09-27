package com.quatico.samples.components;

import com.quatico.samples.UnitTestBase;
import org.apache.sling.api.resource.Resource;
import org.junit.Test;

import static org.junit.Assert.*;

public class TextItemTest extends UnitTestBase {
    @Test
    public void fillWithValidResourceAndTextPropertyYieldsValidComponent() throws Exception {
        Resource page = $.aPageWithParents("/content/test/ko/home/page");
        Resource target = $.aText(page, "foobar", "text", "someValue");

        TextItem testObj = new TextItem().fill(target);

        assertTrue(testObj.isValid());
    }

    @Test
    public void fillWithValidResourceAndNoTextPropertyYieldsInvalidComponent() throws Exception {
        Resource page = $.aPageWithParents("/content/test/ko/home/page");
        Resource target = $.aText(page, "foobar");

        TextItem testObj = new TextItem().fill(target);

        assertFalse(testObj.isValid());
    }

    @Test
    public void getIsRichTextWithValidResourceAndPropertyReturnsTrue() throws Exception {
        Resource page = $.aPageWithParents("/content/test/ko/home/page");
        Resource target = $.aText(page, "foobar", "isRichText", true);

        TextItem testObj = new TextItem().fill(target);

        assertTrue(testObj.getIsRichText());
    }

    @Test
    public void getTextWithValidResourceAndTextPropertyReturnsPropertyValue() throws Exception {
        Resource page = $.aPageWithParents("/content/test/ko/home/page");
        Resource target = $.aText(page, "foobar", "text", "expectedValue");

        TextItem testObj = new TextItem().fill(target);

        assertEquals("expectedValue", testObj.getText());
    }

    @Test
    public void getTitleWithValidResourceAndJcrTitlePropertyReturnsPropertyValue() throws Exception {
        Resource page = $.aPageWithParents("/content/test/ko/home/page");
        Resource target = $.aText(page, "foobar", "jcr:title", "expectedValue");

        TextItem testObj = new TextItem().fill(target);

        assertEquals("expectedValue", testObj.getTitle());
    }
}