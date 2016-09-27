package com.quatico.samples.components;


import com.quatico.samples.AbstractController;
import com.quatico.samples.model.ImageItem;
import com.quatico.samples.model.ImageItem.ImagePosition;
import com.quatico.samples.model.ImageItem.ImageSize;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;


public class TextImageController extends AbstractController<TextImageItem> {
	
	@Override
	protected void init() {
		if (this.content == null) {
			this.content = new TextImageItem().fill(getResource());
		}
	}
	
	public boolean isShowTitleBelow() {
		ImageItem image = this.content.getImage();
		return image.isValid()
		       && image.getImageSize().equals(ImageSize.NORMAL)
		       && image.getImagePosition().equals(ImagePosition.LEFT_ABOVE);
	}
	
	/**
	 * This method forms the figure caption which is a composite of the caption and the source value.
	 * Method is placed in the components because it has to return HTML.
	 *
	 * @return HTML escaped image caption or {@link StringUtils#EMPTY} when neither caption nor source is set
	 */
	public String getImageLegend() {
		return StringUtils.replace(StringEscapeUtils.escapeHtml4(this.content.getImage().getImageLegend("<br />")), "&lt;br /&gt;", "<br />");
	}
}
