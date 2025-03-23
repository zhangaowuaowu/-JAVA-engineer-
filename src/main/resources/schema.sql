CREATE TABLE currency (
    id INT AUTO_INCREMENT PRIMARY KEY,
    currency_code VARCHAR(3) NOT NULL,
    currency_name VARCHAR(50) NOT NULL,
    create_name VARCHAR(50),
    create_time TIMESTAMP,
    update_name VARCHAR(50),
    update_time TIMESTAMP
);

INSERT INTO currency (currency_code, currency_name, create_name, create_time) VALUES
('USD', '美元', 'Andy', NOW()),
('EUR', '歐元', 'Andy', NOW()),
('TWD', '新台幣', 'Andy', NOW()),
('JPY', '日圓', 'Andy', NOW()),
('CNY', '人民幣', 'Andy', NOW()),
('GBP', '英鎊', 'Andy', NOW()),
('KRW', '韓元', 'Andy', NOW()),
('AUD', '澳元', 'Andy', NOW()),
('CAD', '加拿大元', 'Andy', NOW());