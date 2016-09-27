package com.quatico.samples.components;

import com.quatico.samples.AbstractController;
import org.apache.commons.lang3.StringUtils;

public class ImageController extends AbstractController<ImageItem> {

    public String getSource() {
        return getValue(content.getSource(), StringUtils.EMPTY);
    }

    public String getAlt() {
        return getValue(content.getAlt(), StringUtils.EMPTY);
    }

    public boolean isHasCaption() {
        return StringUtils.isNotEmpty(content.getCaption());
    }

    public String getCaption() {
        return getValue(content.getCaption(), StringUtils.EMPTY);
    }

    @Override
    protected void init() {
        this.content = new ImageItem().fill(getResource());
    }
}
