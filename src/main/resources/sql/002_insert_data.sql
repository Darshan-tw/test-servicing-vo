insert into vo_plan (plan_number, below_above_nml_questionnaire, benefit_code, cover_basis, created_at, created_by,
                     insurance_class, loan_type, max_age, max_sum_assured, max_term, min_age, min_sum_assured, min_term,
                     nml_questionnaire, plan_effective_date, plan_end_date, plan_name, plan_start_date, policy_number,
                     product_code, refund_on_surrender_flag, updated_at, updated_by)
values (
           'PLAN001', 'No', 'BEN001', 'Basis A', current_timestamp,
           'SYSTEM_USER', 'Class A', 'Home Loan', 85, 1250000,
           20, 18, 750000, 5, '2023-01-01',
           '2023-01-01', '2033-01-01', 'PLAN A', '2023-01-01',
           'POL001', 'PROD001', 'Yes', CURRENT_TIMESTAMP, 'SYSTEM_USER'
       );

insert into vo_member_details (member_number, address_line1, address_line2, address_line3, age, c_percentage, city,
                               claimant_id, client_number, coi_number, cover_type_flag, created_at, dependent_number,
                               dob, effective_date, email, employee_no, excess_si, first_name, funded_flag, gender,
                               informed_premium, interest_paid_during_moratorium, interest_rate, lan, last_name,
                               loan_amount, loan_disbursement_date, loan_term, marital_status, member_category,
                               member_status, member_type, moratorium_flag, mph_id, nationality, occupation_class,
                               occupation_code, partner, percentage_allocation, phone_number, pincode, policy_number,
                               premium_end_date, premium_frequency, premium_payable, premium_start_date, premium_term,
                               premium_type, relationship_to_customer, relationship_to_nominee, risk_end_date,
                               risk_start_date, risk_term, s_percentage, start_date, state, sum_assured,
                               sum_insured_type, title, top_up_premium, transaction_date, undecision_desc,
                               undecision_flag, updated_at, plan_number)
values ('MEM001', '123 Main St', 'Anytown, ST 12345', 'Bengaluru', 33, 50, 'City A', '123e4567-e89b-12d3-a456-426614174000',
        'CLIENT001', 'COI001', 'Y', current_timestamp, 2, '1990-01-01', '2023-01-01', 'name@email.com', 'EMP001',
        500, 'John', 1, 'Male', 10000, 2000, 5, 'LAN001', 'Doe', 300000, '2023-01-01', 15, 'Single', 'Category A',
        'Active', 'Type A', TRUE, 'MPH001', 'Country A', 'Class A', 123, 'Partner A', 100, '1234567890', '123456',
        'POL001', '2033-01-01', 'Monthly', 15000, '2023-01-01', 20, 'Type A', 'Self', 'Spouse', '2033-01-01',
        '2023-01-01', 10, 50, '2023-01-01', 'State A', 200000, 'Type A', 'Mr', 5000, '2023-01-01', 'None', 0,
        current_timestamp, 'PLAN001');

