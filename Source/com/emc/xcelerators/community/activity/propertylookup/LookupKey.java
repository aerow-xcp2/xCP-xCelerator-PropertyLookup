package com.emc.xcelerators.community.activity.propertylookup;

/**
 * LookupKey describes a key to look up. It contains two entries, 
 * the key itself, and a default value. The default value are not
 * always used by the PropertyLookup module.
 * 
 * @author Ingemar Axelsson <ingemar.axelsson@emc.com>
 * 
 */
public final class LookupKey {
	private String key = "";
	private String defaultValue = "";

	/**
	 * Key is the name of the key to lookup.
	 * @param key
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * Return the key to lookup.
	 * 
	 * @return
	 * 		returns the key value set by setKey
	 */
	public String getKey() {
		return key;
	}

	/**
	 * DefaultValue is a value that is used if the key could not be found.
	 * 
	 * Needs to be turned on in the options.
	 * 
	 * @param defaultValue
	 */
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	/**
	 * Returns the default value given for the key. This method is 
	 * called when the key can not be found.
	 * 
	 * @return
	 * 		Default value for a key.
	 */
	public String getDefaultValue() {
		return defaultValue;
	}

	/**
	 * Returns a string representation of the LookupKey.
	 * 
	 * @return
	 * 		String representation of the LookupKey.
	 */
	public String toString() {
		String propertySeparator = ", ";
		StringBuilder result = new StringBuilder();
		result.append("key=").append(getKey()).append(propertySeparator);
		result.append("defaultValue=").append(getDefaultValue());

		return result.toString();
	}
}
