package com.springbatch.app.batchImporter;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;


public class BookInsertListener implements StepExecutionListener {

    private final DataSource dataSource;

    public BookInsertListener(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        try (Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement()) {
            stmt.execute("SET IDENTITY_INSERT books ON");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to enable IDENTITY_INSERT", e);
        }
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("SET IDENTITY_INSERT books OFF");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to disable IDENTITY_INSERT", e);
        }
        return ExitStatus.COMPLETED;
    }
}