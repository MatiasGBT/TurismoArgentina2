--
-- PostgreSQL database dump
--

-- Dumped from database version 15.2
-- Dumped by pg_dump version 15.2

-- Started on 2023-03-10 12:55:44

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 5 (class 2615 OID 24577)
-- Name: turismo_argentina; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA turismo_argentina;


ALTER SCHEMA turismo_argentina OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 214 (class 1259 OID 24578)
-- Name: activities; Type: TABLE; Schema: turismo_argentina; Owner: postgres
--

CREATE TABLE turismo_argentina.activities (
    id_activity bigint NOT NULL,
    name character varying(65)[] NOT NULL,
    description character varying(600)[] NOT NULL,
    image1 character varying(80)[] NOT NULL,
    image2 character varying(80)[],
    image3 character varying(80)[],
    price double precision NOT NULL,
    duration double precision,
    deletion_date date,
    id_location bigint NOT NULL
);


ALTER TABLE turismo_argentina.activities OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 24618)
-- Name: activities_id_activity_seq; Type: SEQUENCE; Schema: turismo_argentina; Owner: postgres
--

ALTER TABLE turismo_argentina.activities ALTER COLUMN id_activity ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME turismo_argentina.activities_id_activity_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 222 (class 1259 OID 24620)
-- Name: coupons; Type: TABLE; Schema: turismo_argentina; Owner: postgres
--

CREATE TABLE turismo_argentina.coupons (
    id_coupon bigint NOT NULL,
    name character varying(30)[] NOT NULL,
    discount smallint NOT NULL,
    start_date date NOT NULL,
    finish_date date NOT NULL
);


ALTER TABLE turismo_argentina.coupons OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 24627)
-- Name: coupons_id_coupon_seq; Type: SEQUENCE; Schema: turismo_argentina; Owner: postgres
--

ALTER TABLE turismo_argentina.coupons ALTER COLUMN id_coupon ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME turismo_argentina.coupons_id_coupon_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 216 (class 1259 OID 24592)
-- Name: locations; Type: TABLE; Schema: turismo_argentina; Owner: postgres
--

CREATE TABLE turismo_argentina.locations (
    id_location bigint NOT NULL,
    name character varying(45)[] NOT NULL,
    description character varying(600)[] NOT NULL,
    image character varying(80)[] NOT NULL,
    price double precision NOT NULL,
    deletion_date date,
    id_province bigint NOT NULL
);


ALTER TABLE turismo_argentina.locations OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 24617)
-- Name: locations_id_location_seq; Type: SEQUENCE; Schema: turismo_argentina; Owner: postgres
--

ALTER TABLE turismo_argentina.locations ALTER COLUMN id_location ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME turismo_argentina.locations_id_location_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 215 (class 1259 OID 24585)
-- Name: provinces; Type: TABLE; Schema: turismo_argentina; Owner: postgres
--

CREATE TABLE turismo_argentina.provinces (
    id_province bigint NOT NULL,
    name character varying(20)[] NOT NULL,
    description character varying(600)[] NOT NULL,
    image character varying(80)[] NOT NULL,
    deletion_date date
);


ALTER TABLE turismo_argentina.provinces OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 24616)
-- Name: provinces_id_province_seq; Type: SEQUENCE; Schema: turismo_argentina; Owner: postgres
--

ALTER TABLE turismo_argentina.provinces ALTER COLUMN id_province ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME turismo_argentina.provinces_id_province_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 230 (class 1259 OID 24672)
-- Name: puchases_locations; Type: TABLE; Schema: turismo_argentina; Owner: postgres
--

CREATE TABLE turismo_argentina.puchases_locations (
    id_puchase_location bigint NOT NULL,
    id_purchase bigint NOT NULL,
    id_location bigint NOT NULL
);


ALTER TABLE turismo_argentina.puchases_locations OWNER TO postgres;

--
-- TOC entry 231 (class 1259 OID 24687)
-- Name: puchases_locations_id_puchase_location_seq; Type: SEQUENCE; Schema: turismo_argentina; Owner: postgres
--

