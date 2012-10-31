package com.emc.xcelerators.community.activity.propertylookup.impl;

import com.documentum.fc.common.DfException;
import com.emc.xcelerators.community.activity.propertylookup.LookupKey;

public interface PropertyReader {

	String getPropertyValue(LookupKey key) throws DfException;

}
