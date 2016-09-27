package com.quatico.samples.tags;


import com.quatico.samples.model.AbstractItem;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;


/**
 * Replacement for jsp EL 'not empty'. Similar to jsp specs:
 * <p>
 * To evaluate A:
 * </p>
 * <ul>
 * <li>If A is null, return false</li>
 * <li>Otherwise, if A is the empty string, then return false</li>
 * <li>Otherwise, if A is an empty array, then return false</li>
 * <li>Otherwise, if A is an empty Map, return false</li>
 * <li>Otherwise, if A is an empty Collection, return false</li>
 * <li>Otherwise return true</li>
 * </ul>
 */
public class ExistsTag {

	public static boolean exists(Object object) {
		if (object == null) {
			return false;
		}
		if (isNullObject(object)) {
			return false;
		}
		//noinspection IndexOfReplaceableByContains
		if (isEmptyString(object) || object.toString().toUpperCase().indexOf(AbstractItem.NULL_PATH) >= 0) {
			return false;
		}
		if (object.getClass().isArray()) {
			int length = Array.getLength(object);
			for (int idx = 0; idx < length; idx++) {
				if (exists(Array.get(object, idx))) {
					return true;
				}
			}
			return false;
		}
		if (object instanceof Collection<?>) {
			Collection<?> objects = (Collection<?>) object;
			for (Object cur : objects) {
				if (exists(cur)) {
					return true;
				}
			}
			return false;
		}
		if (object instanceof Map<?, ?>) {
			Map<?, ?> objects = (Map<?, ?>) object;
			for (Object cur : objects.values()) {
				if (exists(cur)) {
					return true;
				}
			}
			return false;
		}

		return true;
	}

	private static boolean isEmptyString(Object object) {
		return object instanceof CharSequence
		       && StringUtils.isEmpty((CharSequence) object);
	}

	private static boolean isNullObject(Object object) {
		return object instanceof AbstractItem && AbstractItem.NULL_PATH.equals(((AbstractItem<?>) object).getPath());
	}
}
