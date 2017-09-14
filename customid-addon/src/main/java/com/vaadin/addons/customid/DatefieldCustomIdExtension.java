package com.vaadin.addons.customid;

import com.vaadin.addons.customid.client.DateFieldState;
import com.vaadin.server.AbstractExtension;
import com.vaadin.ui.DateField;

public class DatefieldCustomIdExtension extends AbstractExtension {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DatefieldCustomIdExtension(final DateField field) {
		super.extend(field);
	}
	
	@Override
	protected DateFieldState getState() {
		return (DateFieldState) super.getState();
	}
	
	public void setTextFieldCustomId(String id) {
		getState().textFieldId = id;
	}
	
}
