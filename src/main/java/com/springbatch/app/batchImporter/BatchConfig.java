
package com.springbatch.app.batchImporter;

import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.UrlResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.stereotype.Component;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		dataSource.setUrl("jdbc:sqlserver://localhost:1433;databaseName=demo;encrypt=false;trustServerCertificate=true");
        //dataSource.setUrl("jdbc:postgresql://localhost:5432/book");
		// Set username and password if needed
		//dataSource.setUsername("admin");
        dataSource.setUsername("sa");
		dataSource.setPassword("P@ssw0rd1234");
		return dataSource;
	}

	@Bean
	public FlatFileItemReader<BookRecord> booksReader() throws MalformedURLException {
		return new FlatFileItemReaderBuilder<BookRecord>()
				.name("bookRecordReader")
				.resource(new UrlResource("https://gist.github.com/hhimanshu/d55d17b51e0a46a37b739d0f3d3e3c74/raw/5b9027cf7b1641546c1948caffeaa44129b7db63/books.csv"))
				.delimited()
				.delimiter(",")
				.names(new String[]{
						"bookId", "title", "author", "rating", "description", "language", "isbn", "bookFormat", "edition", "pages", "publisher", "publishDate", "firstPublishDate", "likedPercent", "price"
				})
				.fieldSetMapper(new BeanWrapperFieldSetMapper<BookRecord>() {{
					setTargetType(BookRecord.class);
				}})
				.linesToSkip(1) // skip header
				.build();
	}

    @Component
    public class BookRecordProcessor implements ItemProcessor<BookRecord, BookRecord> {

        @Override
        public BookRecord process(BookRecord book) throws Exception {
            // Basic validation
            // if (book.getIsbn() == null) {
            //     return null; // Skip invalid rows
            // }

            // Trim and normalize fields
            book.setTitle(capitalize(book.getTitle()));
            book.setLanguage(book.getLanguage() != null ? book.getLanguage().toLowerCase() : null);
            book.setIsbn(book.getIsbn() != null ? book.getIsbn().trim() : null);

            System.out.println("Processing book: " + book.getTitle());

            return book;
        }

        private String capitalize(String input) {
            if (input == null || input.isBlank()) return input;
            return Arrays.stream(input.trim().split(" "))
                        .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase())
                        .collect(Collectors.joining(" "));
        }
    }

	@Bean
	public JdbcBatchItemWriter<BookRecord> booksWriter(DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder<BookRecord>()
				.itemSqlParameterSourceProvider(new org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider<>())
				.sql("INSERT INTO books (bookId, title, author, rating, description, language, isbn, bookFormat, edition, pages, publisher, publishDate, firstPublishDate, likedPercent, price) " +
						"VALUES (:bookId, :title, :author, :rating, :description, :language, :isbn, :bookFormat, :edition, :pages, :publisher, :publishDate, :firstPublishDate, :likedPercent, :price)")
				.dataSource(dataSource)
				.build();
	}

    @Bean
    public Job booksImportJob(JobRepository jobRepository,
                           JdbcTransactionManager transactionManager,
                           FlatFileItemReader<BookRecord> booksReader,
                           JdbcBatchItemWriter<BookRecord> booksWriter) {

        Step booksImportStep = new StepBuilder("booksImportStep", jobRepository)
            .<BookRecord, BookRecord>chunk(1000, transactionManager)
            .reader(booksReader)
            .processor(new BookRecordProcessor())
            .writer(booksWriter)
            .build();

        return new JobBuilder("booksImportJob", jobRepository)
            .start(booksImportStep)
            .build();
    }

}

