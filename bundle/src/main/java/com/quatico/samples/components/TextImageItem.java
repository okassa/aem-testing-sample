package com.quatico.samples.components;


import com.quatico.samples.AbstractItem;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;


@Getter
@Setter
@Accessors(chain = true)
public class TextImageItem extends AbstractItem<TextImageItem> {
	
	private TextItem text;
	private ImageItem image;
	
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
