package com.emc.xcp.xcelerator.activity.propertylookup.impl.propertyloader.cache;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.documentum.fc.common.DfException;
import com.emc.xcp.xcelerator.activity.propertylookup.impl.Logger;
import com.emc.xcp.xcelerator.activity.propertylookup.impl.MappingSource;
import com.emc.xcp.xcelerator.activity.propertylookup.impl.propertyloader.PropertyLoaderDecorator;

/**
 * Loads a java.util.Properties object given by the MappingSource. If the 
 * MappingSource has already been loaded, the Properties is retrieved from 
 * the cache. If the properties are not found in the cache, then they are
 * loaded from a given property loader.
 * 
 * This property loaders cache is synchronized so it can be used from different
 * threads. 
 * 
 * @author Ingemar Axelsson <ingemar.axelsson@emc.com>
 * 
 */
public class CachingPropertyLoader extends PropertyLoaderDecorator {
	private PropertyLoaderCachingStrategy cachingStrategy = null;
	
	private static Map<String, PropertyCacheItem> propertyCache = new HashMap<String, PropertyCacheItem>();
	private static int[] mutex = new int[0];
	
	/**
	 * Set the caching strategy to use.
	 * 
	 * @param aStrategy
	 */
	public void setCachingStrategy(PropertyLoaderCachingStrategy aStrategy) {
		cachingStrategy = aStrategy;
	}
	
	/**
	 * CachinPropertyLoader has an cache algorithm that first tries to load the property item from
	 * the cache. If it can't load the property item, the item will be null. The property item is
	 * passed to the caching strategy.shouldReloadCacheItem method which decides if a cache item should
	 * be reloaded or not. If the strategy.shouldReloadCacheItem() return true, a call to 
	 * strategy.createCacheItem() is made. 
	 * 
	 */
	@Override
	public Properties getProperties() throws DfException, UnsupportedEncodingException, IOException {
		MappingSource source = getCorePropertyLoader().getMappingSource();
		PropertyCacheItem props = null;
		synchronized (mutex) {
			props = propertyCache.get(source.getSourceId());
			if (cachingStrategy.shouldReloadCacheItem(source, props)) {
				props = reloadProperties(source);
			}
		}
		if (props == null) {
			throw new DfException(getStringFromResource("error_mapping_source_null"));
		}
		return props.getProperties();
	}
	
	private PropertyCacheItem reloadProperties(MappingSource source) throws UnsupportedEncodingException, DfException, IOException {
		Logger.info(getStringFromResource("info_cache_miss"), source.getSourceId());
		Properties props = getCorePropertyLoader().getProperties();
		PropertyCacheItem item = cachingStrategy.createCacheItem(source, props);
		propertyCache.put(source.getSourceId(), item);
		return item;
	}
}
