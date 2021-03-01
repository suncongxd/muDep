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

package droidsafe.helpers;

//Droidsafe Imports
import droidsafe.annotations.*;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;

public class DSUtils {
	public final static String UNKNOWN_STRING = "";
	public final static int UNKNOWN_INT = 0;
	public final static short UNKNOWN_SHORT = 0;
	public final static byte UNKNOWN_BYTE = 0;
	public final static long UNKNOWN_LONG = 0;
	public final static float UNKNOWN_FLOAT = 0;
	public final static double UNKNOWN_DOUBLE = 0;
	public static final boolean UNKNOWN_BOOLEAN = false;
	public static final char UNKNOWN_CHAR = 0;
	
	public static final int FAKE_INT = -1;
	public static final String FAKE_STRING = "Unknown";
	public static final int[] FAKE_INT_ARRAY = new int[] {0};
	
	/** This utility method should be used to create Intents from IntentFilters.  This
	 * was originally developed to exercise {@link BroadcastReceiver} with all of the possible
	 * intents for which the application registers
	 * @param filter
	 * @return
	 */

    //mgordon: using a static method is bad for the precision of the analysis!
    //do not use 
    /**
	public static Intent[] getIntentFromFilter(IntentFilter filter) {
		int actionCount = filter.countActions();
		int catCount = filter.countCategories();
		Intent[] intents = new Intent[actionCount];
		for (int actionIndex = 0; actionIndex < actionCount; actionIndex++) {
			Intent intent = new Intent(filter.getAction(actionIndex));

			for (int catIndex = 0; catIndex < catCount; catIndex++) {
				intent.addCategory(filter.getCategory(catIndex));
			}
			intents[actionIndex] = intent;
		}
		
		return intents;
	}
    **/

    /** 
     * Used to inject taint of info kind constant in the API code
     */
    public static int dsGenerateTaint(String constant) {
        return 0;
    }
}
