package com.napier.devops.repository;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Tag("repository")
@Disabled("Placeholder for repository layer tests")
class RepositoryLayerTests {
}


