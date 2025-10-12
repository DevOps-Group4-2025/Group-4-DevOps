-- This query retrieves the total population of the world by summing the population of all countries.
SELECT SUM(Population) AS WorldPopulation
FROM country;
