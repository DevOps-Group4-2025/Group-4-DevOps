# Group 5 — Basic Population Totals (UC58–UC62, UC65)

This document defines the use cases and operations that generate population totals at different administrative levels, mirroring the repository convention of a diagram `.puml` plus a definition `.md`.

> Diagram: `use-cases/Population Totals.puml`

---

## UC26 — Population of the world
**Primary actor:** Analyst  
**Goal:** Produce a single headline figure for total world population, with date and source.  
**Preconditions:** Data source configured (e.g., UN DESA, World Bank, etc.).  
**Main flow:**
1. System retrieves latest world total from the configured provider.
2. System validates retrieval timestamp and units.
3. System returns `{value, as_of_date, source}`.
   **Extensions / Exceptions:**
- E1: Source unavailable → retry with backup provider; on failure, flag for manual input.
- E2: Value missing/zero → mark as stale and notify analyst.

---

## UC27 — Population of a continent
**Actor:** Analyst  
**Goal:** Return total population per selected continent (Africa, Asia, etc.).  
**Main flow:**
1. Analyst selects continent.
2. System aggregates latest country totals within the continent.
3. System returns `{continent, value, as_of_date, source}`.
   **Extensions:**
- E1: Country missing → use last known value, mark partial.
- E2: Continent mapping conflict → log and prompt for correction.

---

## UC28 — Population of a region
**Actor:** Analyst  
**Goal:** Return population for a supra-national region (e.g., EU, ASEAN, Sub-Saharan Africa).  
**Main flow:** Select region → aggregate member entities → return `{region, value, date, source}`.  
**Extensions:** E1: Membership set changes → recompute; E2: Missing member → partial flag.

---

## UC29 — Population of a country
**Actor:** Analyst  
**Goal:** Return country total.  
**Main flow:** Select country → fetch latest total → return `{iso3, country, value, date, source}`.  
**Extensions:** E1: No fresh census → use modeled estimate and mark provenance.

---

## UC30 — Population of a district
**Actor:** Analyst  
**Goal:** Return district (admin-2/3) total.  
**Main flow:** Select country → select district → fetch total → return `{admin_code, name, value, date, source}`.  
**Extensions:** E1: Boundary changes → normalize to current definition; E2: Missing admin code → reject.

---

## UC31 — Population of a city
**Actor:** Analyst  
**Goal:** Return city total; specify whether **metro** or **administrative city**.  
**Main flow:** Select country → select city → fetch total → return `{city_id, scope, value, date, source}`.  
**Extensions:** E1: Ambiguous name → prompt for disambiguation; E2: Scope mismatch → display warning.

---

## Non-functional notes
- Provenance and update cadence must be recorded alongside every figure.
- All totals should be timestamped (ISO-8601) and include provider/source metadata.
