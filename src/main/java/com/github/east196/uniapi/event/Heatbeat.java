package com.github.east196.uniapi.event;

import com.github.east196.uniapi.core.BasePojo;
import com.google.common.eventbus.EventBus;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
class Heatbeat extends BasePojo {
	long ctime;
	long stime;
	long gtime;

	public static void main(final String[] args) {
		EventBus eventBus = new EventBus();
		EventBusRecorder eventBusRecorder = new EventBusRecorder();
		eventBus.register(eventBusRecorder);
		Heatbeat heatbeat = new Heatbeat();
		eventBus.post(heatbeat);

	}
}