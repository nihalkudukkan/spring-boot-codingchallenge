INSERT INTO "category"("category_name") values ('Fashion')
INSERT INTO "category"("category_name") values ('Electronics')
INSERT INTO "category"("category_name") values ('Books')
INSERT INTO "category"("category_name") values ('Groceries')
INSERT INTO "category"("category_name") values ('Medicines')

INSERT INTO "role"("role") values ('CONSUMER')
INSERT INTO "role"("role") values ('SELLER')

INSERT INTO "user"("username","password") values ('jack','pass_word')
INSERT INTO "user"("username","password") values ('bob','pass_word')
INSERT INTO "user"("username","password") values ('apple','pass_word')
INSERT INTO "user"("username","password") values ('glaxo','pass_word')

INSERT INTO "cart"("total_amount","user_user_id") values (20,1)
INSERT INTO "cart"("total_amount","user_user_id") values (0,2)

INSERT INTO "user_roles"("user_user_id","roles_role_id") values (1,1)
INSERT INTO "user_roles"("user_user_id","roles_role_id") values (2,1)
INSERT INTO "user_roles"("user_user_id","roles_role_id") values (3,2)
INSERT INTO "user_roles"("user_user_id","roles_role_id") values (4,2)

INSERT INTO "product"("price","product_name","category_category_id","seller_user_id") values (29190,'Apple iPad 10.2 8th Gen WiFi iOS Tablet',2,3)
INSERT INTO "product"("price","product_name","category_category_id","seller_user_id") values (10,'Crocin pain relief tablet',5,4)

INSERT INTO "cart_product"("cart_cart_id","product_product_id","quantity") values (1,2,2)