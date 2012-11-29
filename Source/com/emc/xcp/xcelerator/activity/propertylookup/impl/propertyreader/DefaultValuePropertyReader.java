package com.emc.xcp.xcelerator.activity.propertylookup.impl.propertyreader;

import java.util.Properties;
import java.util.ResourceBundle;

import com.documentum.fc.common.DfException;
import com.emc.xcp.xcelerator.activity.propertylookup.LookupKey;
import com.emc.xcp.xcelerator.activity.propertylookup.impl.Logger;
import com.emc.xcp.xcelerator.activity.propertylookup.impl.PropertyReader;

/**
 * Reads a value from a java.util.Properties class, if the key is not found, then the 
 * value returned is the default value for that key. The default value is given by the
 * caller.
 * 
 * @author Ingemar Axelsson <ingemar.axelsson@emc.com>
 *
 */
public class DefaultValuePropertyReader implements PropertyReader {
	private static ResourceBundle resource = ResourceBundle.getBundle(PropertyReader.class.getCanonicalName());

	Properties properties = null;
	
	public DefaultValuePropertyReader(Properties properties) {
		this.properties = properties;
	}

	public String getPropertyValue(LookupKey key) throws DfException {
		assert key != null;
		if (properties == null) {
			String msg = resource.getString("error_null_properties");
			Logger.error(msg);
			throw new DfException(msg);
		}
		if (key == null) {
			String msg = resource.getString("error_param_key_null");
			Logger.error(msg);
			throw new DfException(msg);
		}
		return properties.getProperty(key.getKey(), key.getDefaultValue());
	}
}
