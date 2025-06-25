-- Tworzenie tabeli billing_account jeśli nie istnieje
CREATE TABLE IF NOT EXISTS billing_account
(
    account_id UUID PRIMARY KEY,
    patient_id UUID NOT NULL,
    status VARCHAR(50) NOT NULL
);

-- Wstawienie kont billingowych z patient_id z Twojego dokumentu
INSERT INTO billing_account (account_id, patient_id, status)
SELECT '3fa85f64-5717-4562-b3fc-2c963f66afa6', '123e4567-e89b-12d3-a456-426614174000', 'ACTIVE'
WHERE NOT EXISTS (
    SELECT 1 FROM billing_account WHERE account_id = '3fa85f64-5717-4562-b3fc-2c963f66afa6'
);

INSERT INTO billing_account (account_id, patient_id, status)
SELECT '6fa459ea-ee8a-3ca4-894e-db77e160355e', '123e4567-e89b-12d3-a456-426614174001', 'ACTIVE'
WHERE NOT EXISTS (
    SELECT 1 FROM billing_account WHERE account_id = '6fa459ea-ee8a-3ca4-894e-db77e160355e'
);

INSERT INTO billing_account (account_id, patient_id, status)
SELECT '7fa85f64-5717-4562-b3fc-2c963f66afb7', '123e4567-e89b-12d3-a456-426614174002', 'INACTIVE'
WHERE NOT EXISTS (
    SELECT 1 FROM billing_account WHERE account_id = '7fa85f64-5717-4562-b3fc-2c963f66afb7'
);

INSERT INTO billing_account (account_id, patient_id, status)
SELECT '9fa85f64-5717-4562-b3fc-2c963f66afc8', '123e4567-e89b-12d3-a456-426614174003', 'ACTIVE'
WHERE NOT EXISTS (
    SELECT 1 FROM billing_account WHERE account_id = '9fa85f64-5717-4562-b3fc-2c963f66afc8'
);

INSERT INTO billing_account (account_id, patient_id, status)
SELECT '1fa85f64-5717-4562-b3fc-2c963f66afd9', '123e4567-e89b-12d3-a456-426614174004', 'CLOSED'
WHERE NOT EXISTS (
    SELECT 1 FROM billing_account WHERE account_id = '1fa85f64-5717-4562-b3fc-2c963f66afd9'
);

INSERT INTO billing_account (account_id, patient_id, status)
SELECT '4fa85f64-5717-4562-b3fc-2c963f66afe0', '223e4567-e89b-12d3-a456-426614174005', 'ACTIVE'
WHERE NOT EXISTS (
    SELECT 1 FROM billing_account WHERE account_id = '4fa85f64-5717-4562-b3fc-2c963f66afe0'
);

INSERT INTO billing_account (account_id, patient_id, status)
SELECT '5fa85f64-5717-4562-b3fc-2c963f66afe1', '223e4567-e89b-12d3-a456-426614174006', 'ACTIVE'
WHERE NOT EXISTS (
    SELECT 1 FROM billing_account WHERE account_id = '5fa85f64-5717-4562-b3fc-2c963f66afe1'
);

INSERT INTO billing_account (account_id, patient_id, status)
SELECT '6fa85f64-5717-4562-b3fc-2c963f66afe2', '223e4567-e89b-12d3-a456-426614174007', 'INACTIVE'
WHERE NOT EXISTS (
    SELECT 1 FROM billing_account WHERE account_id = '6fa85f64-5717-4562-b3fc-2c963f66afe2'
);

INSERT INTO billing_account (account_id, patient_id, status)
SELECT '7fa85f64-5717-4562-b3fc-2c963f66afe3', '223e4567-e89b-12d3-a456-426614174008', 'ACTIVE'
WHERE NOT EXISTS (
    SELECT 1 FROM billing_account WHERE account_id = '7fa85f64-5717-4562-b3fc-2c963f66afe3'
);

INSERT INTO billing_account (account_id, patient_id, status)
SELECT '8fa85f64-5717-4562-b3fc-2c963f66afe4', '223e4567-e89b-12d3-a456-426614174009', 'CLOSED'
WHERE NOT EXISTS (
    SELECT 1 FROM billing_account WHERE account_id = '8fa85f64-5717-4562-b3fc-2c963f66afe4'
);
