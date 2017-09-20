package com.vaadin.addons.customid.demo;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import com.vaadin.addons.customid.DatefieldCustomIdExtension;
import com.vaadin.addons.customid.ListSelectCustomIdExtension;
import com.vaadin.addons.customid.OptionGroupCustomIdExtension;
import com.vaadin.addons.customid.TwinColSelectCustomIdExtension;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TwinColSelect;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("demo")
@Title("Custom Id Add-on Demo")
@SuppressWarnings("serial")
public class DemoUI extends UI
{

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class, widgetset = "com.vaadin.addons.customid.demo.DemoWidgetSet")
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {
        final VerticalLayout layout = new VerticalLayout();
        layout.setStyleName("demoContentLayout");
        layout.setSizeFull();

        GridLayout gridLayout = new GridLayout(4,2);
        gridLayout.setSizeFull();
                      
        // datefield
        DateField df = new DateField();
        df.setId("CustomId");
        DatefieldCustomIdExtension dcie = new DatefieldCustomIdExtension(df);
        dcie.setTextFieldCustomId("CustomHTMLID");
        
        gridLayout.addComponent(df, 0, 0);
        gridLayout.setComponentAlignment(df, Alignment.MIDDLE_CENTER);
                
        // optiongroup
        OptionGroup single = new OptionGroup("Single Selection");
        single.addItems("Single", "Sola", "Yksi");
                
        final OptionGroupCustomIdExtension extension = new OptionGroupCustomIdExtension(single,"CUSTOM_ID");
        
        Button b = new Button("Test");
        b.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				extension.setCustomId("CUSTOM_ID_CHANGED");
				extension.updateIds();
			}
        });
		extension.updateIds();
		
		VerticalLayout optionLayout = new VerticalLayout(single, b);
		gridLayout.addComponent(optionLayout, 1, 0);
        gridLayout.setComponentAlignment(optionLayout, Alignment.MIDDLE_CENTER);
		
		// twincolselect
		TwinColSelect tcs = new TwinColSelect();
        tcs.addItems("item 1", "item 2", "item 3");
        final TwinColSelectCustomIdExtension twinColExtension = new TwinColSelectCustomIdExtension(tcs, "CustomLeftHTMLID", "CustomRightHTMLID");
        
        Button b2 = new Button("Test Twin Select");
        b2.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				twinColExtension.setTwinColOptionsId("CustomLeft_ID_CHANGED");
				twinColExtension.setTwinColSelectionsId("CustomRight_ID_CHANGED");
				twinColExtension.updateIds();
			}			
        });
        
        VerticalLayout tcsLayout = new VerticalLayout(tcs, b2);
        gridLayout.addComponent(tcsLayout, 2, 0);
        gridLayout.setComponentAlignment(tcsLayout, Alignment.MIDDLE_CENTER);
        
        // listselect
        List<String> data = new ArrayList<>();
        for(int i = 0; i < 6; i++) {
        	data.add("Option " + i);
        }
        ListSelect ls = new ListSelect("", data);
        ls.setRows(6);
        ls.setNullSelectionAllowed(false);
        ListSelectCustomIdExtension lsExtension = new ListSelectCustomIdExtension(ls, "CUSTOM_ID");
        
        gridLayout.addComponent(ls, 3, 0);
        gridLayout.setComponentAlignment(ls, Alignment.MIDDLE_CENTER);
               
        layout.addComponents(gridLayout);
        setContent(layout);

    }

}