ALTER TABLE turismo_argentina.puchases_locations ALTER COLUMN id_puchase_location ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME turismo_argentina.puchases_locations_id_puchase_location_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 226 (class 1259 OID 24645)
-- Name: purchases; Type: TABLE; Schema: turismo_argentina; Owner: postgres
--

CREATE TABLE turismo_argentina.purchases (
    id_purchase bigint NOT NULL,
    date date NOT NULL,
    id_user bigint NOT NULL
);


ALTER TABLE turismo_argentina.purchases OWNER TO postgres;

--
-- TOC entry 228 (class 1259 OID 24656)
-- Name: purchases_activities; Type: TABLE; Schema: turismo_argentina; Owner: postgres
--

CREATE TABLE turismo_argentina.purchases_activities (
    id_purchase_activity bigint NOT NULL,
    id_purchase bigint NOT NULL,
    id_activity bigint NOT NULL
);


ALTER TABLE turismo_argentina.purchases_activities OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 24671)
-- Name: purchases_activities_id_purchase_activity_seq; Type: SEQUENCE; Schema: turismo_argentina; Owner: postgres
--

ALTER TABLE turismo_argentina.purchases_activities ALTER COLUMN id_purchase_activity ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME turismo_argentina.purchases_activities_id_purchase_activity_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 227 (class 1259 OID 24655)
-- Name: purchases_id_purchase_seq; Type: SEQUENCE; Schema: turismo_argentina; Owner: postgres
--

ALTER TABLE turismo_argentina.purchases ALTER COLUMN id_purchase ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME turismo_argentina.purchases_id_purchase_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 224 (class 1259 OID 24628)
-- Name: redeemed_coupons; Type: TABLE; Schema: turismo_argentina; Owner: postgres
--

CREATE TABLE turismo_argentina.redeemed_coupons (
    id_redeemed_coupon bigint NOT NULL,
    id_user bigint NOT NULL,
    id_coupon bigint NOT NULL,
    date timestamp without time zone NOT NULL,
    used boolean DEFAULT false NOT NULL
);


ALTER TABLE turismo_argentina.redeemed_coupons OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 24644)
-- Name: redeemed_coupons_id_redeemed_coupon_seq; Type: SEQUENCE; Schema: turismo_argentina; Owner: postgres
--

ALTER TABLE turismo_argentina.redeemed_coupons ALTER COLUMN id_redeemed_coupon ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME turismo_argentina.redeemed_coupons_id_redeemed_coupon_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 217 (class 1259 OID 24609)
-- Name: users; Type: TABLE; Schema: turismo_argentina; Owner: postgres
--

CREATE TABLE turismo_argentina.users (
    id_user bigint NOT NULL,
    username character varying(45) NOT NULL,
    name character varying(60)[] NOT NULL,
    last_name character varying(60)[] NOT NULL,
    creation_date date NOT NULL,
    deletion_date date
);


ALTER TABLE turismo_argentina.users OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 24619)
-- Name: users_id_user_seq; Type: SEQUENCE; Schema: turismo_argentina; Owner: postgres
--

ALTER TABLE turismo_argentina.users ALTER COLUMN id_user ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME turismo_argentina.users_id_user_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 3383 (class 0 OID 24578)
-- Dependencies: 214
-- Data for Name: activities; Type: TABLE DATA; Schema: turismo_argentina; Owner: postgres
--

COPY turismo_argentina.activities (id_activity, name, description, image1, image2, image3, price, duration, deletion_date, id_location) FROM stdin;
\.


--
-- TOC entry 3391 (class 0 OID 24620)
-- Dependencies: 222
-- Data for Name: coupons; Type: TABLE DATA; Schema: turismo_argentina; Owner: postgres
--

COPY turismo_argentina.coupons (id_coupon, name, discount, start_date, finish_date) FROM stdin;
\.


--
-- TOC entry 3385 (class 0 OID 24592)
-- Dependencies: 216
-- Data for Name: locations; Type: TABLE DATA; Schema: turismo_argentina; Owner: postgres
--

