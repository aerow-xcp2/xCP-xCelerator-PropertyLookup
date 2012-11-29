package com.emc.xcp.xcelerator.activity.propertylookup.impl;

import com.documentum.fc.common.DfLogger;
import com.emc.xcp.xcelerator.activity.propertylookup.PropertyLookup;

/**
 * Logger is a wrapper around DfLogger, and it is used to make the
 * logging easier within this package.
 * 
 * @author Ingemar Axelsson <ingemar.axelsson@emc.com>
 *
 */
public class Logger {
	private static String logSource = PropertyLookup.class.getCanonicalName();

	public static void info(String message, Object... params) {
		if (DfLogger.isInfoEnabled(logSource)) {
			DfLogger.info(logSource, message, params, null);
		}
	}

	public static void error(String message, Object[] params, Throwable t) {
		if (DfLogger.isErrorEnabled(logSource)) {
			DfLogger.error(logSource, message, params, t);
		}
	}
	
	public static void error(String message, Throwable t) {
		error(message, null, t);
	}

	public static void error(String message) {
		error(message, null, null);
	}
}
