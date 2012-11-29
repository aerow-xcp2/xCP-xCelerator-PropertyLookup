package com.emc.xcp.xcelerator.activity.propertylookup.impl.propertyloader;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import com.emc.xcp.xcelerator.activity.propertylookup.Options;
import com.emc.xcp.xcelerator.activity.propertylookup.impl.MappingSource;
import com.emc.xcp.xcelerator.activity.propertylookup.impl.PropertyLoader;
import com.emc.xcp.xcelerator.activity.propertylookup.impl.propertyloader.cache.CachingPropertyLoader;
import com.emc.xcp.xcelerator.activity.propertylookup.impl.propertyloader.cache.PropertyLoaderCachingStrategy;

/**
 * Factory implementation that reads the property loader from a configuration file. This to make it possible
 * to change the caching strategy after the application is deployed.
 * 
 * TODO: CachingPropertyLoader is hard coded into this factory, however, the CachingPropertyLoader is
 * a simple wrapper that wraps the cache and it is possible to configure its caching strategy using
 * PropetyLoaderCachingStrategy subclasses. This is however due to the fact that the options class is 
 * not needed in this factory, maybe it should.
 * 
 * @author Ingemar Axelsson <ingemar.axelsson@emc.com>
 *
 */
public class PropertyLoaderFactory {
	private static ResourceBundle resource = ResourceBundle.getBundle(PropertyLoaderFactory.class.getCanonicalName());
	private static PropertyLoaderFactory currentFactory = new PropertyLoaderFactory();
	
	/**
	 * Register another factory if the default factory is not wanted. This will
	 * change the instance factory that is returned from getInstance().
	 * 
	 * Useful for testing purposes.
	 * 
	 * @param aFactory
	 */
	public static void registerFactory(PropertyLoaderFactory aFactory) {
		currentFactory = aFactory;
	}
	
	public static PropertyLoaderFactory getInstance() {
		return currentFactory;
	}
	
	/**
	 * Returns a property loader
	 * 
	 * @param mapping
	 * @param options
	 * @return
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 */
	public PropertyLoader getPropertyLoader(MappingSource mapping, Options options) throws InstantiationException, ClassNotFoundException, IllegalAccessException {
		PropertyLoader loader = propertyLoaderByName(resource.getString("property_loader"));
		loader.setMappingSource(mapping);
		loader.setCharsetName(options.getCharsetName());
		
		if (options.isUsingCache()) {
			loader = getCachingPropertyLoader(loader);
		}
		return loader;
	}
	
	private PropertyLoader propertyLoaderByName(String name) throws InstantiationException, ClassNotFoundException, IllegalAccessException {
		Class<?> aClass = Class.forName(name);
		if (PropertyLoader.class.isAssignableFrom(aClass)) {
			return (PropertyLoader) aClass.newInstance();
		}
		throw new InstantiationException(MessageFormat.format(resource.getString("instantiation_error_message"), name));
	}
	
	protected PropertyLoader getCachingPropertyLoader(PropertyLoader decoratee) throws InstantiationException, ClassNotFoundException, IllegalAccessException {
		CachingPropertyLoader loader = new CachingPropertyLoader();
		loader.setCachingStrategy(getCachingStrategy());
		loader.setCorePropertyLoader(decoratee);

		return loader;
	}

	protected PropertyLoaderCachingStrategy getCachingStrategy() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		return cachingStrategyByName(resource.getString("caching_strategy"));
	}
	
	private PropertyLoaderCachingStrategy cachingStrategyByName(String name) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Class<?> aClass = Class.forName(name);
		if (PropertyLoaderCachingStrategy.class.isAssignableFrom(aClass)) {
			return (PropertyLoaderCachingStrategy)aClass.newInstance();
		}
		throw new InstantiationException(MessageFormat.format(resource.getString("instantiation_error_message"), name));
	}
}
