package com.emc.xcelerators.community.activity.propertylookup;

/**
 * Simple pojo class for representing the path to the property file.
 * 
 * @author Ingemar Axelsson <ingemar.axelsson@emc.com>
 * 
 */
public final class MappingFile {
	private String path = "";

	/**
	 * Set the path to the mapping file. Mapping files are resolved with
	 * IDfSession.getObject(), or by IDfSession.getObjectByPath() if it is a
	 * path.
	 * 
	 * @param path
	 *            Path to mapping file, this can be either an ObjectId, or an
	 *            absolute repository path.
	 * 
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * Returns the path associated with the mapping file. It might be an
	 * absolute path, or a document id.
	 * 
	 * @return path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * 
	 * @return true iff the path is a repository relative path, beginning with
	 *         '/'.
	 */
	public boolean isAbsolutePath() {
		return getPath().startsWith("/");
	}

	/**
	 * Returns true iff the object represented by the path is an object.
	 *
	 * @return true iff the path starts with '09', that is, if it represents a
	 *         document.
	 */
	public boolean isObjectId() {
		return getPath().startsWith("09");
	}
	
	/**
	 * Returns a string representation of the mapping file object.
	 */
	public String toString() {
		String propertySeparator = ", ";
		StringBuilder result = new StringBuilder();
		result.append("isObjectId=").append(isObjectId()).append(propertySeparator);
		result.append("isAbsolutePath=").append(isAbsolutePath()).append(propertySeparator);
		result.append("path=").append(getPath());
		
		return result.toString();
	}
}
