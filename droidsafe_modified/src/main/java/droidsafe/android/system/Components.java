/*
 * Copyright (C) 2015,  Massachusetts Institute of Technology
 * 
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the
 * Free Software Foundation; either version 2 of the License, or (at your
 * option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc., 
 * 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 * 
 * Please email droidsafe@lists.csail.mit.edu if you need additional
 * information or have any questions.
 */

package droidsafe.android.system;

import java.util.LinkedList;
import java.util.List;

import droidsafe.android.app.Hierarchy;

import soot.SootClass;
import soot.Scene;

public class Components {
	public static String ACTIVITY_CLASS = "android.app.Activity";
	public static String SERVICE_CLASS = "android.app.Service";
	public static String CONTENTPROVIDER_CLASS = "android.content.ContentProvider";
	public static String BROADCASTRECEIVER_CLASS = "android.content.BroadcastReceiver";
	
	/** This array stores objects that are created by the android runtime, and passed into 
	 * event handlers of components 
	 */
	public static String[] RUNTIME_OBJECTS = {"android.os.Bundle"};
	
	public static List<String> CLASS_NAMES; 
	
	static {
		CLASS_NAMES = new LinkedList<String>();
		CLASS_NAMES.add(ACTIVITY_CLASS);
		CLASS_NAMES.add(SERVICE_CLASS);
		CLASS_NAMES.add(CONTENTPROVIDER_CLASS);
		CLASS_NAMES.add(BROADCASTRECEIVER_CLASS);
	}

	/** 
	 * Return a list of soot classes for the components of an android application. 
	 */
	public static List<SootClass> getComponentSootClasses() {
		LinkedList<SootClass> comps = new LinkedList<SootClass>();
		
		for (String c : CLASS_NAMES) {
			comps.add(Scene.v().getSootClass(c));
		}
		
		return comps;
	}
	
	/**
	 * Return true if the name is an application component class from the
	 * android api (direct class, not superclass or subclass).
	 */
	public static boolean isComponentClass(String name) {
		return CLASS_NAMES.contains(name);
	}
	
	public static SootClass getSootClassForComponent(AndroidComponents c) {
	    switch (c) {
	        case ACTIVITY : return Scene.v().getSootClass(ACTIVITY_CLASS);
	        case SERVICE : return Scene.v().getSootClass(SERVICE_CLASS);
	        case CONTENT_PROVIDER : return Scene.v().getSootClass(CONTENTPROVIDER_CLASS);
	        case BROADCAST_RECEIVER : return Scene.v().getSootClass(BROADCASTRECEIVER_CLASS);
	        default: return null;
	    }
	}
}
