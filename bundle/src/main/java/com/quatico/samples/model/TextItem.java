package com.quatico.samples.model;


import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;

import com.day.cq.commons.jcr.JcrConstants;


public class TextItem extends AbstractItem<TextItem> {
	
	private String  title;
	private String  text;
	private Boolean isRichText;
	
	public TextItem() {
		this.title = StringUtils.EMPTY;
		this.text = StringUtils.EMPTY;
		this.isRichText = false;
	}
	
	public String getText() {
		return this.text;
	}
	
	public TextItem setText(String text) {
		this.text = text;
		return this;
	}
	
	public Boolean getIsRichText() {
		return this.isRichText;
	}
	
	public TextItem setIsRichText(Boolean isRichText) {
		this.isRichText = isRichText;
		return this;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public TextItem setTitle(String title) {
		this.title = title;
		return this;
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
