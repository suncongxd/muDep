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


/***** THIS FILE HAS BEEN MODIFIED FROM THE ORIGINAL BY THE DROIDSAFE PROJECT. *****/


package javax.sip;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import java.util.TooManyListenersException;

import javax.sip.header.CallIdHeader;
import javax.sip.message.Request;
import javax.sip.message.Response;

public interface SipProvider {
    
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    void setListeningPoint(ListeningPoint listeningPoint)
            throws ObjectInUseException;
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    void addListeningPoint(ListeningPoint listeningPoint)
            throws ObjectInUseException;
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    void removeListeningPoint(ListeningPoint listeningPoint)
            throws ObjectInUseException;
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    void removeListeningPoints();
    
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    ListeningPoint getListeningPoint();
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    ListeningPoint getListeningPoint(String transport);
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    ListeningPoint[] getListeningPoints();

    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    void addSipListener(SipListener sipListener)
            throws TooManyListenersException;
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    void removeSipListener(SipListener sipListener);

    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    CallIdHeader getNewCallId();

    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    ClientTransaction getNewClientTransaction(Request request)
            throws TransactionUnavailableException;
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    ServerTransaction getNewServerTransaction(Request request)
            throws TransactionAlreadyExistsException,
            TransactionUnavailableException;

    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    Dialog getNewDialog(Transaction transaction) throws SipException;

    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    boolean isAutomaticDialogSupportEnabled();
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    void setAutomaticDialogSupportEnabled(boolean flag);

    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    SipStack getSipStack();

    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    void sendRequest(Request request) throws SipException;
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    void sendResponse(Response response) throws SipException;
}
