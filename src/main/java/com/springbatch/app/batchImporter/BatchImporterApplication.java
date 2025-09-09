package com.springbatch.app.batchImporter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BatchImporterApplication {

	public static void main(String[] args) {
		// String[] updatedArgs = Stream.concat(
		// 	Arrays.stream(args),
		// 	Stream.of("--spring.batch.job.name=importJob")
		// ).toArray(String[]::new);

		SpringApplication.run(BatchImporterApplication.class, args);
	}

}
