package com.pd.pojo;

import lombok.Data;
import org.apache.solr.client.solrj.beans.Field;

import java.io.Serializable;

@Data
public class Item implements Serializable {
    private static final long serialVersionUID = 1L;

    @Field("id")
    private String id;
    @Field("title")
    private String title;
    @Field("sellPoint")
    private String sellPoint;
    @Field("price")
    private Long price;
    @Field("image")
    private String image;
}
