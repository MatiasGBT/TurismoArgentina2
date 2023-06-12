--
-- PostgreSQL database dump
--

-- Dumped from database version 15.2
-- Dumped by pg_dump version 15.2

-- Started on 2023-03-21 11:48:43

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
-- TOC entry 219 (class 1259 OID 24827)
-- Name: activities; Type: TABLE; Schema: turismo_argentina; Owner: postgres
--

CREATE TABLE turismo_argentina.activities (
    id_activity bigint NOT NULL,
    name character varying(65) NOT NULL,
    description character varying(600) NOT NULL,
    image1 character varying(80) NOT NULL,
    image2 character varying(80),
    image3 character varying(80),
    price double precision NOT NULL,
    duration double precision,
    deletion_date date,
    id_location bigint NOT NULL
);


ALTER TABLE turismo_argentina.activities OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 24826)
-- Name: activities_id_activity_seq; Type: SEQUENCE; Schema: turismo_argentina; Owner: postgres
--

ALTER TABLE turismo_argentina.activities ALTER COLUMN id_activity ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME turismo_argentina.activities_id_activity_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 42
);


--
-- TOC entry 223 (class 1259 OID 24846)
-- Name: coupons; Type: TABLE; Schema: turismo_argentina; Owner: postgres
--

CREATE TABLE turismo_argentina.coupons (
    id_coupon bigint NOT NULL,
    name character varying(30) NOT NULL,
    discount smallint NOT NULL,
    start_date date NOT NULL,
    finish_date date NOT NULL
);


ALTER TABLE turismo_argentina.coupons OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 24845)
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
-- TOC entry 217 (class 1259 OID 24814)
-- Name: locations; Type: TABLE; Schema: turismo_argentina; Owner: postgres
--

CREATE TABLE turismo_argentina.locations (
    id_location bigint NOT NULL,
    name character varying(45) NOT NULL,
    description character varying(600) NOT NULL,
    image character varying(80) NOT NULL,
    price double precision NOT NULL,
    deletion_date date,
    id_province bigint NOT NULL
);


ALTER TABLE turismo_argentina.locations OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 24813)
-- Name: locations_id_location_seq; Type: SEQUENCE; Schema: turismo_argentina; Owner: postgres
--

ALTER TABLE turismo_argentina.locations ALTER COLUMN id_location ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME turismo_argentina.locations_id_location_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 31
);


--
-- TOC entry 215 (class 1259 OID 24806)
-- Name: provinces; Type: TABLE; Schema: turismo_argentina; Owner: postgres
--

CREATE TABLE turismo_argentina.provinces (
    id_province bigint NOT NULL,
    name character varying(20) NOT NULL,
    description character varying(600) NOT NULL,
    image character varying(80) NOT NULL,
    deletion_date date
);


ALTER TABLE turismo_argentina.provinces OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 24805)
-- Name: provinces_id_province_seq; Type: SEQUENCE; Schema: turismo_argentina; Owner: postgres
--

ALTER TABLE turismo_argentina.provinces ALTER COLUMN id_province ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME turismo_argentina.provinces_id_province_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 11
);


--
-- TOC entry 229 (class 1259 OID 24881)
-- Name: purchases_locations; Type: TABLE; Schema: turismo_argentina; Owner: postgres
--

CREATE TABLE turismo_argentina.purchases_locations (
    id_puchase_location bigint NOT NULL,
    id_purchase bigint NOT NULL,
    id_location bigint NOT NULL
);


ALTER TABLE turismo_argentina.purchases_locations OWNER TO postgres;

--
-- TOC entry 228 (class 1259 OID 24880)
-- Name: purchases_locations_id_puchase_location_seq; Type: SEQUENCE; Schema: turismo_argentina; Owner: postgres
--

ALTER TABLE turismo_argentina.purchases_locations ALTER COLUMN id_puchase_location ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME turismo_argentina.purchases_locations_id_puchase_location_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 227 (class 1259 OID 24869)
-- Name: purchases; Type: TABLE; Schema: turismo_argentina; Owner: postgres
--

CREATE TABLE turismo_argentina.purchases (
    id_purchase bigint NOT NULL,
    date timestamp with time zone NOT NULL,
    id_user bigint NOT NULL,
    refunded boolean DEFAULT false NOT NULL,
    price double precision NOT NULL
);


ALTER TABLE turismo_argentina.purchases OWNER TO postgres;

--
-- TOC entry 231 (class 1259 OID 24897)
-- Name: purchases_activities; Type: TABLE; Schema: turismo_argentina; Owner: postgres
--

CREATE TABLE turismo_argentina.purchases_activities (
    id_purchase_activity bigint NOT NULL,
    id_purchase bigint NOT NULL,
    id_activity bigint NOT NULL
);


ALTER TABLE turismo_argentina.purchases_activities OWNER TO postgres;

--
-- TOC entry 230 (class 1259 OID 24896)
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
-- TOC entry 226 (class 1259 OID 24868)
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
-- TOC entry 225 (class 1259 OID 24852)
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
-- TOC entry 224 (class 1259 OID 24851)
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
-- TOC entry 221 (class 1259 OID 24840)
-- Name: users; Type: TABLE; Schema: turismo_argentina; Owner: postgres
--

CREATE TABLE turismo_argentina.users (
    id_user bigint NOT NULL,
    username character varying(45) NOT NULL,
    name character varying(60) NOT NULL,
    last_name character varying(60) NOT NULL,
    creation_date date NOT NULL,
    deletion_date date
);


ALTER TABLE turismo_argentina.users OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 24839)
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
-- TOC entry 3394 (class 0 OID 24827)
-- Dependencies: 219
-- Data for Name: activities; Type: TABLE DATA; Schema: turismo_argentina; Owner: postgres
--

