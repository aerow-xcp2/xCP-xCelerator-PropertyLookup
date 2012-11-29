package com.emc.xcp.xcelerator.activity.propertylookup.impl;

import com.documentum.fc.common.DfException;
import com.emc.xcp.xcelerator.activity.propertylookup.LookupKey;

public interface PropertyReader {

	String getPropertyValue(LookupKey key) throws DfException;

}
