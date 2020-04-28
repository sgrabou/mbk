package com.sopra.notification.batch.common;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BatchLauncher {

	private static Integer codeExit = 255;
	private static final Logger LOG = LoggerFactory
			.getLogger(BatchLauncher.class);
	private final String jobLauncher;
	private final String jobName;
	private final String jobRepository;
	private final String springFileName;

	public BatchLauncher(String springFileName, String jobLauncher,
			String jobRepository, String jobName) {
		this.springFileName = springFileName;
		this.jobLauncher = jobLauncher;
		this.jobRepository = jobRepository;
		this.jobName = jobName;
	}

	public int getCodeExit() {
		return codeExit;
	}

	public void launch() throws FileNotFoundException,
			JobExecutionAlreadyRunningException, JobRestartException,
			JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		final long startTime = System.currentTimeMillis();
		try {
			LOG.info("Starting " + jobName + " ...");
			AbstractApplicationContext applicationContext = new ClassPathXmlApplicationContext(
					springFileName);
			applicationContext.registerShutdownHook();
			JobParametersBuilder builder = new JobParametersBuilder();
			builder.addDate("date", new Date());
			JobLauncher jobLauncherBean = (JobLauncher) applicationContext
					.getBean(jobLauncher);
			Job job = (Job) applicationContext.getBean(jobName);
			jobLauncherBean.run(job, builder.toJobParameters());
			JobRepository jobRepositoryBean = (JobRepository) applicationContext
					.getBean(jobRepository);
			JobExecution jobExecution = jobRepositoryBean.getLastJobExecution(
					job.getName(), builder.toJobParameters());
			if (jobExecution != null
					&& BatchStatus.COMPLETED.equals(jobExecution.getStatus())) {
				codeExit = 0;
			}
		} catch (Throwable e) { // NOPMD by abarakat on 11/11/13 09:19
			LOG.error("Exception", e);
		} finally {
			final long endTime = System.currentTimeMillis();
			LOG.info("Finished  " + jobName + " in: "
					+ calculateDuration(startTime, endTime) + "!");
			System.exit(this.getCodeExit());// NOSONAR
		}
	}

	private static String calculateDuration(long startTime, long endTime) {
		final long duration = endTime - startTime;
		StringBuilder sb = new StringBuilder();
		sb.append(TimeUnit.MILLISECONDS.toHours(duration));
		sb.append(" h ");
		sb.append(TimeUnit.MILLISECONDS.toMinutes(duration)
				- TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS
						.toHours(duration)));
		sb.append(" min ");
		sb.append(TimeUnit.MILLISECONDS.toSeconds(duration)
				- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS
						.toMinutes(duration)));
		sb.append(" sec ");
		return sb.toString();
	}

}
