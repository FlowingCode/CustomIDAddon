package com.flowingcode.vaadin.addons.customid.client;

import com.flowingcode.vaadin.addons.customid.OptionGroupCustomIdExtension;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ServerConnector;
import com.vaadin.client.extensions.AbstractExtensionConnector;
import com.vaadin.v7.client.ui.VOptionGroup;
import com.vaadin.shared.ui.Connect;

@SuppressWarnings("serial")
@Connect(OptionGroupCustomIdExtension.class)
public class OptionGroupCustomIdConnector extends AbstractExtensionConnector {

	@Override
	protected void extend(ServerConnector target) {
		final VOptionGroup optionGroupWidget = (VOptionGroup) ((ComponentConnector) target).getWidget();
		
        registerRpc(OptionGroupCustomIdClientRpc.class, new OptionGroupCustomIdClientRpc() {

			@Override
			public void updateIds() {
				optionGroupWidget.getElement().setId(getState().customId);
				Panel panel = optionGroupWidget.panel;
				for (Widget panelItem : panel) {
					String label = panelItem.getElement().getChild(1).getFirstChild().getNodeValue();
					panelItem.getElement().getFirstChildElement().setId(getState().customId + "_" + label);
				}
			}
            
        });
		
	}
	
	@Override
	public OptionGroupCustomIdState getState() {
		return (OptionGroupCustomIdState) super.getState();
	}	
	
}
