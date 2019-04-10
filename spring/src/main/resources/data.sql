/* 従業員テーブルへのINSERT */
INSERT INTO employee (employee_id, employee_name, age)
VALUES(1, 'Taro Yamada', 30);

/* ユーザーマスタへのINSERT（Admin） */
INSERT INTO m_user (user_id, password, user_name, birthday, age, marriage, role)
VALUES('yamada@example.com', 'password', 'Taro Yamada', '1988-07-11', 28, false, 'ROLE_ADMIN')
