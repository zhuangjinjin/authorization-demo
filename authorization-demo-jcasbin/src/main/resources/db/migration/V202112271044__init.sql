INSERT INTO casbin_rule(ptype, v0, v1, v2) VALUES
('p', 'r.sub.city == "xiamen"', '/index', 'READ'),
('p', 'r.sub.age >= 18', '/index', 'READ');