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

package com.google.android.gms.drive;

import com.google.android.gms.common.api.DroidsafePendingResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.drive.query.Query;

import droidsafe.annotations.*;
import droidsafe.helpers.DSOnlyType;

public class DroidsafeDriveApi implements DriveApi {

	public DroidsafeDriveApi(DSOnlyType dontcare) {
		
	}
	@DSSink({DSSinkKind.GOOGLE_SERVICES})	
    @DSSafe(DSCat.SAFE_LIST)
    @Override
	public PendingResult query(GoogleApiClient r0, Query r1) {
		// TODO Auto-generated method stub
		PendingResult res = new DroidsafePendingResult(DSOnlyType.DONTCARE);
		res.addTaint(r1.getTaint());
		res.addTaint(r0.getTaint());
		return res;
	}

	@DSSafe(DSCat.SAFE_LIST)
    @Override
	public PendingResult newContents(GoogleApiClient r0) {
		// TODO Auto-generated method stub

		PendingResult res = new DroidsafePendingResult(DSOnlyType.DONTCARE);
		res.addTaint(r0.getTaint());
		return res;
	}

	@Override
	public PendingResult discardContents(GoogleApiClient r0, Contents r1) {
		// TODO Auto-generated method stub
        PendingResult res = new DroidsafePendingResult(DSOnlyType.DONTCARE);
		res.addTaint(r0.getTaint());
		res.addTaint(r1.hashCode());
		return res;
	}

	@Override
	public PendingResult fetchDriveId(GoogleApiClient r0, String r1) {
		// TODO Auto-generated method stub
        PendingResult res = new DroidsafePendingResult(DSOnlyType.DONTCARE);
		res.addTaint(r0.getTaint());
		res.addTaint(r1.hashCode());
		return res;
	}

	@DSSafe(DSCat.SAFE_LIST)
    @DSSource({DSSourceKind. GOOGLE_SERVICES})
	@Override
	public DriveFile getFile(GoogleApiClient r0, DriveId r1) {
		// TODO Auto-generated method stub
		DriveFile driveFile = new DroidsafeDriveFile(DSOnlyType.DONTCARE);
		return driveFile;
	}

	@DSSafe(DSCat.SAFE_LIST)
    @Override
	public CreateFileActivityBuilder newCreateFileActivityBuilder() {
		// TODO Auto-generated method stub
		CreateFileActivityBuilder builder = new CreateFileActivityBuilder();
		builder.addTaint(getTaint());
		return builder;
	}

	@DSSource({DSSourceKind.GOOGLE_DRIVE})
	public DriveFolder getRootFolder(GoogleApiClient r0) {
		// TODO Auto-generated method stub
		return null;
	}

	@DSSource({DSSourceKind.GOOGLE_DRIVE})
	public DriveFolder getAppFolder(GoogleApiClient r0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@DSSource({DSSourceKind.GOOGLE_DRIVE})
	public DriveFolder getFolder(GoogleApiClient r0, DriveId r1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OpenFileActivityBuilder newOpenFileActivityBuilder() {
		// TODO Auto-generated method stub
		return null;
	}

	public PendingResult requestSync(GoogleApiClient r0) {
		// TODO Auto-generated method stub
		PendingResult res = new DroidsafePendingResult(DSOnlyType.DONTCARE);
		res.addTaint(r0.getTaint());
		return res;
	}

}
