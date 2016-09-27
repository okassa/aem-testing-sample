package com.quatico.samples.components;


import com.quatico.samples.model.AbstractItem;
import com.quatico.samples.model.ImageItem;
import com.quatico.samples.model.TextItem;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;


public class TextImageItem extends AbstractItem<TextImageItem> {
	
	private TextItem text;
	private ImageItem image;
	
	public TextItem getText() {
		return text;
	}
	
	public ImageItem getImage() {
		return image;
	}
	
	@Override
	public TextImageItem fill(Resource resource) {
		setPath(resource.getPath());
		Resource imageResource = resource.getChild("image");
		if (imageResource != null) {
			this.image = new ImageItem().fill(imageResource);
		}
		Resource textResource = resource.getChild("text");
		if (textResource != null) {
			this.text = new TextItem().fill(textResource);
		}
		return this;
	}
	
	@Override
	public boolean isValid() {
		return this.text != null && text.isValid() && StringUtils.isNotBlank(text.getTitle())
		       || this.image != null && image.isValid()
		       || this.text != null && StringUtils.isNotBlank(this.text.getTitle());
	}
}
