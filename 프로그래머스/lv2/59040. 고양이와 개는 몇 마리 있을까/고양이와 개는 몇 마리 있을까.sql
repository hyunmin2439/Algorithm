SELECT ANIMAL_TYPE, COUNT(*) as "count"
FROM ANIMAL_INS
GROUP BY ANIMAL_TYPE
ORDER BY ANIMAL_TYPE;