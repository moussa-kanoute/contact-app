/**
 * 
 */
package com.owt.contact.mock;

import java.io.IOException;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author moussa.kanoute
 *
 */
public class DataUtils {

	private DataUtils() {
		throw new UnsupportedOperationException("DataUtils is a final util class");
	}

	public static <T> T load(String fileName, Class<T> returnClass) {
		try {
			return new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
					.readValue(DataUtils.class.getClassLoader().getResourceAsStream(fileName), returnClass);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
