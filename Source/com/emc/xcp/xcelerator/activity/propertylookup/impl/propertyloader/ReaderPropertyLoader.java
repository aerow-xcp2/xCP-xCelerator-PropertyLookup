package com.emc.xcp.xcelerator.activity.propertylookup.impl.propertyloader;

import java.io.IOException;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import com.documentum.fc.common.DfException;

/**
 * Load properties from a java.io.Reader object.
 * 
 * @author Ingemar Axelsson <ingemar.axelsson@emc.com>
 *
 */
public class ReaderPropertyLoader extends AbstractPropertyLoader {
	private Properties properties = null;
	
	@Override
	public Properties getProperties() throws UnsupportedEncodingException, IOException, DfException {
		assert getMappingSource() != null;
		
		if (properties == null) {
			//We load the properties from file lazy, when it is first needed.
			properties = loadPropertiesFromReader(getMappingSource().getMapContent(getCharsetName()));
		}
		return properties;
	}
	
	private Properties loadPropertiesFromReader(Reader propertyReader) throws IOException {
		Properties props = new Properties();
		props.load(propertyReader);
		return props;
	}
}
