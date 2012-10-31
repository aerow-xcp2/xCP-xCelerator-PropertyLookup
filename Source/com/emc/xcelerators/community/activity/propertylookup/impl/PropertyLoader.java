package com.emc.xcelerators.community.activity.propertylookup.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import com.documentum.fc.common.DfException;

/**
 * Strategy describing how to load a property file.
 * 
 * @author Ingemar Axelsson <ingemar.axelsson@emc.com>
 *
 */
public interface PropertyLoader {

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
	Properties getProperties() throws DfException, UnsupportedEncodingException, IOException;
	
	/**
	 * 
	 * @return
	 * 		MappingSource describing the source of the mapping file.
	 */
	MappingSource getMappingSource();
	
	/**
	 * 
	 * @param aMappingSource
	 * 		Set the input mapping source.
	 */
	void setMappingSource(MappingSource aMappingSource);

	/**
	 * Charset defaults to ISO-8859-1
	 * 
	 * @param charsetName
	 * 		charset name to identify which charset are to be used when loading properties.
	 */
	void setCharsetName(String charsetName);
	
	/**
	 * 
	 * @return
	 * 		The charset name to use when loading properties.
	 */
	String getCharsetName();
	
	/**
	 * 
	 * @return
	 * 		The decorated propertyLoader or null if no decorated propertyLoader exists.
	 */
	PropertyLoader getCorePropertyLoader();
	
	/**
	 * Set the object that are to be decorated with the PropertyLoader. Used by decorators
	 * mainly, if PropertyLoader does not support decoration, then do not implement this 
	 * method, see AbstractPropertyLoader.setPropertyLoader(PropertyLoader) for more 
	 * information.
	 * 
	 * @param aPropertyLoader
	 */
	void setCorePropertyLoader(PropertyLoader aPropertyLoader);

}