INSERT INTO turismo_argentina.activities (id_activity, name, description, image1, image2, image3, price, duration, deletion_date, id_location) OVERRIDING SYSTEM VALUE VALUES (2, 'Entrada para el Museo de Arte Latinoamericano de Buenos Aires', 'Malba es un espacio cultural dinámico y participativo en el que se presentan exposiciones temporarias de diversa índole (en muchas ocasiones junto a otros museos alrededor del mundo, colecciones internacionales y fundaciones afines) y muestras de arte contemporáneo argentino y latinoamericano.', 'malba1.jpg', 'malba2.jpg', 'malba3.jpg', 700, NULL, NULL, 1);
INSERT INTO turismo_argentina.activities (id_activity, name, description, image1, image2, image3, price, duration, deletion_date, id_location) OVERRIDING SYSTEM VALUE VALUES (3, 'Recorrido privado por la ciudad de Buenos Aires en coche', 'Recorra Buenos Aires en auto privado en este tour personalizado de un día completo. Vea los puntos destacados, como Plaza de Mayo, con un guía profesional que señala importantes puntos de referencia en la capital cosmopolita. Disfrute de un itinerario personalizado mientras viaja a un ritmo relajado por barrios populares como La Boca, San Telmo, Palermo y Recoleta.', 'recorrido_privado_bsas1.jpg', 'recorrido_privado_bsas2.jpg', 'recorrido_privado_bsas3.jpg', 21027.98, 7, NULL, 1);
INSERT INTO turismo_argentina.activities (id_activity, name, description, image1, image2, image3, price, duration, deletion_date, id_location) OVERRIDING SYSTEM VALUE VALUES (4, 'Entrada para Aquasol', 'El Parque Acuático más grande de Latinoamérica. Te esperamos en Aquasol para disfrutar un día espectacular en familia o con amigos ¡Diversión asegurada para todas las edades!', 'aquasol1.jpg', 'aquasol2.jpg', 'aquasol3.jpg', 2200, NULL, NULL, 2);
INSERT INTO turismo_argentina.activities (id_activity, name, description, image1, image2, image3, price, duration, deletion_date, id_location) OVERRIDING SYSTEM VALUE VALUES (6, 'Sesión de fotos privada con un fotógrafo', '¿Quieres capturar tu increíble luna de miel? ¿Vacaciones familiares mientras los niños todavía son pequeños? ¿Tus parejas perfectas se retiran de la vida cotidiana ocupada? ¿Un viaje en solitario sin pedirle a extraños que te tomen fotos? ¡Deja de buscar, te tenemos aquí!', 'sesion_fotos_privada1.jpg', 'sesion_fotos_privada2.jpg', 'sesion_fotos_privada3.jpg', 29821.94, 0.5, NULL, 2);
INSERT INTO turismo_argentina.activities (id_activity, name, description, image1, image2, image3, price, duration, deletion_date, id_location) OVERRIDING SYSTEM VALUE VALUES (7, 'Cabalgatas Dos Montes', 'Dos Montes es una estancia rodeada de animales, naturaleza e historia, un paseo a caballo que brinda la posibilidad de conocer paisajes, atardeceres, y experiencias que serán difíciles de olvidar.', 'cabalgata_dos_montes1.jpg', 'cabalgata_dos_montes2.jpg', NULL, 11000, NULL, NULL, 3);
INSERT INTO turismo_argentina.activities (id_activity, name, description, image1, image2, image3, price, duration, deletion_date, id_location) OVERRIDING SYSTEM VALUE VALUES (8, 'Tour de San Isidro y Delta del Tigre', 'La histórica ciudad de San Isidro y los pintorescos humedales del Delta del Tigre se encuentran justo al norte de Buenos Aires. En esta visita, tome la ruta panorámica mientras navega por el río de la Plata y por el Delta del Tigre, explore la ciudad de Tigre y, a continuación, vuelva a la ciudad en microbús, haciendo una parada en San Isidro por el camino.', 'tour_tigre1.jpg', 'tour_tigre2.jpg', 'tour_tigre3.jpg', 11008.77, 5.5, NULL, 4);
INSERT INTO turismo_argentina.activities (id_activity, name, description, image1, image2, image3, price, duration, deletion_date, id_location) OVERRIDING SYSTEM VALUE VALUES (9, 'Excursión en lancha', 'Durante la experiencia de navegación, también aprenderá sobre la historia de Tigre, los piratas, los tesoros, la oligarquía argentina de 1900, la sostenibilidad e incluso conocerá algunos residentes locales (isleños) y la vida silvestre.', 'excursion_lancha1.jpg', 'excursion_lancha2.jpg', 'excursion_lancha3.jpg', 10513.99, 3, NULL, 4);
INSERT INTO turismo_argentina.activities (id_activity, name, description, image1, image2, image3, price, duration, deletion_date, id_location) OVERRIDING SYSTEM VALUE VALUES (10, 'Tour guiado en kayak', 'Tenemos la oportunidad de conocer uno de los deltas más grandes y hermosos del mundo, a sólo minutos de Buenos Aires. Luego podemos disfrutar de una buena comida en nuestra isla, en un entorno ideal para conectar con la naturaleza.', 'tigre_kayak1.jpg', 'tigre_kayak2.jpg', 'tigre_kayak3.jpg', 11132.46, 4, NULL, 4);
INSERT INTO turismo_argentina.activities (id_activity, name, description, image1, image2, image3, price, duration, deletion_date, id_location) OVERRIDING SYSTEM VALUE VALUES (11, 'Excursión a los valles de Cafayate Calchaquí con vino', 'El noroeste de Argentina es famoso por sus extraordinarios paisajes y el exclusivo vino Torrontes y esta excursión de día completo desde Salta combina ambos en una excursión. Viaje a través de los espectaculares valles de Calchaquí, maravíllese ante coloridas formaciones rocosas como la Garganta del Diablo, el Anfiteatro y los Castillos en la reserva Quebrada de las Conchas para, a continuación, disfrutar de una cata de vinos en algunas de las bodegas más famosas de Cafayate.', 'excursion_cafayate1.jpg', 'excursion_cafayate2.jpg', NULL, 3785.04, 11, NULL, 5);
INSERT INTO turismo_argentina.activities (id_activity, name, description, image1, image2, image3, price, duration, deletion_date, id_location) OVERRIDING SYSTEM VALUE VALUES (12, 'Recorrido en bicicleta en la Quebrada de las Conchas', 'La reserva de la Quebrada de las Conchas es hogar de algunos de la mayoría de los extraordinarios paisajes del norte de Argentina y ofrece un telón de fondo para una aventura en bicicleta. Este recorrido en bicicleta de 5 horas desde Cafayate le llevará a todos los miradores más memorables, como la Garganta del Diablo, el anfiteatro, Tres Cruces, La Punilla, y La Yesera.', 'recorrido_bicicleta_quebrada1.jpg', 'recorrido_bicicleta_quebrada2.jpg', 'recorrido_bicicleta_quebrada3.jpg', 30305.03, 5, NULL, 5);
INSERT INTO turismo_argentina.activities (id_activity, name, description, image1, image2, image3, price, duration, deletion_date, id_location) OVERRIDING SYSTEM VALUE VALUES (13, 'Lujoso recorrido vinícola', 'Descubra el viñedo más alto del mundo en este viaje de noche a Colomé. Recorra los paisajes cambiantes y las carreteras de montaña con viento hasta llegar a los Valles Calchaquíes, donde se encuentra Estancia Colomé. Conozca y pruebe sus exclusivos vinos. Pase la noche en el hotel de lujo justo al lado, y explore las ciudades cercanas de Molinos y Cachi.', 'recorrido_vinicola1.jpg', 'recorrido_vinicola2.jpg', 'recorrido_vinicola3.jpg', 75320.21, 48, NULL, 6);
INSERT INTO turismo_argentina.activities (id_activity, name, description, image1, image2, image3, price, duration, deletion_date, id_location) OVERRIDING SYSTEM VALUE VALUES (27, 'Aventura en bicicleta de montaña', 'Pedalea por la Patagonia en esta divertida aventura de medio día en bicicleta de montaña desde San Martín de Los Andes. Tome el Circuito Arrayanes (ruta Cerro Chapelco), con vistas al poderoso Monte Chapelco, el Volcán Lanín y el azul Lago Lácar.', 'aventura_bicicleta1.jpg', 'aventura_bicicleta2.jpg', 'aventura_bicicleta3.jpg', 9321.52, 5, NULL, 17);
INSERT INTO turismo_argentina.activities (id_activity, name, description, image1, image2, image3, price, duration, deletion_date, id_location) OVERRIDING SYSTEM VALUE VALUES (5, 'Paseo a caballo y polo', 'Disfruta de una experiencia prestigiosa y única en Polo Farm. Ven y experimenta el majestuoso juego de polo. Ofrecemos una combinación de energía y paz en un lugar que es famoso por su auténtico ambiente argentino con el lujo continental muy apreciado después de un duro día en la silla de montar. Ofrecemos unas vacaciones de polo únicas que satisfarán tanto su capacidad de conducción como su experiencia en polo.', 'paseo_caballo_polo1.jpg', 'paseo_caballo_polo2.jpg', 'paseo_caballo_polo3.jpg', 28936.39, 48, NULL, 2);
INSERT INTO turismo_argentina.activities (id_activity, name, description, image1, image2, image3, price, duration, deletion_date, id_location) OVERRIDING SYSTEM VALUE VALUES (14, 'Excursión a las cuevas de Acsibi', 'Descubra una de las maravillas naturales más sorprendentes del noroeste de Argentina en esta excursión de día completo a las cuevas de Acsibi desde Cachi, con almuerzo incluido. Maravíllese ante el cañón de roca rojiza plagado de cuevas y descubra cómo se ganó su nombre de \"lugar de fuego\" mientras el sol del mediodía se filtra por la roca, tiñendo las cuevas de vibrantes tonos rojos.', 'excursion_cuevas1.jpg', 'excursion_cuevas2.jpg', 'excursion_cuevas3.jpg', 11750.93, 10, NULL, 7);
INSERT INTO turismo_argentina.activities (id_activity, name, description, image1, image2, image3, price, duration, deletion_date, id_location) OVERRIDING SYSTEM VALUE VALUES (16, 'Excursión a caballo en Bariloche con asado', 'Pase tiempo al aire libre y emprenda una aventura a caballo que comienza en las faldas de los Andes argentinos. No necesita preocuparse si es un principiante a caballo: este recorrido flexible es adecuado tanto para principiantes como para jinetes experimentados. Disfrute cabalgando a través de bosques y lagos del pasado, descubra la vida silvestre local y finalice el recorrido con un delicioso almuerzo de asado y barra libre.', 'excursion_caballo_bariloche1.jpg', 'excursion_caballo_bariloche2.jpg', 'excursion_caballo_bariloche3.jpg', 17057.67, 6, NULL, 8);
INSERT INTO turismo_argentina.activities (id_activity, name, description, image1, image2, image3, price, duration, deletion_date, id_location) OVERRIDING SYSTEM VALUE VALUES (17, 'Recorrido en barco', 'Navega por algunos de los paisajes más hermosos de Argentina en este viaje en barco con salida desde Bariloche. Comenzando en la península de Llao Llao, verá la isla Centinela y se detendrá en la Cascada de los Cántaros, donde desembarcará para caminar por la selva valdiviana hasta la desembocadura de la cascada. En Puerto Blest, tendrá la opción de disfrutar del almuerzo y visitar la Bahía Blest y el lago Frías, alimentado por glaciares.', 'recorrido_barco_bariloche1.jpg', 'recorrido_barco_bariloche2.jpg', 'recorrido_barco_bariloche3.jpg', 14862.91, 8, NULL, 8);
INSERT INTO turismo_argentina.activities (id_activity, name, description, image1, image2, image3, price, duration, deletion_date, id_location) OVERRIDING SYSTEM VALUE VALUES (18, 'Tour por El Bolsón', 'La posibilidad de visitar los Lagos Gutiérrez, Mascardi y Guillelmo y cruza los ríos Villegas, Foyel y Quemquemtreu para ingresar a la ciudad. Allí es ineludible la visita a la famosa Feria Artesanal que reúne una cantidad enorme de productos locales y a las chacras, que producen frutas finas y dulces.', 'tour_el_bolson1.jpg', 'tour_el_bolson2.jpg', 'tour_el_bolson3.jpg', 14482.67, 10, NULL, 10);
INSERT INTO turismo_argentina.activities (id_activity, name, description, image1, image2, image3, price, duration, deletion_date, id_location) OVERRIDING SYSTEM VALUE VALUES (19, 'Parapente Vuelo Biplaza con Instructor', 'Somos una empresa dedicada a las actividades y turismo de vuelo libre. Haciendo lo posible para concretar tu sueño de volar. Debido al clima seco, volamos casi la totalidad del año. Es sencillo, predecir las condiciones para hacer un vuelo seguro y placentero.', 'parapente_vuelo1.jpg', 'parapente_vuelo2.jpg', 'parapente_vuelo3.jpg', 11023.33, 3, NULL, 11);
INSERT INTO turismo_argentina.activities (id_activity, name, description, image1, image2, image3, price, duration, deletion_date, id_location) OVERRIDING SYSTEM VALUE VALUES (20, 'Día de spa en las aguas termales de Cacheuta', 'Pase un día relajante disfrutando del spa de las Termas de Cacheuta con esta excursión desde Mendoza. Tendrá acceso completo a las amplias instalaciones de spa, desde un circuito de hidroterapia hasta la única gruta con sauna del país. Después de su experiencia en el spa, podrá sentarse a disfrutar de un almuerzo bufé incluido con numerosas opciones saludables antes de regresar a Mendoza.', 'dia_spa1.jpg', 'dia_spa2.jpg', 'dia_spa3.jpg', 12789.54, 10, NULL, 11);
INSERT INTO turismo_argentina.activities (id_activity, name, description, image1, image2, image3, price, duration, deletion_date, id_location) OVERRIDING SYSTEM VALUE VALUES (21, 'Tour por el cañón del Atuel', 'Disfrute de la belleza de las pintorescas cascadas. Visitaremos la Villa Fundacional 25 de Mayo, considerada un verdadero museo habitado. Una pieza de historia del siglo XIX con granjas y calles flanqueadas por carolinos centenarios. Continuaremos nuestro viaje al cañón de Atuel, una imponente formación geológica donde la erosión del viento y el agua han transformado las rocas, dándoles formas y colores que nos impactarán a cada momento. Después de una sinuosa pendiente, descubriremos la inmensidad de la presa del Valle Grande, un lugar ideal para la aventura y las actividades acuáticas.', 'excursion_atuel1.jpg', 'excursion_atuel2.jpg', 'excursion_atuel3.jpg', 9006.04, 12, NULL, 12);
INSERT INTO turismo_argentina.activities (id_activity, name, description, image1, image2, image3, price, duration, deletion_date, id_location) OVERRIDING SYSTEM VALUE VALUES (22, 'City Tour Tucumán', 'Un recorrido por las raíces de la historia moderna Argentina. Le ayudaremos a comprender los fundamentos de aquellos decisivos momentos que se vivieron en esta provincia.', 'city_tour1.jpg', 'city_tour2.jpg', 'city_tour3.jpg', 6474.04, 4, NULL, 14);
INSERT INTO turismo_argentina.activities (id_activity, name, description, image1, image2, image3, price, duration, deletion_date, id_location) OVERRIDING SYSTEM VALUE VALUES (23, 'Noche de Casinos', 'Éste es el único tour de casinos en el norte de Argentina. Ofrecemos transporte privado y seguro. Cuidamos sus pertenencias dentro de los casinos para que puedan apostar tranquilos. ', 'noche_casinos1.jpg', 'noche_casinos2.jpg', 'noche_casinos3.jpg', 3096.44, 3, NULL, 14);
INSERT INTO turismo_argentina.activities (id_activity, name, description, image1, image2, image3, price, duration, deletion_date, id_location) OVERRIDING SYSTEM VALUE VALUES (24, 'Tradición Norteña', 'Recorrido por el norte argentino para descubrir su cultura y sus bellos paisajes. Desde las yungas a las altas montañas. Desde los ocres hasta el blanco más puro. Un viaje en contacto con la naturaleza y con las comunidades originarias.', 'tradicion_nortena1.jpg', 'tradicion_nortena2.jpg', 'tradicion_nortena3.jpg', 167207.79, 216, NULL, 14);
INSERT INTO turismo_argentina.activities (id_activity, name, description, image1, image2, image3, price, duration, deletion_date, id_location) OVERRIDING SYSTEM VALUE VALUES (25, 'Escapada al lago Huechulafquen y el volcán Lanín', 'Escape de las rutas turísticas y disfrute de la belleza natural del Distrito de los Lagos de Argentina en esta aventura de día completo desde San Martín de los Andes. Disfrute de las mejores vistas del lago glacial de Huechulafquen, camine por las playas de arena volcánica negra y haga fotos del volcán Lanín, cubierto de nieve. Por último, descubra la cultura única de los indígenas mapuche mientras se detiene en los pueblos locales y visita iglesias históricas.', 'escapada_lago_volcan1.jpg', 'escapada_lago_volcan2.jpg', NULL, 8799.94, 10, NULL, 17);
INSERT INTO turismo_argentina.activities (id_activity, name, description, image1, image2, image3, price, duration, deletion_date, id_location) OVERRIDING SYSTEM VALUE VALUES (26, 'Excursión de rafting en aguas bravas', 'Enfréntate a los rápidos espeluznantes de Clase II y III del Río Chimehuín en esta divertida excursión de rafting en aguas bravas desde San Martín de los Andes. Esté atento a los jabalíes, los buitres y los martines pescadores mientras se precipita a través de las olas, se tropieza con los escalones de piedra y baja la corriente, luego relájese en una playa desierta con algunos merecidos refrigerios.', 'aguas_bravas1.jpg', 'aguas_bravas2.jpg', 'aguas_bravas3.jpg', 12860.14, 5, NULL, 17);
INSERT INTO turismo_argentina.activities (id_activity, name, description, image1, image2, image3, price, duration, deletion_date, id_location) OVERRIDING SYSTEM VALUE VALUES (29, 'Pesca de truchas', 'Un servicio de pesca único en la zona. Además de pasión por la actividad y amor al lugar, el servicio esta armado pensado para que usted viva una experiencia que recuerde por mucho tiempo y quiera volver. Es una excursión fuera de lo convencional que ofrecen en la región. ', 'pesca_truchas1.jpg', 'pesca_truchas2.jpg', 'pesca_truchas3.jpg', 21055.8, 9, NULL, 18);
INSERT INTO turismo_argentina.activities (id_activity, name, description, image1, image2, image3, price, duration, deletion_date, id_location) OVERRIDING SYSTEM VALUE VALUES (30, 'City Tour', 'Dar una paseo por la ciudad de Neuquen, es una experiencia única en donde se podrá disfrutar de sus paisajes, conocer su historia y ver su exponencial crecimiento ', 'city_tour_neuquen1.jpg', 'city_tour_neuquen2.jpg', NULL, 8050.75, 3, NULL, 19);
INSERT INTO turismo_argentina.activities (id_activity, name, description, image1, image2, image3, price, duration, deletion_date, id_location) OVERRIDING SYSTEM VALUE VALUES (31, 'Snorkeling con leones marinos', 'Puerto Madryn es famoso por sus increíbles avistamientos de vida silvestre, y este recorrido le permite observar de cerca a los leones marinos en su entorno natural. Disfrute nadando y practicando esnórquel en una caleta poco profunda conocida por sus numerosos leones marinos salvajes, observe aves marinas alrededor de la Reserva Natural de Punta Loma y esté atento a las ballenas, delfines y pingüinos en el camino.', 'snorkeling1.jpg', 'snorkeling2.jpg', 'snorkeling3.jpg', 12704.08, 2.5, NULL, 20);
INSERT INTO turismo_argentina.activities (id_activity, name, description, image1, image2, image3, price, duration, deletion_date, id_location) OVERRIDING SYSTEM VALUE VALUES (32, 'Punta Tombo Tour', 'Vea pingüinos en su hábitat natural mientras se aventura a Punta Tombo en esta excursión de un día completo desde Puerto Madryn. Esta área protegida ve venir a los pingüinos de Magallanes a anidar y criar a sus crías entre los meses de septiembre y abril. Luego, elija una de las tres paradas opcionales en el camino de regreso: el Museo Paleontológico, un tour de observación de delfines o una visita a La Escondida para observar elefantes marinos.', 'punta_tombo1.jpg', 'punta_tombo2.jpg', 'punta_tombo3.jpg', 9528.37, 10, NULL, 20);
INSERT INTO turismo_argentina.activities (id_activity, name, description, image1, image2, image3, price, duration, deletion_date, id_location) OVERRIDING SYSTEM VALUE VALUES (33, 'Barco a motor bajo las cataratas', 'Obtenga un subidón de adrenalina mientras visita las Cataratas del Iguazú en esta excursión llena de aventura. Pasee en lancha de alta velocidad directo a la zona de chapuzones mientras se acerca a las cataratas tanto como sea posible. También dé un paseo movido en 4x4 por la selva y opte por una caminata autoguiada por el Parque Nacional Iguazú para ver las cataratas desde diferentes perspectivas.', 'barco_a_motor1.jpg', 'barco_a_motor2.jpg', 'barco_a_motor3.jpg', 10775.61, 12, NULL, 22);
INSERT INTO turismo_argentina.activities (id_activity, name, description, image1, image2, image3, price, duration, deletion_date, id_location) OVERRIDING SYSTEM VALUE VALUES (34, 'Paseo en barco, tren y camión de safari', 'Pase tiempo al aire libre y explore el lado argentino de las cataratas del Iguazú. Visite lugares a los que no podría llegar solo, como explorar la selva tropical subtropical en un camión y dar un paseo en barco de aventura para disfrutar de una vista de cerca de las cataratas. El viaje también incluye un emocionante paseo en tren ecológico.', 'barco_tren_camion1.jpg', 'barco_tren_camion2.jpg', 'barco_tren_camion3.jpg', 23161.38, 9, NULL, 22);
INSERT INTO turismo_argentina.activities (id_activity, name, description, image1, image2, image3, price, duration, deletion_date, id_location) OVERRIDING SYSTEM VALUE VALUES (35, 'Vuelo panorámico en helicóptero por las Cataratas del Iguazú', 'Realice un vuelo panorámico en helicóptero sobre las Cataratas del Iguazú en esta excursión que le dejará boquiabierto. Disfrute de unas vistas panorámicas incomparables y de oportunidades para hacer fotos mientras el helicóptero pasa por encima de las cataratas de 82 metros (270 pies). Sobrevuele la selva circundante para disfrutar de las abundantes plantas y animales de camino a las cataratas y desde ellas. El transporte de ida y vuelta al hotel y el traslado al helipuerto están incluidos.', 'vuelo_cataratas1.jpg', 'vuelo_cataratas2.jpg', 'vuelo_cataratas3.jpg', 16182, NULL, NULL, 22);
INSERT INTO turismo_argentina.activities (id_activity, name, description, image1, image2, image3, price, duration, deletion_date, id_location) OVERRIDING SYSTEM VALUE VALUES (36, 'Aventura Privada de Tirolina y Rappel', 'Conozca la selva misionera de primera mano, volando a traves de la copa de los árboles y descendiendo haciendo rappel por una cascada.', 'tirolina_rappel1.jpg', 'tirolina_rappel2.jpg', NULL, 3096.44, 2.5, NULL, 22);
INSERT INTO turismo_argentina.activities (id_activity, name, description, image1, image2, image3, price, duration, deletion_date, id_location) OVERRIDING SYSTEM VALUE VALUES (37, 'Recorrido por las Cataratas del Iguazú', 'Las cataratas del Iguazú, que son una de las nuevas Siete Maravillas de la Naturaleza del mundo, una visita y muchas más. Disfrute al máximo de esta maravilla declarada Patrimonio de la Humanidad por la UNESCO en una excursión desde Puerto Iguazú que le permitirá observarlas desde todos los ángulos con el mínimo esfuerzo. Camine por los senderos para disfrutar de vistas panorámicas, navegue bajo las aguas que rompen y opte por la opción superior que incluye paseo en todoterreno por la selva. Los cómodos traslados de ida y vuelta al hotel mejoran aún más la experiencia.', 'recorrido_cataratas1.jpg', 'recorrido_cataratas2.jpg', 'recorrido_cataratas3.jpg', 16101.49, 8, NULL, 22);
INSERT INTO turismo_argentina.activities (id_activity, name, description, image1, image2, image3, price, duration, deletion_date, id_location) OVERRIDING SYSTEM VALUE VALUES (38, 'Visita a las ruinas de San Ignacio', 'Explore las ruinas jesuitas de San Ignacio y las minas de Wanda en una excursión de un día desde Puerto Iguazú. Aprenda sobre la historia de los jesuitas y los pueblos nativos de la región y vea muchas piedras preciosas en las minas de Wanda.', 'ruinas_minas1.jpg', 'ruinas_minas2.jpg', 'ruinas_minas3.jpg', 3715.73, 11, NULL, 24);
INSERT INTO turismo_argentina.activities (id_activity, name, description, image1, image2, image3, price, duration, deletion_date, id_location) OVERRIDING SYSTEM VALUE VALUES (39, 'Degustacion exclusiva y privada de vinos Argentinos', 'Esta experiencia es divertida, en un ambiente muy confortable, podrán probar muy buenos vinos argentinos y a la vez reír, pasarla bien y relajarse.', 'degustacion_vinos1.jpg', 'degustacion_vinos2.jpg', NULL, 3720.2, 2.5, NULL, 26);
INSERT INTO turismo_argentina.activities (id_activity, name, description, image1, image2, image3, price, duration, deletion_date, id_location) OVERRIDING SYSTEM VALUE VALUES (40, 'Entrada para el Parque Nacional El Palmar', 'En la provincia de Entre Ríos, a la orilla del Río Uruguay, una región poblada de estuarios, bosques y humedales donde conviven más de 250 especies de aves. En el último bosque de palmeras Yatay del mundo, atardeceres inolvidables.', 'entrada_el_palmar1.jpg', 'entrada_el_palmar2.jpg', 'entrada_el_palmar3.jpg', 5417.77, NULL, NULL, 28);
INSERT INTO turismo_argentina.activities (id_activity, name, description, image1, image2, image3, price, duration, deletion_date, id_location) OVERRIDING SYSTEM VALUE VALUES (15, 'Aventura de remo de kayak', 'Esta escapada privada de un día le lleva a hacer piragüismo al otro lado del apacible lago en Bariloche, con traslado al hotel para llegar. Podrá familiarizarse con el esplendor panorámico de la Región de los Lagos de Argentina y la cordillera andina desde una perspectiva diferente. El itinerario se puede adaptar a su nivel de habilidad, con guías que le ofrecen consejos de remo y comentarios sobre la historia natural. No tendrá que preocuparse por el almuerzo: aperitivos, bebidas y un almuerzo campestre incluidos.', 'remo_kayak_bariloche1.jpg', 'remo_kayak_bariloche2.jpg', 'remo_kayak_bariloche3.jpg', 19817.22, 7, NULL, 8);
INSERT INTO turismo_argentina.activities (id_activity, name, description, image1, image2, image3, price, duration, deletion_date, id_location) OVERRIDING SYSTEM VALUE VALUES (1, 'Entrada para el Teatro Colón', 'Para los fanáticos culturales, ninguna visita a Buenos Aires estaría completa sin visitar el Teatro Colón. Ahorre tiempo de espera con una entrada reservada con antelación que incluye una visita guiada al magnífico edificio. Explorar con un guía significa que obtendrá información sobre la historia y la arquitectura del monumento.', 'teatro_colon1.jpg', 'teatro_colon2.jpg', 'teatro_colon3.jpg', 7204.61, 1, NULL, 1);
INSERT INTO turismo_argentina.activities (id_activity, name, description, image1, image2, image3, price, duration, deletion_date, id_location) OVERRIDING SYSTEM VALUE VALUES (28, 'Navegación a Hua Hum, Quila Quina y Cascada Chachin', 'La excursión a Hua Hum en bote, es un recorrido agradable que cruza las aguas cristalinas de los lagos patagónicos, y como muchas de origen glaciar. Lagos rodeados por la Cordillera de los Andes, cubiertos de vegetación, en su mayoría nativos de los bosques subantárticos. Tienen gran protagonismo en la fundación y colonización del territorio argentino, y en el progreso de la ciudad de San Martín de los Andes.', 'navegacion_triple1.jpg', 'navegacion_triple2.jpg', 'navegacion_triple3.jpg', 13197.03, 7, NULL, 17);
INSERT INTO turismo_argentina.activities (id_activity, name, description, image1, image2, image3, price, duration, deletion_date, id_location) OVERRIDING SYSTEM VALUE VALUES (41, 'Entrada para Termas de Federación', 'Parque Termal ubicado en pleno centro de la Ciudad de Federación en Entre Ríos - Primer Parque Termal de la Mesopotamia Argentina - Posee 13 Piscinas de diferentes temperaturas y prestaciones, entre 32 y 41 grados - Cubiertas - Al aire Libre con dos sectores bien diferenciados uno pasivo, otro recreativo.', 'entrada_termas1.jpg', 'entrada_termas2.jpg', 'entrada_termas3.jpg', 1200, NULL, NULL, 29);


