package com.quatico.samples.tags;


import javax.servlet.jsp.tagext.TagData;
import javax.servlet.jsp.tagext.TagExtraInfo;
import javax.servlet.jsp.tagext.VariableInfo;


/**
 * Tag extra info for components tag.
 */
public class ControllerTei extends TagExtraInfo {

	private static final String ATTRIBUTE_CLS = "cls";

	private static final String ATTRIBUTE_VAR = "var";

	private static final String DEFAULT_TYPE = "java.lang.Object";

	/**
	 * Get variable info for components tag.
	 *
	 * @param data Tag data
	 * @return Variable infos
	 */
	@Override
	public VariableInfo[] getVariableInfo(TagData data) {
		String type = data.getAttributeString(ATTRIBUTE_CLS);
		if (type == null) {
			type = DEFAULT_TYPE;
		}

		return new VariableInfo[] { new VariableInfo(data.getAttributeString(ATTRIBUTE_VAR), type, true, VariableInfo.AT_BEGIN) };
	}
}
