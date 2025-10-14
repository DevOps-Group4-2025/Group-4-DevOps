
# USE CASE: 32 - Generate Language Report

## CHARACTERISTIC INFORMATION

### Goal in Context

As an analyst I want to produce a report showing the number of people who speak Chinese, English, Hindi, Spanish, and Arabic from greatest to smallest including the percentage of the world population so that I can support linguistic reporting.

### Scope

World demographic reporting system.

### Level

Primary task

### Preconditions

Dataset contains population of all countries with the languages they speak.

### Success End Condition

A complete report is generated listing the number of people who speak Chinese, English, Hindi, Spanish, and Arabic from greatest to smallest including the percentage of the world population.

### Failed End Condition

No report produced.

### Primary Actor

Data Analyst

### Trigger

Analyst initiates “Generate Language Report”

## MAIN SUCCESS SCENARIO

1. Analyst opens the system and selects **Language Report**.
2. System pulls all language and population info from the database.
3. System computes ranking by the number of people speaking the languages.
4. System generates the report with columns : Language, NumberOfPeople and WorldPopulation

## EXTENSIONS

2. **Data is unreachable**:
    1. System aborts as the source is unavailable.

## SUB-VARIATIONS

none

## SCHEDULE

**DUE DATE**: *Relase 1.0