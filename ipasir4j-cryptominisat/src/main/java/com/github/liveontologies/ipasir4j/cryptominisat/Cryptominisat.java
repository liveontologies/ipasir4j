package com.github.liveontologies.ipasir4j.cryptominisat;

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
import com.github.liveontologies.ipasir4j.jna.JNAIpasir;
import com.github.liveontologies.ipasir4j.jna.IpasirNativeSolver;
import com.sun.jna.Native;

public class Cryptominisat {

	private final static JNAIpasir CRYPTOMINISAT_JNA = Native
			.load("cryptominisat", JNAIpasir.class);

	public static IpasirSolver createSolver() {
		return new IpasirNativeSolver(CRYPTOMINISAT_JNA);
	}

}
