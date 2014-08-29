package com.systemsinmotion.petresque.scheduler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.systemsinmotion.petrescue.data.writer.RemoteIdentifierWriter;
import com.systemsinmotion.petrescue.mail.MailManager;
import com.systemsinmotion.petrescue.translator.RemoteIdentifierTranslator;

@Service("scheduler")
public class Scheduler {

	@Autowired
	private RemoteIdentifierWriter remoteIdentifierWriter;

	@Autowired
	private RemoteIdentifierTranslator remoteIdentifierTranslator;

	@Autowired
	private MailManager mailManager;

	@Scheduled(cron = "#{dataBaseBackUpUtil.getCronProperty()}")
	public void performDataBaseBackUp() {

		try {

			remoteIdentifierWriter.write(remoteIdentifierTranslator.translatePetRecords());

		} catch (Exception e) {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			mailManager.send_error(e,
					"Fail to write records at " + dateFormat.format(date) + " caused by " + e.getCause());
		}
	}

}
