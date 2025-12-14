CREATE SCHEMA IF NOT EXISTS cursed;

CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TYPE user_role AS ENUM ('ADMIN', 'USER');

CREATE TABLE
  cursed.users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid (),
    email VARCHAR(100) NOT NULL UNIQUE,
    first_name VARCHAR(100) NOT NULL,
    middle_name VARCHAR(100),
    last_name VARCHAR(100),
    password VARCHAR(100) NOT NULL,
    role user_role NOT NULL
  );