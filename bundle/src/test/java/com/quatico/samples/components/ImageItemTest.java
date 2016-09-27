package com.quatico.samples.components;

import com.quatico.samples.UnitTestBase;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.junit.Test;

import static com.day.cq.commons.DownloadResource.PN_FILE_NAME;
import static com.day.cq.commons.DownloadResource.PN_REFERENCE;
import static com.day.cq.commons.jcr.JcrConstants.JCR_TITLE;
import static org.junit.Assert.*;

public class ImageItemTest extends UnitTestBase {
    @Test
    public void fillWithValidResourceYieldsValidImageItem() throws Exception {
        Resource page = $.aPageWithParents("/content/test/ko/home/page");
        Resource asset = $.anAsset().path("/path/to/asset").classpathResource("/sample-image.png").build().adaptTo(Resource.class);
        Resource target = $.anImage(page, "image", PN_FILE_NAME, "sample-image.png", PN_REFERENCE, asset.getPath());

        ImageItem testObj = new ImageItem().fill(target);

        assertTrue(testObj.isValid());
    }

    @Test
    public void getHtmlWithInvalidResourceReturnsEmptyString() throws Exception {
        Resource page = $.aPageWithParents("/content/test/ko/home/page");
        Resource target = $.anImage(page, "whatever");

        ImageItem testObj = new ImageItem().fill(target);

        assertEquals(StringUtils.EMPTY, testObj.getHtml());
    }

    @Test
    public void getHtmlWithValidResourceReturnsEmptyString() throws Exception {
        Resource page = $.aPageWithParents("/content/test/ko/home/page");
        Resource asset = $.anAsset().path("/path/to/asset").classpathResource("/sample-image.png").build().adaptTo(Resource.class);
        Resource target = $.anImage(page, "whatever", "alt", "some alt", JCR_TITLE, "some title", PN_FILE_NAME, "whatever.png", PN_REFERENCE, asset.getPath());

        ImageItem testObj = new ImageItem().fill(target);

        assertEquals("<img src=\"/path/to/asset\" alt=\"some alt\" title=\"some title\" >", testObj.getHtml());
    }

    @Test
    public void getHrefWithValidAssetReturnsPathToAsset() throws Exception {
        Resource page = $.aPageWithParents("/content/test/ko/home/page");
        Resource asset = $.anAsset("/path/to/asset");
        Resource target = $.anImage(page, "whatever", PN_FILE_NAME, "whatever.png", PN_REFERENCE, asset.getPath());

        ImageItem testObj = new ImageItem().fill(target);

        assertEquals("/path/to/asset", testObj.getHref());
    }

    @Test
    public void getImageLegendWithSlashSeparatorAndCaptionAndSourceReturnCombinedString() throws Exception {
        Resource page = $.aPageWithParents("/content/test/ko/home/page");
        Resource target = $.anImage(page, "whatever", "caption", "cap", "source", "hello");

        ImageItem testObj = new ImageItem().fill(target);

        assertEquals("cap/hello", testObj.getImageLegend("/"));
    }

    @Test
    public void getImageLegendWithSlashSeparatorAndNoCaptionReturnSource() throws Exception {
        Resource page = $.aPageWithParents("/content/test/ko/home/page");
        Resource target = $.anImage(page, "whatever", "source", "hello");

        ImageItem testObj = new ImageItem().fill(target);

        assertEquals("hello", testObj.getImageLegend("/"));
    }

    @Test
    public void getImageLegendWithSlashSeparatorAndNoSourceReturnCaption() throws Exception {
        Resource page = $.aPageWithParents("/content/test/ko/home/page");
        Resource target = $.anImage(page, "whatever", "caption", "cap");

        ImageItem testObj = new ImageItem().fill(target);

        assertEquals("cap", testObj.getImageLegend("/"));
    }

    @Test
    public void getImageLegendWithSlashSeparatorAndNoSourceAndNoCaptionReturnEmptyString() throws Exception {
        Resource page = $.aPageWithParents("/content/test/ko/home/page");
        Resource target = $.anImage(page, "whatever");

        ImageItem testObj = new ImageItem().fill(target);

        assertEquals(StringUtils.EMPTY, testObj.getImageLegend("/"));
    }
}