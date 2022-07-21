package com.kshitij.ticket.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HallMovie {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @NotNull
  @ManyToOne
  @JoinColumn(name = "hall_id")
  private Hall hall;

  @NotNull
  @ManyToOne
  @JoinColumn(name = "movie_id")
  private Movie movie;

  @NotNull private Date date;
  private long reserved;
}
