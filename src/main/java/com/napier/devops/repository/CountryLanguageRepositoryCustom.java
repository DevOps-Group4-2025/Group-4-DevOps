package com.napier.devops.repository;

import java.util.List;

/**
 * Custom repository fragment for the {@link CountryLanguageRepository}.
 *
 * <p>This interface defines additional database operations that cannot be
 * expressed using standard Spring Data JPA query derivation. Implementations
 * of this interface typically provide native SQL queries or optimized
 * database-specific logic for retrieving aggregated or complex results.</p>
 *
 * <p>The interface contains only one abstract method, which technically makes it
 * a functional interface according to the Java Language Specification.
 * However, it is <strong>not</strong> intended to be used as a functional interface
 * (e.g., through lambda expressions). For this reason, the PMD rule
 * <code>ImplicitFunctionalInterface</code> is explicitly suppressed.</p>
 *
 * <h2>Implementation Notes</h2>
 * <ul>
 *   <li>The implementing class must be named
 *       <code>CountryLanguageRepositoryImpl</code>.</li>
 *   <li>Use {@link jakarta.persistence.EntityManager} to execute native SQL or JPQL.</li>
 *   <li>Return results using the {@link LanguageStatsProjection} projection interface.</li>
 * </ul>
 *
 * <h2>Typical Usage</h2>
 * <pre>
 * &#64;Autowired
 * CountryLanguageRepository repository;
 *
 * List&lt;LanguageStatsProjection&gt; stats = repository.getLanguageStatistics();
 * </pre>
 *
 * @see LanguageStatsProjection
 * @see CountryLanguageRepository
 */
@SuppressWarnings("PMD.ImplicitFunctionalInterface")
public interface CountryLanguageRepositoryCustom {

    /**
     * Retrieves aggregated statistical information about all languages spoken
     * across countries in the database.
     *
     * <p>The returned data structure is defined by the
     * {@link LanguageStatsProjection} projection interface, which ensures
     * efficient database field selection.</p>
     *
     * @return a list of {@link LanguageStatsProjection} objects containing
     *         computed language statistics such as number of speakers.
     */
    List<LanguageStatsProjection> getLanguageStatistics();
}