COPY turismo_argentina.locations (id_location, name, description, image, price, deletion_date, id_province) FROM stdin;
\.


--
-- TOC entry 3384 (class 0 OID 24585)
-- Dependencies: 215
-- Data for Name: provinces; Type: TABLE DATA; Schema: turismo_argentina; Owner: postgres
--

COPY turismo_argentina.provinces (id_province, name, description, image, deletion_date) FROM stdin;
\.


--
-- TOC entry 3399 (class 0 OID 24672)
-- Dependencies: 230
-- Data for Name: puchases_locations; Type: TABLE DATA; Schema: turismo_argentina; Owner: postgres
--

COPY turismo_argentina.puchases_locations (id_puchase_location, id_purchase, id_location) FROM stdin;
\.


--
-- TOC entry 3395 (class 0 OID 24645)
-- Dependencies: 226
-- Data for Name: purchases; Type: TABLE DATA; Schema: turismo_argentina; Owner: postgres
--

COPY turismo_argentina.purchases (id_purchase, date, id_user) FROM stdin;
\.


--
-- TOC entry 3397 (class 0 OID 24656)
-- Dependencies: 228
-- Data for Name: purchases_activities; Type: TABLE DATA; Schema: turismo_argentina; Owner: postgres
--

COPY turismo_argentina.purchases_activities (id_purchase_activity, id_purchase, id_activity) FROM stdin;
\.


--
-- TOC entry 3393 (class 0 OID 24628)
-- Dependencies: 224
-- Data for Name: redeemed_coupons; Type: TABLE DATA; Schema: turismo_argentina; Owner: postgres
--

COPY turismo_argentina.redeemed_coupons (id_redeemed_coupon, id_user, id_coupon, date, used) FROM stdin;
\.


--
-- TOC entry 3386 (class 0 OID 24609)
-- Dependencies: 217
-- Data for Name: users; Type: TABLE DATA; Schema: turismo_argentina; Owner: postgres
--

COPY turismo_argentina.users (id_user, username, name, last_name, creation_date, deletion_date) FROM stdin;
\.


--
-- TOC entry 3406 (class 0 OID 0)
-- Dependencies: 220
-- Name: activities_id_activity_seq; Type: SEQUENCE SET; Schema: turismo_argentina; Owner: postgres
--

SELECT pg_catalog.setval('turismo_argentina.activities_id_activity_seq', 1, false);


--
-- TOC entry 3407 (class 0 OID 0)
-- Dependencies: 223
-- Name: coupons_id_coupon_seq; Type: SEQUENCE SET; Schema: turismo_argentina; Owner: postgres
--

SELECT pg_catalog.setval('turismo_argentina.coupons_id_coupon_seq', 1, false);


--
-- TOC entry 3408 (class 0 OID 0)
-- Dependencies: 219
-- Name: locations_id_location_seq; Type: SEQUENCE SET; Schema: turismo_argentina; Owner: postgres
--

SELECT pg_catalog.setval('turismo_argentina.locations_id_location_seq', 1, false);


--
-- TOC entry 3409 (class 0 OID 0)
-- Dependencies: 218
-- Name: provinces_id_province_seq; Type: SEQUENCE SET; Schema: turismo_argentina; Owner: postgres
--

SELECT pg_catalog.setval('turismo_argentina.provinces_id_province_seq', 1, false);


--
-- TOC entry 3410 (class 0 OID 0)
-- Dependencies: 231
-- Name: puchases_locations_id_puchase_location_seq; Type: SEQUENCE SET; Schema: turismo_argentina; Owner: postgres
--

SELECT pg_catalog.setval('turismo_argentina.puchases_locations_id_puchase_location_seq', 1, false);


--
-- TOC entry 3411 (class 0 OID 0)
-- Dependencies: 229
-- Name: purchases_activities_id_purchase_activity_seq; Type: SEQUENCE SET; Schema: turismo_argentina; Owner: postgres
--

