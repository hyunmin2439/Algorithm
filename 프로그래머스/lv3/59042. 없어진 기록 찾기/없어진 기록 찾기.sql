SELECT B.ANIMAL_ID, B.NAME
FROM ANIMAL_INS as A RIGHT OUTER JOIN ANIMAL_OUTS as B
ON A.ANIMAL_ID = B.ANIMAL_ID
WHERE A.ANIMAL_ID IS NULL
ORDER BY B.ANIMAL_ID;