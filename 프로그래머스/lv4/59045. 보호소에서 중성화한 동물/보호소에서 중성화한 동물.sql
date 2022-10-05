select a.animal_id, a.animal_type, a.name
from animal_ins as A inner join animal_outs as B
on A.animal_id = B.animal_id
where A.sex_upon_intake like "%Intact%" and
B.sex_upon_outcome not like "%Intact%"
order by animal_id;