SELECT pg_catalog.setval('turismo_argentina.purchases_activities_id_purchase_activity_seq', 1, false);


--
-- TOC entry 3412 (class 0 OID 0)
-- Dependencies: 227
-- Name: purchases_id_purchase_seq; Type: SEQUENCE SET; Schema: turismo_argentina; Owner: postgres
--

SELECT pg_catalog.setval('turismo_argentina.purchases_id_purchase_seq', 1, false);


--
-- TOC entry 3413 (class 0 OID 0)
-- Dependencies: 225
-- Name: redeemed_coupons_id_redeemed_coupon_seq; Type: SEQUENCE SET; Schema: turismo_argentina; Owner: postgres
--

SELECT pg_catalog.setval('turismo_argentina.redeemed_coupons_id_redeemed_coupon_seq', 1, false);


--
-- TOC entry 3414 (class 0 OID 0)
-- Dependencies: 221
-- Name: users_id_user_seq; Type: SEQUENCE SET; Schema: turismo_argentina; Owner: postgres
--

SELECT pg_catalog.setval('turismo_argentina.users_id_user_seq', 1, false);


--
-- TOC entry 3215 (class 2606 OID 24584)
-- Name: activities activities_pkey; Type: CONSTRAINT; Schema: turismo_argentina; Owner: postgres
--

ALTER TABLE ONLY turismo_argentina.activities
    ADD CONSTRAINT activities_pkey PRIMARY KEY (id_activity);


--
-- TOC entry 3223 (class 2606 OID 24626)
-- Name: coupons coupons_pkey; Type: CONSTRAINT; Schema: turismo_argentina; Owner: postgres
--

ALTER TABLE ONLY turismo_argentina.coupons
    ADD CONSTRAINT coupons_pkey PRIMARY KEY (id_coupon);


--
-- TOC entry 3219 (class 2606 OID 24598)
-- Name: locations locations_pkey; Type: CONSTRAINT; Schema: turismo_argentina; Owner: postgres
--

ALTER TABLE ONLY turismo_argentina.locations
    ADD CONSTRAINT locations_pkey PRIMARY KEY (id_location);


--
-- TOC entry 3217 (class 2606 OID 24591)
-- Name: provinces provinces_pkey; Type: CONSTRAINT; Schema: turismo_argentina; Owner: postgres
--

ALTER TABLE ONLY turismo_argentina.provinces
    ADD CONSTRAINT provinces_pkey PRIMARY KEY (id_province);


--
-- TOC entry 3231 (class 2606 OID 24676)
-- Name: puchases_locations puchases_locations_pkey; Type: CONSTRAINT; Schema: turismo_argentina; Owner: postgres
--

ALTER TABLE ONLY turismo_argentina.puchases_locations
    ADD CONSTRAINT puchases_locations_pkey PRIMARY KEY (id_puchase_location);


--
-- TOC entry 3229 (class 2606 OID 24660)
-- Name: purchases_activities purchases_activities_pkey; Type: CONSTRAINT; Schema: turismo_argentina; Owner: postgres
--

ALTER TABLE ONLY turismo_argentina.purchases_activities
    ADD CONSTRAINT purchases_activities_pkey PRIMARY KEY (id_purchase_activity);


--
-- TOC entry 3227 (class 2606 OID 24649)
-- Name: purchases purchases_pkey; Type: CONSTRAINT; Schema: turismo_argentina; Owner: postgres
--

ALTER TABLE ONLY turismo_argentina.purchases
    ADD CONSTRAINT purchases_pkey PRIMARY KEY (id_purchase);


--
-- TOC entry 3225 (class 2606 OID 24633)
-- Name: redeemed_coupons redeemed_coupons_pkey; Type: CONSTRAINT; Schema: turismo_argentina; Owner: postgres
--

ALTER TABLE ONLY turismo_argentina.redeemed_coupons
    ADD CONSTRAINT redeemed_coupons_pkey PRIMARY KEY (id_redeemed_coupon);


