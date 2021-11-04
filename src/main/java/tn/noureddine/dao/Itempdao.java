package tn.noureddine.dao;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.noureddine.entity.Pluvo;
import tn.noureddine.entity.Shape;
import tn.noureddine.entity.Temp;


@Repository
public interface Itempdao extends CrudRepository<Temp, Integer>{
	@Query("SELECT p from Temp p  where p.station.id = :id")
	List<Temp> gettempbystation (@Param(value ="id") Integer id);
}
