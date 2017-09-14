package com.vaadin.addons.customid;

import com.vaadin.addons.customid.client.TwinColSelectCustomIdClientRpc;
import com.vaadin.addons.customid.client.TwinColSelectCustomIdState;
import com.vaadin.server.AbstractExtension;
import com.vaadin.ui.TwinColSelect;

/**
 * Extension that allows to add and change the HTML ID attribute of the two columns inside a TwinColSelect component.
 * 
 * 
 * @author pbartolo
 *
 */
@SuppressWarnings("serial")
public class TwinColSelectCustomIdExtension extends AbstractExtension {
	
	public TwinColSelectCustomIdExtension(TwinColSelect twinColSelect, String twinColOptionsCustomId, String twinColSelectionsCustomId) {
		super.extend(twinColSelect);
		setTwinColOptionsId(twinColOptionsCustomId);
		setTwinColSelectionsId(twinColSelectionsCustomId);
		updateIds();
	}
	
	public TwinColSelectCustomIdState getState() {
		return (TwinColSelectCustomIdState) super.getState();
	}
	
	public void setTwinColOptionsId(String twinColOptionsCustomId) {
		getState().twinColOptionsCustomId = twinColOptionsCustomId;
	}
	
	public void setTwinColSelectionsId(String twinColSelectionsCustomId) {
		getState().twinColSelectionsCustomId = twinColSelectionsCustomId;
	}
	
	/**
	 * Triggers the update of the custom HTML ids.
	 */
	public void updateIds() {
		getRpcProxy(TwinColSelectCustomIdClientRpc.class).updateIds();
	}
	
}
