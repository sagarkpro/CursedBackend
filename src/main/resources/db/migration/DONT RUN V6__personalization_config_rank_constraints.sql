ALTER TABLE cursed.personalization_configurations
  ALTER COLUMN rank SET NOT NULL;

ALTER TABLE cursed.personalization_configurations
  ADD CONSTRAINT uq_personalization_configurations_user_email_rank
  UNIQUE (user_email, rank);
