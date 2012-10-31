package com.emc.xcelerators.community.activity.propertylookup.impl.propertyloader.cache;

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