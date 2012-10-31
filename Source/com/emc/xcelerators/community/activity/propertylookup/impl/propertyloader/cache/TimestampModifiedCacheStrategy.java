package com.emc.xcelerators.community.activity.propertylookup.impl.propertyloader.cache;

import java.util.Properties;
import java.util.ResourceBundle;

import com.documentum.fc.common.DfException;
import com.documentum.fc.common.IDfTime;
import com.emc.xcelerators.community.activity.propertylookup.impl.Logger;
import com.emc.xcelerators.community.activity.propertylookup.impl.MappingSource;
import com.emc.xcelerators.community.activity.propertylookup.impl.PropertyLoader;
/**
 * Extended cache implementation that verifies that an object in the cache has not
 * been changed in the repository. If the object have been changed, then the object
 * will be reloaded and stored into the cache, if not the object in the cache will 
 * be returned.
 * 
 * This is implemented as a subclass to CachingPropertyLoader, that is, it should not
 * decorate the CachingPropertyLoader.
 * 
 * @author Ingemar Axelsson <ingemar.axelsson@emc.com>
 *
 */
public class TimestampModifiedCacheStrategy implements PropertyLoaderCachingStrategy {
	private static ResourceBundle resource = ResourceBundle.getBundle(PropertyLoader.class.getCanonicalName());

	/*
	 * Private class that describing the properties of the PropertyCacheItem that are
	 * needed by this cache mechanism.
	 */
	private static class ModifiedTimestampPropertyCacheItem extends AbstractPropertyCacheItem {
		private IDfTime timestamp = null;
		
		public void setTimestamp(IDfTime timestamp) {
			this.timestamp = timestamp;
		}
		public IDfTime getTimestamp() {
			return timestamp;
		}
		
	}

	private boolean isCacheTooOld(MappingSource source, PropertyCacheItem props) throws DfException {
		ModifiedTimestampPropertyCacheItem anItem = (ModifiedTimestampPropertyCacheItem)props;
		return source.getModifiedTimestamp().compareTo(anItem.getTimestamp()) == 1;
	}

	public boolean shouldReloadCacheItem(MappingSource source, PropertyCacheItem props){
		assert(source != null);
		String anId = "unknown source id";
		try {
			if (source == null) {
				//There is simply not anything to reload!
				return false;
			}
			anId = source.getSourceId();
			return props == null || isCacheTooOld(source, props);
		} catch (DfException e) {
			// If there are a problem getting the modified time stamp, then try to reload it. 
			return true;
		} catch (ClassCastException e) {
			//There is a problem casting from PropertyCacheItem, to ModifiedTimestampPropertyCacheItem
			//and hence the cache must be updated (since the status is not known.
			Logger.info(resource.getString("incorrect_cache_item_type"), anId);
			return true;
		}
	}

	public PropertyCacheItem createCacheItem(MappingSource source, Properties props) throws DfException {
		ModifiedTimestampPropertyCacheItem item = new ModifiedTimestampPropertyCacheItem();
		item.setProperties(props);
		item.setTimestamp(source.getModifiedTimestamp());
		return item;
	}
}
