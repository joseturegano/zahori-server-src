package io.zahori.server.controller;

/*-
 * #%L
 * zahori-server
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2021 - 2022 PANEL SISTEMAS INFORMATICOS,S.L
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

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.zahori.server.model.TestRepository;
import io.zahori.server.repository.TestRepositoriesRepository;
import io.zahori.server.security.JWTUtils;

/**
 * The type test repository controller.
 */
@RestController
@RequestMapping("/api/testRepositories")
public class TestRepositoriesController {
    private static final Logger LOG = LoggerFactory.getLogger(TestRepositoriesController.class);

    @Autowired
    private TestRepositoriesRepository testRepositoriesRepository;

    /**
     * Gets repositories.
     *
     * @return the test repositories
     */
    @GetMapping()
    public ResponseEntity<Object> getRepositories(HttpServletRequest request) {
        try {
            LOG.info("get test repositories");

            Iterable<TestRepository> repos = testRepositoriesRepository.findByClientId(JWTUtils.getClientId(request));

            return new ResponseEntity<>(repos, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Gets test repository.
     *
     * @param testRepoId  the test repo id
     * @param request the request
     * @return the test repository
     */
    @GetMapping(path = "/{testRepoId}")
    public ResponseEntity<Object> getTestRepository(@PathVariable Long testRepoId, HttpServletRequest request) {
        try {
            LOG.info("get test repository by id");

            Optional<TestRepository> testRepository = testRepositoriesRepository.findById(testRepoId);
            return new ResponseEntity<>(testRepository, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Save test repository response entity.
     *
     * @param testRepository the test repository
     * @param request        the request
     * @return the response entity
     */
    @PostMapping()
    public ResponseEntity<Object> saveTestRepository(@RequestBody TestRepository testRepository, HttpServletRequest request) {
        try {
            LOG.info("update test repository");
            testRepository = testRepositoriesRepository.save(testRepository);
            return new ResponseEntity<>(testRepository, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
