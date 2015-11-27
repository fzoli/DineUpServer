package com.dineup.builder;

import com.dineup.dom.Price;

import java.math.BigDecimal;

public class PriceBuilder implements Price {

    private final BigDecimal amount;
    private final  String currency;

    private PriceBuilder(Builder builder) {
        amount = builder.amount;
        currency = builder.currency;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Override
    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public String getCurrency() {
        return currency;
    }

    public static final class Builder {
        private BigDecimal amount;
        private String currency;

        private Builder() {
        }

        public Builder amount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder currency(String currency) {
            this.currency = currency;
            return this;
        }

        public PriceBuilder build() {
            return new PriceBuilder(this);
        }
    }

}
