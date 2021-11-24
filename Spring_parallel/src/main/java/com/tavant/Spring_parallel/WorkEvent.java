package com.tavant.Spring_parallel;

import org.springframework.context.ApplicationEvent;

public class WorkEvent extends ApplicationEvent {

	public WorkEvent(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "Work Event Practise...";
	}
}
