package com.vaadin.addons.customid.client;

import com.google.gwt.dom.client.Node;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.user.client.Element;
import com.vaadin.addons.customid.ListSelectCustomIdExtension;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ServerConnector;
import com.vaadin.client.extensions.AbstractExtensionConnector;
import com.vaadin.client.ui.VListSelect;
import com.vaadin.shared.ui.Connect;

@SuppressWarnings({ "serial", "deprecation" })
@Connect(ListSelectCustomIdExtension.class)
public class ListSelectCustomIdConnector extends AbstractExtensionConnector {

	@Override
	protected void extend(ServerConnector target) {
		final VListSelect lsWidget = (VListSelect) ((ComponentConnector) target).getWidget();
		
		registerRpc(ListSelectCustomIdClientRpc.class, new ListSelectCustomIdClientRpc() {
			
			@Override
			public void updateIds() {
				lsWidget.getElement().getFirstChildElement().setId(getState().customId);

				NodeList<Node> nodes = lsWidget.getElement().getFirstChildElement().getChildNodes();
				int nodesLength = nodes.getLength();
		        for (int i = 0; i < nodesLength; i++) {
		            if (nodes.getItem(i).getNodeType() == Node.ELEMENT_NODE) {
		            	Element el = (Element) nodes.getItem(i);
		            	String label = el.getInnerText();
		            	el.setId(getState().customId + "_" + label);		            	 
		            }
		        }							
			}
		});
		
	}
	
	@Override
	public ListSelectCustomIdState getState() {
		return (ListSelectCustomIdState) super.getState();
	}

}
