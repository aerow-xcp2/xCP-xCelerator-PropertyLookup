package com.emc.xcp.xcelerator.activity.propertylookup.impl.propertyloader.cache;

import java.util.Properties;

/**
 * PropertyCacheItem implementation helper. This class aims to help with 
 * the implementation of PropertyCacheItems which are used by the cache 
 * mechanism. 
 * 
 * @author Ingemar Axelsson <ingemar.axelsson@emc.com>
 *
 */
abstract class AbstractPropertyCacheItem implements PropertyCacheItem {
	private Properties property = null;

	/* (non-Javadoc)
	 * @see com.emc.xcelerators.community.activity.lookup.impl.PropertyCacheItem#setProperties(java.util.Properties)
	 */
	public void setProperties(Properties property) {
		this.property = property;
	}
	/* (non-Javadoc)
	 * @see com.emc.xcelerators.community.activity.lookup.impl.PropertyCacheItem#getProperties()
	 */
	public Properties getProperties() {
		return property;
	}
}
