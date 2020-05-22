package com.github.east196.uniapi.event;
import com.google.common.eventbus.Subscribe;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class EventBusRecorder {

	@Subscribe void recordHeatbeat(Heatbeat e) {
		log.debug("e");
		System.out.println(e);
	}
}