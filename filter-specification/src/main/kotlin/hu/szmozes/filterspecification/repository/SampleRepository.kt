package hu.szmozes.filterspecification.repository

import hu.szmozes.filterspecification.entity.SampleEntity
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface SampleRepository : JpaRepositoryImplementation<SampleEntity, UUID>