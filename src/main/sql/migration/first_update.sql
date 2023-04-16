create table first_update
(
    barcode bigint   not null
        primary key,
    time    datetime null
);

INSERT IGNORE INTO first_update (barcode, time)
SELECT barcode, DATE('1970-01-01') FROM sale;

INSERT IGNORE INTO umag_hacknu.first_update (barcode, time)
SELECT barcode, DATE('1970-01-01') FROM supply;