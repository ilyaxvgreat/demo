
-- INSERT INTO public.roles (id, role_name) VALUES (1, 'ROLE_ADMIN');
-- INSERT INTO public.roles (id, role_name) VALUES (2, 'ROLE_MANAGER');
-- INSERT INTO public.roles (id, role_name) VALUES (3, 'ROLE_DEFAULT');

INSERT INTO public.users (username, password, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled) VALUES ('super', '$2a$10$P8QnkvTfFuWjphAejrAKEenBKXx5VvNN2O6EYnGFtIQUbVCwUuCuC', true, true, true, true);
INSERT INTO public.users (username, password, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled) VALUES ('admin', '$2a$10$4ysB0373BTwuYann6lhZ/uEqUaF3An72N0VQ5lLftTptW49eV5WdS', true, true, true, true);
INSERT INTO public.users (username, password, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled) VALUES ('manager', '$2a$10$CWWvGsGCHbs/VwpgG2RLZ.7cOLsc1MDGOGHTuZjvIh9SLTmvGJH4G', true, true, true, true);
INSERT INTO public.users (username, password, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled) VALUES ('default', '$2a$10$pCBlEagPN6AXZ86kmwfbO.bGebBALMmKJSlqttawB10jUI0OfgwvK', true, true, true, true);

INSERT INTO public.roles_users (user_id, role_id) VALUES (1, 1);
INSERT INTO public.roles_users (user_id, role_id) VALUES (1, 2);
INSERT INTO public.roles_users (user_id, role_id) VALUES (1, 3);
INSERT INTO public.roles_users (user_id, role_id) VALUES (2, 1);
INSERT INTO public.roles_users (user_id, role_id) VALUES (3, 2);
INSERT INTO public.roles_users (user_id, role_id) VALUES (4, 3);