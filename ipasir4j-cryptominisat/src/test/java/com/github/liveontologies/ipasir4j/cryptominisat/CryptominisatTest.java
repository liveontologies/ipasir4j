package com.github.liveontologies.ipasir4j.cryptominisat;

import org.junit.Ignore;
import org.junit.Test;

/*-
 * #%L
 * Java Bindings for the Minisat solver
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

import com.github.liveontologies.ipasir4j.IpasirSolver;
import com.github.liveontologies.ipasir4j.SolverTerminatedException;

import comp.github.liveontologies.ipasir4j.IpasirSolverTest;

public class CryptominisatTest extends IpasirSolverTest {

	IpasirSolver solver;

	@Override
	protected IpasirSolver createSolver() {
		return Cryptominisat.createSolver();
	}

	/*
	 * @see https://github.com/msoos/cryptominisat/issues/614
	 */
	@Override
	@Test
	@Ignore
	public void testFailed1() throws SolverTerminatedException {
		super.testFailed1();
	}

	@Override
	@Test
	@Ignore
	public void testFailed2() throws SolverTerminatedException {
		super.testFailed2();
	}

}
