CREATE TABLE employees (
                           id SERIAL PRIMARY KEY,
                           full_name VARCHAR(255) NOT NULL,
                           email VARCHAR(255),
                           phone_work VARCHAR(20),
                           phone_mobile VARCHAR(20),
                           phone_country_code VARCHAR(5),
                           address VARCHAR(200),
                           employment_date DATE NOT NULL,
                           full_time_percentage INTEGER NOT NULL DEFAULT 100 CHECK (full_time_percentage BETWEEN 0 AND 100),
                           company_id BIGINT NOT NULL REFERENCES companies(id),
                           created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                           updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
