create table script 
```
CREATE TABLE customers (
    customer_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    customer_date DATETIME NOT NULL,
    is_vip BOOLEAN DEFAULT FALSE,
    is_deleted BOOLEAN DEFAULT FALSE NOT NULL,
    created_on TIMESTAMP NOT NULL,
    modified_on TIMESTAMP NOT NULL,
    created_by VARCHAR(255) NOT NULL,
    modified_by VARCHAR(255)
);


CREATE TABLE sales (
    sale_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_id BIGINT NOT NULL,
    sale_amount DOUBLE NOT NULL,
    sale_date DATE NOT NULL,
    created_on TIMESTAMP NOT NULL,
    modified_on TIMESTAMP NOT NULL,
    created_by VARCHAR(255) NOT NULL,
    modified_by VARCHAR(255),
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
        ON DELETE CASCADE
);
```

indexs need to consider
```
sales.sale_date
```

find top sale by customer script
```
WITH RankedSales AS (
    SELECT 
        customer_id,
        sale_amount,
        sale_date,
        RANK() OVER (PARTITION BY customer_id ORDER BY sale_amount DESC) AS rank
    FROM sales
    WHERE sale_date >= DATE_SUB(CURDATE(), INTERVAL :year YEAR) -- Only sales in the past year
)
SELECT 
    customer_id AS customerId,
    sale_amount AS saleAmount,
    sale_date AS saleDate,
    rank
FROM RankedSales
WHERE rank = 1 
ORDER BY sale_amount DESC;
```