--
-- TOC entry 3221 (class 2606 OID 24615)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: turismo_argentina; Owner: postgres
--

ALTER TABLE ONLY turismo_argentina.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id_user);


--
-- TOC entry 3232 (class 2606 OID 24604)
-- Name: activities fk_activity_location; Type: FK CONSTRAINT; Schema: turismo_argentina; Owner: postgres
--

ALTER TABLE ONLY turismo_argentina.activities
    ADD CONSTRAINT fk_activity_location FOREIGN KEY (id_location) REFERENCES turismo_argentina.locations(id_location) NOT VALID;


--
-- TOC entry 3233 (class 2606 OID 24599)
-- Name: locations fk_location_province; Type: FK CONSTRAINT; Schema: turismo_argentina; Owner: postgres
--

ALTER TABLE ONLY turismo_argentina.locations
    ADD CONSTRAINT fk_location_province FOREIGN KEY (id_province) REFERENCES turismo_argentina.provinces(id_province);


--
-- TOC entry 3237 (class 2606 OID 24666)
-- Name: purchases_activities fk_pa_activity; Type: FK CONSTRAINT; Schema: turismo_argentina; Owner: postgres
--

ALTER TABLE ONLY turismo_argentina.purchases_activities
    ADD CONSTRAINT fk_pa_activity FOREIGN KEY (id_activity) REFERENCES turismo_argentina.activities(id_activity);


--
-- TOC entry 3238 (class 2606 OID 24661)
-- Name: purchases_activities fk_pa_purchase; Type: FK CONSTRAINT; Schema: turismo_argentina; Owner: postgres
--

ALTER TABLE ONLY turismo_argentina.purchases_activities
    ADD CONSTRAINT fk_pa_purchase FOREIGN KEY (id_purchase) REFERENCES turismo_argentina.purchases(id_purchase);


--
-- TOC entry 3239 (class 2606 OID 24682)
-- Name: puchases_locations fk_pl_location; Type: FK CONSTRAINT; Schema: turismo_argentina; Owner: postgres
--

ALTER TABLE ONLY turismo_argentina.puchases_locations
    ADD CONSTRAINT fk_pl_location FOREIGN KEY (id_location) REFERENCES turismo_argentina.locations(id_location);


--
-- TOC entry 3240 (class 2606 OID 24677)
-- Name: puchases_locations fk_pl_purchase; Type: FK CONSTRAINT; Schema: turismo_argentina; Owner: postgres
--

ALTER TABLE ONLY turismo_argentina.puchases_locations
    ADD CONSTRAINT fk_pl_purchase FOREIGN KEY (id_purchase) REFERENCES turismo_argentina.purchases(id_purchase);


--
-- TOC entry 3236 (class 2606 OID 24650)
-- Name: purchases fk_purchase_user; Type: FK CONSTRAINT; Schema: turismo_argentina; Owner: postgres
--

ALTER TABLE ONLY turismo_argentina.purchases
    ADD CONSTRAINT fk_purchase_user FOREIGN KEY (id_user) REFERENCES turismo_argentina.users(id_user);


--
-- TOC entry 3234 (class 2606 OID 24639)
-- Name: redeemed_coupons fk_rc_coupon; Type: FK CONSTRAINT; Schema: turismo_argentina; Owner: postgres
--

ALTER TABLE ONLY turismo_argentina.redeemed_coupons
    ADD CONSTRAINT fk_rc_coupon FOREIGN KEY (id_coupon) REFERENCES turismo_argentina.coupons(id_coupon);


--
-- TOC entry 3235 (class 2606 OID 24634)
-- Name: redeemed_coupons fk_rc_user; Type: FK CONSTRAINT; Schema: turismo_argentina; Owner: postgres
--

ALTER TABLE ONLY turismo_argentina.redeemed_coupons
    ADD CONSTRAINT fk_rc_user FOREIGN KEY (id_user) REFERENCES turismo_argentina.users(id_user);


-- Completed on 2023-03-10 12:55:44

--
-- PostgreSQL database dump complete
--

