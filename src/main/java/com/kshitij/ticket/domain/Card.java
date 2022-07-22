package com.kshitij.ticket.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card {
  @NotNull(message = "Card number is blank")
  private String cardNumber;
  @NotNull(message = "Owner is blank")
  private String owner;
  @NotNull(message = "Expiry date is blank")
  private Date expiry;
  @NotNull(message = "CVV is blank")
  private int cvv;
}
