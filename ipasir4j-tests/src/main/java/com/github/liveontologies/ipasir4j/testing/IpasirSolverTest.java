package com.github.liveontologies.ipasir4j.testing;

/*-
 * #%L
 * Java IPASIR interfaces
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2020 Live Ontologies Project
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.github.liveontologies.ipasir4j.ClauseReader;
import com.github.liveontologies.ipasir4j.IpasirSolver;
import com.github.liveontologies.ipasir4j.LearningListener;
import com.github.liveontologies.ipasir4j.SolverTerminatedException;
import com.github.liveontologies.ipasir4j.TerminationRequest;

public abstract class IpasirSolverTest {

	IpasirSolver solver;

	protected abstract IpasirSolver createSolver();

	@Before
	public void before() {
		solver = createSolver();
	}

	@Test
	public void testGetSignatureNotNull() {
		assertNotNull(solver.getSignature());
	}

	@Test
	public void testSetTerminatePossible() {
		solver.setTerminate(new TerminationRequest() {

			@Override
			public boolean terminateASAP() {
				return false;
			}
		});
	}

	@Test
	public void testSetLearningListenerPossible() {
		solver.setLearningListener(0, new LearningListener() {

			@Override
			public void clauseLearned(ClauseReader reader) {
			}
		});
	}

	@Test
	public void testInitiallySatisfiable() throws SolverTerminatedException {
		assertTrue(solver.isSatisfiable());
	}

	@Test
	public void testSimpleClash() throws SolverTerminatedException {
		solver.add(1);
		solver.add(0);
		assertTrue(solver.isSatisfiable());
		assertEquals(1, solver.val(1));
		solver.add(-2);
		solver.add(0);
		assertTrue(solver.isSatisfiable());
		assertEquals(-2, solver.val(2));
		solver.add(-1);
		solver.add(0);
		assertFalse(solver.isSatisfiable());
	}

	@Test
	public void testAssume1() throws SolverTerminatedException {
		solver.add(-1);
		solver.add(0);
		assertTrue(solver.isSatisfiable());
		solver.assume(1);
		assertFalse(solver.isSatisfiable());
		// the assumption is ignored upon the following calls
		assertTrue(solver.isSatisfiable());
		assertEquals(-1, solver.val(1));
	}

	@Test
	public void testAssume2() throws SolverTerminatedException {
		solver.add(-1);
		solver.add(-2);
		solver.add(0);
		assertTrue(solver.isSatisfiable());
		solver.assume(1);
		assertTrue(solver.isSatisfiable());
		assertEquals(1, solver.val(1));
		assertEquals(-2, solver.val(2));
		solver.assume(2);
		assertTrue(solver.isSatisfiable());
		assertEquals(2, solver.val(2));
		assertEquals(-1, solver.val(1));
	}

	@Test
	public void testFailed1() throws SolverTerminatedException {
		solver.add(-1);
		solver.add(0);
		assertTrue(solver.isSatisfiable());
		solver.assume(1);
		assertFalse(solver.isSatisfiable());
		assertTrue(solver.isFailed(1));
	}

	@Test
	public void testFailed2() throws SolverTerminatedException {
		solver.add(-1);
		solver.add(-2);
		solver.add(0);
		assertTrue(solver.isSatisfiable());
		solver.add(-2);
		solver.add(-3);
		solver.add(-4);
		solver.add(0);
		assertTrue(solver.isSatisfiable());
		solver.assume(1);
		solver.assume(2);
		solver.assume(3);
		assertFalse(solver.isSatisfiable());
		assertTrue(solver.isFailed(1));
		assertTrue(solver.isFailed(2));
		assertFalse(solver.isFailed(3));
	}

}
