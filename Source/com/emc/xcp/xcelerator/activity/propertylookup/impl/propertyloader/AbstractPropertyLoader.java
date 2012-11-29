package com.emc.xcp.xcelerator.activity.propertylookup.impl.propertyloader;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.ResourceBundle;

import com.documentum.fc.common.DfException;
import com.emc.xcp.xcelerator.activity.propertylookup.impl.MappingSource;
import com.emc.xcp.xcelerator.activity.propertylookup.impl.PropertyLoader;

/**
 * AbstractPropertyLoader is a implementation helper that makes it easier to 
 * implement the PropertyLoader interface. This class also add a resource
 * management functionality to allow subclasses to use the same resources for 
 * error messages and so on. Resources are stored in PropertyLoader.properties
 * 
 * @author Ingemar Axelsson <ingemar.axelsson@emc.com>
 *
 */
public abstract class AbstractPropertyLoader implements PropertyLoader {
	
	private static ResourceBundle resource = ResourceBundle.getBundle(PropertyLoader.class.getCanonicalName());
	private String charsetName = "ISO-8859-1";
	private MappingSource mappingSource = null;
	
	/**
	 * Fetch the properties associated with a given loader (see loader construction for more
	 * info about how properties are associated with the loader). 
	 * 
	 * @return
	 * 		Properties associated with the given loader.
	 * 
	 * @throws DfException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public abstract Properties getProperties() throws DfException, UnsupportedEncodingException, IOException;
	
	/**
	 * 
	 * @return
	 * 		MappingSource describing the source of the mapping file.
	 */
	public MappingSource getMappingSource() {
		return mappingSource;
	}

	public void setMappingSource(MappingSource aMappingSource) {
		mappingSource = aMappingSource;
	}

	public void setCharsetName(String charsetName) {
		this.charsetName = charsetName;
	}

	public String getCharsetName() {
		return charsetName;
	}
	
	public PropertyLoader getCorePropertyLoader() {
		return null;
	}

	public void setCorePropertyLoader(PropertyLoader aPropertyLoader) {
		//Empty method, this is not done with the basic implementation.
	}

	/**
	 * Helper method for retrieving values for a given key from the resource file associated with 
	 * the PropertyLoader classes. Used by subclasses only.
	 * 
	 * @param key
	 * @return
	 */
	protected final String getStringFromResource(String key) {
		return resource.getString(key);
	}
}