--
-- TOC entry 3398 (class 0 OID 24846)
-- Dependencies: 223
-- Data for Name: coupons; Type: TABLE DATA; Schema: turismo_argentina; Owner: postgres
--



--
-- TOC entry 3392 (class 0 OID 24814)
-- Dependencies: 217
-- Data for Name: locations; Type: TABLE DATA; Schema: turismo_argentina; Owner: postgres
--

INSERT INTO turismo_argentina.locations (id_location, name, description, image, price, deletion_date, id_province) OVERRIDING SYSTEM VALUE VALUES (2, 'Mar del Plata', 'Mar del Plata es una ciudad balnearia argentina en la costa del Atlántico. Su larga franja de playas incluye la amplia Punta Mogotes y Playa Grande, con sus rompientes para el surf. Detrás de Playa Grande, las calles rodeadas de árboles del barrio Los Troncos tienen elegantes casas de comienzos del siglo XX, que ahora son museos.', 'mar_del_plata.jpg', 9183, NULL, 1);
INSERT INTO turismo_argentina.locations (id_location, name, description, image, price, deletion_date, id_province) OVERRIDING SYSTEM VALUE VALUES (3, 'Cariló', 'Cariló es un viaje a la imaginación. Al cruzar su entrada vas a sentir que llegaste a un mundo de fantasía. Sus casas y hoteles se ocultan detrás de los pinos que cubren toda la ciudad desde la entrada. Esto hace que parezca estar habitado por duendes, hadas y otros seres mágicos.', 'carilo.jpg', 9183, NULL, 1);
INSERT INTO turismo_argentina.locations (id_location, name, description, image, price, deletion_date, id_province) OVERRIDING SYSTEM VALUE VALUES (4, 'Tigre', 'A tan solo un día de viaje de Buenos Aires, el delta del Río Tigre les ofrece tanto a los porteños como a los turistas un espacio ideal para realizar una gran variedad de actividades que van desde montar a caballo y hacer senderismo hasta pescar y nadar.', 'tigre.jpg', 6829, NULL, 1);
INSERT INTO turismo_argentina.locations (id_location, name, description, image, price, deletion_date, id_province) OVERRIDING SYSTEM VALUE VALUES (6, 'Molinos', 'Molinos es una pequeña localidad que se encuentra ubicada en el extremo sudoeste de la provincia de Salta y al noroeste de la Argentina.', 'molinos.jpg', 8064, NULL, 2);
INSERT INTO turismo_argentina.locations (id_location, name, description, image, price, deletion_date, id_province) OVERRIDING SYSTEM VALUE VALUES (7, 'Cuevas de Acsibi', 'Las Cuevas de Acsibi son un accidente geográfico, que se ubican a 15 km de la localidad de Seclantás dentro de los Valles Calchaquíes, en la provincia de Salta. También llamado Valle de las Cuevas, Acsibi es el lugar donde se asentaban las tribus de los Sichas y Malcachiscos.', 'cuevas_de_acsibi.jpg', 8064, NULL, 2);
INSERT INTO turismo_argentina.locations (id_location, name, description, image, price, deletion_date, id_province) OVERRIDING SYSTEM VALUE VALUES (8, 'San Carlos de Bariloche', 'En la base de los Andes, San Carlos de Bariloche es un destino para esquiar mundialmente famoso, establecido en un paisaje que ofrece todas las maravillas naturales de Argentina. Los visitantes pueden experimentar la nieve, los lagos y las tranquilas playas, junto con vibrantes clubes nocturnos y cocina gourmet. Durante todo el año, la zona es sede de varios festivales de música, exhibiciones de arte, exposiciones y convenciones.', 'san_carlos_de_bariloche.jpg', 11767, NULL, 3);
INSERT INTO turismo_argentina.locations (id_location, name, description, image, price, deletion_date, id_province) OVERRIDING SYSTEM VALUE VALUES (9, 'Las Grutas', 'Las Grutas es un concurrido balneario argentino del Golfo de San Matías, en el norte de la Patagonia. Sus largas playas están resguardadas por acantilados llenos de cuevas. En las cercanías, la tranquila playa Piedras Coloradas, tiene formaciones rocosas rojizas del período precámbrico. La bahía de San Antonio, cerca de la ciudad pesquera de San Antonio Oeste, es un hábitat protegido de aves costeras migratorias. El área alberga leones marinos y loros barranqueros.', 'las_grutas.jpg', 19607, NULL, 3);
INSERT INTO turismo_argentina.locations (id_location, name, description, image, price, deletion_date, id_province) OVERRIDING SYSTEM VALUE VALUES (5, 'Cafayate', 'Cafayate es una localidad de la provincia de Salta, en el noroeste de Argentina. Se ubica en los valles Calchaquíes, un área conocida por sus formaciones rocosas rojizas. Hay varios viñedos en la zona y, en la localidad, el Museo de la Vid y el Vino explica el proceso de producción de este producto.', 'cafayate.jpg', 8064, NULL, 2);
INSERT INTO turismo_argentina.locations (id_location, name, description, image, price, deletion_date, id_province) OVERRIDING SYSTEM VALUE VALUES (14, 'San Miguel de Tucumán', 'San Miguel de Tucumán es la capital de la provincia de Tucumán, en el noroeste de Argentina. La Plaza Independencia, el núcleo ajetreado de la ciudad, alberga una catedral neoclásica y la gran Casa de Gobierno modernista. El Museo Casa Padilla, antiguo hogar de una prominente familia local, tiene arte y muebles del siglo XIX. El Museo Casa Histórica de la Independencia conmemora la emancipación argentina del régimen español en 1816.', 'san_miguel_de_tucuman.jpg', 8358, NULL, 5);
INSERT INTO turismo_argentina.locations (id_location, name, description, image, price, deletion_date, id_province) OVERRIDING SYSTEM VALUE VALUES (15, 'Tafí del Valle', 'Tafí del Valle es una ciudad de los Valles Calchaquíes, en el noroeste de Argentina. Está rodeada de altas montañas con senderos, como Matadero, Muñoz y Pabellón. En la ciudad, la capilla jesuita La Banda tiene un museo que conserva arte religioso y reliquias culturales. El río Tafí fluye hacia el sur, hacia el enorme embalse La Angostura. Cerca está la reserva arqueológica Los Menhires, donde hay estatuas de piedra antiguas con diseños tallados.', 'tafi_del_valle.jpg', 8358, NULL, 5);
INSERT INTO turismo_argentina.locations (id_location, name, description, image, price, deletion_date, id_province) OVERRIDING SYSTEM VALUE VALUES (16, 'Yerba Buena', 'Yerba Buena es una localidad y municipio de Tucumán, Argentina situado al oeste de la ciudad capital de la provincia en el departamento homónimo y del cual es cabecera. Forma parte del conglomerado urbano denominado Gran San Miguel de Tucumán.', 'yerba_buena.jpg', 8358, NULL, 5);
INSERT INTO turismo_argentina.locations (id_location, name, description, image, price, deletion_date, id_province) OVERRIDING SYSTEM VALUE VALUES (17, 'San Martín de los Andes', 'A orillas del soñado lago Lácar, San Martín de los Andes es un paraíso patagónico. Los observadores de aves acuden en manada y revolotean por la Feria de Aves de Sudamérica anual, mientras quienes buscan la aventura aman practicar kayak, esquí o ciclismo por el hermoso paisaje. Las tiendas de artes y artesanías, así como los productores de comidas artesanales, deleitarán a los visitantes que buscan muestras sensoriales de las culturas locales.', 'san_martin_de_los_andes.jpg', 22316, NULL, 6);
INSERT INTO turismo_argentina.locations (id_location, name, description, image, price, deletion_date, id_province) OVERRIDING SYSTEM VALUE VALUES (18, 'Villa La Angostura', 'Villa La Angostura, un pueblo tranquilo en el corazón de la Patagonia, es la escapada perfecta para los turistas que aman la sensación alpina que ofrecen los bosques neblinosos de las montañas. Prueba algunas cosas ricas para comer de elaboración local y artesanal mientras disfrutás del aire fresco y puro de los Andes. A los fanáticos del esquí y el snowboard les encantará Villa La Angostura en el invierno; es un verdadero país de hadas de nieve perfecta.', 'villa_la_angostura.jpg', 11767, NULL, 6);
INSERT INTO turismo_argentina.locations (id_location, name, description, image, price, deletion_date, id_province) OVERRIDING SYSTEM VALUE VALUES (19, 'Neuquén (Capital)', 'Neuquén es una ciudad y municipio ubicada en la Patagonia argentina. Administrativamente es la capital de la Provincia del Neuquén y cabecera del departamento Confluencia. Es la capital de provincia más joven del país​, y desde la década de 1990, la ciudad más poblada de la Patagonia argentina.', 'neuquen_capital.jpg', 9360, NULL, 6);
INSERT INTO turismo_argentina.locations (id_location, name, description, image, price, deletion_date, id_province) OVERRIDING SYSTEM VALUE VALUES (20, 'Puerto Madryn', 'El Puerto Madryn está protegido por el Golfo Nuevo, comprendido por Península Valdés y Punta Nifas, y es uno de los lugares más protegidos de la costa de Patagonia. Con cinco kilómetros de playas que bordean la ciudad, Puerto Mandryn es el punto de entrada a muchas otras atracciones en Argentina. La principal es la Reserva Natural de la Península Valdés, declarada Patrimonio Mundial por UNESCO desde 1999.', 'puerto_madryn.jpg', 13769, NULL, 7);
INSERT INTO turismo_argentina.locations (id_location, name, description, image, price, deletion_date, id_province) OVERRIDING SYSTEM VALUE VALUES (21, 'Esquel', 'Esquel es una ciudad argentina a los pies de la cordillera de los Andes. Es conocida por ser la parada más austral del tren a vapor de vía estrecha La Trochita, que alguna vez recorrió más de 400 km a través de la Patagonia. Al oeste de la ciudad, se encuentra el Parque Nacional Los Alerces, que alberga bosques, lagos, cascadas y pudúes poco comunes. Cerca está la montaña La Hoya, un hábitat de guanacos con vista a todo el valle.', 'esquel.jpg', 21678, NULL, 7);
INSERT INTO turismo_argentina.locations (id_location, name, description, image, price, deletion_date, id_province) OVERRIDING SYSTEM VALUE VALUES (22, 'Puerto Iguazú', 'Situada en las Tres Fronteras, donde se juntan Brasil, Paraguay y Argentina, Puerto Iguazú se conecta con Brasil por el puente Tancredo Neves. Alberga el Museo Imágenes de la Selva, el Museo Mbororé, un centro de rehabilitación de aves y el famoso Parque Nacional Iguazú. El Parque Nacional posee 275 cascadas, incluidas las mundialmente conocidas cataratas del Iguazú, con 82 metros de altura.', 'puerto_iguazu.jpg', 9230, NULL, 8);
INSERT INTO turismo_argentina.locations (id_location, name, description, image, price, deletion_date, id_province) OVERRIDING SYSTEM VALUE VALUES (23, 'Posadas', 'Posadas es una ciudad del noreste de Argentina, junto al río Paraná, que marca la frontera con Paraguay. La Avenida Costanera, en la ribera, es un paseo arbolado sombreado con vista a la ciudad de Encarnación, en la orilla opuesta. Cuando llega a la calle Fleming, tiene bares y restaurantes a sus costados. El puente San Roque González de Santa Cruz une las 2 ciudades. El Mercado del Puente, en su base, ofrece artesanía y comida local.', 'posadas.jpg', 7153, NULL, 8);
INSERT INTO turismo_argentina.locations (id_location, name, description, image, price, deletion_date, id_province) OVERRIDING SYSTEM VALUE VALUES (24, 'San Ignacio', 'En la actualidad San Ignacio Miní es la mejor conservada de las misiones jesuíticas de los siglos XVI y XVII en territorio argentino. Alrededor de una plaza central se distribuyen la iglesia, la Casa de los Padres, el cementerio, las viviendas y el cabildo.', 'san_ignacio.jpg', 7153, NULL, 8);
INSERT INTO turismo_argentina.locations (id_location, name, description, image, price, deletion_date, id_province) OVERRIDING SYSTEM VALUE VALUES (26, 'Rosario', 'Este mundo acuático urbano es una ciudad en rápido auge, escondida donde dobla el Río Paraná, casi 300 kilómetros al noroeste de Buenos Aires. Navegá en kayak o en barco para admirar las mejores vistas del río, o andá al centro de la ciudad que rebosa de discotecas, teatros, tiendas de la peatonal, restaurantes y parques populares entre los ciclistas. Un monumento nacional en la rambla rinde homenaje a su rol histórico: es la ciudad donde se izó por primera vez la bandera argentina.', 'rosario.jpg', 6846, NULL, 9);
INSERT INTO turismo_argentina.locations (id_location, name, description, image, price, deletion_date, id_province) OVERRIDING SYSTEM VALUE VALUES (27, 'Rafaela', 'Rafaela es una ciudad de la República Argentina ubicada en el centro-oeste de la provincia de Santa Fe. Es la cabecera del departamento Castellanos y a su vez es el tercer centro urbano más poblado e importante de la provincia, detrás de Rosario y Santa Fe.', 'rafaela.jpg', 6846, NULL, 9);
INSERT INTO turismo_argentina.locations (id_location, name, description, image, price, deletion_date, id_province) OVERRIDING SYSTEM VALUE VALUES (28, 'Colón', 'Colón es una ciudad de la provincia de Entre Ríos, en el noreste de Argentina. Se ubica en las orillas del río Uruguay y es conocida por sus playas y su enorme feria anual de artesanías. Los bares y restaurantes se agrupan en la calle 12 de Abril, que lleva a la frondosa Plaza San Martín. El Parque Dr. Herminio J. Quirós tiene senderos que cruzan sus jardines escalonados. En el sur de la ciudad, el puente General Artigas conecta Colón con la ciudad de Paysandú en Uruguay.', 'colon.jpg', 144173, NULL, 10);
INSERT INTO turismo_argentina.locations (id_location, name, description, image, price, deletion_date, id_province) OVERRIDING SYSTEM VALUE VALUES (1, 'Ciudad Autónoma de Buenos Aires', 'La cuna del tango es, como la danza en sí, cautivante, seductora y está repleta de vibrante energía. Las antiguas colonias evocadoras están llenas de restaurantes románticos y emocionante vida nocturna, y la herencia europea es evidente en la arquitectura de Buenos Aires, en sus bulevares y en sus parques. El Café Tortoni, el bar más antiguo de la ciudad, te transportará a 1858, y el sensacional Teatro Colón sigue causando el mismo efecto en el espectador que en 1908. La capital de las compras de Latinoamérica ofrece la promesa de una lujosa terapia de compras por sus amplios bulevares.', 'caba.jpg', 6829, NULL, 1);
INSERT INTO turismo_argentina.locations (id_location, name, description, image, price, deletion_date, id_province) OVERRIDING SYSTEM VALUE VALUES (25, 'Santa Fe (Capital)', 'Santa Fe de la Vera Cruz es la capital de la provincia de Santa Fe, en el noreste de Argentina. Está rodeada en parte por ríos y tiene paseos costeros con cafés y vistas al Puente Colgante. Los cruceros turísticos navegan por el río Paraná y su amplia red de afluentes. El distrito histórico central tiene sitios icónicos que datan del siglo XVII en adelante.', 'santa_fe.jpg', 12529, NULL, 9);
INSERT INTO turismo_argentina.locations (id_location, name, description, image, price, deletion_date, id_province) OVERRIDING SYSTEM VALUE VALUES (10, 'El Bolsón', 'El Bolsón es una localidad argentina situada en la provincia de Río Negro, en el norte de la Patagonia. Se encuentra a orillas del río Quemquemtreu, en un valle repleto de árboles frutales. El cerro Piltriquitrón, muy escarpado, se alza al este. Al oeste están las montañas de los Andes y Cabeza del Indio, una formación rocosa que recuerda a la cabeza de un hombre. En el centro del municipio se celebra la Feria Regional, un mercado donde se venden artículos artesanales, mermeladas y cervezas locales.', 'el_bolson.jpg', 11767, NULL, 3);
INSERT INTO turismo_argentina.locations (id_location, name, description, image, price, deletion_date, id_province) OVERRIDING SYSTEM VALUE VALUES (11, 'Mendoza (Capital)', 'El cerro Aconcagua, la cumbre más alta del hemisferio occidental, domina esta tranquila capital, que comparte nombre con la provincia de Cuyo, en la que reside. Aunque atrae a un buen número de viajeros en busca de aventura, maravillados por las oportunidades de practicar alpinismo, senderismo y rafting a poca distancia en coche del centro, Mendoza también es querida por los enófilos que van a degustar sus caldos, especialmente el Malbec. Las visitas y las catas en los más de mil viñedos de la zona son una de las principales atracciones de la región.', 'mendoza_capital.jpg', 7476, NULL, 4);
INSERT INTO turismo_argentina.locations (id_location, name, description, image, price, deletion_date, id_province) OVERRIDING SYSTEM VALUE VALUES (12, 'San Rafael', 'San Rafael es una ciudad de la provincia semiárida de Mendoza en Argentina, que limita con Chile por los Andes. Las calles bordeadas de sicomoros convergen en la Plaza San Martín, rodeada de cafés y la Catedral San Rafael Arcángel de estilo neorromántico. El río Diamante, que fluye en la ciudad, y el río Atuel al sur permiten practicar el descenso de rápidos en el verano. Alrededor de la ciudad hay famosas bodegas de vino que producen malbecs y otros vinos tintos.', 'san_rafael.jpg', 17223, NULL, 4);
INSERT INTO turismo_argentina.locations (id_location, name, description, image, price, deletion_date, id_province) OVERRIDING SYSTEM VALUE VALUES (13, 'Malargüe', 'Malargüe es una ciudad de la provincia argentina de Mendoza, a los pies de la cordillera de los Andes. Al suroeste está la Caverna de las Brujas, una reserva natural con un amplio complejo cavernoso.', 'malargue.jpg', 17223, NULL, 4);
INSERT INTO turismo_argentina.locations (id_location, name, description, image, price, deletion_date, id_province) OVERRIDING SYSTEM VALUE VALUES (29, 'Federación', 'Federación es el municipio y cabecera del departamento Federación en la provincia de Entre Ríos, Argentina. El municipio comprende la localidad del mismo nombre y un área rural. Se encuentra en la margen derecha del embalse formado por la represa de Salto Grande en el río Uruguay.', 'federacion.jpg', 10147, NULL, 10);
INSERT INTO turismo_argentina.locations (id_location, name, description, image, price, deletion_date, id_province) OVERRIDING SYSTEM VALUE VALUES (30, 'Gualeguaychú', 'Gualeguaychú es una ciudad del este de Argentina, cerca de la frontera con Uruguay. Es conocida por su celebración anual del Carnaval que se lleva a cabo en el Corsódromo, una antigua estación de trenes transformada. La ciudad se extiende por la orilla oeste del rio Gualeguaychú, con numerosas playas. También alberga 2 baños termales. Al otro lado del río se encuentra el amplio y frondoso Parque Unzué, con una laguna, senderos, parques de juegos y sitios de picnic.', 'gualeguaychu.jpg', 9147, NULL, 10);


