INSERT INTO `spengermed`.`p_patient` (`id`, `p_active`, `p_deceasedboolean`, `p_gender`) VALUES ('123', true, false, 'male');
INSERT INTO `spengermed`.`p_patient` (`id`, `p_active`, `p_deceasedboolean`, `p_gender`) VALUES ('abc', true, false, 'female');

#1:1 Beziehungen mit dem Patienten müssen vor dem Patienten eingefügt werden.
INSERT INTO `spengermed`.`p_patient` (`id`, `p_active`, `p_deceasedboolean`, `p_gender`) VALUES ('643221yu', false, true, 'other');
#1:n Beziehungen mit dem Patienten müssen nach dem Patienten eingefügt werden
INSERT INTO `spengermed`.`i_identifier` (`id`, `i_system`, `i_use`, `i_value`, `i_p_id`) VALUES ('123lasd', 'urn:oid:1.2.36.146.595.217.0.1', 'usual', '12345', '643221yu');
INSERT INTO `spengermed`.`hn_humanname` (`id`, `hn_family`, `hn_use`, `hn_pe_id`, `hn_p_id`) VALUES ('23923i', 'Pirker', 'official', NULL, '643221yu');
INSERT INTO `spengermed`.`hn_humanname` (`id`, `hn_family`, `hn_use`, `hn_pe_id`, `hn_p_id`) VALUES ('23i', 'Pirker', 'usual', NULL, '643221yu');
INSERT INTO `spengermed`.`given_humanname` (`id`, `given`) VALUES ('23i','Simon');
INSERT INTO `spengermed`.`given_humanname` (`id`, `given`) VALUES ('23i','Kein weiterer');
INSERT INTO `spengermed`.`prefix_humanname` (`id`, `prefix`) VALUES ('23i','Ing.');
INSERT INTO `spengermed`.`prefix_humanname` (`id`, `prefix`) VALUES ('23i','Dipl.Ing.');
INSERT INTO `spengermed`.`prefix_humanname` (`id`, `prefix`) VALUES ('23i','Mag.');
INSERT INTO `spengermed`.`prefix_humanname` (`id`, `prefix`) VALUES ('23i','Dr.phil.');
INSERT INTO `spengermed`.`prefix_humanname` (`id`, `prefix`) VALUES ('23i','Dr.');
INSERT INTO `spengermed`.`suffix_humanname` (`id`, `suffix`) VALUES ('23i','Bakk');
INSERT INTO `spengermed`.`suffix_humanname` (`id`, `suffix`) VALUES ('23i','MSc');
#Insert für telecom
INSERT INTO `spengermed`.`pe_period` (`id`, `pe_end`, `pe_start`) VALUES ('12sd', '2040-01-05', '1999-01-01');
INSERT INTO `spengermed`.`cp_contactpoint` (`id`, `cp_rank`, `cp_system`, `cp_use`, `cp_value`, `cp_p_id`, `cp_pe_id`) VALUES ('123AF', '1', 'phone', 'work', '015552231123', '643221yu','12sd');
INSERT INTO `spengermed`.`cp_contactpoint` (`id`, `cp_rank`, `cp_system`, `cp_use`, `cp_value`, `cp_p_id`) VALUES ('AFU43D', '2', 'email', 'work', 'pirker@spengergasse.at', '643221yu');
#Insert für address
INSERT INTO `spengermed`.`pe_period` (`id`, `pe_end`, `pe_start`) VALUES ('32234asdfa', '2040-01-05', '1999-01-01');
INSERT INTO `spengermed`.`a_address` (`id`, `a_city`, `a_country`, `a_district`,  `a_postalcode`, `a_state`, `a_type`, `a_use`, `a_p_id`, `a_pe_id`) VALUES ('23asdf', 'Wien', 'Österreich', 'Wien', '1050', 'Wien', 'both', 'home', '643221yu','32234asdfa');
INSERT INTO `spengermed`.`a_address_line` (`address_id`, `line`) VALUES ('23asdf','Simon Pirker');
INSERT INTO `spengermed`.`a_address_line` (`address_id`, `line`) VALUES ('23asdf','Spengergasse 20');
INSERT INTO `spengermed`.`a_address_line` (`address_id`, `line`) VALUES ('23asdf','1050 Wien');