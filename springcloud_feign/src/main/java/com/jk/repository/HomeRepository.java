package com.jk.repository;

import com.jk.dto.Content;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.data.repository.CrudRepository;

public interface HomeRepository extends ElasticsearchCrudRepository<Content,String> {

    void deleteById(Integer id);
}
