package com.springbatch.app.batchImporter;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class JobRunner implements CommandLineRunner {

    private final JobLauncher jobLauncher;
    private final Job booksImportJob;

    @Autowired
    public JobRunner(JobLauncher jobLauncher, Job booksImportJob) {
        this.jobLauncher = jobLauncher;
        this.booksImportJob = booksImportJob;
    }

    @Override
    public void run(String... args) throws Exception {
        JobParameters params = new JobParametersBuilder()
            .addLong("timestamp", System.currentTimeMillis())
            .toJobParameters();

        jobLauncher.run(booksImportJob, params);
    }
}