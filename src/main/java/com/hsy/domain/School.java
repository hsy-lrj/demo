package com.hsy.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
public class School {

   private String name;
   private String address;
   private String id;

}
