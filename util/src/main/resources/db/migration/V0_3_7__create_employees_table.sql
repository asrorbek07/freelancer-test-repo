CREATE TABLE employees (
    id SERIAL PRIMARY KEY,
    employee_number VARCHAR(10) UNIQUE,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    phone_work VARCHAR(20),
    phone_mobile VARCHAR(20),
    phone_home VARCHAR(20),
    phone_country_code VARCHAR(5),
    address_id BIGINT REFERENCES addresses(id),
    employment_date DATE NOT NULL,
    employee_category VARCHAR(50),
    full_time_percentage INTEGER NOT NULL DEFAULT 100,
    company_id BIGINT NOT NULL REFERENCES companies(id),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    
    CONSTRAINT full_time_percentage_range CHECK (full_time_percentage BETWEEN 0 AND 100)
);
