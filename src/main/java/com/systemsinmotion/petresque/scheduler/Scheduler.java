package com.systemsinmotion.petresque.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;

import com.systemsinmotin.petresque.load.data.DataBaseBackUpManager;

public class Scheduler {

	@Autowired
	DataBaseBackUpManager dataBaseBackUpManager;

	@Bean
	public Scheduler getSchedule() {
		return new Scheduler();
	}

	@Scheduled(cron = "#{dataBaseBackUpUtil.getCronProperty()}")
	public void performDataBaseBackUp() {
		dataBaseBackUpManager.updateDataBase();
	}

}
