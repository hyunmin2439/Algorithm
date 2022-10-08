select A.animal_id, B.name
from animal_ins as A inner join animal_outs as B
on A.animal_id = B.animal_id
where A.DATETIME > B.DATETIME
order by A.DATETIME;