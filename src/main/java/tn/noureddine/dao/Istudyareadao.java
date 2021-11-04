package tn.noureddine.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tn.noureddine.entity.Index;
import tn.noureddine.entity.Studyarea;

public interface Istudyareadao extends CrudRepository<Studyarea, Integer> {
	@Query("SELECT i from Studyarea i  where i.id = :std")
	Studyarea getstudybyid (@Param(value ="std") Integer std);

}
