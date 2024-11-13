package com.example.powerAdmin.service;

import com.example.powerAdmin.entity.EsUser;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EsUserRepository extends ElasticsearchRepository<EsUser, String> {
}