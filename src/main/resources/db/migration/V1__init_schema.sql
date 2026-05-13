CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(320) NOT NULL UNIQUE,
    password_hash VARCHAR(120) NOT NULL
);

CREATE TABLE IF NOT EXISTS processing_log (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    input_text VARCHAR(10000) NOT NULL,
    output_text VARCHAR(10000) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    CONSTRAINT fk_processing_log_user
        FOREIGN KEY (user_id)
        REFERENCES users (id)
        ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_processing_log_user_id
    ON processing_log (user_id);