--
-- TOC entry 3390 (class 0 OID 24806)
-- Dependencies: 215
-- Data for Name: provinces; Type: TABLE DATA; Schema: turismo_argentina; Owner: postgres
--

INSERT INTO turismo_argentina.provinces (id_province, name, description, image, deletion_date) OVERRIDING SYSTEM VALUE VALUES (2, 'Salta', 'Salta es una provincia del noroeste de Argentina que abarca partes de la cordillera de los Andes, bosques de las Yungas y zonas semiáridas del Gran Chaco. La capital, también llamada Salta, tiene arquitectura colonial, incluida la Catedral de Salta neoclásica. Es el punto de partida del Tren a las Nubes, un ferrocarril a gran altura.', 'salta.jpg', NULL);
INSERT INTO turismo_argentina.provinces (id_province, name, description, image, deletion_date) OVERRIDING SYSTEM VALUE VALUES (3, 'Río Negro', 'Río Negro es una provincia en el norte de la Patagonia argentina. Sus paisajes naturales incluyen desde la nevada Cordillera de los Andes en el oeste hasta el océano Atlántico en el este, y se destacan los lagos glaciales y mesetas con bosques. La ciudad de Bariloche, que se encuentra a los pies de los Andes en el Parque Nacional Nahuel Huapi, es conocida por sus playas de piedras, el chocolate casero y el área de esquí en el cerro Catedral.', 'rio_negro.jpg', NULL);
INSERT INTO turismo_argentina.provinces (id_province, name, description, image, deletion_date) OVERRIDING SYSTEM VALUE VALUES (4, 'Mendoza', 'La provincia de Mendoza es uno de los principales centros turísticos de Argentina, tanto nacional como internacional. Hay sitios de interés histórico, en su mayoría relacionados con José de San Martín y el Ejército de los Andes, destacándose el Cerro de la Gloria, las rutas sanmartinianas y los caminos del vino.', 'mendoza.jpg', NULL);
INSERT INTO turismo_argentina.provinces (id_province, name, description, image, deletion_date) OVERRIDING SYSTEM VALUE VALUES (6, 'Neuquén', 'Neuquén es una provincia argentina en el noroeste de la Patagonia. En su lado sur, se encuentra el icónico volcán Lanín con su cono nevado. San Martín de los Andes, una ciudad de estilo alpino en el lago Lácar, está conectada a través del Camino de los Siete Lagos con Villa La Angostura, una ciudad en un lago profundo y claro rodeado del Parque Nacional Nahuel Huapi. Cerca de la ciudad, el Parque Nacional Los Arrayanes es una península arbolada con arrayanes de 300 años de antigüedad.', 'neuquen.jpg', NULL);
INSERT INTO turismo_argentina.provinces (id_province, name, description, image, deletion_date) OVERRIDING SYSTEM VALUE VALUES (7, 'Chubut', 'Chubut es una provincia de la Patagonia argentina ubicada en vastas llanuras entre la cordillera de los Andes y el océano Atlántico. La remota península Valdés alberga ballenas francas australes, elefantes marinos y pingüinos. La ciudad de Puerto Madryn se ubica en la playa y es una vía de acceso a la península Valdés y a Punta Tombo, que alberga una colonia de crianza de pingüinos de Magallanes.', 'chubut.jpg', NULL);
INSERT INTO turismo_argentina.provinces (id_province, name, description, image, deletion_date) OVERRIDING SYSTEM VALUE VALUES (8, 'Misiones', 'La Provincia de Misiones es una de las 23 provincias que hay en la República Argentina. A su vez, es uno de los 24 estados autogobernados o jurisdicciones de primer orden que conforman el país, y uno de los 24 distritos electorales legislativos nacionales. Su capital y ciudad más poblada es Posadas. Está ubicada en el noreste del país, en la región del Norte Grande Argentino.', 'misiones.jpg', NULL);
INSERT INTO turismo_argentina.provinces (id_province, name, description, image, deletion_date) OVERRIDING SYSTEM VALUE VALUES (5, 'Tucumán', 'Tucumán posee algunos de los destinos más variados de Argentina. Con la ventaja de tener distancias cortas, cada uno de los circuitos turísticos que ofrece la provincia invita a vivir una experiencia única entre la historia que guarda la ciudad y la magia de su vegetación, sus diques, cerros y valles.', 'tucuman.jpg', NULL);
INSERT INTO turismo_argentina.provinces (id_province, name, description, image, deletion_date) OVERRIDING SYSTEM VALUE VALUES (9, 'Santa Fe', 'Santa Fe es una provincia agrícola en el noreste de Argentina. A lo largo de su frontera oriental fluye el río Paraná, una de las principales vías fluviales de América del Sur. Rosario, un concurrido puerto en el río, alberga el Monumento de la Bandera Nacional, que conmemora la Guerra de la Independencia del siglo XIX en Argentina. La capital, Santa Fe de la Vera Cruz, tiene un centro colonial del siglo XVII.', 'santa_fe.png', NULL);
INSERT INTO turismo_argentina.provinces (id_province, name, description, image, deletion_date) OVERRIDING SYSTEM VALUE VALUES (1, 'Buenos Aires', 'Buenos Aires es la gran capital cosmopolita de Argentina. Su centro es la Plaza de Mayo, rodeada de imponentes edificios del siglo XIX, incluida la Casa Rosada, el icónico palacio presidencial que tiene varios balcones. Entre otras atracciones importantes, se incluyen el Teatro Colón, un lujoso teatro de ópera de 1908 con cerca de 2,500 asientos, y el moderno museo MALBA, que exhibe arte latinoamericano.', 'bsas.jpg', NULL);
INSERT INTO turismo_argentina.provinces (id_province, name, description, image, deletion_date) OVERRIDING SYSTEM VALUE VALUES (10, 'Entre Ríos', 'Entre Ríos es una provincia de la región de Mesopotamia en el noreste de Argentina, entre los ríos Paraná y Uruguay. Es conocida por sus fuentes termales, como las de Federación, y su tradición de cultivar la yerba mate y beber el mate. Alberga 2 parques nacionales: Predelta, abundante en especies de aves, y El Palmar, una de las reservas que quedan de la palma yatay que alguna vez cubrió la región.', 'entre_rios.jpg', NULL);

