package com.emc.xcelerators.community.activity.propertylookup.impl.propertyreader;

import java.text.MessageFormat;
import java.util.Properties;
import java.util.ResourceBundle;

import com.documentum.fc.common.DfException;
import com.emc.xcelerators.community.activity.propertylookup.LookupKey;
import com.emc.xcelerators.community.activity.propertylookup.impl.Logger;
import com.emc.xcelerators.community.activity.propertylookup.impl.PropertyReader;

public class ThrowingPropertyReader implements PropertyReader {
	private static ResourceBundle resource = ResourceBundle.getBundle(PropertyReader.class.getCanonicalName());
	
	private Properties properties = null;
	
	public ThrowingPropertyReader(Properties properties) {
		this.properties = properties;
	}

	public String getPropertyValue(LookupKey key) throws DfException {
		if (key == null) {
			String msg = resource.getString("error_param_key_null");
			Logger.error(msg);
			throw new DfException(msg);
		}
		
		if (properties == null) {
			String msg = resource.getString("error_null_properties");
			Logger.error(msg);
			throw new DfException(msg);
		}
		
		String result = properties.getProperty(key.getKey());
		
		if (result == null) {
			String msg = MessageFormat.format(resource.getString("error_no_key_found"), key.getKey());
			Logger.error(msg);
			throw new DfException(msg);
		}
		
		return result;
	}
}
