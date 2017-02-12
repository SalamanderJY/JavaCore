package com.event;

import java.awt.*;
import java.beans.*;
import java.lang.reflect.*;

public class EventTracer 
{
    public EventTracer()
    {
    	//the handler for all event proxies
    	handler = new InvocationHandler()
    	{

			@Override
			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
				// TODO Auto-generated method stub
				System.out.println(method + ":" + args[0]);
				return null;
			}
    		
    	};
    }
    
    //adds event tracers for all events to which this component and its children can listen
    public void add(Component c)
    {
    	try
    	{
    		//get all events to which this component can listen
    		BeanInfo info = Introspector.getBeanInfo(c.getClass());
    		
    		EventSetDescriptor[] eventSets = info.getEventSetDescriptors();
    		for(EventSetDescriptor eventSet : eventSets)
    			addListener(c, eventSet);
    	}
    	catch (IntrospectionException e)
    	{
    	}
    	//ok not to add listeners if exception is thrown
    	
    	if(c instanceof Container)
    	{
    		//get all children and call add recursively
    		for(Component comp : ((Container) c).getComponents())
    			add(comp);
    	}
    }
    
    
    //Add a listener to the given event set.
    public void addListener(Component c, EventSetDescriptor eventSet)
    {
    	//make proxy object for this listener type and route all calls to the handler
    	Object proxy = Proxy.newProxyInstance(null, new Class[] { eventSet.getListenerType() }, handler);
    	
    	//add the proxy as a listener to the component
    	Method addListenerMethod = eventSet.getAddListenerMethod();
    	try
    	{
    		addListenerMethod.invoke(c, proxy);
    	}
    	catch(InvocationTargetException e)
    	{
    	}
    	catch(IllegalAccessException e)
    	{
    	}
    	//ok not to add listener if exception is thrown
    }
    
    private InvocationHandler handler;
}
