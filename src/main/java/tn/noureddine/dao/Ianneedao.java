package tn.noureddine.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.noureddine.entity.Annee;
@Repository
public interface Ianneedao extends CrudRepository<Annee, Integer> {
	@Query(value ="SELECT description FROM annee",nativeQuery=true
	    )
  public String[] getannee();

}
