select a.name, a.datetime
from animal_ins as A left outer join animal_outs as B
on A.animal_id = B.animal_id
where b.animal_id is null
order by a.datetime
limit 3;