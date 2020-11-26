
-- create a new database named recipesdb
create database recipesdb;

-- switch to the new database
use recipesdb;


-- create tables 
create table recipes (
    recipe_id int not null auto_increment,
    title varchar(255) not null,
    category varchar(255),
    description varchar(255),
    prep_time integer,
    cook_time integer,
    servings integer,
    last_made date,
    primary key (recipe_id)
)

create table categories (
    category_id int not null auto_increment,
    name varchar(255) not null,
    description varchar(255) not null,
    primary key (category_id)
)

create table food_groups (
    food_group_id int not null auto_increment,
    name varchar(255) not null,
    description varchar(255) not null,
    primary key (food_group_id)
)

create table ingredients (
    ingredient_id int not null auto_increment,
    name varchar(255) not null,
    food_group varchar(255) not null,
    primary key (ingredient_id)
)

create table ingredients_lists (
    ingredients_list_id int not null auto_increment,
    recipe_id int not null,
    ingredient varchar(255) not null,
    description varchar(255) not null,
    measurement_type varchar(255) not null,
    measurement_amount numeric(4,2) not null,
    foreign key (recipe_id) references recipes(recipe_id),
    primary key (ingredients_list_id)
)

create table instructions (
    instruction_id int not null auto_increment,
    step int not null,
    instruction varchar(225) not null,
    recipe_id int not null,
    foreign key (recipe_id) references recipes(recipe_id),
    primary key (instruction_id)
)

create table equipment (
    equipment_id int not null auto_increment,
    name varchar(225) not null,
    description varchar(225) not null,
    primary key (equipment_id)
)

create table recipe_equipment (
    recipe_equipment_id int not null auto_increment,
    name varchar(225) not null,
    recipe_id int not null,
    foreign key (recipe_id) references recipes(recipe_id),
    primary key (recipe_equipment_id)
)


-- insert data into recipes table  
-- Example tuple: ("'Meatloaf'", "'Beef'",  "'Quick to make'", 10, 70, 8)
insert into recipes (title, category, description, prep_time, cook_time, servings) values ($title, $category, $description, $prep_time, $cook_time, $servings);

-- insert data into categories 
-- Example tuple: ("'Beef'", "'Ground beef and steak recipes'")
insert into categories (name, description) values ($name, $description);

-- insert data into food_groups table 
-- Example tuple: ("'Protein'", "'Beef, Chicken, Fish'")
insert into food_groups (name, description) values ($name, $description);

-- insert data into ingredients table 
-- Example tuple: ("'Milk'", "'Dairy'")
insert into ingredients (name, food_group) values ($name, $food_group);

-- insert data into ingredients_lists table 
-- Example tuple: (4, "'Apples'", "'Red'", "'Cup'", 0.5)
insert into ingredients_lists (recipe_id, ingredient, description, measurement_type, measurement_amount) values ($recipe_id, $ingredient, $description, $measurement_type, $measurement_amount);

-- insert data into instructions table 
-- Example tuple: (3, 5, "'Bake 20 minutes at 350F degrees - 375F degrees.'")
insert into instructions (recipe_id, step, instruction) values ($step, $instruction, $recipe_id);

-- insert data into equipment table 
-- Example tuple: ("'Baking Pan'", "'Mostly for baking recipes'")
insert into equipment (name, description) values ($name, $description);


-- get all tuples from a table
SELECT * FROM $SCHEMA.$tableName ORDER BY name ASC