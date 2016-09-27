package com.quatico.samples.components;


import com.quatico.samples.AbstractItem;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;

import com.day.cq.commons.jcr.JcrConstants;


@Getter
@Setter
@Accessors(chain = true)
public class TextItem extends AbstractItem<TextItem> {
	
	private String  title;
	private String  text;
	private Boolean isRichText;
	
	public TextItem() {
		this.title = StringUtils.EMPTY;
		this.text = StringUtils.EMPTY;
		this.isRichText = false;
	}
	
	public TextItem fill(Resource resource) {
		ValueMap properties = resource.adaptTo(ValueMap.class);
		setPath(resource.getPath());
		if (properties != null) {
			setTitle(properties.get(JcrConstants.JCR_TITLE, String.class));
			setText(properties.get("text", String.class));
			setIsRichText(properties.get("isRichText", Boolean.class));
		}
		return this;
	}
	
	@Override
	public boolean isValid() {
		return StringUtils.isNotBlank(text);
	}
}
