package com.systemsinmotion.petrescue.data.migration;

import static org.junit.Assert.assertNotNull;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = { "/test-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class PetFinderConfigurationTest {
	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job petFinderJob;

	@Test
	public void testSimpleProperties() throws Exception {
		assertNotNull(jobLauncher);
	}

	@Test
	public void testLaunchJob() throws Exception {
		Map<String, JobParameter> params = new HashMap<String, JobParameter>();
		params.put("timestamp", new JobParameter(GregorianCalendar.getInstance().getTime()));
		jobLauncher.run(petFinderJob, new JobParameters(params));
	}
}
