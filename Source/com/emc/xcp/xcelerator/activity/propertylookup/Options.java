package com.emc.xcp.xcelerator.activity.propertylookup;

/**
 * Options are arguments that controls how the PropertyLookup module should behave.
 * 
 * @author Ingemar Axelsson <ingemar.axelsson@emc.com>
 * 
 */
public final class Options {
	private boolean throwingOnMissingKey = false;
	private boolean useCache = true;
	private String charsetName = "ISO-8859-1";
	

	/**
	 * true  => exception is thrown when key is not found.
	 * false => return given default value if key is not found.
	 * 
	 * Default is to not throw exception on missing key, but to return the 
	 * default value given by the LookupKey.getDefaultValue().
	 * 
	 * @param value
	 * 		True iff the lookup should throw an exception when a key is not found.
	 */
	public void setThrowingOnMissingKey(boolean value) {
		throwingOnMissingKey = value;
	}
	
	/**
	 * 
	 * @return
	 * 		True iff the lookup will throw an exception when a key is not found.
	 */
	public boolean isThrowingOnMissingKey() {
		return throwingOnMissingKey;
	}
	
	/**
	 * 
	 * @return
	 * 		True if the lookup are using cache to increase performance.
	 */
	public boolean isUsingCache() {
		return useCache;
	}
	
	/**
	 *  true  => retrieval of the property file will be cached locally, in memory. 
	 *  false => no caching is used at all. 
	 *  
	 *  Default is to use cache.
	 *  
	 * @param value
	 * 		Set to true if the lookup should use Cache, false otherwise
	 */
	public void setUseCache(boolean value) {
		useCache = value;
	}

	/**
	 * Set a character set name if the property file is of some other character set than the 
	 * default ISO-8859-1 character set. 
	 * 
	 * @param name
	 * 		Character set name.
	 */
	public void setCharsetName(String name) {
		this.charsetName = name;
	}

	/**
	 * Returns the given Character set name. 
	 * 
	 * @return
	 * 		Character set name.
	 */
	public String getCharsetName() {
		return charsetName;
	}
	
	/**
	 * Creates a string representation of the Options object.
	 * 
	 * @return	
	 * 		String representation of the Options object.
	 */
	public String toString() {
		String propertySeparator = ", ";
		StringBuilder result = new StringBuilder();
		result.append("throwingOnMissingKey=").append(isThrowingOnMissingKey()).append(propertySeparator);
		result.append("useCache=").append(isUsingCache()).append(propertySeparator);
		result.append("charset=").append(getCharsetName());

		return result.toString();
	}
}
