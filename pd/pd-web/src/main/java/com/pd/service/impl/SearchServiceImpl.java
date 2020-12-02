package com.pd.service.impl;

import com.pd.pojo.Item;
import com.pd.service.SearchService;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SolrClient solrClient;

    @Override
    public List<Item> search(String key) {
        SolrQuery query = new SolrQuery(key);
        query.setStart(0);
        query.setRows(20);

        try {
            QueryResponse response = solrClient.query(query);
            List<Item> itemList = response.getBeans(Item.class);
            return itemList;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }


    }
}
