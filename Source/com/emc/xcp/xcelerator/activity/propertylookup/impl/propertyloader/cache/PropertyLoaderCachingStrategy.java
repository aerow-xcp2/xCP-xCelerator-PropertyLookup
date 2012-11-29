package com.emc.xcp.xcelerator.activity.propertylookup.impl.propertyloader.cache;

import java.util.Properties;

import com.documentum.fc.common.DfException;
import com.emc.xcp.xcelerator.activity.propertylookup.impl.MappingSource;

/**
 * Strategy describing how caching should be done. This is used by the 
 * CachingPropertyLoader implementation.
 * 
 * @author Ingemar Axelsson <ingemar.axelsson@emc.com>
 *
 */
public interface PropertyLoaderCachingStrategy {
	
	/**
	 * This method to control when an item should be reloaded or not. 
	 * 
	 * @param source
	 * @param props
	 * @return
	 */
	boolean shouldReloadCacheItem(MappingSource source, PropertyCacheItem props);

	/**
	 * Decide how to create the PropertyCacheItem, this can be used by subclasses to 
	 * extend the PropertyCacheItem class with more information that can be used to 
	 * decide if the cache should be reloaded or not. 
	 * 
	 * @param source
	 * @param props
	 * @return
	 * @throws DfException 
	 */
	PropertyCacheItem createCacheItem(MappingSource source, Properties props) throws DfException;
}
