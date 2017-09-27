/**
 * 
 */
package com.flowingcode.vaadin.addons.customid;

import com.flowingcode.vaadin.addons.customid.client.OptionGroupCustomIdClientRpc;
import com.flowingcode.vaadin.addons.customid.client.OptionGroupCustomIdState;
import com.vaadin.server.AbstractExtension;
import com.vaadin.v7.ui.OptionGroup;

/**
 * This extension allows to change the HTML ID attribute of an OptionGroup. After initializing the OptionGroup (with their
 * correspondent options), this extension has to be instantiated using the constructor that supplies the string that you
 * want to use as a custom HTML id. The ids of each option will be created using the customId string + "_" + the label value.
 * 
 * @author mlopez
 *
 */
@SuppressWarnings("serial")
public class OptionGroupCustomIdExtension extends AbstractExtension {
	
	/**
	 * @param optionGroup The optionGroup to be customized
	 * @param customId The customId of the optionGroup
	 */
	public OptionGroupCustomIdExtension(OptionGroup optionGroup, String customId) {
		super.extend(optionGroup);
		setCustomId(customId);
		updateIds();
	}
	
	protected OptionGroupCustomIdState getState() {
		return (OptionGroupCustomIdState) super.getState();
	}
	
	/**
	 * Allows to change the customId, after setting this, updateIds() has to be called.
	 * @param id
	 */
	public void setCustomId(String id) {
		getState().customId = id;
	}
	
	/**
	 * Triggers the update of the custom HTML ids.
	 */
	public void updateIds() {
		getRpcProxy(OptionGroupCustomIdClientRpc.class).updateIds();
	}

}
