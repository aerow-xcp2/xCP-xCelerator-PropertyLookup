package com.emc.xcp.xcelerator.activity.propertylookup.impl.mappingsource;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import com.documentum.fc.client.IDfSession;
import com.documentum.fc.common.DfException;
import com.emc.xcp.xcelerator.activity.propertylookup.MappingFile;
import com.emc.xcp.xcelerator.activity.propertylookup.Options;
import com.emc.xcp.xcelerator.activity.propertylookup.impl.Logger;
import com.emc.xcp.xcelerator.activity.propertylookup.impl.MappingSource;

public class MappingSourceFactory {
	private static ResourceBundle resource = ResourceBundle.getBundle(MappingSource.class.getCanonicalName());
	
	public static MappingSource getMappingSource(IDfSession aSession, MappingFile propertyFile, Options options) throws DfException {
		if (propertyFile.isAbsolutePath()) {
			return new PathMappingSource(aSession, propertyFile);
		} else if (propertyFile.isObjectId()) {
			return new ObjectIdMappingSource(aSession, propertyFile);
		} else {
			String msg = MessageFormat.format(resource.getString("error_invalid_mapping_file"), propertyFile);
			Logger.error(msg);
			throw new DfException(msg);
		}
	}
}
