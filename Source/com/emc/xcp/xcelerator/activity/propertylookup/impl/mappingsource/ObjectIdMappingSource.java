package com.emc.xcp.xcelerator.activity.propertylookup.impl.mappingsource;

import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.ResourceBundle;

import com.documentum.fc.client.DfIdNotFoundException;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.client.IDfSysObject;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.DfId;
import com.documentum.fc.common.IDfTime;
import com.emc.xcp.xcelerator.activity.propertylookup.MappingFile;
import com.emc.xcp.xcelerator.activity.propertylookup.impl.Logger;
import com.emc.xcp.xcelerator.activity.propertylookup.impl.MappingSource;

/**
 * Find mapping file given an object ID.
 * 
 * Throws exception if it can not find file with the given object id.
 * 
 * @author Ingemar Axelsson <ingemar.axelsson@emc.com>
 *
 */
class ObjectIdMappingSource implements MappingSource {
	private static ResourceBundle resource = ResourceBundle.getBundle(MappingSource.class.getCanonicalName());
	
	private IDfSysObject mappingDocument = null;
	
	ObjectIdMappingSource(IDfSession aSession, MappingFile propertyFile) throws DfException {
		DfId anId = new DfId(propertyFile.getPath());
		mappingDocument = (IDfSysObject)aSession.getObject(anId);
		
		if (mappingDocument == null) {
			String msg = MessageFormat.format(resource.getString("error_id_not_found"), propertyFile.getPath());
			Logger.error(msg);
			throw new DfIdNotFoundException(anId);
		}
	}
	
	public Reader getMapContent(String charsetName) throws DfException, UnsupportedEncodingException {			
		assert mappingDocument != null;
		return new InputStreamReader(mappingDocument.getContent(), charsetName);
	}

	public String getSourceId() throws DfException {
		assert mappingDocument != null;
		return mappingDocument.getObjectId().getId();
	}

	public IDfTime getModifiedTimestamp() throws DfException {
		assert mappingDocument != null;
		return mappingDocument.getModifyDate();
	}

}