--
-- TOC entry 3413 (class 0 OID 0)
-- Dependencies: 218
-- Name: activities_id_activity_seq; Type: SEQUENCE SET; Schema: turismo_argentina; Owner: postgres
--

SELECT pg_catalog.setval('turismo_argentina.activities_id_activity_seq', 1, false);


--
-- TOC entry 3414 (class 0 OID 0)
-- Dependencies: 222
-- Name: coupons_id_coupon_seq; Type: SEQUENCE SET; Schema: turismo_argentina; Owner: postgres
--

SELECT pg_catalog.setval('turismo_argentina.coupons_id_coupon_seq', 1, false);


--
-- TOC entry 3415 (class 0 OID 0)
-- Dependencies: 216
-- Name: locations_id_location_seq; Type: SEQUENCE SET; Schema: turismo_argentina; Owner: postgres
--

SELECT pg_catalog.setval('turismo_argentina.locations_id_location_seq', 1, false);


--
-- TOC entry 3416 (class 0 OID 0)
-- Dependencies: 214
-- Name: provinces_id_province_seq; Type: SEQUENCE SET; Schema: turismo_argentina; Owner: postgres
--

SELECT pg_catalog.setval('turismo_argentina.provinces_id_province_seq', 10, true);


--
-- TOC entry 3418 (class 0 OID 0)
-- Dependencies: 230
-- Name: purchases_activities_id_purchase_activity_seq; Type: SEQUENCE SET; Schema: turismo_argentina; Owner: postgres
--

