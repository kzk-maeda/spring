/* 従業員テーブルへのINSERT */
INSERT INTO employee (employee_id, employee_name, age)
VALUES(1, 'Taro Yamada', 30);

/* ユーザーマスタへのINSERT（Admin） */
INSERT INTO m_user (user_id, password, user_name, birthday, age, marriage, role)
VALUES('kawakami@example.com', '$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa', 'Takuro Kawakami', '1988-07-11', 28, false, 'ROLE_ADMIN');

/* ユーザーマスタへのINSERT（User） */
INSERT INTO m_user (user_id, password, user_name, birthday, age, marriage, role)
VALUES('tamura@example.com', '$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa', 'Tadashi Tamura', '1988-07-11', 28, false, 'ROLE_GENERAL');
