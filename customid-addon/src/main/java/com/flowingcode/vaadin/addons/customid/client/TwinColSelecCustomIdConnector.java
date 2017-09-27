package com.flowingcode.vaadin.addons.customid.client;

import com.flowingcode.vaadin.addons.customid.TwinColSelectCustomIdExtension;
import com.google.gwt.dom.client.Node;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.user.client.Element;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ServerConnector;
import com.vaadin.client.extensions.AbstractExtensionConnector;
import com.vaadin.client.ui.VTwinColSelect;
import com.vaadin.shared.ui.Connect;

/**
 * 
 * @author pbartolo
 *
 */
@SuppressWarnings({ "serial", "deprecation" })
@Connect(TwinColSelectCustomIdExtension.class)
public class TwinColSelecCustomIdConnector extends AbstractExtensionConnector {

	@Override
	protected void extend(ServerConnector target) {
		final VTwinColSelect tcsWidget =  (VTwinColSelect) ((ComponentConnector) target).getWidget();
				
		registerRpc(TwinColSelectCustomIdClientRpc.class, new TwinColSelectCustomIdClientRpc() {
			
			@Override
			public void updateIds() {
				// updates the ids of the options column and selections column
				if(getState().twinColOptionsCustomId != null) {
					tcsWidget.getSubPartElement("leftSelect").setId(getState().twinColOptionsCustomId);
				}
				
				if(getState().twinColSelectionsCustomId != null) {
					tcsWidget.getSubPartElement("rightSelect").setId(getState().twinColSelectionsCustomId);
				}
				
				// updates the ids of the elements in the options column
				NodeList<Node> nodesLeft = tcsWidget.getSubPartElement("leftSelect").getChildNodes();
				int lengthLeft = nodesLeft.getLength();
		        for (int i = 0; i < lengthLeft; i++) {
		            if (nodesLeft.getItem(i).getNodeType() == Node.ELEMENT_NODE) {
		            	 Element el = (Element) nodesLeft.getItem(i);
		            	 String label = el.getInnerText();
		            	 String elementName = tcsWidget.getSubPartName(el);
		            	 tcsWidget.getSubPartElement(elementName).setId(getState().twinColOptionsCustomId + "_" + label);		            	 
		            }
		        }
		        
		     // updates the ids of the elements in the selections column
		        NodeList<Node> nodesRight = tcsWidget.getSubPartElement("rightSelect").getChildNodes();
				int lengthRight = nodesRight.getLength();
		        for (int i = 0; i < lengthRight; i++) {
		            if (nodesRight.getItem(i).getNodeType() == Node.ELEMENT_NODE) {
		            	 Element el = (Element) nodesRight.getItem(i);
		            	 String label = el.getInnerText();
		            	 String elementName = tcsWidget.getSubPartName(el);
		            	 tcsWidget.getSubPartElement(elementName).setId(getState().twinColSelectionsCustomId + "_" + label);		            	 
		            }
		        }
				
			}
		});
	}
		
	@Override
	public TwinColSelectCustomIdState getState() {
		return (TwinColSelectCustomIdState) super.getState();
	}

}
