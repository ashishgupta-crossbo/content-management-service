CREATE TABLE CMS.hotel (
                           id SERIAL PRIMARY KEY,
                           hotel_code VARCHAR(255) NOT NULL,
                           hotel_name VARCHAR(255) NOT NULL,
                           continent VARCHAR(255),
                           region VARCHAR(255),
                           is_active BOOLEAN NOT NULL,
                           created_at VARCHAR(255),
                           updated_at VARCHAR(255),
                           fact_sheet_url VARCHAR(255)
);