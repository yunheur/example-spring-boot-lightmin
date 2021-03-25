# example-spring-boot-lightmin

- https://github.com/tuxdevelop/spring-batch-lightmin


### Meta-Data Schema
- server
  - https://github.com/tuxdevelop/spring-batch-lightmin/tree/master/spring-batch-lightmin-repository/spring-batch-lightmin-repository-jdbc/src/main/resources/org/tuxdevelop/spring/batch/lightmin/repository
  ```
  CREATE TABLE BATCH_JOB_CONFIGURATION (
    job_configuration_id   BIGINT AUTO_INCREMENT NOT NULL,
    application_name       VARCHAR(255)          NOT NULL,
    job_name               VARCHAR(255),
    job_incrementer        VARCHAR(255),
    job_configuration_type INT                   NOT NULL,
    PRIMARY KEY (job_configuration_id)
  );
  CREATE TABLE BATCH_JOB_CONFIGURATION_VALUE (
    id                   BIGINT AUTO_INCREMENT NOT NULL,
    job_configuration_id BIGINT                NOT NULL,
    value_key            VARCHAR(255)          NOT NULL,
    configuration_value  VARCHAR(255),
    PRIMARY KEY (id),
    FOREIGN KEY (job_configuration_id) REFERENCES BATCH_JOB_CONFIGURATION (job_configuration_id)
  );
  CREATE TABLE BATCH_JOB_CONFIGURATION_PARAMETERS (
    id                   BIGINT AUTO_INCREMENT NOT NULL,
    job_configuration_id BIGINT                NOT NULL,
    parameter_name       VARCHAR(255)          NOT NULL,
    parameter_value      VARCHAR(255)          NOT NULL,
    parameter_type       INT                   NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (job_configuration_id) REFERENCES BATCH_JOB_CONFIGURATION (job_configuration_id)
  );
  ```

- client
  - spring batch 수행관련 내용들을 메타데이터로 남겨서 테이블에 저장함
  - https://docs.spring.io/spring-batch/docs/current/reference/html/schema-appendix.html
    ![client-schema](https://docs.spring.io/spring-batch/docs/current/reference/html/images/meta-data-erd.png)
    
