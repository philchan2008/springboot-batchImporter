# 📘 Spring Batch CSV Importer

Spring Batch is mature, battle-tested framework designed for robust, scalable batch processing, and it still shines in modern enterprise environments where large-scale data handling is critical.
Here’s how it holds up:
✅ Why Spring Batch is still relevant
- Chunk-oriented processing: Efficiently handles massive datasets without blowing up memory.
- Retry, skip, and fault tolerance: Built-in mechanisms for handling bad records or transient failures.
- Declarative job configuration: Clear separation of steps, readers, processors, and writers.
- Parallel and partitioned execution: Scales across threads or nodes for high throughput.
- Integration with Spring ecosystem: Plays nicely with Spring Boot, Spring Data, and scheduling tools like Quartz.

🆕 Modern alternatives (depending on use case)
- Apache Beam or Spark for distributed data processing
- Airflow or Dagster for orchestrating complex workflows
- Spring Cloud Data Flow if you want to scale Spring Batch jobs as microservices

This Spring Boot + Spring Batch application loads books data from a CSV file into a SQL Server database. It includes SQL scripts for schema setup and Docker configuration to spin up the database locally.

---

## 🛠 Project Structure

```
project-root/ 
├── src/                  # Spring Boot + Batch source code 
├── sql/                  # SQL scripts for schema setup 
│   └── schema.sql 
├── docker/               # Docker config for SQL Server 
│   └── docker-compose.yml 
├── build/libs/           # Compiled JAR output 
└── README.md
```

---

## 🚀 Getting Started

### 1. Start SQL Server via Docker

```bash
cd docker
docker-compose up -d
```

This will start a SQL Server container on port 1433 with default credentials.
⚠️ Make sure Docker is installed and running.


# 2. Prepare the Database
Run the SQL script to create the required schema:

```sql
sqlcmd -S localhost -U sa -P P@ssw0rd1234 -i sql/schema.sql
```
To configure batch metadata tables for different database platforms, consult the templates provided in the Spring Batch core module on GitHub.

Update credentials if needed in docker-compose.yml.

3. Import CSV Data
Ensure you can access the [books.csv](https://gist.github.com/hhimanshu/d55d17b51e0a46a37b739d0f3d3e3c74/raw/5b9027cf7b1641546c1948caffeaa44129b7db63/books.csv) file.
The application is configured to automatically start the `booksImportJob` on launch



# 🧪 Running the Application
## Option 1: From Source
```batch
./gradlew bootRun
```

## Option 2: From JAR
```bash
./gradlew bootJar
java -jar ./build/libs/batchImporter-0.0.1-SNAPSHOT.jar
```

# ⚙️ Configuration
Update `application.properties` or `application.yml` with your DB credentials:
```yml
spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=BooksDB
spring.datasource.username=sa
spring.datasource.password=YourStrong!Passw0rd
```

# 📂 Notes
- Ensure Docker container is running and port 1433 is available
- CSV file must be reachable at runtime
- Job auto-starts on application launch

# 📄 License
MIT — feel free to use, adapt, and share.
