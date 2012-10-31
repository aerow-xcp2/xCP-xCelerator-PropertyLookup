package com.emc.xcelerators.community.activity.propertylookup.impl.propertyloader;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import com.documentum.fc.common.DfException;
import com.emc.xcelerators.community.activity.propertylookup.impl.MappingSource;
import com.emc.xcelerators.community.activity.propertylookup.impl.PropertyLoader;

/**
 * PropertyLoaderDecorator is a implementation helper class that knows how to
 * be a decorator. Decorator implementations can subclass this class and 
 * override only those methods that they need to change.
 * 
 * This class extends AbstractPropertyLoader to be able to use the PropertyLoader.properties
 * resources for different messages and configuration.
 * 
 * @author Ingemar Axelsson <ingemar.axelsson@emc.com>
 *
 */
public abstract class PropertyLoaderDecorator extends AbstractPropertyLoader {

	private PropertyLoader theCoreLoader = null;
	
	public Properties getProperties() throws DfException, UnsupportedEncodingException, IOException {
		return theCoreLoader.getProperties();
	}

	public MappingSource getMappingSource() {
		return theCoreLoader.getMappingSource();
	}

	public void setMappingSource(MappingSource aMappingSource) {
		theCoreLoader.setMappingSource(aMappingSource);
	}

	public void setCharsetName(String charsetName) {
		theCoreLoader.setCharsetName(charsetName);
	}

	public String getCharsetName() {
		return theCoreLoader.getCharsetName();
	}

	public PropertyLoader getCorePropertyLoader() {
		return theCoreLoader;
	}

	public void setCorePropertyLoader(PropertyLoader aPropertyLoader) {
		theCoreLoader = aPropertyLoader;
	}
}
