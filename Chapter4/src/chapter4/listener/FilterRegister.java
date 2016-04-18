package chapter4.listener;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import chapter4.EncodingFilter;
import chapter4.TimeFilter;

@WebListener
public class FilterRegister implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		System.out.println("START FilterRegister#contextInitialised()");

		ServletContext context = servletContextEvent.getServletContext();

		addFilter(context, "EncodingFilter", EncodingFilter.class, "/*");
		addFilter(context, "TimeFilter", TimeFilter.class, "/*");

		System.out.println("END FilterRegister#contextInitialized()");

	}

	private void addFilter(ServletContext context, String filterName, Class<? extends Filter> filterClass, String... urlPatterns){
		context.addFilter(filterName, filterClass);
		FilterRegistration filterRegistration = context.getFilterRegistration(filterName);

		EnumSet<DispatcherType> dispatcherTypes = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD);
		filterRegistration.addMappingForUrlPatterns(dispatcherTypes, false, urlPatterns);

	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
