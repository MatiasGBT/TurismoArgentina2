PGDMP     #    /    
            {            turismo_argentina    15.2    15.2 @    R           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            S           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            T           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            U           1262    24576    turismo_argentina    DATABASE     �   CREATE DATABASE turismo_argentina WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Spanish_Argentina.1252';
 !   DROP DATABASE turismo_argentina;
                postgres    false            V           0    0    DATABASE turismo_argentina    COMMENT     I   COMMENT ON DATABASE turismo_argentina IS 'Turismo Argentina 2 database';
                   postgres    false    3413                        2615    24577    turismo_argentina    SCHEMA     !   CREATE SCHEMA turismo_argentina;
    DROP SCHEMA turismo_argentina;
                postgres    false            �            1259    24827 
   activities    TABLE     �  CREATE TABLE turismo_argentina.activities (
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
 )   DROP TABLE turismo_argentina.activities;
       turismo_argentina         heap    postgres    false    5            �            1259    24826    activities_id_activity_seq    SEQUENCE     �   ALTER TABLE turismo_argentina.activities ALTER COLUMN id_activity ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME turismo_argentina.activities_id_activity_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            turismo_argentina          postgres    false    219    5            �            1259    24846    coupons    TABLE     �   CREATE TABLE turismo_argentina.coupons (
    id_coupon bigint NOT NULL,
    name character varying(30) NOT NULL,
    discount smallint NOT NULL,
    start_date date NOT NULL,
    finish_date date NOT NULL
);
 &   DROP TABLE turismo_argentina.coupons;
       turismo_argentina         heap    postgres    false    5            �            1259    24845    coupons_id_coupon_seq    SEQUENCE     �   ALTER TABLE turismo_argentina.coupons ALTER COLUMN id_coupon ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME turismo_argentina.coupons_id_coupon_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            turismo_argentina          postgres    false    5    223            �            1259    24814 	   locations    TABLE     0  CREATE TABLE turismo_argentina.locations (
    id_location bigint NOT NULL,
    name character varying(45) NOT NULL,
    description character varying(600) NOT NULL,
    image character varying(80) NOT NULL,
    price double precision NOT NULL,
    deletion_date date,
    id_province bigint NOT NULL
);
 (   DROP TABLE turismo_argentina.locations;
       turismo_argentina         heap    postgres    false    5            �            1259    24813    locations_id_location_seq    SEQUENCE     �   ALTER TABLE turismo_argentina.locations ALTER COLUMN id_location ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME turismo_argentina.locations_id_location_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            turismo_argentina          postgres    false    217    5            �            1259    24806 	   provinces    TABLE     �   CREATE TABLE turismo_argentina.provinces (
    id_province bigint NOT NULL,
    name character varying(20) NOT NULL,
    description character varying(600) NOT NULL,
    image character varying(80) NOT NULL,
    deletion_date date
);
 (   DROP TABLE turismo_argentina.provinces;
       turismo_argentina         heap    postgres    false    5            �            1259    24805    provinces_id_province_seq    SEQUENCE     �   ALTER TABLE turismo_argentina.provinces ALTER COLUMN id_province ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME turismo_argentina.provinces_id_province_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            turismo_argentina          postgres    false    215    5            �            1259    24912    puchases_locations    TABLE     �   CREATE TABLE turismo_argentina.puchases_locations (
    id_puchase_location bigint NOT NULL,
    id_purchase bigint NOT NULL,
    id_location bigint NOT NULL
);
 1   DROP TABLE turismo_argentina.puchases_locations;
       turismo_argentina         heap    postgres    false    5            �            1259    24881    purchases_locations    TABLE     �   CREATE TABLE turismo_argentina.purchases_locations (
    id_puchase_location bigint NOT NULL,
    id_purchase bigint NOT NULL,
    id_location bigint NOT NULL
);
 2   DROP TABLE turismo_argentina.purchases_locations;
       turismo_argentina         heap    postgres    false    5            �            1259    24880 *   puchases_locations_id_puchase_location_seq    SEQUENCE       ALTER TABLE turismo_argentina.purchases_locations ALTER COLUMN id_puchase_location ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME turismo_argentina.puchases_locations_id_puchase_location_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            turismo_argentina          postgres    false    229    5            �            1259    24869 	   purchases    TABLE     �   CREATE TABLE turismo_argentina.purchases (
    id_purchase bigint NOT NULL,
    date date NOT NULL,
    id_user bigint NOT NULL
);
 (   DROP TABLE turismo_argentina.purchases;
       turismo_argentina         heap    postgres    false    5            �            1259    24897    purchases_activities    TABLE     �   CREATE TABLE turismo_argentina.purchases_activities (
    id_purchase_activity bigint NOT NULL,
    id_purchase bigint NOT NULL,
    id_activity bigint NOT NULL
);
 3   DROP TABLE turismo_argentina.purchases_activities;
       turismo_argentina         heap    postgres    false    5            �            1259    24896 -   purchases_activities_id_purchase_activity_seq    SEQUENCE       ALTER TABLE turismo_argentina.purchases_activities ALTER COLUMN id_purchase_activity ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME turismo_argentina.purchases_activities_id_purchase_activity_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            turismo_argentina          postgres    false    5    231            �            1259    24868    purchases_id_purchase_seq    SEQUENCE     �   ALTER TABLE turismo_argentina.purchases ALTER COLUMN id_purchase ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME turismo_argentina.purchases_id_purchase_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            turismo_argentina          postgres    false    227    5            �            1259    24852    redeemed_coupons    TABLE     �   CREATE TABLE turismo_argentina.redeemed_coupons (
    id_redeemed_coupon bigint NOT NULL,
    id_user bigint NOT NULL,
    id_coupon bigint NOT NULL,
    date timestamp without time zone NOT NULL,
    used boolean DEFAULT false NOT NULL
);
 /   DROP TABLE turismo_argentina.redeemed_coupons;
       turismo_argentina         heap    postgres    false    5            �            1259    24851 '   redeemed_coupons_id_redeemed_coupon_seq    SEQUENCE       ALTER TABLE turismo_argentina.redeemed_coupons ALTER COLUMN id_redeemed_coupon ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME turismo_argentina.redeemed_coupons_id_redeemed_coupon_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            turismo_argentina          postgres    false    225    5            �            1259    24840    users    TABLE       CREATE TABLE turismo_argentina.users (
    id_user bigint NOT NULL,
    username character varying(45) NOT NULL,
    name character varying(60) NOT NULL,
    last_name character varying(60) NOT NULL,
    creation_date date NOT NULL,
    deletion_date date
);
 $   DROP TABLE turismo_argentina.users;
       turismo_argentina         heap    postgres    false    5            �            1259    24839    users_id_user_seq    SEQUENCE     �   ALTER TABLE turismo_argentina.users ALTER COLUMN id_user ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME turismo_argentina.users_id_user_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            turismo_argentina          postgres    false    5    221            B          0    24827 
   activities 
   TABLE DATA                 turismo_argentina          postgres    false    219   #T       F          0    24846    coupons 
   TABLE DATA                 turismo_argentina          postgres    false    223   �t       @          0    24814 	   locations 
   TABLE DATA                 turismo_argentina          postgres    false    217   �t       >          0    24806 	   provinces 
   TABLE DATA                 turismo_argentina          postgres    false    215   ]�       O          0    24912    puchases_locations 
   TABLE DATA                 turismo_argentina          postgres    false    232   Ɠ       J          0    24869 	   purchases 
   TABLE DATA                 turismo_argentina          postgres    false    227   ��       N          0    24897    purchases_activities 
   TABLE DATA                 turismo_argentina          postgres    false    231   g�       L          0    24881    purchases_locations 
   TABLE DATA                 turismo_argentina          postgres    false    229   ��       H          0    24852    redeemed_coupons 
   TABLE DATA                 turismo_argentina          postgres    false    225   ��       D          0    24840    users 
   TABLE DATA                 turismo_argentina          postgres    false    221   ��       W           0    0    activities_id_activity_seq    SEQUENCE SET     T   SELECT pg_catalog.setval('turismo_argentina.activities_id_activity_seq', 1, false);
          turismo_argentina          postgres    false    218            X           0    0    coupons_id_coupon_seq    SEQUENCE SET     O   SELECT pg_catalog.setval('turismo_argentina.coupons_id_coupon_seq', 1, false);
          turismo_argentina          postgres    false    222            Y           0    0    locations_id_location_seq    SEQUENCE SET     S   SELECT pg_catalog.setval('turismo_argentina.locations_id_location_seq', 1, false);
          turismo_argentina          postgres    false    216            Z           0    0    provinces_id_province_seq    SEQUENCE SET     S   SELECT pg_catalog.setval('turismo_argentina.provinces_id_province_seq', 10, true);
          turismo_argentina          postgres    false    214            [           0    0 *   puchases_locations_id_puchase_location_seq    SEQUENCE SET     d   SELECT pg_catalog.setval('turismo_argentina.puchases_locations_id_puchase_location_seq', 1, false);
          turismo_argentina          postgres    false    228            \           0    0 -   purchases_activities_id_purchase_activity_seq    SEQUENCE SET     g   SELECT pg_catalog.setval('turismo_argentina.purchases_activities_id_purchase_activity_seq', 1, false);
          turismo_argentina          postgres    false    230            ]           0    0    purchases_id_purchase_seq    SEQUENCE SET     S   SELECT pg_catalog.setval('turismo_argentina.purchases_id_purchase_seq', 1, false);
          turismo_argentina          postgres    false    226            ^           0    0 '   redeemed_coupons_id_redeemed_coupon_seq    SEQUENCE SET     a   SELECT pg_catalog.setval('turismo_argentina.redeemed_coupons_id_redeemed_coupon_seq', 1, false);
          turismo_argentina          postgres    false    224            _           0    0    users_id_user_seq    SEQUENCE SET     K   SELECT pg_catalog.setval('turismo_argentina.users_id_user_seq', 1, false);
          turismo_argentina          postgres    false    220            �           2606    24833    activities activities_pkey 
   CONSTRAINT     l   ALTER TABLE ONLY turismo_argentina.activities
    ADD CONSTRAINT activities_pkey PRIMARY KEY (id_activity);
 O   ALTER TABLE ONLY turismo_argentina.activities DROP CONSTRAINT activities_pkey;
       turismo_argentina            postgres    false    219            �           2606    24850    coupons coupons_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY turismo_argentina.coupons
    ADD CONSTRAINT coupons_pkey PRIMARY KEY (id_coupon);
 I   ALTER TABLE ONLY turismo_argentina.coupons DROP CONSTRAINT coupons_pkey;
       turismo_argentina            postgres    false    223            �           2606    24820    locations locations_pkey 
   CONSTRAINT     j   ALTER TABLE ONLY turismo_argentina.locations
    ADD CONSTRAINT locations_pkey PRIMARY KEY (id_location);
 M   ALTER TABLE ONLY turismo_argentina.locations DROP CONSTRAINT locations_pkey;
       turismo_argentina            postgres    false    217            �           2606    24812    provinces provinces_pkey 
   CONSTRAINT     j   ALTER TABLE ONLY turismo_argentina.provinces
    ADD CONSTRAINT provinces_pkey PRIMARY KEY (id_province);
 M   ALTER TABLE ONLY turismo_argentina.provinces DROP CONSTRAINT provinces_pkey;
       turismo_argentina            postgres    false    215            �           2606    24885 +   purchases_locations puchases_locations_pkey 
   CONSTRAINT     �   ALTER TABLE ONLY turismo_argentina.purchases_locations
    ADD CONSTRAINT puchases_locations_pkey PRIMARY KEY (id_puchase_location);
 `   ALTER TABLE ONLY turismo_argentina.purchases_locations DROP CONSTRAINT puchases_locations_pkey;
       turismo_argentina            postgres    false    229            �           2606    24901 .   purchases_activities purchases_activities_pkey 
   CONSTRAINT     �   ALTER TABLE ONLY turismo_argentina.purchases_activities
    ADD CONSTRAINT purchases_activities_pkey PRIMARY KEY (id_purchase_activity);
 c   ALTER TABLE ONLY turismo_argentina.purchases_activities DROP CONSTRAINT purchases_activities_pkey;
       turismo_argentina            postgres    false    231            �           2606    24873    purchases purchases_pkey 
   CONSTRAINT     j   ALTER TABLE ONLY turismo_argentina.purchases
    ADD CONSTRAINT purchases_pkey PRIMARY KEY (id_purchase);
 M   ALTER TABLE ONLY turismo_argentina.purchases DROP CONSTRAINT purchases_pkey;
       turismo_argentina            postgres    false    227            �           2606    24857 &   redeemed_coupons redeemed_coupons_pkey 
   CONSTRAINT        ALTER TABLE ONLY turismo_argentina.redeemed_coupons
    ADD CONSTRAINT redeemed_coupons_pkey PRIMARY KEY (id_redeemed_coupon);
 [   ALTER TABLE ONLY turismo_argentina.redeemed_coupons DROP CONSTRAINT redeemed_coupons_pkey;
       turismo_argentina            postgres    false    225            �           2606    24844    users users_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY turismo_argentina.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id_user);
 E   ALTER TABLE ONLY turismo_argentina.users DROP CONSTRAINT users_pkey;
       turismo_argentina            postgres    false    221            �           2606    24834    activities fk_activity_location    FK CONSTRAINT     �   ALTER TABLE ONLY turismo_argentina.activities
    ADD CONSTRAINT fk_activity_location FOREIGN KEY (id_location) REFERENCES turismo_argentina.locations(id_location);
 T   ALTER TABLE ONLY turismo_argentina.activities DROP CONSTRAINT fk_activity_location;
       turismo_argentina          postgres    false    217    219    3221            �           2606    24821    locations fk_location_province    FK CONSTRAINT     �   ALTER TABLE ONLY turismo_argentina.locations
    ADD CONSTRAINT fk_location_province FOREIGN KEY (id_province) REFERENCES turismo_argentina.provinces(id_province);
 S   ALTER TABLE ONLY turismo_argentina.locations DROP CONSTRAINT fk_location_province;
       turismo_argentina          postgres    false    215    3219    217            �           2606    24902 #   purchases_activities fk_pa_activity    FK CONSTRAINT     �   ALTER TABLE ONLY turismo_argentina.purchases_activities
    ADD CONSTRAINT fk_pa_activity FOREIGN KEY (id_activity) REFERENCES turismo_argentina.activities(id_activity);
 X   ALTER TABLE ONLY turismo_argentina.purchases_activities DROP CONSTRAINT fk_pa_activity;
       turismo_argentina          postgres    false    231    219    3223            �           2606    24907 #   purchases_activities fk_pa_purchase    FK CONSTRAINT     �   ALTER TABLE ONLY turismo_argentina.purchases_activities
    ADD CONSTRAINT fk_pa_purchase FOREIGN KEY (id_purchase) REFERENCES turismo_argentina.purchases(id_purchase);
 X   ALTER TABLE ONLY turismo_argentina.purchases_activities DROP CONSTRAINT fk_pa_purchase;
       turismo_argentina          postgres    false    227    231    3231            �           2606    24886 "   purchases_locations fk_pl_location    FK CONSTRAINT     �   ALTER TABLE ONLY turismo_argentina.purchases_locations
    ADD CONSTRAINT fk_pl_location FOREIGN KEY (id_location) REFERENCES turismo_argentina.locations(id_location);
 W   ALTER TABLE ONLY turismo_argentina.purchases_locations DROP CONSTRAINT fk_pl_location;
       turismo_argentina          postgres    false    3221    229    217            �           2606    24915 !   puchases_locations fk_pl_location    FK CONSTRAINT     �   ALTER TABLE ONLY turismo_argentina.puchases_locations
    ADD CONSTRAINT fk_pl_location FOREIGN KEY (id_location) REFERENCES turismo_argentina.locations(id_location);
 V   ALTER TABLE ONLY turismo_argentina.puchases_locations DROP CONSTRAINT fk_pl_location;
       turismo_argentina          postgres    false    217    232    3221            �           2606    24891 "   purchases_locations fk_pl_purchase    FK CONSTRAINT     �   ALTER TABLE ONLY turismo_argentina.purchases_locations
    ADD CONSTRAINT fk_pl_purchase FOREIGN KEY (id_purchase) REFERENCES turismo_argentina.purchases(id_purchase);
 W   ALTER TABLE ONLY turismo_argentina.purchases_locations DROP CONSTRAINT fk_pl_purchase;
       turismo_argentina          postgres    false    229    3231    227            �           2606    24920 !   puchases_locations fk_pl_purchase    FK CONSTRAINT     �   ALTER TABLE ONLY turismo_argentina.puchases_locations
    ADD CONSTRAINT fk_pl_purchase FOREIGN KEY (id_purchase) REFERENCES turismo_argentina.purchases(id_purchase);
 V   ALTER TABLE ONLY turismo_argentina.puchases_locations DROP CONSTRAINT fk_pl_purchase;
       turismo_argentina          postgres    false    227    232    3231            �           2606    24874    purchases fk_purchase_user    FK CONSTRAINT     �   ALTER TABLE ONLY turismo_argentina.purchases
    ADD CONSTRAINT fk_purchase_user FOREIGN KEY (id_user) REFERENCES turismo_argentina.users(id_user);
 O   ALTER TABLE ONLY turismo_argentina.purchases DROP CONSTRAINT fk_purchase_user;
       turismo_argentina          postgres    false    227    3225    221            �           2606    24858    redeemed_coupons fk_rc_coupon    FK CONSTRAINT     �   ALTER TABLE ONLY turismo_argentina.redeemed_coupons
    ADD CONSTRAINT fk_rc_coupon FOREIGN KEY (id_coupon) REFERENCES turismo_argentina.coupons(id_coupon);
 R   ALTER TABLE ONLY turismo_argentina.redeemed_coupons DROP CONSTRAINT fk_rc_coupon;
       turismo_argentina          postgres    false    3227    223    225            �           2606    24863    redeemed_coupons fk_rc_user    FK CONSTRAINT     �   ALTER TABLE ONLY turismo_argentina.redeemed_coupons
    ADD CONSTRAINT fk_rc_user FOREIGN KEY (id_user) REFERENCES turismo_argentina.users(id_user);
 P   ALTER TABLE ONLY turismo_argentina.redeemed_coupons DROP CONSTRAINT fk_rc_user;
       turismo_argentina          postgres    false    225    221    3225            B      x��]Ko#ǵ��W��fl����w�"�5�=�f<���&@���]�J��	S����Yz1�;#@�����S���	�!�x$�����'_�y��.y����)s�7姺�L��*���TF��&�[�u1H
��A��2�fVW���>
��ÿǃd�M�gk���L[������>�y�K��/��>ܼ{���7�$������u������������MQy��d��J�M�K��]�Z�������^ˏ�I�Ǵ��=euI�7Ŵ.T27��T���j]�3W��FuY)������3Z�JJS�g�����Ճ�^'tH����h���^$x�˥����'|��J[��"��։)R[/t�Y״6xZلN�X~���$:3��q��槙u��$/�Ǳ��L<�xV7�t���G����<��tZ�UɃ)+�J�M�kS��[JrW�9��q�0��B����C�܃�r*r(G�����?�qr>�ψ2�o~������߽:,�o ��u����|��[��p�<z�*��]��ٽVvB#K�Q!ݶk?�1CZ��fFSΉ�
��Rj��iQ)��O3W��
��u�U�͙�k_�d��Ȝ��4G^��)m��1�5����++�c�MI��u�3��j? �:o2�����+���R�Suo�/�yM��e9
D| �\~,���e;&����A�|���ɯ|��������=gۓ	�����(2������NIҚ�aj��/Qɭ��tJ��N]��_4cW%=��j��DH��}B2��'|�A2'YG-���E��@�C�)=]��s����	�4y�L�9=��^�L��(ڋW��yIw�k���@��ՌT��9��f��/��)�}M����,�V6��I%)��G{Sъ=��x�E����Ym�wuK�H�6Hޓ���6���%N�98���Ś+GN�����H������]�9�v��̉"O��F����q�s�ɪҹ��V��8�K��A�Wi-(*ɗ�d�U�1�E���gH��O�l2u&�Ix%2�M�c@\x�^���;H���4����U�U�iݮ�r���k���zNt��%�ó{���lC�U�;><�8e  i3Q�2pp��4��Q"j�� �P@���� �\�V ���������琜}$� 0���֮t����.�7L���:ՠ*������o������k��&�0[O��@�v@�Y!��2�s��h&z���-�֏�3dhI�׋D��h���Y������&�@���si��!M$����'�	��T�2�;*��L]�l�4&(:ZYV�r,����2��6R._�
���s�9�?������lx|9HN.�'�h��u	��A=�������d�=�ɿ��}m4�N5��O{^/?M8[P4�h���_���RF���hl���,� ��l�ыg���wu	�i ��=�l	l�i�$��>3�W]��(Z�K���P04 k��  0�gdzZ68���2j�u!g�'�mz=^4��T�?A�
P?�,���f��	GK'[��n'�}Fo���nD����hxI
t4<=`�&���;U���t;�aF�9��&6�b��O���WA+���^ݸ`�ي"�L�!��3sB���6�jB�ʔP	����3���,��.�K͢23��O��"U�����"��3mR����6<�(@��Ѫ�?><Z ��������dJ����8���Գ=u+���@~�7�m#2��|puN@M�Ǯ�
�DWZ��+��ؤp^,���6Ln�V���K����������b@���O.�A2�*��ʺ 7��_�nT�L��J���y��VU�<�#&�n��������p�c���sta%))�m�
\T�i�Y�iѼ����s�vѸsX������9���V&�\���)������H�Dz��C^"!�@�%�LF��Q��]w�F2N�1����H��IOa�A�7ǆ�G��:���$�����M.]��v`5؇��d�e:N�򩗖�×�f\@v����+k�Gr:;���gwUk�6�5:=:^��0m���e�>������8����p���ŪJ���k�+d��;&9\�pÔ�qt��e.�p�u�'�e�-[[3R�x{k��nB��eDP�xM�lH���� ���m�`�Ӛ<%>A[j��v�&��>��R���n��{��VG���	A��C�������@���kuO����%f�����b	^R��ݞh��e�j.�;�ѧ����ҧ�8�&`���y/�q!zZ�k���Hű1t���=,sq"�w!&�0Q	-fpl��L���$� O�Hs��p#��[^�\Q��I��Q�**xi��[яWŽ�$(h�Z����r�����k=�/�_u�
���Q6@�5L��Zp�ⷆ8����N�4�{S��N�+Z�Y����hux�>~��������t8:G�?� #�{
���IM��c��-4�t����腶��BQ�ˈ\-G�#?l�V�p��[~_$�]T敶��8��9Ȑ N������Cz��{�INi��+�	����	�N�9�̱�Ԝ�R��q:FUv�j�q��a�k_�H������Z෿ฟR���H�z�*Í���c�=�{�5���xDE~z����m�����+`�UO$V>7�_tB�I\����������!��&�/&1<h���ī������^W:Ĝٵ��E}
D�t�5xT�� ������!�ҵx�D�ZiE��R�L=]�Z8��R��0�ו�oJb��x�E�؍-m��cі�D�v�OU�)���lYк���O�r��i�f;O��G���G��Qױ|vx�>��j�t�R�6�E���[SW�ǟ�gfT�ꍴ�:3m�b��+���W�O�na&����5�����6DQW�2���]��G^ԃ�i��/�3J�)�
�x�3�К�Xrp6��Vx1CJ�T[��&�ˏ�6O^<����#{�NX;�m��;�E|y<>��;�����h��dʹ���Ie���J������s��p���
��Y�",Y�d��[��� ]B����ON#$H���[�=n�_�����<�Y5Y �]��t���7�$Y��@\A6������������߷�Hz�㬦���3���X����S-*h7���v�f�EAU��;����X_�2E�������q⬏j����������%��$�]�͙:9�I�Թa.�E�Bv�"&b~P�  <]�[��D�kn&s���x�+���_(�����$!��84���}�c̛lh*�zt����Ȃ@5���7Lz�R��)�`�"		F���c8��,���v3���Xk�7��U���Q���<����r.�Ā�HĂA@�(�c̜ⴤ�q��B�� ����ĄY�Ir���8������t��O���	;��F�����;[�3�GZn��qm|�y��{���ˋ���x�Id�8@6<[�U1���Q%qǋ�����Y�Q����UԊ�ۏ_�!���`���b3���3�i%%�VA�ɩ�]�*M�إ!`�&G��?��Қ@�V�� �g:���%¬ޝ&�����c�m�n@�m�`*aN���{m�JگA��Z��1Z-�7���wS֯�.B�ͅ�ҵ\���4�J.�Y��K�u�+x������{���F;��ψ�������\ʧ�Oo��j�5յ��uBkhO�y'</�I� �"i��q�>�4����ԫ[��g��9f%dAf
guY��0-�|�� Kt���Kf	)�X���cu칒P���f+9 <'y@��Ԛ�Pa,�v�����8���X���+�ՠQEj�ݺ��V�^b�
=��z ��yĘb��5<�ȧ���y�Sd��m��?����O�����\����D���%M*�� �WΖ�8�v-ߥ��W}S���{�4 ;�S��k����h|�$&d2����.�k�А    ���:'���,��LM1�K�T�M&W�.?%\x�m�p�G��QH�� K(;(U�[�z�[�E%�g�9�?3�:�\l^D�����rg�1d���KVlc�6�F�8^:��vw�����`a�ɖ*O.�,�[c�ht�ty��f����քe�23�S��}U��ƅy��{Sn��X�a�(��9;��p L�<�(��ۘ�b��>X��^W�Yr�"v��K`j�f)�+HU���!V�"#�f�*9��!M�xD�|}�p�dM��Ց�M����Ȋ9���I$����p�|��i�� w��>7v;�>?���3>w�%��Da����!�U�����B���U�%T@�������|T�E0�1_�W�a��<b�y� B^�����A4�JIN�U��)��.G�N�7�w��$��?�-ҫb ���C?�qY�W���@|'}=\�:��3����k5��I}����[����Y�4#%��\��;����2�6�2.i��*�>�u"v,���������������rxzғ���LG]\��w��"�����N����(MB'�����`ӗ>p��ױn�d��4��!	�����NH�r�(�x�M������NڜE�3��?������*1�m��� `���V	A��Ku��s���.dݒ��	�z�`>��BT��?;�S���)�T�$��x�!@(��6�%;늒g	�]x{ah�́�ڐ��C��W�A�PȮB�Ћ�2wR��XqI{~#őq�Kc����LA��I�T��Ys3��s�mmM'��v�$�5d�B��ڍ��M���~ѭ��'�\�Fg�X2n�� S��{qMKH�s��&;�m���GE�O�!���y!����՚��䖨|A�[�@�*��*���LRRcM�g%�05����ȉ̽+���9�Q[T v�M�=�H�_yr�`_}2�����&��� ���Ȋx��L�*%��a��h+1\pRƌ��<��z�O�:�%Ҍ�Uы��I\`��D��"�|��_Ȳ���Zg
�Юd��!����x��A�a�g;U���N���<�˳��I "E L{G�Ԉ&z����z¡����Q*s�.�*Ci�[�K��G���u��Àt%9�����A@m	��L���tfLU�b3�Aґ���aV�E0�rɌ�yI�M9�m�riN&�����W�ڳX<;�·痨?;h�:��e]��D����Cm�=qv�Ԙ��7^�Q:bF��Ў�Va  ��f�n<d��і�cT4�\���Ž��&B�o������V�[ɢ�T�����@���ѿd�/bW$·�4,�4%?��
գ���ф�İ�jF�5⶞ĺ�L�t��Lİ7S�!�q"Ƭ��h���M�1�I�
=�=]p���%�{[�-� P	AE:��-������?I�]�_^r1f�<;�d��Jƴ���N9�=t�sI�)�=��G��q� 3'����hl�k�ȫWt��� �w(��~0�~����cz��<�_7D9?'�H��O,�DH�:�M�%��8q� ��W�,���d�t� n	�	Q]��ld��B�ݔ��1N��D�|��,�e����9?SR���*�24�
1�\cA�Vݓ6�lZ>/����I,�Ô��'P�y�>1��#[���h����NZ_4��4d|[��� �� 'R�%Y%����&C3��CFr4�a)�oM.5��lN��L㜤���gb��S�S���+Иn �).v�B�2���Q�aP{Rq��d�n3m��j��S]ŲT��m�@�ݿ���0YOHn,?zn�7L�*��{N�M��pih΅/Ea���Z�p��4��S�����,�IF�:��d3�o/\ѥ�������E�&>c���g��0����9g�4�qx	�w�xD�o!���D^X �#�J*zr�C�2͆�Q�ڛF 1�qCq�p3�`A��t�i$�Z����G��Z�H$3���ȁ]�Z���F��#��ei��0ǴMo���k¦Rɶ�|�����m���c}04>��I^6�t�!��Q���nin�B/���oot-p<���\oۃ�)���^���MӉXsK"��&s.AoDq~X�� r���tI=��Z��������렸<��D��}��ߵ�dL��֋�����Ze~Ql�>m[�ze�LN<�Aw3�Rs�͙C�Y��Th�ʅv�C�K ��%]˚���Z,H���y�q*yHeAf�9¢�#ԥ�{�A[
ia�qw!����Rٹ���[���~3M�ҕ*�7���7z��˕K��û_p�A[n��=�E-����jE��-�K����S��1;��lӉӍN�#�}�NW�L�83K���\>q�D����@<-?"bV5Γ����^�N���b��e��z��&�ԏ^���"}F�<�,L,{�,��~S����2�?�0����̙�X����HY��z=,��j����c���Hi������q�Fnm첋���B�x��\сDF���%�6ҷ����ء�6D�b��ݒV��ڭ/���H�=��'��/�ǽl��d�B��4Q��*nY�jd�2
�Tp,ߡ��c�dܘ,\#Q�.�>�vD���8�1�Ȥ{Z���oX~�դ&S��c�Ct7�k�B?C�)aF���Ԭ~�e�FOuwr����V1����|&#Y�4���<d��t��T]�T�{Ee!�]W.4!���M���Sab4�P�GS�QvK@�p ߧ���e�~(N��vZi�5:??�^�MLx|�1��A��S�~l���
/�=I�g�B5���Nx(H�t'��-fv�b~���OM)t���:��oȏ�u� �l~��2q/�$�Ʊ�ޞ�L��e6kz�K����ꎸ�H�OMϦ��sǔ�l���|�hs,�bG�͐�<��E�}ȷ�L�]Cw�>3��_x|tv4<�Z���PIk�d����%�_gH�lB3E�J�D�I�5[��
�2��E�#�Uqӭ��VM��Ŏ��dJ:ap�i�GT?�U"K�B��i�2t�_�u��.���m����2/�&�s�n���ɨYeA��N��+*�Ů�����lU�-��Ш"fF�d'�߁�|_�j�9 ٫��������X<W����! ]t�������Ň{��������ߜ��P]4���[{�����3���x�f3�j��4��M���f���h�S�$��ɥJݬq���3q��T2�2�6w�����*���7��鸃����R�m#WZ-Ō��y|�7߯B{V��n1R~R�o�	����FWZ�H!�7mJJ��^�\�P\���|��'��/	�T�$9uV:��UѢ+L�|[�J��Û���߭�F��Q�#�/��R� �����i�9\R�/?X�.�9/|H��f;8.��d�56�6���-5����#�����5ܹ���,��d�D�t�z�͐��p��L��X-���� Wm�[!YJ�V��}���k������{������SXw����4�E1��t���A��irz�em���C�r'�_UH��5�jd�Xm�\���^9�!.`�K��Fb� T&��PJ�a�B���� >04㐃�V����eǨ�dǨ&����tx~�m�7>��8|����$��?34璞]���WM/�M[�n�N٦�Į���S��'H"�㫁� ��<�'c�?�HOWyY�x T\-=�X����sgX�J}�/���w6��o1�x�_�nmG���L��!=���� �|������Q���,I�J��DZ�a9��P���r�~�5ɣ�`�(Kfnb5�;V��e����i��R��9�A�Ƨ#�jKv�t��9���i�E(uZ����0i��J69�xR7V�i�o`�<x�/\�<8J�ӓ�sn{�3�0�~���mU���c��ğ�d˟ۤ��*��:9����^71��$x�C��À#�I�3��uI!�N�
�8�ُ����L�츛�u��B v�jz%P9���1}pr V   �����q-�S
?_O�-{J�3��)W�����*�A7RW�D�ܸ�Ե׬ޟ�p9�LwNi/��0n/��0���}�2~���_F�      F   
   x���          @      x��\�r�F���+jG;�M�!�ҝES2o�2G����(6A��������Wh�����?�/��dV��IwIm�n���S�'O&|����͍9}�i�P4��Ն��ۢ����l[��1������Զr#��&�T����4���t<�kn[|����gE�������޼9����+s��뛳K��������|�?2;�E��ܜt����W4/:W�Ɯ�5;�ͅ5YW�O�im=��5#��ʛGm�`��M�����k����0�˻����9�h�MpS<��dV��3]�,>�]sa�CŤÇ̗�.���`�Wi�"�)KW�.�lm'�iL����7�|��-fEnM�3ؽ�#��}炫�O�?u���*r��ğ��ϮhO۰�HV�5fܕnfq���CS��]sV�S{��`n|h��T�����ye�,G���bA��� �Y����3>5Nn\�XYWin�m�7����L�k9���u��ۊ3��|4r��hM�'����{�hy�9-Z\U��毦A�|,��V���5�6�LH�\#�!<����[�.%G�E�1���e�6�%�~(��e7��=s�F���O'8�����y���bd����7�og`Y��3w�Ui[��~�p���� EY;
�.>y!����;iˈ�]s��oab�-�r/ƞ�v[��n.�s[s�ո¥��VPz��W81w��
Z�rZ8�H���u�v����@���g
L,6#OΝ�.��a�y0W��8�&��[��]�n��6���	��o^O�Ko~�e$P�w�#��� ��ib�����9P��wl��G����J�����4Y�,w`�x��6c�d�h��%-ܴ�BG�1,�ʷ��m$�^w��sst�k�3>�J�y���|	�LF?^9������]�.�HCY|"Ŀ���"Z�,��=dD>�F"	96yΠz'(����ga\E��0�o	j����O���b�r�d
H��O�h^��7�#s#���Go�:�.�W����
��-����i=(F��y;d�<�CЗ� ��&��T(����yy�o� �x�k:�)a������;�;0�MaU�r#�6l�S���D���co[�3��b;�,��4_�ʚ�-�9�y���;nU��_��Fb3�2��3�������?��G"�D�u��8d:)���P	Y��d��=|n򓝋y�b���A3�؅���~��K߸�wE����q�#�d�7���5�3|��ů�F=��m�Y!�l��I������/w� �;e��	�:�W�I�T�6��� �
��= �"�����ϖ��̎�d_�e���:7Sjp�5Ÿ�b`�����)��8?A�E�%���k���*�le�^��D՜W�\���n������]sc�q���J�V�qr~�������;J#~2�G��Z�q��Z�hC1��<|]�!�$/�86�+���Qn�;����x�:;�X8�{)Tм`��ٝ�3��J�8Q���s"��OX�Dn۠�cNTز����7@�ɸt@�TM8wj��I�8J�֓��7.8+J~�-+E���0~!1�)Z�������O�yo<	�TnFV�y݉԰�{[�i�Q���wL�$F�/�1��E���!0��.T���B��.��?�_�_.F�i�&���T������;�)��@c5�IGy�z��_7�	W��P�%?E���'�>N�>�a�U���vV�E�a�Y���N���K;/$�/o��[��k|��W(h&�B��Kٗ��uҡ����o3��eV�\��"�� ^�a?���F=����
܏��)*�<~%���x���A%��^Y���Aj�nۻHs�蓚�0(����*b���ߚ���F��w��,"Z��M
-p��e���W����(�U�Q�d˱c�\:Y6q��K�@����pa[d����	�Ϗ�;f��;K����ʷMºT!���Oֳ��!��$�/�vHx��3$^+D��{�*���F1�
׍��_�ni�2�k���UQ"GR�X����#j�)v�Hj�@��ZySR	�1 k�J^$N��=h�u�|�Gb��	h=�� x�&�Z�g�L��Fp���;�Y�s
p�93�1�g�tԃ� ה���W�(Dy�	3�X5c��#�v<5�qckh��W��55L5sܣ��mq��������>U�K����ߞ���]ķ����"@J��:�!J����sU��:FiI$M��J�8����R%��8is`_�˴��QN��O|QQ���aW���KG�f�Mb��_p��Հ��(�FI��Lc�$���d����Z]�0לּm9-jzm4,��&�[xpbX;��	Tg*#��{̎L�v�h�J!՞�<���ۢ�K���>�D�iDw*��#��{���9�n���D��'ۤZ���TU��V#��-	l"�@46C{gKSR�����Y6�R{\ōq���(�'�t_D�E�+wV�����Ef�݀pK�O��0|Oj�eQ�*�ӻ�:y_B��0*�c�1���$�
O�E��*-�1&�ڲ�6S��-�Ii�:	��D�2nZ���·egHr���fҦ�JnE3��{�Ĉ�!�=i;�eQ��n�j�t������(<�E�S�',�֞I'�`����t��\kK+��lu�'�l*r	.U��X��3�,�F����,j�ˏ;f�MB�����~�b�|e	�Q�B�~�g@!�e����o�@!�-Cȋ���F�?,�ljS�'[��4�$'���
(+��+�ʾ&�<�Q��F�e7�)6��'�Bϕk�����~�5�"��07��,�����$KpH��7U����Ȑ��"T-%���}j���ʏ��y�fF��
����L�Y���yq���hQ'i�`'N�W���4����8u�b�����;���/����.l�D_�*���UV2�r���`4�0�� �?��RZ�������e���t�v���#*��d���l��v6m	*��>��3nX&*��aE!MV!\>MN���:��l̢\(K��d��}�5s9�ᅭq7S������b��J�Fx>S��ue��)Q�����
���8Z~s�@�Uc[6@ ���ƥvͩ��}�L1R�!8�0!~)�!@�i[K�@r���7�a8R��jCAD�����~E`e�.����dV`�u�8�Jƕ�y�V�{r�a� �S�XA���Xr��
�,�J���"ZJ[TW��K�Fk ����P����!zW-��pz�l��3��"w�T�v�ih�w(�4"������{�ɑ���Q������P�n��}��c�s �Q��f�.W�����@0�o�ecT�fz�5��f~l�1qq<�����X=II��R�&5d�j�����F��#��Y)E'o+�w��4v��b� r�]*k�X��8F~��qɜ"��+BZ�<UY�@�ǧ+A6nd���hX&�؇�O�'L��4��7J-��v�}�x�H����;�2ø�����QSr�+#i�,�>Ď�PE*HenJ���t�ծ�ʈ��"�b+�	���a�ƞjqJ7�6�����]s�']-IEtk�4N<eZR9��j��8{	@[.wR�H"P"�<T�1XN�e�0��(~�S��T�3t����{r� ��g 7�M]�3a�K#CI+�$D#�5n%i0�6��r��L�[D��
]�d^���P�����U�7̡���K����3��-��Um�k�uMi�{��|)�W2쩯A��ȓI�]s��E�����d���?�;��'��Ň,R�����F��/�O�����J7P�ְ.�=?<�{�8`��s�%���<�=V�Eºl!�̤���2�H'Y���߯RQ�t��e���Z�.n���
�>D{F?��-�	��vM 8�F4��Sn��g9�)iKd��b�_\W'#9�2fl�ӂ�S'c^B�P�|Ru��i���H�_G�#�i�$�^VZ���"�»Ԯ������ӟ#��� ����RH:<>�Î)�ؗ8cZ�U?��eq��W�Κ �  �A/'�m�f֮F�@%���l�����8c�����t�M��]�FEW�#��[Y>��^j����i����F�ɶrc�:��	h)	��8�N�z��f�j���ļ>҉�]�d��5���_���n'�KД��П�\YK�5�C���m�B4)aKW�	iAn钉+��?:~�ءx����X���/�놝]�gÚ/,ƾ�h�c�	B�-P�y���A;`����D0�bw@�M�u�I���u\��L5Xuz?fx��_���\�rfc���~>,>�W��*����.�����S>��-Z��ߦ�q�?��7�)�����ݩ�� =+��-t��فY�s+uŧ"_</���a�w�=R�Qƿ��u:&~�P�DOې��|����Ƭ�����%�{P��5$%[�~e��MLl꼆b��bMY���n�{�O�H0n7g��!��kj%o��u#sv'o���}�����t ������C+�/Y��)o�q�H��s��|��x_"<�\��4tX��;�y��w����iw�KJ2@7J�l��ZF�X?kݳ�����z����z���/gm�������沀!"ө�=BV�S�T�r%ʱ����1O��ߘ_ޝØ��9-���SH�)�������!���F'�x�n��k
�cH�
��
�(�%�9	C����ά�E5E�_�Иo�G��+��9�;8�~�����������2�j���3��c�ؕJg��/J���B(3���[^�M�K����g�T��i�r�NގRU/]&'�ި�T<���&Q�s��W���b�d��lc`P\Q�M�%��N��)Z��ʋZn�7���"�pJy�m�j�K�^=�<]���������Ļ����Q٬S'	ܫ��3 ��W��s��V����ެd8D���e
��1��kA� ) �;��jD�Cz�⨳ͫ"X�h暄�4~�4`�����`.m��tdi�5��7o]&��
j�2����J���7c��)�� �f4��2̩J�&s�T��Y�VcI��8����$�_��_i����l񀒆�f���Ŏ���?�Y-$��C���c�0{:":n��u�⍛.��;<�>���)
��?�f��̥��Iы/&�9�Эx�nP��e%��W�8l$/HכB���-F����mzU��y�B��������Wn5F���9m�3JD��oC+@��Ft�h�\_N�~�4����7u���	n+���$tS��ÔJ���C'0K�Ln�� g�ژ#Vv?�]����F����{W�şMJ|�O��t�f�/H�ې���,}Iz��k�ǲ�}�����pZ[L��-uqt[�]�����
?�h�:Zz��=rvʦO��/�}�{���H��1�J���5n��,�9�|�́������I��x,���D�|��e�޹6Ϯ��ĸ8Y�5���!	�����E�M�������9�_�^,;�~o����<^P��y� ฼�Nu��[D�-
����% YnS&��h}l$�X��C�����S8�RC�ԇf�g|���d���"�V�/l5�s���R(��.<�%($� ��,W̡j�P�v�����I)=@��6��Z&@����Ƒ�y���6��~設eq�����G�9���rj�N_Y�e��d�i�^Y_���'@��7���)�      >   Y  x��XMs�6��Wত��e9�&�'Y+;ڒ&*IV%'W���" �2�7:��Öo�����9�H�\�*[���~��ǳ���Ku<��Eu)����*�:�h�~i�6Q}c���]�5�[���m;�]�lC�<�?�\Rg�U�\����x�Q]�vqyt��N>��7��y���Q�`�Σ{�5�*�S�Z�Q������qs�`���HJ�:�ᳳ�ne�)�|��/���6�w�J����"|W�V�W���9]'���"�s�y�0�����ګ�j�Ǫ���4N[x�{2������3ϩְw��3�w��"���/ġ�{T�h��ql������0Z��T�n|���/���kk����O{?"D�	Z�����E8�y̾�9�"8���W�'�
���qm�FQ���z�:XM��rv^ۦz= �R|��%�ڽi+�}:9��_���^d����;�/ɑZ;/	w>x��)�$4'�`ˡ`p0����!�5�ԕ�����f�o�U�Y�[��Mc�� 0e�}d<^����Fd��fn�{$����*��(��lg4�ɞ��Y�h��) ?����"2�E�}� �K�e��e �H���9/	8[���l��*Ĥ0�
b�E��-�<�x53U�;wOᐫ��h`��*��)�DD�F���85W�X��;�̒sx�3���k�P�����:$A1y�ڍ�� e�FU1�+՘h:��I`�eF�M%���{�R����%�)A\�������y{�4jƜ� �]'<�9�GF�fL��T�Hմ��f��� F��h�!%��� x�lj��[��Y���(�}OO�+�����&���Uơ���J.s3��{�%2�M1ap)�1�7@�@�ã]�34��.����뿂���T�&	;����ץ�(����}��^����;���D��@0�0<�P����c탅��5!1T#�����6y*M�uC[FԶ��!�/*����eҩ��w&ר�h@�u��d�*Y'�D�Mes�, rK�Ӎ��CG�DI"�������9@i4��#q�E0Z2�K떬�`�.m�4@[X�6������U>dq2e�*Q�mh��_#����T�&mE�0;����5Ή_�2nM��!�;�{������ݛ���?�?���I��{��x��ˬ�z�7k2m9�9f5���Pl��T�.}͵�w\�\����8� ��i�n�*{����T����!�u��A0�w���H#o��(\��R���k���P��� q+:pxčs�<���ek�"���]�#kw/F��	�2�dݻ�a��s�pB@sry�!Y2��jN��$q��1�[V��s��m�����VЎ8xQ�8zx���Y_mS��H ��VX	[�p��W]�=͚�Qʹm<U9�P�i|���+�K��s*�Tm�E,�E5�R�Dq��B6P�� ����p��<�@g�t�S*��qeɚxEL��Y���%7EεFN���m>���Қԥo�>k��q�y��<2H.�M\�;Պ8��p`�Ջӏ�rl�m� s�6d��n��C�� �܎�!Tr��M��9�9!�x�;�m�H1�Ϥ$M�R�+?g�÷^�p<J;�9��b?��N�b���	յ��2{s~n�Az(b���D�զ����K���6�C��s��[?N��gr��'B����=ah	k�rdL����L6*���h�gTҐ���dc�����L����
�O³�4.4��jc<�]B����X��g�DK@ü+����o����,�֊�5лV�-�E
�r(���2��6��Sm��/[+�T��ޥ��p,�=���ݩ�ـ��GLFR>�p0P>o�<�b���LO�Γ\��Ðn��^A>>�S_?��Ǭ�U����7{�(��Q�ir�\�{�2lҢoє�W�+ƾ�9��<`��)$��<�/R>�L���dW��è��!�^6�ө�P`�W&�Yló��@M��p�����'>rL��V�[Ì3���[�,5�F� �4G���u�f���?���`���Xݰ��jn��<`�poE�dK���]�^���      O   
   x���          J   w   x���v
Q���W()-�,�͏O,JO�+��K�+(-J�H,N-V��L���tRK�$P��8�HS�?�5(�����]!828��W!��'�B+h�(�����(jZsqq @�%5      N   �   x���v
Q���W()-�,�͏O,JO�+��K�+(-J�H,N-�OL.�,�,�L-V��L��I��+u�D������kP������Bpdp���B��O�+�V�0�Q !Mk.�A�#�k��k���1��� �Q�Q      L   ~   x���v
Q���W()-�,�͏O,JO�+��K�+(-J�H,N-���ON,���+V��L�/(�Eu���`LFS�?�5(�����]!828��W!��'�B+h�(���5�瀻��#�[�� ��^5      H   
   x���          D   �   x�=��
�0Fw��n*h�g�Th(���)�z��FI��_����,�u=pѷ@�3~ݔv3Z2V���Cb&V�V����E{R��:�d6�&MgN��?ShG�u����G����E	I�A���;�L�)�*�:/꼬�CӤ�(�~ 9�     