package com.emc.xcp.xcelerator.activity.propertylookup;

import com.documentum.fc.client.IDfModule;
import com.documentum.fc.common.DfException;

/**
 * PropertyLookup module can be used for looking up a set of values given a
 * set of keys, and a mapping file. 
 * 
 * @author Ingemar Axelsson <ingemar.axelsson@emc.com>
 *
 */
public interface PropertyLookup extends IDfModule {
	
	/**
	 * Returns the value for a given key in a given mapping file.
	 * 
	 * The mapping file should conform to the java.util.Properties file format.
	 * 
	 * @param propertyFile
	 *            objectID or repository absolute path.
	 * @param key
	 *            property name for the wanted value.
	 * @return 
	 * 			  Depends on the options given as arguments, but basically it returns the
	 * 			  value mapped to the given key in the given mapping file. For some options
	 * 			  it is possible to return a default value if a key is not found.
	 * @throws DfException
	 *             if there are problem with the connection, problem reading
	 *             mapping file InputStream, or if the path argument is not
	 *             beginning with '/' or '09'.
	 */
	String getPropertyValueFromFile(MappingFile propertyFile, LookupKey key, Options options) throws DfException;
	
	/**
	 * Returns values in an array for a given set of keys and a path to a mapping file.
	 * 
	 * The mapping file should conform to the java.util.Properties file format.
	 * 
	 * @param propertyFile
	 *            objectID or repository absolute path to the mapping file.
	 * @param keys
	 *            An array containing all keys to lookup.
	 * @param options
	 * 			  Options used to control how keys are retrieved from the repository.
	 * @return 
	 *         Depends on the options, but basically an array with the values retrieved 
	 *         by looking up all the keys. If no keys are given, then an empty array is
	 *         returned. Values are mapped with the same index as their corresponding key.
	 *         
	 * @throws DfException
	 *             If there are problem with the connection or if there are problem reading the
	 *             mapping file.
	 */
	String[] getPropertyValueFromFile(MappingFile propertyFile, LookupKey[] keys, Options options) throws DfException;
}
