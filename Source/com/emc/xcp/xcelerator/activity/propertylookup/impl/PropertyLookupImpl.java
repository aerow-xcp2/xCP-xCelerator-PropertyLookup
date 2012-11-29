package com.emc.xcp.xcelerator.activity.propertylookup.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import com.documentum.fc.client.DfSingleDocbaseModule;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.common.DfException;
import com.emc.xcp.xcelerator.activity.propertylookup.LookupKey;
import com.emc.xcp.xcelerator.activity.propertylookup.MappingFile;
import com.emc.xcp.xcelerator.activity.propertylookup.Options;
import com.emc.xcp.xcelerator.activity.propertylookup.PropertyLookup;
import com.emc.xcp.xcelerator.activity.propertylookup.impl.mappingsource.MappingSourceFactory;
import com.emc.xcp.xcelerator.activity.propertylookup.impl.propertyloader.PropertyLoaderFactory;
import com.emc.xcp.xcelerator.activity.propertylookup.impl.propertyreader.DefaultValuePropertyReader;
import com.emc.xcp.xcelerator.activity.propertylookup.impl.propertyreader.ThrowingPropertyReader;

/**
 * 
 * @author Ingemar Axelsson <ingemar.axelsson@emc.com>
 * 
 */
public class PropertyLookupImpl extends DfSingleDocbaseModule implements PropertyLookup{

	private static ResourceBundle resource = ResourceBundle.getBundle(PropertyLookupImpl.class.getCanonicalName());
	
	/**
	 * Interface implementation method.
	 */
	public String getPropertyValueFromFile(MappingFile propertyFile, LookupKey key, Options options) throws DfException {
		assert propertyFile != null;
		assert key != null;
		assert options != null;

		if (propertyFile == null) {
			String msg = resource.getString("error_null_propertyfile");
			Logger.error(msg);
			throw new DfException(msg);
		}
		if (key == null) {
			String msg = resource.getString("error_null_lookup_key");
			Logger.error(msg);
			throw new DfException(msg);
		}
		if (options == null) {
			Logger.info(resource.getString("info_using_default_options"));
			options = new Options();
		}

		Logger.info(resource.getString("info_parameters"), propertyFile, key, options);

		IDfSession aSession = null;
		String result = null;
		try {
			aSession = getSession();
			PropertyReader reader = createPropertyReader(aSession, propertyFile, options);
			result = reader.getPropertyValue(key);
			Logger.info(resource.getString("info_result"), result);
			return result;
		} catch (DfException e) {
			Logger.error(e.getMessage(), e);
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			// Property file is not correctly formatted or is missing!
			Logger.error(e.getMessage(), e);
			e.printStackTrace();
			throw new DfException(e);
		} catch (InstantiationException e) {
			e.printStackTrace();
			throw new DfException(e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new DfException(e);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new DfException(e);
		} finally {
			if (aSession != null) {
				releaseSession(aSession);
			}
		}
	}

	/**
	 * Interface implementation method.
	 */
	public String[] getPropertyValueFromFile(MappingFile propertyFile, LookupKey[] keys, Options options) throws DfException {
		assert propertyFile != null;
		assert keys != null;
		assert options != null;

		if (propertyFile == null) {
			String msg = resource.getString("error_null_propertyfile");
			Logger.error(msg);
			throw new DfException(msg);
		}
		if (keys == null || keys.length == 0) {
			Logger.error(resource.getString("error_null_lookup_key"));
			return new String[0];
		}
		if (options == null) {
			Logger.info(resource.getString("info_using_default_options"));
			options = new Options();
		}

		Logger.info(resource.getString("info_parameters"), propertyFile, Arrays.toString(keys), options);

		IDfSession aSession = null;
		try {
			aSession = getSession();
			PropertyReader reader = createPropertyReader(aSession, propertyFile, options);
			List<String> result = new ArrayList<String>();
			for (LookupKey key : keys) {
				result.add(reader.getPropertyValue(key));
			}
			Logger.info(resource.getString("info_result_array"), result.toString());
			return result.toArray(new String[result.size()]);
		} catch (IOException e) {
			// Property file is not correctly formatted or is missing!
			e.printStackTrace();
			throw new DfException(e);
		} catch (InstantiationException e) {
			e.printStackTrace();
			throw new DfException(e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new DfException(e);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new DfException(e);
		} finally {
			if (aSession != null) {
				releaseSession(aSession);
			}
		}
	}

	private PropertyReader createPropertyReader(IDfSession aSession, MappingFile propertyFile, Options options) throws DfException,
			IOException, InstantiationException, ClassNotFoundException, IllegalAccessException {
		MappingSource mapping = MappingSourceFactory.getMappingSource(aSession, propertyFile, options);
		PropertyLoader loader = PropertyLoaderFactory.getInstance().getPropertyLoader(mapping, options);
		return propertyReaderFactory(loader, options);
	}

	private PropertyReader propertyReaderFactory(PropertyLoader loader, Options options) throws IOException, DfException {
		Properties properties = loader.getProperties(); 
		if (options.isThrowingOnMissingKey()) {
			return new ThrowingPropertyReader(properties);
		}
		return new DefaultValuePropertyReader(properties);
	}
}
