-- 1. Make password optional (Google users won't have passwords)
ALTER TABLE `User` MODIFY COLUMN password VARCHAR(255) NULL;

-- 2. Add email verification flag
ALTER TABLE `User` ADD COLUMN email_verified BOOLEAN NOT NULL DEFAULT FALSE;

-- 3. Create table to link Google accounts to your users
CREATE TABLE user_identities (
                                 user_id          BIGINT PRIMARY KEY,
                                 provider         VARCHAR(50)  NOT NULL,
                                 provider_user_id VARCHAR(255) NOT NULL,
                                 created_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                 CONSTRAINT fk_user_identities_user
                                     FOREIGN KEY (user_id) REFERENCES `User`(id) ON DELETE CASCADE,
                                 CONSTRAINT uq_provider_user UNIQUE (provider, provider_user_id)
);
