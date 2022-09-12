package io.zahori.server.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/*-
 * #%L
 * zahori-server
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2021 PANEL SISTEMAS INFORMATICOS,S.L
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * #L%
 */

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import io.zahori.server.model.Execution;

/**
 * The interface Executions repository.
 */
@RepositoryRestResource(path = "executions")
public interface ExecutionsRepository extends PagingAndSortingRepository<Execution, Long> {

    /**
     * Find by client id and process id iterable.
     *
     * @param clientId  the client id
     * @param processId the process id
     * @return the iterable
     */
    @Query("select e from Execution e inner join e.process p where p.processId = :processId and p.client.clientId = :clientId ORDER BY e.executionId DESC")
    Iterable<Execution> findByClientIdAndProcessId(@Param("clientId") Long clientId, @Param("processId") Long processId);

    @Query("select e from Execution e inner join e.process p where p.processId = :processId and p.client.clientId = :clientId ORDER BY e.executionId DESC")
    Page<Execution> findByClientIdAndProcessId(@Param("clientId") Long clientId, @Param("processId") Long processId, Pageable pageable);
}
