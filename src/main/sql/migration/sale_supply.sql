create table sale_supply
(
    sale_id bigint   not null
        primary key,
    barcode bigint,
    supply_id bigint,
    supply_seq integer,
    prefix_margin bigint,
    prefix_quantity integer,
    prefix_revenue bigint,
    sale_time    datetime null
);

create index sale_supply_barcode_time_idx
    on sale_supply (barcode, sale_time);
