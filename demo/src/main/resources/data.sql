CREATE DATABASE IF NOT EXISTS blog_cms;
USE blog_cms;

INSERT INTO author (id, name) VALUES
(1, 'Aiko Tanaka'),
(2, 'Jonas Schmidt'),
(3, 'Cas Van Dijk');

INSERT INTO blog_post (id, author_id, title, post) VALUES
(1, 1, 'Boost Your Productivity with 10 Easy Tips', 'Productivity - we all want it but it seems ...'),
(2, 1, 'How to Focus', 'Do you ever sit down to work and find yourself ...'),
(3, 2, 'Learn to Speed Read in 30 Days', 'Knowledge, not ability, is the great determiner of ...');