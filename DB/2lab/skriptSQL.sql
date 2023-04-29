--variant - 78878973
--1
SELECT
    Н_ЛЮДИ.ОТЧЕСТВО,
    Н_СЕССИЯ.ДАТА
FROM
    Н_ЛЮДИ
    LEFT JOIN Н_СЕССИЯ ON Н_ЛЮДИ.ИД = Н_СЕССИЯ.ЧЛВК_ИД
    AND Н_ЛЮДИ.ИД < 152862
    AND Н_СЕССИЯ.УЧГОД = ('2001/2002' :: varchar(9));

--task's solution
--2
SELECT
    Н_ЛЮДИ.ИД,
    Н_ВЕДОМОСТИ.ДАТА,
    Н_СЕССИЯ.ИД
FROM
    Н_ЛЮДИ
    INNER JOIN Н_ВЕДОМОСТИ ON Н_ЛЮДИ.ИМЯ < ('Роман' :: varchar)
    AND Н_ВЕДОМОСТИ.ЧЛВК_ИД = 117219
    INNER JOIN Н_СЕССИЯ ON Н_ЛЮДИ.ИД = Н_СЕССИЯ.ЧЛВК_ИД
    AND Н_СЕССИЯ.ИД = 14369;

--task's solution
--3
SELECT
    COUNT(*)
FROM
    Н_УЧЕНИКИ
    INNER JOIN Н_ЛЮДИ ON Н_УЧЕНИКИ.ЧЛВК_ИД = Н_ЛЮДИ.ИД
    AND Н_ЛЮДИ.ИНН IS NULL
    AND Н_УЧЕНИКИ.ГРУППА = ('3102' :: varchar);

--task's solution
--4
SELECT
    *
FROM
    Н_ПЛАНЫ
WHERE
    Н_ПЛАНЫ.ФО_ИД = 1;

SELECT
    *
FROM
    Н_ПЛАНЫ
WHERE
    Н_ПЛАНЫ.ФО_ИД = 3;

SELECT
    Н_ЛЮДИ1.ОТЧЕСТВО,
    Н_ЛЮДИ1.КОЛИЧЕСТВО
FROM
    (
        SELECT
            DISTINCT Н_ЛЮДИ.ОТЧЕСТВО,
            COUNT(Н_ЛЮДИ.ОТЧЕСТВО) AS КОЛИЧЕСТВО
        FROM
            Н_ЛЮДИ
            JOIN Н_УЧЕНИКИ ON Н_ЛЮДИ.ИД = Н_УЧЕНИКИ.ЧЛВК_ИД
            AND Н_ЛЮДИ.ОТЧЕСТВО <> ('' :: varchar)
            AND Н_ЛЮДИ.ОТЧЕСТВО <> ('.' :: varchar)
            JOIN Н_ПЛАНЫ ON Н_УЧЕНИКИ.ПЛАН_ИД = Н_ПЛАНЫ.ИД
            AND Н_ПЛАНЫ.ФО_ИД = 1
        GROUP BY
            Н_ЛЮДИ.ОТЧЕСТВО
    ) AS Н_ЛЮДИ1
WHERE
    Н_ЛЮДИ1.КОЛИЧЕСТВО = 10;

--task's solution
--5
SELECT
    AVG(age(ДАТА_РОЖДЕНИЯ))
FROM
    Н_УЧЕНИКИ AS p
    INNER JOIN Н_ЛЮДИ AS l ON p.ГРУППА = ('1101' :: varchar)
    AND p.ЧЛВК_ИД = l.ИД;

--average age in 1101 group
SELECT
    AVG(age(ДАТА_РОЖДЕНИЯ))
FROM
    Н_УЧЕНИКИ AS p
    INNER JOIN Н_ЛЮДИ AS l ON Cp.ЧЛВК_ИД = l.ИД;

--average age in all groups
SELECT
    ГРУППА,
    AVG(AGE(ДАТА_РОЖДЕНИЯ))
FROM
    Н_УЧЕНИКИ AS p
    INNER JOIN Н_ЛЮДИ AS l ON p.ЧЛВК_ИД = l.ИД
GROUP BY
    p.ГРУППА
HAVING
    AVG(AGE(ДАТА_РОЖДЕНИЯ)) >= AVG(AGE(ДАТА_РОЖДЕНИЯ)) FILTER(
        WHERE
            p.ГРУППА = ('1101' :: varchar)
            AND p.ЧЛВК_ИД = l.ИД
    )
ORDER BY
    ГРУППА;

--average age of only 1101 group
SELECT
    ГРУППА,
    AVG(AGE(ДАТА_РОЖДЕНИЯ))
FROM
    Н_УЧЕНИКИ AS p,
    Н_ЛЮДИ AS l
WHERE
    p.ЧЛВК_ИД = l.ИД
GROUP BY
    p.ГРУППА
HAVING
    (
        AVG(AGE(ДАТА_РОЖДЕНИЯ)) > (
            SELECT
                AVG(AGE(ДАТА_РОЖДЕНИЯ))
            FROM
                Н_УЧЕНИКИ,
                Н_ЛЮДИ
            WHERE
                Н_УЧЕНИКИ.ГРУППА = ('1101' :: varchar)
                AND Н_ЛЮДИ.ИД = Н_УЧЕНИКИ.ЧЛВК_ИД
            LIMIT
                1
        )
    )
ORDER BY
    ГРУППА;

--task's solution
--6
SELECT
    DISTINCT l.ИД,
    ИМЯ,
    ФАМИЛИЯ,
    ОТЧЕСТВО,
    ГРУППА,
    П_ПРКОК_ИД,
    СОСТОЯНИЕ
FROM
    Н_УЧЕНИКИ AS p
    INNER JOIN Н_ЛЮДИ as l ON l.ИД = p.ЧЛВК_ИД
    AND p.НАЧАЛО = (timestamp '2012-09-01')
WHERE
    p.ПЛАН_ИД IN (
        SELECT
            ИД
        FROM
            Н_ПЛАНЫ
        WHERE
            СОСТОЯНИЕ = 'утвержден'
            AND КУРС = '1'
    );

--task's solution
--7
SELECT
    DISTINCT ПРИЗНАК
FROM
    Н_УЧЕНИКИ;

--all unique values of ПРИЗНАК from Н_УЧЕНИКИ to fetch 'active students'
SELECT
    DISTINCT Н_ЛЮДИ.ИМЯ
FROM
    Н_ЛЮДИ
    INNER JOIN Н_УЧЕНИКИ ON Н_УЧЕНИКИ.ЧЛВК_ИД = Н_ЛЮДИ.ИД
    AND Н_ЛЮДИ.ИМЯ IS NOT NULL
    AND Н_ЛЮДИ.ИМЯ <> ('' :: varchar)
    AND Н_ЛЮДИ.ИМЯ <> ('.' :: varchar)
    AND Н_ЛЮДИ.ИМЯ <> (' ' :: varchar)
    AND (
        Н_УЧЕНИКИ.ПРИЗНАК = ('обучен' :: varchar)
        OR Н_УЧЕНИКИ.ПРИЗНАК = ('продлен' :: varchar)
    )
ORDER BY
    Н_ЛЮДИ.ИМЯ;

--task's solution

--lab3 function

CREATE FUNCTION count_of_people_in_location(location_id int) RETURNS integer AS $ $ DECLARE result := 0;

BEGIN
SELECT
    count(*) INTO result
FROM
    location
    JOIN creature ON creature.location = location.id
    AND location.id = location_id;

RAISE NOTICE 'Count creatures in location with typed id: %', result;
END;

$ $ LANGUAGE plpgsql;