SELECT pg_catalog.setval('turismo_argentina.purchases_activities_id_purchase_activity_seq', 1, false);


--
-- TOC entry 3419 (class 0 OID 0)
-- Dependencies: 226
-- Name: purchases_id_purchase_seq; Type: SEQUENCE SET; Schema: turismo_argentina; Owner: postgres
--

SELECT pg_catalog.setval('turismo_argentina.purchases_id_purchase_seq', 1, false);


--
-- TOC entry 3420 (class 0 OID 0)
-- Dependencies: 224
-- Name: redeemed_coupons_id_redeemed_coupon_seq; Type: SEQUENCE SET; Schema: turismo_argentina; Owner: postgres
--

SELECT pg_catalog.setval('turismo_argentina.redeemed_coupons_id_redeemed_coupon_seq', 1, false);


--
-- TOC entry 3421 (class 0 OID 0)
-- Dependencies: 220
-- Name: users_id_user_seq; Type: SEQUENCE SET; Schema: turismo_argentina; Owner: postgres
--

SELECT pg_catalog.setval('turismo_argentina.users_id_user_seq', 1, false);


--
-- TOC entry 3223 (class 2606 OID 24833)
-- Name: activities activities_pkey; Type: CONSTRAINT; Schema: turismo_argentina; Owner: postgres
--

