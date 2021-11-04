package tn.noureddine.dao;



import org.springframework.data.repository.CrudRepository;

import tn.noureddine.entity.Document;

public interface Idocumentdao extends CrudRepository<Document, Integer> {

	
}
