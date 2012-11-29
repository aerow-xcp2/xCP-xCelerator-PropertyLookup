package com.emc.xcp.xcelerator.activity.propertylookup.impl.propertyloader.cache;

import java.util.Properties;

import com.documentum.fc.common.DfException;
import com.emc.xcp.xcelerator.activity.propertylookup.impl.MappingSource;

/**
 * Create a cache item based on the existence of the item in the current cache. 
 * 
 * @author Ingemar Axelsson <ingemar.axelsson@emc.com>
 *
 */
public class LoadIfNotInCacheStrategy implements PropertyLoaderCachingStrategy {

	private static class NotLoadedPropertyCacheItem extends AbstractPropertyCacheItem {
		//No extra cache information is stored using this item.
	}
	
	public boolean shouldReloadCacheItem(MappingSource source, PropertyCacheItem props) {
		return isExistingInCache(props);
	}

	private boolean isExistingInCache(PropertyCacheItem anItem) {
		return anItem == null;
	}
	
	public PropertyCacheItem createCacheItem(MappingSource source, Properties props) throws DfException {
		PropertyCacheItem anItem = new NotLoadedPropertyCacheItem();
		anItem.setProperties(props);
		return anItem;
	}
}
