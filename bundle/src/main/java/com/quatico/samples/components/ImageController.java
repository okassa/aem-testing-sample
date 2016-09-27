package com.quatico.samples.components;


import com.quatico.samples.AbstractController;
import org.apache.commons.lang3.StringUtils;

public class ImageController extends AbstractController<ImageItem> {

    @Override
	protected void init() {
		this.content = new ImageItem().fill(getResource());
	}

	public boolean isHasCaption() {
		return StringUtils.isNotBlank(content.getCaption());
	}

	public String getSource() {
		return getValue(content.getHref(), StringUtils.EMPTY);
	}

    public String getAlt() {
        return getValue(content.getAlt(), StringUtils.EMPTY);
    }

    public String getCaption() {
        return getValue(content.getCaption(), StringUtils.EMPTY);
    }
}
