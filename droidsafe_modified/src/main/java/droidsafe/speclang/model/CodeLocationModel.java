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

/**
 * 
 */
package droidsafe.speclang.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import droidsafe.utils.SourceLocationTag;

/**
 * Wrapper around the droidsafe.speclang.SourceLineNumberTag to include the status of the issue.
 * 
 * @author Marcel Becker (becker@kestrel.edu)
 * 
 */
public class CodeLocationModel extends SourceLocationTag implements IModelChangeSupport {

  /**
   * Serialization id for class.
   */
  private static final long serialVersionUID = -8049573443676341282L;

  /**
   * Logger.
   */
  @SuppressWarnings("unused")
  private static final Logger logger = LoggerFactory.getLogger(CodeLocationModel.class);

  /**
   * Current status of the code location.
   */
  private DroidsafeIssueResolutionStatus status = DroidsafeIssueResolutionStatus.UNSAFE;

  /** Element to support updates to the object to be communicated to the UI. */
  private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);


  private List<HotspotModel> hotspots = new ArrayList<HotspotModel>();

  public CodeLocationModel(String clz, int line) {
    super(clz, line);
  }

  public CodeLocationModel(SourceLocationTag line) {
    this(line.getClz(), line.getLine());
  }

  /**
   * Setter for status value. Fires a property change event to notify the UI.
   * 
   * The property change event is only fired if the new status value is not the same as the current
   * value.
   * 
   * @param newStatus
   */
  public void setStatus(DroidsafeIssueResolutionStatus newStatus) {
    if (newStatus != getStatus()) {
      // logger.debug("Firing propertyChange event for "+this);
      firePropertyChange("status", this.status, this.status = newStatus);
    }
  }

  /**
   * Set the status of this code location to SAFE.
   */
  public void setSafe() {
    setStatus(DroidsafeIssueResolutionStatus.SAFE);
  }

  /**
   * Set the status of this code location to UNSAFE.
   */
  public void setUnsafe() {
    setStatus(DroidsafeIssueResolutionStatus.UNSAFE);
  }

  /**
   * Set the status of this code location to PENDING further decision on safety of issue.
   */
  public void setPending() {
    setStatus(DroidsafeIssueResolutionStatus.PENDING);
  }

  /**
   * Set the status of this code location to UNRESOLVED, meaning that the issue has not yet been
   * considered.
   */
  public void setUnresolved() {
    setStatus(DroidsafeIssueResolutionStatus.UNRESOLVED);
  }


  /**
   * @return the current status of the location.
   */
  public DroidsafeIssueResolutionStatus getStatus() {
    return this.status;
  }

  /**
   * Returns true if the status of the location is safe.
   */
  public boolean isSafe() {
    return (this.status == DroidsafeIssueResolutionStatus.SAFE);
  }

  /**
   * Returns true if the status of the location is unsafe, false otherwise.
   */
  public boolean isUnsafe() {
    return (this.status == DroidsafeIssueResolutionStatus.UNSAFE);
  }

  /**
   * Returns true if the status of the location is unresolved, false otherwise.
   */
  public boolean isUnresolved() {
    return (this.status == DroidsafeIssueResolutionStatus.UNRESOLVED);
  }

  public void addHotspot(HotspotModel hot) {
    this.hotspots.add(hot);
  }

  public void addHotspots(Collection<HotspotModel> hotspots) {
    this.hotspots.addAll(hotspots);
  }

  public void removeHotspot(HotspotModel hot) {
    this.hotspots.remove(hot);
  }
  
  public List<HotspotModel>getHotspots(){
    return this.hotspots;
  }

  public void addPropertyChangeListener(PropertyChangeListener listener) {
    changeSupport.addPropertyChangeListener(listener);
  }

  public void removePropertyChangeListener(PropertyChangeListener listener) {
    changeSupport.removePropertyChangeListener(listener);
  }

  public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
    changeSupport.addPropertyChangeListener(propertyName, listener);
  }

  public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
    changeSupport.removePropertyChangeListener(propertyName, listener);
  }

  protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
    changeSupport.firePropertyChange(propertyName, oldValue, newValue);
  }

 
}
