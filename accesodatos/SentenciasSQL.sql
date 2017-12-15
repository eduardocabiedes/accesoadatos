/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Laboratorio
 * Created: 11-may-2017
 */

desc disqueras;
insert into disqueras (nombre) values ('Sony Music');

select * from disqueras;

desc artistas;
insert into artistas (nombre,Disqueras_idDisquera) 
              values ('Natalia Lafourcade',1);

select * from artistas;

insert into albums 
        (nombre,fecha,Artistas_idArtista,Artistas_Disqueras_idDisquera)
 values ('MUSAS','2017-5-5',1,1);

insert into canciones (nombre,duracion, Albums_idAlbum,Albums_Artistas_idArtista,Albums_Artistas_Disqueras_idDisquera) values
                      ('Mi tierra veracruzana',3.4,1,1,1);


select c.nombre, 
       c.duracion, 
       al.nombre, 
       al.fecha, 
       ar.nombre, 
       d.nombre 
  from canciones c 
    inner join albums al on c.Albums_idAlbum = al.idAlbum
    inner join artistas ar on c.Albums_Artistas_idArtista = ar.idArtista
    inner join disqueras d on c.Albums_Artistas_Disqueras_idDisquera = d.idDisquera; 
                  
