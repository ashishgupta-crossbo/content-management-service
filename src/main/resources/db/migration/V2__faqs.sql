CREATE TABLE CMS.faqs (
                          faq_id SERIAL PRIMARY KEY,
                          headline VARCHAR(255),
                          description TEXT,
                          is_active BOOLEAN,
                          created_at VARCHAR(255),
                          updated_at VARCHAR(255)
);