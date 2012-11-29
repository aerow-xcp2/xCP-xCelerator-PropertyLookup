package com.emc.xcp.xcelerator.activity.propertylookup.impl.mappingsource;

import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.ResourceBundle;

import com.documentum.fc.client.DfPathNotFoundException;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.client.IDfSysObject;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.IDfTime;
import com.emc.xcp.xcelerator.activity.propertylookup.MappingFile;
import com.emc.xcp.xcelerator.activity.propertylookup.impl.Logger;
import com.emc.xcp.xcelerator.activity.propertylookup.impl.MappingSource;

class PathMappingSource implements MappingSource {
	private static ResourceBundle resource = ResourceBundle.getBundle(MappingSource.class.getCanonicalName());
	IDfSysObject mappingDocument = null;
	
	public PathMappingSource(IDfSession aSession, MappingFile propertyFile) throws DfException {
		mappingDocument = (IDfSysObject)aSession.getObjectByPath(propertyFile.getPath());
		
		if (mappingDocument == null) {
			String msg = MessageFormat.format(resource.getString("error_path_not_found"), propertyFile.getPath());
			Logger.error(msg);
			throw new DfPathNotFoundException(propertyFile.getPath());
		}
	}
	
	public Reader getMapContent(String charsetName) throws DfException, UnsupportedEncodingException {
		assert mappingDocument != null;
		return new InputStreamReader(mappingDocument.getContent(), charsetName);
	}

	/**
	 * @return
	 * 		Always returns an object id representing the object at the given path.
	 */
	public String getSourceId() throws DfException {
		assert mappingDocument != null;
		return mappingDocument.getObjectId().getId();
	}

	public IDfTime getModifiedTimestamp() throws DfException {
		assert mappingDocument != null;
		return mappingDocument.getModifyDate();
	}
}
