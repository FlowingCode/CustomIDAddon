package com.flowingcode.vaadin.addons.customid;

import com.flowingcode.vaadin.addons.customid.client.ListSelectCustomIdClientRpc;
import com.flowingcode.vaadin.addons.customid.client.ListSelectCustomIdState;
import com.vaadin.server.AbstractExtension;
import com.vaadin.v7.ui.ListSelect;

@SuppressWarnings("serial")
public class ListSelectCustomIdExtension extends AbstractExtension {
	
	public ListSelectCustomIdExtension(ListSelect listSelect, String customId) {
		super.extend(listSelect);
		setCustomId(customId);
		updateIds();
	}
	
	@Override
	protected ListSelectCustomIdState getState() {
		return (ListSelectCustomIdState) super.getState();
	}
	
	public void setCustomId(String customId) {
		getState().customId = customId;
	}

	/**
	 * Triggers the update of the custom HTML ids.
	 */
	public void updateIds() {
		getRpcProxy(ListSelectCustomIdClientRpc.class).updateIds();
	}
}
