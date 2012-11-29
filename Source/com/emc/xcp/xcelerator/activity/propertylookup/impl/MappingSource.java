package com.emc.xcp.xcelerator.activity.propertylookup.impl;

import java.io.Reader;
import java.io.UnsupportedEncodingException;

import com.documentum.fc.common.DfException;
import com.documentum.fc.common.IDfTime;

/**
 * The MappingSource interface describes how the KeyValueLookup module will find
 * the mapping file given as argument.
 * 
 * 
 * @author Ingemar Axelsson <ingemar.axelsson@emc.com>
 *
 */
public interface MappingSource {

	/**
	 * Returns the object ID of the source.
	 * 
	 * @return
	 * @throws DfException
	 */
	String getSourceId() throws DfException;

	/**
	 * Returns a java.io.Reader holding the file content of the mapping file.
	 * 
	 * @param charsetName
	 * @return
	 * 		Reader object with the content of the file.
	 * @throws DfException
	 * @throws UnsupportedEncodingException
	 */
	Reader getMapContent(String charsetName) throws DfException, UnsupportedEncodingException;

	/**
	 * Get the source objects last modified date. This is used
	 * by the caching mechanism to reload properties if their content
	 * has changed.
	 * 
	 * @return
	 * 		IDfTime object representing the modified date.
	 * @throws DfException 
	 */
	IDfTime getModifiedTimestamp() throws DfException;
}