ALTER TABLE ONLY turismo_argentina.activities
    ADD CONSTRAINT activities_pkey PRIMARY KEY (id_activity);


--
-- TOC entry 3227 (class 2606 OID 24850)
-- Name: coupons coupons_pkey; Type: CONSTRAINT; Schema: turismo_argentina; Owner: postgres
--

ALTER TABLE ONLY turismo_argentina.coupons
    ADD CONSTRAINT coupons_pkey PRIMARY KEY (id_coupon);


--
-- TOC entry 3221 (class 2606 OID 24820)
-- Name: locations locations_pkey; Type: CONSTRAINT; Schema: turismo_argentina; Owner: postgres
--

ALTER TABLE ONLY turismo_argentina.locations
    ADD CONSTRAINT locations_pkey PRIMARY KEY (id_location);


--
-- TOC entry 3219 (class 2606 OID 24812)
-- Name: provinces provinces_pkey; Type: CONSTRAINT; Schema: turismo_argentina; Owner: postgres
--

ALTER TABLE ONLY turismo_argentina.provinces
    ADD CONSTRAINT provinces_pkey PRIMARY KEY (id_province);


--
-- TOC entry 3235 (class 2606 OID 24901)
-- Name: purchases_activities purchases_activities_pkey; Type: CONSTRAINT; Schema: turismo_argentina; Owner: postgres
--

