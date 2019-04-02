package model;

public class OrderItem {
    private Integer id;
    private Integer userId;
    private String user;
    private Double price;
    private String status;

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getUser() {
        return user;
    }

    public Double getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }

    private OrderItem(Builder builder) {
        id = builder.id;
        userId = builder.userId;
        user = builder.user;
        price = builder.price;
        status = builder.status;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private Integer id;
        private Integer userId;
        private String user;
        private Double price;
        private String status;

        private Builder() {
        }

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder userId(Integer val) {
            userId = val;
            return this;
        }

        public Builder user(String val) {
            user = val;
            return this;
        }

        public Builder price(Double val) {
            price = val;
            return this;
        }

        public Builder status(String val) {
            status = val;
            return this;
        }

        public OrderItem build() {
            return new OrderItem(this);
        }
    }
}


