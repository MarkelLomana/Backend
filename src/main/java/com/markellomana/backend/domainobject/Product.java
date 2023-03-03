package com.markellomana.backend.domainobject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;


@Data // getter, setter, toString, equals
@NoArgsConstructor // automatically generates a constructor with no parameters
@AllArgsConstructor // automatically generates a constructor with all parameters
@Builder
@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" }) // JSON serialization error not to show
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @Column(name = "code")
  private String code;

  @Column(name = "entryDate")
  private Date entryDate;

  @Column(name = "location")
  private String location;

  @Column(name = "type")
  private String type;

  @ColumnDefault("true")
  private Boolean active;
}
