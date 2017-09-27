package com.flowingcode.vaadin.addons.customid.client;

import com.flowingcode.vaadin.addons.customid.DatefieldCustomIdExtension;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ServerConnector;
import com.vaadin.client.extensions.AbstractExtensionConnector;
import com.vaadin.client.ui.VPopupCalendar;
import com.vaadin.shared.ui.Connect;


@Connect(DatefieldCustomIdExtension.class)
public class DateFieldConnector extends AbstractExtensionConnector {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void extend(ServerConnector target) {
        // Get the extended widget
        final VPopupCalendar pw =
                (VPopupCalendar) ((ComponentConnector) target).getWidget();
        
        DateFieldState state = getState();
        
        if (state.textFieldId!=null)
        	pw.text.asWidget().getElement().setId(state.textFieldId);
        
    }
	
	@Override
	public DateFieldState getState() {
		return (DateFieldState) super.getState();
	}

}
