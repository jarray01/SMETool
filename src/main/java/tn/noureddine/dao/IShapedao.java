package tn.noureddine.dao;


import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tn.noureddine.entity.Shape;

public interface IShapedao extends CrudRepository<Shape, Integer> {
	
		@Query(value ="SELECT string_agg(CONCAT('{\"type\":\"Feature\",\"geometry\":',st_asgeojson(ST_TRANSFORM(a.geom,4326)),'}'), ', ') AS geo FROM adm_me32 a",nativeQuery=true )
		public String getgeojson();
		
		@Query(value ="SELECT json FROM listejson a,shape s where s.nom = a.nom and  s.id = :id",nativeQuery=true)
		public String getgeojson1( @Param("id") Integer id);
		
		@Query(value ="SELECT string_agg(CONCAT('{\"type\":\"Feature\",\"geometry\":',st_asgeojson(ST_TRANSFORM(a.geom,4326)),'}'), ', ') AS geo FROM rfshp12012017rf a",nativeQuery=true )
		public String getgeojsonlim();
		@Query(value ="SELECT a.nom FROM listejson a,shape s where s.nom = a.nom and  s.id = :id",nativeQuery=true)
		public String getnom( @Param("id") Integer id);
		@Query(value ="SELECT a.geojson FROM listejson a",nativeQuery=true)
		public String getgeo();
		@Query("SELECT p from Shape p  where p.gouv.id = :id")
		List<Shape> getshapebygouv (@Param(value ="id") Integer id);
		@Query("SELECT p from Shape p  where p.theme.id = :id")
		List<Shape> getshapebytheme (@Param(value ="id") Integer id);
		@Query("SELECT p from Shape p  where p.id = :id")
		Shape getshapebyid (@Param(value ="id") Integer id);
		@Query("SELECT p from Shape p  where p.nom = :nom")
		Shape getshapebynom (@Param(value ="nom") Integer nom);
		@Query(value ="SELECT string_agg(CONCAT('{\"type\":\"Feature\",\"geometry\":',st_asgeojson(ST_TRANSFORM(a.geom,4326)),'}'), ', ') AS geo FROM rfshp12012017rf a",nativeQuery=true )
		public String getgeojsonlim1();
		@Query(value ="SELECT  g.f_table_name from geometry_columns g",nativeQuery=true)
		public List<String> getgeometrycolumln();
		@Query(value ="insert into listejson (nom, json)   SELECT x=:nom, ( string_agg(CONCAT('{\\\"type\\\":\\\"Feature\\\",\\\"geometry\\\":',st_asgeojson(ST_TRANSFORM(x=:nom.geom,4326)),'}'), ', ') ) FROM x=:nom",nativeQuery=true )
		public void insertlistgson(String nom);
		
		@Query(value ="SELECT string_agg(CONCAT('{\"type\":\"Feature\",\"geometry\":',st_asgeojson(ST_TRANSFORM(a.geom,4326)),'}'), ', ') AS geo FROM chammakh a",nativeQuery=true )
		public String getjsonck();
		@Query(value ="SELECT string_agg(CONCAT('{\"type\":\"Feature\",\"geometry\":',st_asgeojson(ST_TRANSFORM(a.geom,4326)),'}'), ', ') AS geo FROM dardhaoui a",nativeQuery=true )
		public String getjsondd();
		@Query(value ="SELECT string_agg(CONCAT('{\"type\":\"Feature\",\"geometry\":',st_asgeojson(ST_TRANSFORM(a.geom,4326)),'}'), ', ') AS geo FROM rf12012017rf a",nativeQuery=true )
		public String getjsonrf();
		//@Query(value ="SELECT a.geojson FROM listejson a where a.nom ='chammakh'",nativeQuery=true)
		//public String getjsonck();

		//@Query(value ="select distinct l.nom,s.description,s.datecreation,s.datemiseajour from listegeom l,shape s   where s.nom = l.nom  and   st_intersects(	st_transform( st_setsrid(ST_geomfromgeojson(:geojson),4326), 3857), st_transform(l.geom,3857))",nativeQuery=true )
		//@Query(value ="select s from listegeom l,shape s   where s.nom = l.nom  and   st_intersects(	st_transform( st_setsrid(ST_geomfromgeojson("+ geojson  +"),4326), 3857), st_transform(l.geom,3857))",nativeQuery=true )
//	@Query(value ="select distinct l.nom from listegeom l,Shape s   where s.nom = l.nom  and   st_intersects(	st_transform( st_setsrid(ST_geomfromgeojson('{\"type\":\"Polygon\",\"coordinates\":[[[10.228271484374998,32.52458414463839],[10.228271484374998,33.58350641838985],[11.3214111328125,33.58350641838985],[11.3214111328125,32.52458414463839],[10.228271484374998,32.52458414463839]]]}'),4326), 3857), st_transform(l.geom,3857))",nativeQuery=true )
		
	@Query(value ="select distinct s.id from listegeom l,Shape s   where s.nom = l.nom  and   st_intersects(	st_transform( st_setsrid(ST_geomfromgeojson(?),4326), 3857), st_transform(l.geom,3857))",nativeQuery=true )
   public Integer[] getlistespatiale(String geojson);
	
	
	@Query("SELECT p from Shape p  where p.copyright = 'vecteur'")
	List<Shape> getshapevecteur ();
	@Query("SELECT p from Shape p  where p.copyright = 'raster'")
	List<Shape> getshaperaster ();
	@Query("SELECT p from Shape p  where p.copyright = 'raster' and p.modele.id = :idmodel")
	List<Shape> getshaperasterparc (@Param(value ="idmodel") Integer idmodel);
	@Query("SELECT p from Shape p  where p.copyright = 'raster' and p.modele.id = :idmodel and p.study.id = :idarea and p.datecreation = :datecarte")
	List<Shape> getshaperasterparmodelarea (@Param(value ="idmodel") Integer idmodel,@Param(value ="idarea") Integer idarea,@Param(value ="datecarte") Date datecarte);
	@Query("SELECT p from Shape p  where p.copyright = 'raster' and p.modele.id = :idmodel and p.study.id = :idarea")
	List<Shape> getshaperasterparmodelareasansdate (@Param(value ="idmodel") Integer idmodel,@Param(value ="idarea") Integer idarea);

}


