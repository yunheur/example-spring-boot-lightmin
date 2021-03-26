# example-spring-boot-lightmin

- https://github.com/tuxdevelop/spring-batch-lightmin


### Meta-Data Schema
- server
  - lightmin 서버에서 사용하는 테이블 생성
    ```
    CREATE TABLE SCHEDULER_CONFIGURATION
    (
        id                   BIGINT AUTO_INCREMENT NOT NULL,
        application_name     VARCHAR(255) NOT NULL,
        job_name             VARCHAR(255) NOT NULL,
        configuration_status VARCHAR(255) NOT NULL,
        PRIMARY KEY (id)
    );

    CREATE TABLE SCHEDULER_CONFIGURATION_VALUE
    (
        id                         BIGINT AUTO_INCREMENT NOT NULL,
        scheduler_configuration_id BIGINT       NOT NULL,
        type                       VARCHAR(255) NOT NULL,
        value                      VARCHAR(255) NOT NULL,
        PRIMARY KEY (id),
        FOREIGN KEY (scheduler_configuration_id) REFERENCES SCHEDULER_CONFIGURATION (id)
    );

    CREATE TABLE SCHEDULER_EXECUTION
    (
        id                         BIGINT AUTO_INCREMENT NOT NULL,
        scheduler_configuration_id BIGINT    NOT NULL,
        next_fire_time             TIMESTAMP NOT NULL,
        execution_count            INT       NOT NULL DEFAULT 0,
        state                      INT       NOT NULL,
        last_update                TIMESTAMP NOT NULL,
        next_retry                 TIMESTAMP,
        PRIMARY KEY (id),
        FOREIGN KEY (scheduler_configuration_id) REFERENCES SCHEDULER_CONFIGURATION (id)
    );

    -- INDEX

    -- SCHEDULER_CONFIGURATION
    CREATE INDEX idx_sc_app_name ON SCHEDULER_CONFIGURATION (application_name);
    -- SCHEDULER_EXECUTION
    CREATE INDEX idx_se_next_fire_time ON SCHEDULER_EXECUTION (next_fire_time);
    CREATE INDEX idx_se_state ON SCHEDULER_EXECUTION (state);
    CREATE INDEX idx_se_state_next_fire_time ON SCHEDULER_EXECUTION (state, next_fire_time);
    CREATE INDEX idx_se_sc_id ON SCHEDULER_EXECUTION (scheduler_configuration_id);
    CREATE INDEX idx_se_sc_id_state ON SCHEDULER_EXECUTION (scheduler_configuration_id, state);
    ```
- client
  - spring batch 수행관련 내용들을 메타데이터로 남겨서 테이블에 저장함
  - https://docs.spring.io/spring-batch/docs/current/reference/html/schema-appendix.html
    ![client-schema](https://docs.spring.io/spring-batch/docs/current/reference/html/images/meta-data-erd.png)
  - 추가로 lightmin 클라이언트에서 사용하는 테이블 생성
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
