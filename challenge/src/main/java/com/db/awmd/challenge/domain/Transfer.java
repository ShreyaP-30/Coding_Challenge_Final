package com.db.awmd.challenge.domain;

import java.math.BigDecimal;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Data
@Slf4j
public class Transfer{
	@NotNull
	@NotEmpty
	private String accountFrom;

	@NotNull
	@NotEmpty
	private String accountTo;
	
	@NotNull
    	@Min(value = 0, message = "Amount to be transferred must be positive.")
    	private BigDecimal amount;

	 @JsonCreator
         public Transfer(@JsonProperty("accountFrom") String accountFrom,
                    @JsonProperty("accountTo") String accountTo,
                    @JsonProperty("amount") BigDecimal amount) {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.amount = amount;
    }
	
	 @Override
    public String toString() {
        return String.format("%s-%s-%f", accountFrom, accountTo, amount.doubleValue());
    }


}