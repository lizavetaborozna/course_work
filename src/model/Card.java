package model;

public class Card {
    private String number;
    private Integer month;
    private Integer year;
    private String description;

    public String getNumber() {
        return number;
    }

    public Integer getMonth() {
        return month;
    }

    public Integer getYear() {
        return year;
    }

    public String getDescription() {
        return description;
    }

    private Card(Builder builder) {
        number = builder.number;
        month = builder.month;
        year = builder.year;
        description = builder.description;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private String number;
        private Integer month;
        private Integer year;
        private String description;

        private Builder() {
        }

        public Builder number(String val) {
            number = val;
            return this;
        }

        public Builder month(Integer val) {
            month = val;
            return this;
        }

        public Builder year(Integer val) {
            year = val;
            return this;
        }

        public Builder description(String val) {
            description = val;
            return this;
        }

        public Card build() {
            return new Card(this);
        }
    }
}
