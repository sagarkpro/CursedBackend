DROP TABLE cursed.users;

DROP TYPE cursed.user_role;

CREATE TYPE cursed.user_role AS ENUM ('ROLE_ADMIN', 'ROLE_USER');

CREATE TABLE cursed.users (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  email VARCHAR(100) NOT NULL UNIQUE,
  first_name VARCHAR(100) NOT NULL,
  middle_name VARCHAR(100),
  last_name VARCHAR(100),
  password VARCHAR(100) NOT NULL,
  role cursed.user_role NOT NULL
);
