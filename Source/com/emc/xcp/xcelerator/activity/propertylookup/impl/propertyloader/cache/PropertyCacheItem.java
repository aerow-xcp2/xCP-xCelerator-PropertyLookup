package com.emc.xcp.xcelerator.activity.propertylookup.impl.propertyloader.cache;

import java.util.Properties;

interface PropertyCacheItem {

	/**
	 * 
	 * @param property
	 */
	void setProperties(Properties property);

	/**
	 * 
	 * @return
	 */
	Properties getProperties();

}