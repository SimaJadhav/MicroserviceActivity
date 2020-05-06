DROP TABLE IF EXISTS CURRENCY_MANAGER;

CREATE TABLE CURRENCY_MANAGER(
  COUNTRY_CODE VARCHAR(250) NOT NULL PRIMARY KEY,
  CONVERSION_FACTOR DECIMAL(20,4) NOT NULL);

insert into currency_manager(country_code,conversion_factor)
values('EUR',95);
insert into currency_manager(country_code,conversion_factor)
values('GBP',93);