INSERT INTO empresas(epr_logo, epr_nombre, epr_ruc) VALUES ('', 'LabHidro', '0107438434001');

INSERT INTO paises(pais_nombre) VALUES ('Ecuador');
INSERT INTO paises(pais_nombre) VALUES ('Mexico');
INSERT INTO paises(pais_nombre) VALUES ('EE.UU');

INSERT INTO provincias(prov_nombre, pais_id) VALUES ('Azuay', 1);
INSERT INTO provincias(prov_nombre, pais_id) VALUES ('Pichincha', 1);
INSERT INTO provincias(prov_nombre, pais_id) VALUES ('Cañar', 1)

INSERT INTO cantones(can_nombre, prov_id) VALUES ('Cuenca', 1);
INSERT INTO cantones(can_nombre, prov_id) VALUES ('Gualaceo', 1);
INSERT INTO cantones(can_nombre, prov_id) VALUES ('Azogues', 1);
INSERT INTO cantones(can_nombre, prov_id) VALUES ('Quito', 2);

INSERT INTO parroquias(parr_nombre, can_id) VALUES ('Sidcay', 1);
INSERT INTO parroquias(parr_nombre, can_id) VALUES ('Ricaurte', 1);
INSERT INTO parroquias(parr_nombre, can_id) VALUES ('Sayausi', 1);

INSERT INTO direcciones(dir_calle_principal, dir_calle_secundaria, dir_referencia, parr_id) VALUES ('Av. Americas', 'La Bomba', 'Fachada ladrillo visto', 1);
INSERT INTO direcciones(dir_calle_principal, dir_calle_secundaria, dir_referencia, parr_id) VALUES ('Av. Americas', 'Parque miraflores', 'Fachada vidrio', 1);

INSERT INTO sucursales(suc_email, suc_hora_abre, suc_hora_cierra, suc_nombre, suc_telefono, dir_id, epr_id) VALUES ('labHidro-C@gmail.com', '8am', '8pm', 'Cañar', '0963728461', 1, 1);
INSERT INTO sucursales(suc_email, suc_hora_abre, suc_hora_cierra, suc_nombre, suc_telefono, dir_id, epr_id) VALUES ('labHidro-T@gmail.com', '8am', '8pm', 'Tambo', '0974993644', 2, 1);

INSERT INTO personas(per_apellido, per_cedula, per_email, per_nombre, per_telefono) VALUES ('Pinduisaca', '0107394595', 'ivanpindui99@gmail.com', 'Ivan', '0986348234');
INSERT INTO personas(per_apellido, per_cedula, per_email, per_nombre, per_telefono) VALUES ('Cuzco', '0107394596', 'ivanpinduiCu99@gmail.com', 'Arturo', '0986348233');
INSERT INTO personas(per_apellido, per_cedula, per_email, per_nombre, per_telefono) VALUES ('Piña', '0101963338', 'carlospin99@gmail.com', 'Carlos', '0986348222');
INSERT INTO personas(per_apellido, per_cedula, per_email, per_nombre, per_telefono) VALUES ('Bermeo', '0106394529', 'irmaBerm99@gmail.com', 'Irma', '0953992253');

INSERT INTO usuarios(usu_clave, usu_usuario) VALUES ('1234', 'ivan');
INSERT INTO usuarios(usu_clave, usu_usuario) VALUES ('12345', 'juan');
INSERT INTO usuarios(usu_clave, usu_usuario) VALUES ('123123', 'carlos');
INSERT INTO usuarios(usu_clave, usu_usuario) VALUES ('123987', 'mateo');

INSERT INTO roles(rol_nombre) VALUES ('admin');
INSERT INTO roles(rol_nombre) VALUES ('servicios');
INSERT INTO roles(rol_nombre) VALUES ('ventas');

INSERT INTO rol_usuarios(rolu_estado, rol_id, usu_id) VALUES (true, 1, 1);
INSERT INTO rol_usuarios(rolu_estado, rol_id, usu_id) VALUES (true, 1, 2);
INSERT INTO rol_usuarios(rolu_estado, rol_id, usu_id) VALUES (true, 2, 4);
INSERT INTO rol_usuarios(rolu_estado, rol_id, usu_id) VALUES (true, 3, 3);

INSERT INTO clientes(cli_fecha_nac, cli_genero, dir_id, per_id, usu_id) VALUES ('1999-03-30', 'M', 1, 1, 1);
INSERT INTO clientes(cli_fecha_nac, cli_genero, dir_id, per_id, usu_id) VALUES ('1997-10-20', 'F', 2, 3, 3);

INSERT INTO empleados(emp_cargo, emp_genero, dir_id, per_id, usu_id) VALUES ('Ventas', 'M', 1, 2, 3);
INSERT INTO empleados(emp_cargo, emp_genero, dir_id, per_id, usu_id) VALUES ('servicios', 'F', 1, 4, 2);
INSERT INTO empleados(emp_cargo, emp_genero, dir_id, per_id, usu_id) VALUES ('Ventas', 'M', 2, 1, 3);

INSERT INTO modulos(mod_nombre, rolu_id) VALUES ('Modulo ventas', 4);
INSERT INTO modulos(mod_nombre, rolu_id) VALUES ('Modulo servicios', 3);
INSERT INTO modulos(mod_nombre, rolu_id) VALUES ('Modulo admin', 2);
INSERT INTO modulos(mod_nombre, rolu_id) VALUES ('Modulo servicios', 3);