ALTER TABLE ONLY turismo_argentina.purchases_activities
    ADD CONSTRAINT purchases_activities_pkey PRIMARY KEY (id_purchase_activity);


--
-- TOC entry 3231 (class 2606 OID 24873)
-- Name: purchases purchases_pkey; Type: CONSTRAINT; Schema: turismo_argentina; Owner: postgres
--

ALTER TABLE ONLY turismo_argentina.purchases
    ADD CONSTRAINT purchases_pkey PRIMARY KEY (id_purchase);


--
-- TOC entry 3229 (class 2606 OID 24857)
-- Name: redeemed_coupons redeemed_coupons_pkey; Type: CONSTRAINT; Schema: turismo_argentina; Owner: postgres
--

ALTER TABLE ONLY turismo_argentina.redeemed_coupons
    ADD CONSTRAINT redeemed_coupons_pkey PRIMARY KEY (id_redeemed_coupon);


--
-- TOC entry 3225 (class 2606 OID 24844)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: turismo_argentina; Owner: postgres
--

ALTER TABLE ONLY turismo_argentina.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id_user);


--
-- TOC entry 3237 (class 2606 OID 24834)
-- Name: activities fk_activity_location; Type: FK CONSTRAINT; Schema: turismo_argentina; Owner: postgres
--

ALTER TABLE ONLY turismo_argentina.activities
    ADD CONSTRAINT fk_activity_location FOREIGN KEY (id_location) REFERENCES turismo_argentina.locations(id_location);


--
-- TOC entry 3236 (class 2606 OID 24821)
-- Name: locations fk_location_province; Type: FK CONSTRAINT; Schema: turismo_argentina; Owner: postgres
--

ALTER TABLE ONLY turismo_argentina.locations
    ADD CONSTRAINT fk_location_province FOREIGN KEY (id_province) REFERENCES turismo_argentina.provinces(id_province);


--
-- TOC entry 3243 (class 2606 OID 24902)
-- Name: purchases_activities fk_pa_activity; Type: FK CONSTRAINT; Schema: turismo_argentina; Owner: postgres
--

ALTER TABLE ONLY turismo_argentina.purchases_activities
    ADD CONSTRAINT fk_pa_activity FOREIGN KEY (id_activity) REFERENCES turismo_argentina.activities(id_activity);


--
-- TOC entry 3244 (class 2606 OID 24907)
-- Name: purchases_activities fk_pa_purchase; Type: FK CONSTRAINT; Schema: turismo_argentina; Owner: postgres
--

ALTER TABLE ONLY turismo_argentina.purchases_activities
    ADD CONSTRAINT fk_pa_purchase FOREIGN KEY (id_purchase) REFERENCES turismo_argentina.purchases(id_purchase);


--
-- TOC entry 3241 (class 2606 OID 24886)
-- Name: purchases_locations fk_pl_location; Type: FK CONSTRAINT; Schema: turismo_argentina; Owner: postgres
--

ALTER TABLE ONLY turismo_argentina.purchases_locations
    ADD CONSTRAINT fk_pl_location FOREIGN KEY (id_location) REFERENCES turismo_argentina.locations(id_location);

--
-- TOC entry 3242 (class 2606 OID 24891)
-- Name: purchases_locations fk_pl_purchase; Type: FK CONSTRAINT; Schema: turismo_argentina; Owner: postgres
--

ALTER TABLE ONLY turismo_argentina.purchases_locations
    ADD CONSTRAINT fk_pl_purchase FOREIGN KEY (id_purchase) REFERENCES turismo_argentina.purchases(id_purchase);

--
-- TOC entry 3240 (class 2606 OID 24874)
-- Name: purchases fk_purchase_user; Type: FK CONSTRAINT; Schema: turismo_argentina; Owner: postgres
--

ALTER TABLE ONLY turismo_argentina.purchases
    ADD CONSTRAINT fk_purchase_user FOREIGN KEY (id_user) REFERENCES turismo_argentina.users(id_user);


--
-- TOC entry 3238 (class 2606 OID 24858)
-- Name: redeemed_coupons fk_rc_coupon; Type: FK CONSTRAINT; Schema: turismo_argentina; Owner: postgres
--

ALTER TABLE ONLY turismo_argentina.redeemed_coupons
    ADD CONSTRAINT fk_rc_coupon FOREIGN KEY (id_coupon) REFERENCES turismo_argentina.coupons(id_coupon);


--
-- TOC entry 3239 (class 2606 OID 24863)
-- Name: redeemed_coupons fk_rc_user; Type: FK CONSTRAINT; Schema: turismo_argentina; Owner: postgres
--

ALTER TABLE ONLY turismo_argentina.redeemed_coupons
    ADD CONSTRAINT fk_rc_user FOREIGN KEY (id_user) REFERENCES turismo_argentina.users(id_user);


-- Completed on 2023-03-21 11:48:45

--
-- PostgreSQL database dump complete
--

