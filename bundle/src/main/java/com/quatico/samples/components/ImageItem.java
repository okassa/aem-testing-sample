package com.quatico.samples.components;


import java.io.IOException;
import java.io.StringWriter;

import com.quatico.samples.AbstractItem;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;

import com.day.cq.wcm.foundation.Image;


@Getter
@Setter
@Accessors(chain = true)
public class ImageItem extends AbstractItem<ImageItem> {
	
	private String        caption;
	private String        alt;
	private String        source;
	private String        target;
	private ImageSize     imageSize;
	private ImagePosition imagePosition;
	private Image         imageData;
	
	public ImageItem() {
		this.caption = StringUtils.EMPTY;
		this.source = StringUtils.EMPTY;
		this.alt = StringUtils.EMPTY;
		this.imageSize = ImageSize.NORMAL;
		this.imagePosition = ImagePosition.LEFT_ABOVE;
		this.target = StringUtils.EMPTY;
	}
	
	public String getHtml() throws IOException {
		if (!isValid()) {
			return StringUtils.EMPTY;
		}
		
		StringWriter result = new StringWriter();
		imageData.draw(result);
		return result.toString();
		
	}
	
	public String getHref() {
		return this.imageData.getHref();
	}
	
	/**
	 * This method forms the figure legend which is a composite of the caption and the source value.
	 *
	 * @param separator that is added between caption and source if they are not empty
	 * @return image legend or {@link StringUtils#EMPTY} when neither caption nor source is set
	 */
	public String getImageLegend(String separator) {
		String result = StringUtils.EMPTY;
		
		if (StringUtils.isNotEmpty(caption)) {
			result = caption;
		}
		if (StringUtils.isNotEmpty(source)) {
			result = StringUtils.isNotEmpty(result) ? caption + separator + source : source;
		}
		
		return result;
	}
	
	public ImageItem fill(Resource resource) {
		setPath(resource.getPath());
		
		ValueMap properties = resource.adaptTo(ValueMap.class);
		if (properties != null) {
			setCaption(properties.get("caption", String.class));
			setAlt(properties.get("alt", String.class));
			setSource(properties.get("source", String.class));
			setTarget(properties.get("target", String.class));
			setImageData(properties.get("imageData", Image.class));
			setImageSize(properties.get("imageSize", ImageSize.class));
			setImagePosition(properties.get("imagePosition", ImagePosition.class));
		}
		return this;
	}
	
	@Override
	public boolean isValid() {
		return imageData != null && imageData.hasContent();
	}
	
	public enum ImageSize {
		NORMAL("normal"), SMALL("small");
		
		private String value;
		
		ImageSize(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return value;
		}
	}
	
	
	public enum ImagePosition {
		LEFT_ABOVE("left"), RIGHT_BELOW("right");
		
		private String value;
		
		ImagePosition(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return value;
		}
	}
	
}
