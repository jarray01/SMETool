package tn.noureddine.dao;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.noureddine.entity.Station;
import tn.noureddine.entity.Temp;

@Repository
public interface Istationdao extends CrudRepository<Station, Integer>{
	@Query("SELECT p from Station p  where p.id = :id")
	Station getstationbyid (@Param(value ="id") Integer id);

}
