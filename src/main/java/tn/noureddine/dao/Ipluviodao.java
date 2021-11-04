package tn.noureddine.dao;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.noureddine.entity.Pluvo;



@Repository
public interface Ipluviodao extends  CrudRepository<Pluvo, Integer>{
	@Query("SELECT p from Temp p  where p.station.id = :id")
	List<Pluvo> getpbystation (@Param(value ="id") Integer id);
}
