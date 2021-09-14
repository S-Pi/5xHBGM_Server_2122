INSERT INTO `spengerspital`.`patient` (`id`, `p_active`, `p_birthdate`, `p_gender`, `p_deceased_boolean`) VALUES ('gjuerighirgh', 1, '2000-01-01', 'unknown', 1);
INSERT INTO `spengerspital`.`patient` (`id`, `p_active`, `p_birthdate`, `p_gender`, `p_deceased_boolean`) VALUES ('7439re', 0, '2001-04-05', 'male', 0);
INSERT INTO `spengerspital`.`patient` (`id`, `p_active`, `p_birthdate`, `p_gender`, `p_deceased_boolean`) VALUES ('frejifgreu89', 1, '2010-01-21', 'female', 1);
INSERT INTO `spengerspital`.`patient` (`id`, `p_active`, `p_birthdate`, `p_gender`, `p_deceased_boolean`) VALUES ('asdf', 1, '2010-01-21', 'female', 1);


INSERT INTO `spengerspital`.`practitioner` (`id`, `active`, `birthdate`, `gender`) VALUES ('p1', 1, '1969-10-22', 'male');
INSERT INTO `spengerspital`.`practitioner` (`id`, `active`, `birthdate`, `gender`) VALUES ('p2', 1, '1969-10-22', 'male');
INSERT INTO `spengerspital`.`codeable_concept` (`id`, `cc_text`, `cc_practitioner_fk`) VALUES ('123', 'Text', 'p1');
INSERT INTO `spengerspital`.`qualification` (`id`, `pp_end`, `pp_start`, `q_codeableconcept_fk`, `q_practitioner_fk`) VALUES ('abcd', '2020-12-31', '2000-01-01', '123', 'p1');
INSERT INTO `spengerspital`.`identifier` (`id`, `i_code`, `pp_end`, `pp_start`, `i_system`, `i_value`, `i_codeableconcept_fk`, `i_qualification_fk`) VALUES ('dfssfa', 'usual', '2020-01-01', '2000-01-01', 'http://url.com', '421453254', '123', 'abcd');
INSERT INTO `spengerspital`.`identifier` (`id`, `i_code`, `pp_end`, `pp_start`, `i_system`, `i_value`, `i_codeableconcept_fk`, `i_practitioner_fk`) VALUES ('fgrsfs', 'usual', '2020-01-01', '2000-01-01', 'http://url.com', '2134567890', '123', 'p1');
INSERT INTO `spengerspital`.`identifier` (`id`, `i_code`, `pp_end`, `pp_start`, `i_system`, `i_value`, `i_codeableconcept_fk`) VALUES ('yxcv', 'usual', '2020-01-01', '2030-01-01', 'http://url.com', '135426865', '123');
INSERT INTO `spengerspital`.`coding` (`id`, `c_code`, `c_display`, `c_system`, `c_user_selected`, `c_version`, `c_codeableconcept_fk`) VALUES ('asdfg', 'example', 'displayed', 'Windows', True, '1.0.0', '123');
INSERT INTO `spengerspital`.`human_name` (`id`, `hn_family`, `hn_given`, `pp_end`, `pp_start`, `hn_text`, `hn_use`, `hn_patient_fk`, `hn_practitioner_fk`) VALUES ('n1', 'Mustermann', 'given', '2099-12-31', '2000-01-01', 'blabla', 'usual', '7439re', 'p1');
INSERT INTO `spengerspital`.`encounter` (`id`, `pp_end`, `pp_start`, `status`) VALUES ('enc1', '2020-04-15 19:17:45.000000', '2015-04-15 19:17:47.000000', 'arrived');
INSERT INTO `spengerspital`.`encounter` (`id`, `pp_end`, `pp_start`, `status`) VALUES ('gjuerighirgh', '2020-04-15 19:17:45.000000', '2015-04-15 19:17:47.000000', 'arrived');

INSERT INTO `spengerspital`.`codeable_concept` (`id`, `cc_text`, `cc_practitioner_fk`, `cc_encounter_fk`) VALUES ('123123', 'Text', 'p1', 'enc1');
INSERT INTO `spengerspital`.`identifier` (`id`, `i_code`, `pp_end`, `pp_start`, `i_system`, `i_value`, `i_encounter_fk`) VALUES ('nfuhsifgs', 'usual', '2025-04-15 19:42:04.000000', '2020-04-15 19:42:09.000000', 'http://url.com', '444544', 'enc1');
INSERT INTO `spengerspital`.`d_diagnosis` (`id`, `d_rank`, `diag_codeableconcept_fk`, `dia_encounter_fk`) VALUES ('dia1', '9', '123', 'enc1');
INSERT INTO `spengerspital`.`reference` (`id`, `display`, `reference`, `type`, `ref_encounter_reason_fk`, `enc_ep_of_ca_reference_fk`, `ref_encounter_appointment_fk`, `diag_reference_fk`) VALUES ('r1', 'alternativer Text', '/api/id_von_ref', 'SomeEntity', 'enc1', 'enc1', 'enc1', 'dia1');
INSERT INTO `spengerspital`.`status_history` (`id`, `pp_end`, `pp_start`, `status`, `sh_encounter_fk`) VALUES ('sh1', '2021-04-15 20:15:45.000000', '2020-04-15 20:15:49.000000', 'inprogress', 'enc1');
INSERT INTO `spengerspital`.`participant` (`id`, `pp_end`, `pp_start`, `part_reference_fk`, `part_encounter_fk`) VALUES ('participant1', '2025-04-27 16:34:47.000000', '2020-04-27 16:34:52.000000', 'r1', 'enc1');
INSERT INTO `spengerspital`.`a_address` (`id`, `a_city`, `a_country`, `a_district`, `pp_end`, `pp_start`, `a_postalcode`, `a_state`, `a_practitioner_fk`, `a_patient_fk`) VALUES ('addr1', 'Orasje', 'BiH', 'Orase', '2099-12-31 16:36:34.000000', '2020-04-27 16:36:49.000000', '1111', 'BiH', 'p1', '7439re');
INSERT INTO `spengerspital`.`attachment` (`id`, `at_language`, `at_size`, `at_title`, `at_url`, `at_practitioner_fk`, `at_patient_fk`) VALUES ('att1', 'German', '2', 'Dr.', 'httms://url.com', 'p1', 'gjuerighirgh');
INSERT INTO `spengerspital`.`contact_point` (`id`, `pp_end`, `pp_start`, `cp_rank`, `system_enum`, `use_enum`, `cp_value`, `cp_practitioner_fk`) VALUES ('cp3\r\n', '2030-04-27 16:40:19.000000', '2020-04-27 16:40:24.000000', '1', 'email', 'mobile', 'email@email.com', 'p1');
