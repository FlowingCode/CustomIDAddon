package com.flowingcode.vaadin.addons.customid.demo;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import com.flowingcode.vaadin.addons.customid.DatefieldCustomIdExtension;
import com.flowingcode.vaadin.addons.customid.ListSelectCustomIdExtension;
import com.flowingcode.vaadin.addons.customid.OptionGroupCustomIdExtension;
import com.flowingcode.vaadin.addons.customid.TwinColSelectCustomIdExtension;
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
import com.vaadin.v7.ui.OptionGroup;
import com.vaadin.ui.TwinColSelect;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("demo")
@Title("Custom Id Add-on Demo")
@SuppressWarnings("serial")
public class DemoUI extends UI
{

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class, widgetset = "com.flowingcode.vaadin.addons.customid.demo.DemoWidgetSet")
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {
        final VerticalLayout layout = new VerticalLayout();
        layout.setStyleName("demoContentLayout");
        layout.setSizeFull();

        GridLayout gridLayout = new GridLayout(2,2);
        gridLayout.setSizeFull();
        gridLayout.addStyleName("gridLayoutBorders");
                      
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
		optionLayout.setComponentAlignment(single, Alignment.MIDDLE_CENTER);
		optionLayout.setComponentAlignment(b, Alignment.MIDDLE_CENTER);		
		gridLayout.addComponent(optionLayout, 1, 0);
        gridLayout.setComponentAlignment(optionLayout, Alignment.MIDDLE_CENTER);
		
		// twincolselect
        TwinColSelect<String> tcs = new TwinColSelect<>("Select Targets");
        tcs.setItems("item 1", "item 2", "item 3");

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
        tcsLayout.setComponentAlignment(tcs, Alignment.MIDDLE_CENTER);
        tcsLayout.setComponentAlignment(b2, Alignment.MIDDLE_CENTER);
        gridLayout.addComponent(tcsLayout, 0, 1);
        gridLayout.setComponentAlignment(tcsLayout, Alignment.MIDDLE_CENTER);
        
        // listselect
        List<String> data = new ArrayList<>();
        for(int i = 0; i < 6; i++) {
        	data.add("Option " + i);
        }
        ListSelect<String> ls = new ListSelect<>("", data);
        ls.setRows(6); 
        ListSelectCustomIdExtension lsExtension = new ListSelectCustomIdExtension(ls, "CUSTOM_ID");
        
        gridLayout.addComponent(ls, 1, 1);
        gridLayout.setComponentAlignment(ls, Alignment.MIDDLE_CENTER);
               
        layout.addComponents(gridLayout);
        setContent(layout);

    }

}
