DELETE
FROM book;
ALTER TABLE book AUTO_INCREMENT = 1001;

DELETE
FROM category;
ALTER TABLE category AUTO_INCREMENT = 1001;

INSERT INTO `category` (`name`)
VALUES ('Business'),
       ('Children'),
       ('History'),
       ('Mystery'),
       ('Romance'),
       ('Sci-Fi & Fantasy');

/* Business */
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('How to Win Friends and Influence People', 'Dale Carnegie', '', 500, 0, TRUE, FALSE, 1001);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('Freakonomics', 'Steven Levitt', '', 100000, 0, FALSE, FALSE, 1001);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('The Richest Man in Babylon', 'George S Clason', '', 1, 0, TRUE, FALSE, 1001);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('The Big Short', 'Michael Lewis', '', 14.99, 0, FALSE, FALSE, 1001);

/* Children's Books */
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('Chicka Chicka Boom Boom', 'Bill Martin Jr.', '', 999, 0, TRUE, FALSE, 1002);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('Fantastic Mr. Fox', 'Roald Dahl', '', 25000, 0, TRUE, FALSE, 1002);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('The Paper Bag Princess', 'Robert Munsch', '', 1999, 0, FALSE, FALSE, 1002);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('I Am Going!', 'Mo Willems', '', 999, 0, FALSE, FALSE, 1002);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('Are You My Mother?', 'P.D. Eastman', '', 1999, 0, FALSE, FALSE, 1002);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('The Lorax', 'Dr. Seuss', '', 1000, 0, TRUE, FALSE, 1002);

/* History */
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('1776', 'David McCullough', '', 1099, 0, FALSE, FALSE, 1003);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('The Art of War', 'Sun Tzu', '', 1199, 0, TRUE, FALSE, 1003);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('The Diary of a Young Girl', 'Anne Frank', '', 999, 0, TRUE, FALSE, 1003);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('A Short History of Nearly Everything', 'Bill Bryson', '', 1999, 0, FALSE, FALSE, 1003);

/* Mystery */
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('Fer-de-Lance', 'Rex Stout', '', 199, 0, FALSE, FALSE, 1004);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('The Adventures of Sherlock Holmes', 'Sir Arthur Conan Doyle', '', 1099, 0, TRUE, FALSE, 1004);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('And Then There Were None', 'Agatha Christie', '', 499, 0, TRUE, FALSE, 1004);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('Gone Girl', 'Gillian Flynn', '', 1999, 0, FALSE, FALSE, 1004);

/* Romance */
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('White Hot Kiss', 'Jennifer Armentrout', '', 199, 0, FALSE, FALSE, 1005);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('Pride and Prejudice', 'Jane Austen', '', 199, 0, TRUE, FALSE, 1005);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('Eleanor Oliphant is Completely Fine', 'Gail Honeyman', '', 499, 0, FALSE, FALSE, 1005);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('Romeo and Juliet', 'William Shakespeare', '', 99, 0, TRUE, FALSE, 1005);

/* Sci-Fi & Fantasy */
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('The Dying Earth', 'Jack Vance', '', 699, 0, TRUE, FALSE, 1006);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('The Fifth Season', 'N.K. Jemisin', '', 1499, 0, FALSE, FALSE, 1006);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('Ringworld', 'Larry Niven', '', 699, 0, FALSE, FALSE, 1006);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('Nine Princes in Amber', 'Roger Zelazny', '', 999, 0, TRUE, FALSE, 1006);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('Jonathan Strange & Mr Norell', 'Susanna Clarke', '', 1999, 0, FALSE, FALSE, 1006);