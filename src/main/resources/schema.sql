CREATE TABLE IF NOT EXISTS product(
    productId INTEGER PRIMARY KEY AUTO_INCREMENT,
    productName TEXT,
    price DOUBLE
);

CREATE TABLE IF NOT EXISTS review(
    reviewId INTEGER PRIMARY KEY AUTO_INCREMENT,
    reviewContent TEXT,
    rating INTEGER,
    productId INTEGER,
    FOREIGN KEY (productId) REFERENCES product(productId)
);