CREATE TABLE IF NOT EXISTS system_info (
    id SERIAL PRIMARY KEY,
    version VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW()
);

INSERT INTO system_info (version) VALUES ('1.0.0');
