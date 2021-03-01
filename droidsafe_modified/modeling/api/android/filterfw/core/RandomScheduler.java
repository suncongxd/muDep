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
 * 
 * 
 * This file incorporates work covered by the following copyright and
 * permission notice:
 *
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */



/***** THIS FILE HAS BEEN MODIFIED FROM THE ORIGINAL BY THE DROIDSAFE PROJECT. *****/


package android.filterfw.core;

import droidsafe.annotations.*;
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import java.util.Random;
import java.util.Vector;

import android.filterfw.core.Filter;
import android.filterfw.core.Scheduler;

/**
 * @hide
 */
public class RandomScheduler extends Scheduler {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:06:04.847 -0400", hash_original_field = "79600200ABA0C1EEBC07BED750DEA1CC", hash_generated_field = "9E0704D945E6BEF328A09AB9D77F1681")


    private Random mRand = new Random();

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:06:04.849 -0400", hash_original_method = "2CE9AA8285F6A2A65F790477982CB914", hash_generated_method = "961D332E848C27E5009D3057D2FFB607")
    
public RandomScheduler(FilterGraph graph) {
        super(graph);
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:06:04.851 -0400", hash_original_method = "4EF91A3E15873EFCED84A853021A57CF", hash_generated_method = "0B2662DB1D26440275E15FE32301A5F4")
    
@Override
    public void reset() {
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:06:04.853 -0400", hash_original_method = "D3EF8D567F50643ECAB0AA4C2A6CA652", hash_generated_method = "4F4299CDB615271353146E326A45C7FD")
    
@Override
    public Filter scheduleNextNode() {
        Vector<Filter> candidates = new Vector<Filter>();
        for (Filter filter : getGraph().getFilters()) {
            if (filter.canProcess())
                candidates.add(filter);
        }
        if (candidates.size() > 0) {
          int r = mRand.nextInt(candidates.size());
          return candidates.elementAt(r);
        }
        return null;
    }
}
