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

package com.google.android.gms.drive.metadata;


import java.util.Collection;
import java.util.LinkedList;

import android.os.Bundle;

import com.google.android.gms.common.data.DataHolder;

import droidsafe.annotations.*;
import droidsafe.runtime.*;
import droidsafe.helpers.*;
public abstract interface SearchableMetadataField extends com.google.android.gms.drive.metadata.MetadataField
{
	
	public static class DroidsafeSearchableMetadataField implements SearchableMetadataField {

		public DroidsafeSearchableMetadataField() {
			
		}

		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return "<some-name>";
		}

		@Override
		public Collection gC() {
			// TODO Auto-generated method stub
			return new LinkedList<Object>();
		}

		@Override
		public Object e(Bundle r0) {
			// TODO Auto-generated method stub
			Object o = new Object();
			o.addTaint(r0.getTaint());
			return o;
		}

		@Override
		public Object a(DataHolder r0, int i1, int i2) {
			// TODO Auto-generated method stub

			Object o = new Object();
			o.addTaint(r0.getTaintInt() + i1 + i2);
			return null;
		}

		@Override
		public void a(Object r0, Bundle r1) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
