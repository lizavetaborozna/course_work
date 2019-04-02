package dto;

public class BookDTO {
    private Integer idbook;
    private String name;
    private String author;
    private String genre;
    private Double price;
    private String imageName;


    public Integer getIdbook() {
        return idbook;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public Double getPrice() {
        return price;
    }

    public String getImageName() {
        return imageName;
    }

    private BookDTO(Builder builder) {
        idbook = builder.idbook;
        name = builder.name;
        author = builder.author;
        genre = builder.genre;
        price = builder.price;
        imageName = builder.imageName;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private Integer idbook;
        private String name;
        private String author;
        private String genre;
        private Double price;
        private String imageName;

        private Builder() {
        }

        public Builder idbook(Integer val) {
            idbook = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder author(String val) {
            author = val;
            return this;
        }

        public Builder genre(String val) {
            genre = val;
            return this;
        }

        public Builder imageName(String val) {
            imageName = val;
            return this;
        }

        public Builder price(Double val) {
            price = val;
            return this;
        }

        public BookDTO build() {
            return new BookDTO(this);
        }
    }

    @Override
    public String toString() {
        return "Book{" +
                "idbook=" + idbook +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", price=" + price +
                ", imageName='" + imageName + '\'' +
                '}';
    }
}