INSERT INTO tipo_pago(tpp_tipo_pago) VALUES ('Efectivo');
INSERT INTO tipo_pago(tpp_tipo_pago) VALUES ('Deposito');

INSERT INTO servicios(ser_caracteristicas, ser_imagen, ser_nombre, ser_precio, ser_estado) VALUES ('Pruebas', '', 'Pruebas quimicas', 200, true);
INSERT INTO servicios(ser_caracteristicas, ser_imagen, ser_nombre, ser_precio, ser_estado) VALUES ('Medidas', null, 'Mediciones', 500, true);	
INSERT INTO servicios(ser_caracteristicas, ser_imagen, ser_nombre, ser_precio, ser_estado) VALUES ('Servicios', '', 'Servicios generales', 320, true);	
INSERT INTO servicios(ser_caracteristicas, ser_imagen, ser_nombre, ser_precio, ser_estado) VALUES ('Estudios', '', 'Estudio de calidad de agua', 150, true);	
INSERT INTO servicios(ser_caracteristicas, ser_imagen, ser_nombre, ser_precio, ser_estado) VALUES ('Revisiones', '', 'Revision de sistema de riego', 100, true);	
INSERT INTO servicios(ser_caracteristicas, ser_imagen, ser_nombre, ser_precio, ser_estado) VALUES ('Predicciones', '', 'Prediccion de caudal', 250, true);

INSERT INTO productos(pro_codigo, pro_descripcion, pro_imagen, pro_nombre, pro_precio, pro_stock, ser_id) VALUES ('PRO001', 'Sistemas de riego', null, 'riego', 250.50, 150, 5);
INSERT INTO productos(pro_codigo, pro_descripcion, pro_imagen, pro_nombre, pro_precio, pro_stock, ser_id) VALUES ('PRO002', 'Sistemas de pruebas', '', 'pruebas', 250, 350, 1);
INSERT INTO productos(pro_codigo, pro_descripcion, pro_imagen, pro_nombre, pro_precio, pro_stock, ser_id) VALUES ('PRO003', 'Sistemas de calidad', null, 'calidad', 550.50, 50, 4);
INSERT INTO productos(pro_codigo, pro_descripcion, pro_imagen, pro_nombre, pro_precio, pro_stock, ser_id) VALUES ('PRO004', 'Sistemas de caudales', null, 'caudal', 400, 100, 6);

INSERT INTO cab_ventas(cve_fecha_emision, cve_numfactura, cli_id, tpp_id) VALUES ('11-05-2022', '000001', 1, 1);
INSERT INTO cab_ventas(cve_fecha_emision, cve_numfactura, cli_id, tpp_id) VALUES ('11-05-2022', '000002', 1, 1);
INSERT INTO cab_ventas(cve_fecha_emision, cve_numfactura, cli_id, tpp_id) VALUES ('11-05-2022', '000003', 1, 2);

INSERT INTO det_ventas(dve_cantidad, dve_descuento, dve_iva, dve_precio_venta, dve_subtotal, dve_subtotal_iva_desc, dve_total, pro_id, cve_id) VALUES (3, 0, 12, 600, 600, 675, 675, 2, 1);
INSERT INTO det_ventas(dve_cantidad, dve_descuento, dve_iva, dve_precio_venta, dve_subtotal, dve_subtotal_iva_desc, dve_total, pro_id, cve_id) VALUES (4, 0, 12, 1000, 1000, 1230, 1230, 1, 1);
INSERT INTO det_ventas(dve_cantidad, dve_descuento, dve_iva, dve_precio_venta, dve_subtotal, dve_subtotal_iva_desc, dve_total, pro_id, cve_id) VALUES (1, 0, 12, 550.50, 550.50, 725.30, 725.30, 3, 1);

INSERT INTO det_ventas(dve_cantidad, dve_descuento, dve_iva, dve_precio_venta, dve_subtotal, dve_subtotal_iva_desc, dve_total, pro_id, cve_id) VALUES (2, 0, 12, 800, 800, 1014, 1014, 4, 2);
INSERT INTO det_ventas(dve_cantidad, dve_descuento, dve_iva, dve_precio_venta, dve_subtotal, dve_subtotal_iva_desc, dve_total, pro_id, cve_id) VALUES (4, 0, 12, 1600, 1600, 1845, 1845, 4, 2);
INSERT INTO det_ventas(dve_cantidad, dve_descuento, dve_iva, dve_precio_venta, dve_subtotal, dve_subtotal_iva_desc, dve_total, pro_id, cve_id) VALUES (2, 0, 12, 800, 800, 1014, 1014, 4, 3);

INSERT INTO citas(cita_direccion, cita_estado, cita_fecha_fin, cita_fecha_inicio, cve_id) VALUES ('Vargas machuca', true, '2020-06-21', '2020-04-06', 1);
INSERT INTO citas(cita_direccion, cita_estado, cita_fecha_fin, cita_fecha_inicio, cve_id) VALUES ('Borrero', true, '2021-01-10', '2020-12-26', 1);
INSERT INTO citas(cita_direccion, cita_estado, cita_fecha_fin, cita_fecha_inicio, cve_id) VALUES ('Maria Auxiliadora', true, '2020-11-17', '2020-09-11', 3);
INSERT INTO citas(cita_direccion, cita_estado, cita_fecha_fin, cita_fecha_inicio, cve_id) VALUES ('Tarqui', true, '2021-09-30', '20201-07-03', 3);






