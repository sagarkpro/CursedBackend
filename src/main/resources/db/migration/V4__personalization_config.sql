CREATE TYPE cursed.shortcut_type AS ENUM ('WEB', 'REPOSITORY');

CREATE TABLE cursed.personalization_configurations (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  user_email VARCHAR(100) NOT NULL,
  type cursed.shortcut_type NOT NULL,
  name VARCHAR(255) NOT NULL,
  url VARCHAR(2048) NOT NULL,
  image VARCHAR(2048) NULL
);

CREATE INDEX IF NOT EXISTS idx_personalization_configurations_user_email
ON cursed.personalization_configurations (user_email);

CREATE TABLE cursed.personalization_wallpapers (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  user_email VARCHAR(100) NOT NULL UNIQUE,
  wallpaper_urls JSONB NOT NULL
);